package com.cse5236.meet_up.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/8/17.
 */

public class Group {
    private long id; // used for object storage
    private String name;
    private String description;

    /** Constructor */
    public Group(){

    }

    public Group(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Group(long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    // getters and setters here...
    public long getId(){
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

}
