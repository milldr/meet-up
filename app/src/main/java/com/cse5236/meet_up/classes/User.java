package com.cse5236.meet_up.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/8/17.
 */

public class User {
    private int id; // used for object storage
    private String name;
    private List<String> groups; // list of keys for associated groups
    private List<String> meetups;   // list of keys for associated meetups

    /** Constructor */
    public User(int id, String name, List<String> groups, List<String> meetups){
        this.id = id;
        this.name = name;
        this.groups = new ArrayList<>();
        this.meetups = new ArrayList<>();
        this.groups.addAll(groups);
        this.meetups.addAll(meetups);
    }

/*  Id cannot be changed
    public void setId(int id) {
        this.id = id;
    }*/

    // getters and setters here...
    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGroupKeys() {
        return groups;
    }

    public void addGroup(String groupKey) {
        this.groups.add(groupKey);
    }

    public List<String> getMeetupKeys() {
        return meetups;
    }

    public void addMeetup(String meetupKey) {
        this.meetups.add(meetupKey);
    }
}
