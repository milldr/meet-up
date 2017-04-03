package com.cse5236.meet_up;

import android.app.Application;

import com.cse5236.meet_up.classes.User;

/**
 * Created by daniel on 4/2/17.
 */

public class MeetUp extends Application {
    private User user;

    public User getCurrentUser() {
        return user;
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }
}
