package com.cse5236.meet_up.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/8/17.
 */

public class User {
    private int id; // used for object storage
    private String name;
    private List<Group> groups;
    private List<Meetup> meetups;

    /** Constructor */
    public User(int id, String name, List<Group> groups, List<Meetup> meetups){
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

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public List<Meetup> getMeetups() {
        return meetups;
    }

    public void addMeetup(Meetup meetup) {
        this.meetups.add(meetup);
    }
}
