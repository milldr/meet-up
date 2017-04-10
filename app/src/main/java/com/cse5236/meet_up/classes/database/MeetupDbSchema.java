package com.cse5236.meet_up.classes.database;

/**
 * Created by Jeff on 3/27/2017.
 */

public class MeetupDbSchema {
    public static final class MeetupTable {
        public static final String TITLE = "meetups";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "meetup_name";
            public static final String DATE = "date";
            public static final String ATTENDING = "attending";
        }
    }
}
