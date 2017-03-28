package com.cse5236.meet_up.classes.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.cse5236.meet_up.classes.Meetup;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Jeff on 3/27/2017.
 */

public class MeetupCursorWrapper extends CursorWrapper {
    public MeetupCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Meetup getMeetup() {
        String uuidString = getString(getColumnIndex(MeetupDbSchema.MeetupTable.Cols.UUID));
        String name = getString(getColumnIndex(MeetupDbSchema.MeetupTable.Cols.NAME));
        long date = getLong(getColumnIndex(MeetupDbSchema.MeetupTable.Cols.DATE));
        int isAttending = getInt(getColumnIndex(MeetupDbSchema.MeetupTable.Cols.ATTENDING));

        Meetup meetup = new Meetup(UUID.fromString(uuidString));
        meetup.setName(name);
        meetup.setDate(new Date(date));
        meetup.setAttending(isAttending != 0);
        return meetup;
    }
}
