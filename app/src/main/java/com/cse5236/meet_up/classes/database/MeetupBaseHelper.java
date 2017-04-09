package com.cse5236.meet_up.classes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeff on 3/27/2017.
 */

public class MeetupBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 3;
    private static final String DATABASE_NAME = "databaseManager.db";
    public MeetupBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MeetupDbSchema.MeetupTable.TITLE + "(" +
                " _id integer primary key autoincrement, " +
                MeetupDbSchema.MeetupTable.Cols.UUID + ", " +
                MeetupDbSchema.MeetupTable.Cols.NAME + ", " +
                MeetupDbSchema.MeetupTable.Cols.DATE + ", " +
                MeetupDbSchema.MeetupTable.Cols.ATTENDING +
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MeetupDbSchema.MeetupTable.TITLE);
    }
}
