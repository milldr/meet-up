package com.cse5236.meet_up.classes;

import android.content.Context;

/**
 * Created by daniel on 3/22/17.
 */

public class Helpers {

    /* ------ User CRUD methods ------ */
    // create and update user
    public static void setUser(Context ctx, User user){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
        String key = "" + user.getId();
        complexPreferences.putObject(key, user);
        complexPreferences.commit();
    }

    // retrieve user
    public static User getUser(Context ctx, String key){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
        User user = complexPreferences.getObject(key, User.class);
        return user;
    }

    /* ------ Group CRUD methods ------ */
    // create and update group
    public static void setGroup(Context ctx, Group group){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
        String key = "" + group.getId();
        complexPreferences.putObject(key, group);
        complexPreferences.commit();
    }

    // retrieve group
    public static Group getGroup(Context ctx, String key){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
        Group group = complexPreferences.getObject(key, Group.class);
        return group;
    }

    /* ------ Misc / Generic ------ */
    // clear all
    public static void clear(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }

    // delete user or group or meetup
    public static void deleteObject(Context ctx, String key){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
        complexPreferences.removeObject(key);
        complexPreferences.commit();
    }
}
