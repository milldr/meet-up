package com.cse5236.meet_up.classes;

import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 3/8/17.
 */

public class Meetup {
    private UUID id; // used for object storage
    private String name;
    private String description;
    private Date date;
    private Time time;
    private List<String> users;
    private boolean attending;

    /** Constructor */
    public Meetup(){
        id = UUID.randomUUID();
        date = new Date();
        attending = false;

    }

    public boolean isAttending() {
        return attending;
    }

    public void setAttending(boolean attending) {
        this.attending = attending;
    }

/*  Id cannot be changed
    public void setId(int id) {
        this.id = id;

    }*/

    // getters and setters here...
    public UUID getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public List<String> getUsers() {
        return users;
    }

    public void addUser(String userKey) {
        this.users.add(userKey);
    }
}
