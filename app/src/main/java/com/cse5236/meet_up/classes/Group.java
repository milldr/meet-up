package com.cse5236.meet_up.classes;

/**
 * Created by daniel on 3/8/17.
 */

public class Group {
    private int id; // used for object storage
    private String name;
    private String description;

    /** Constructor */
    public Group(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
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
}
