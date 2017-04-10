package com.cse5236.meet_up.classes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cse5236.meet_up.classes.Group;
import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/26/17.
 *
 * referenced: http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 * & http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 8;

    // Database Name
    private static final String DATABASE_NAME = "databaseManager.db";

    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_GROUPS = "groups";
    private static final String TABLE_USERGROUP = "user_group";
    private static final String TABLE_USERGROUPMEETUP = "user_group_meetup";


    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_GROUP_ID = "group_id";
    private static final String KEY_MEETUP_ID = "uuid";

    // USERS
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_PASSWORD + " TEXT" + ")";

    // GROUPS
    private static final String CREATE_GROUPS_TABLE = "CREATE TABLE " + TABLE_GROUPS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT" + ")";

    // USERS + GROUPS
    private static final String CREATE_USERGROUP_TABLE = "CREATE TABLE " + TABLE_USERGROUP + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_ID + " INTEGER,"
            + KEY_GROUP_ID + " INTEGER" + ")";

    // MEETUPS + USERS + GROUPS
    private static final String CREATE_USERGROUPMEETUP_TABLE = "CREATE TABLE " + TABLE_USERGROUPMEETUP + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_ID + " INTEGER,"
            + KEY_GROUP_ID + " INTEGER,"
            + KEY_MEETUP_ID + " STRING" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_GROUPS_TABLE);
        db.execSQL(CREATE_USERGROUP_TABLE);
        db.execSQL(CREATE_USERGROUPMEETUP_TABLE);
        db.execSQL("create table " + MeetupDbSchema.MeetupTable.TITLE + "(" +
                " _id integer primary key autoincrement, " +
                MeetupDbSchema.MeetupTable.Cols.UUID + ", " +
                MeetupDbSchema.MeetupTable.Cols.NAME + ", " +
                MeetupDbSchema.MeetupTable.Cols.DATE + ", " +
                MeetupDbSchema.MeetupTable.Cols.ATTENDING +
                ")"
        );
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERGROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERGROUPMEETUP);
        db.execSQL("DROP TABLE IF EXISTS " + MeetupDbSchema.MeetupTable.TITLE);

        // Create tables again
        onCreate(db);
    }

    /* ------------------------ USERS CRUD methods  ------------------------ */

    // Adding new user
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single user
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_NAME, KEY_EMAIL, KEY_PASSWORD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        cursor.close();
        // return user
        return user;
    }

    public User getUser(String email, String password) {
        List<User> users = getAllUsers();
        for (User u : users){
            if(email.equals(u.getEmail())){
                if(password.equals(u.getPassword())){
                    return u;
                }
            }
        }
        return null;
    }

    // Getting All users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        if(cursor != null && !cursor.isClosed()){
            cursor.close();
        }

        // return user list
        return userList;
    }

    public List<User> getAllUsers(Group group) {
        List<User> users = new ArrayList<User>();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                + KEY_ID + " IN (SELECT "
                + KEY_USER_ID + " FROM "
                + TABLE_USERGROUP + " WHERE "
                + KEY_GROUP_ID + " = " + group.getId()
                + ")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                User usr = new User();
                usr.setId(Integer.parseInt(c.getString(0)));
                usr.setName(c.getString(1));
                usr.setEmail(c.getString(2));
                usr.setPassword(c.getString(3));
                users.add(usr);
            } while (c.moveToNext());
        }

        return users;
    }

    // Getting users Count
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        // return count
        return cursor.getCount();
    }

    // Updating single user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());


        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    // Deleting single user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    /* ------------------------ GROUPS CRUD methods  ------------------------ */

    // Adding new group
    public long addGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, group.getName());
        values.put(KEY_DESCRIPTION, group.getDescription());

        // Inserting Row
        long id = db.insert(TABLE_GROUPS, null, values);
        db.close(); // Closing database connection

        // TODO - temporary fix
        group.setId(id);
        updateGroup(group);

        return id;
    }

    // Getting single group
    public Group getGroup(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GROUPS + " WHERE "
                + KEY_ID + " = " + id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        Group group = new Group();
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            group = new Group( cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)) );
        }

        cursor.close();

        // return group
        return group;
    }

    // Getting All groups
    public List<Group> getAllGroups() {
        List<Group> groupList = new ArrayList<Group>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GROUPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Group group = new Group();
                group.setId(Integer.parseInt(cursor.getString(0)));
                group.setName(cursor.getString(1));
                group.setDescription(cursor.getString(2));
                groupList.add(group);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return group list
        return groupList;
    }

    // Getting All groups for a given user
    public List<Group> getAllGroups(User user) {
        List<Group> groupList = new ArrayList<Group>();

        String selectQuery = "SELECT  * FROM " + TABLE_GROUPS + " WHERE "
                + KEY_ID + " IN (SELECT "
                + KEY_GROUP_ID + " FROM "
                + TABLE_USERGROUP + " WHERE "
                + KEY_USER_ID + " = " + user.getId()
                + ")";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Group group = new Group();
                group.setId(Integer.parseInt(c.getString(0)));
                group.setName(c.getString(1));
                group.setDescription(c.getString(2));
                groupList.add(group);
            } while (c.moveToNext());
        }

        return groupList;
    }

    // Getting groups Count
    public int getGroupCount() {
        String countQuery = "SELECT  * FROM " + TABLE_GROUPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single user
    public int updateGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, group.getName());
        values.put(KEY_DESCRIPTION, group.getDescription());


        // updating row
        return db.update(TABLE_GROUPS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(group.getId()) });
    }

    // Deleting single group
    public void deleteGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GROUPS, KEY_ID + " = ?",
                new String[] { String.valueOf(group.getId()) });
        db.close();
    }

    public void addUserToGroup(User user, Group group){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getId());
        values.put(KEY_GROUP_ID, group.getId());

        db.insert(TABLE_USERGROUP, null, values);
        db.close(); // Closing database connection
    }

    public void addUserToMeetup(User user, Meetup meetup){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getId());
        values.put(KEY_MEETUP_ID, meetup.getId().toString());

        db.insert(TABLE_USERGROUPMEETUP, null, values);
        db.close(); // Closing database connection
    }

}
