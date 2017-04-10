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
    private Date date;
    private boolean attending;

    /** Constructor */
    public Meetup(){
        id = UUID.randomUUID();
        date = new Date();
        attending = false;

    }

    public Meetup(UUID mId) {
        id = mId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(UUID id) { this.id = id; }
}
