package com.cse5236.meet_up.classes;

import java.sql.Time;
import java.sql.Date;

/**
 * Created by daniel on 3/8/17.
 */

public class Meetup {
    private int id; // used for object storage
    private String name;
    private String description;
    private Date date;
    private Time time;

    /** Constructor */
    public Meetup(int id, String name, String description, Date date, Time time){
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
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
}
