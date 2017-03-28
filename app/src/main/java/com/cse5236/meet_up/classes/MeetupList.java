package com.cse5236.meet_up.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cse5236.meet_up.classes.database.MeetupBaseHelper;
import com.cse5236.meet_up.classes.database.MeetupCursorWrapper;
import com.cse5236.meet_up.classes.database.MeetupDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jeff on 3/26/2017.
 */

public class MeetupList {
    private static MeetupList sMeetupList;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static MeetupList get(Context context) {
        if (sMeetupList == null) {
            sMeetupList = new MeetupList(context);
        }
        return sMeetupList;
    }
    private MeetupList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new MeetupBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addMeetup(Meetup c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(MeetupDbSchema.MeetupTable.TITLE, null, values);
    }
    public List<Meetup> getMeetups() {
        List<Meetup> meetups = new ArrayList<>();
        MeetupCursorWrapper cursor = queryMeetups(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                meetups.add(cursor.getMeetup());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return meetups;
    }
    public Meetup getMeetup(UUID id) {

        MeetupCursorWrapper cursor = queryMeetups(
                MeetupDbSchema.MeetupTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMeetup();
        } finally {
            cursor.close();
        }
    }

    public void updateMeetup(Meetup meetup) {
        String uuidString = meetup.getId().toString();
        ContentValues values = getContentValues(meetup);
        mDatabase.update(MeetupDbSchema.MeetupTable.TITLE, values,
                MeetupDbSchema.MeetupTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Meetup meetup) {
        ContentValues values = new ContentValues();
        values.put(MeetupDbSchema.MeetupTable.Cols.UUID, meetup.getId().toString());
        values.put(MeetupDbSchema.MeetupTable.Cols.NAME, meetup.getName());
        values.put(MeetupDbSchema.MeetupTable.Cols.DATE, meetup.getDate().getTime());
        values.put(MeetupDbSchema.MeetupTable.Cols.ATTENDING, meetup.isAttending() ? 1 : 0);
        return values;
    }

    private MeetupCursorWrapper queryMeetups(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MeetupDbSchema.MeetupTable.TITLE,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new MeetupCursorWrapper(cursor);
    }
}
