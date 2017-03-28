package com.cse5236.meet_up.classes;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jeff on 3/26/2017.
 */

public class MeetupList {
    private static MeetupList sMeetupList;

    private List<Meetup> mMeetups;

    public static MeetupList get(Context context) {
        if (sMeetupList == null) {
            sMeetupList = new MeetupList(context);
        }
        return sMeetupList;
    }
    private MeetupList(Context context) {
        mMeetups = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Meetup meetup = new Meetup();
            meetup.setName("meetup #" + i);
            meetup.setAttending(i % 2 == 0); // Every other one
            mMeetups.add(meetup);
        }
    }

    public List<Meetup> getMeetups() {
        return mMeetups;
    }
    public Meetup getMeetup(UUID id) {
        for (Meetup meetup : mMeetups) {
            if (meetup.getId().equals(id)) {
                return meetup;
            }
        }
        return null;
    }
}
