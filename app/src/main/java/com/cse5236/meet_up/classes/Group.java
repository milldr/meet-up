package com.cse5236.meet_up.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/8/17.
 */

public class Group {
    private String id; // used for object storage
    private String name;
    private String description;
    private List<String> users; // list of keys of users in group


    /** Constructor */
    public Group(String id, String name, String description, List<String> users){
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = new ArrayList<>();
        this.users.addAll(users);    }

/*  Id cannot be changed
    public void setId(int id) {
        this.id = id;
    }*/

    // getters and setters here...
    public String getId(){
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

    public List<String> getUsers() {
        return users;
    }

    public void addUser(String userKey) {
        this.users.add(userKey);
    }
}
