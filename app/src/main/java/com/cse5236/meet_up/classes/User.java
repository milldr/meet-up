package com.cse5236.meet_up.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/8/17.
 */

public class User {
    private int id; // used for object storage
    private String name;
    private String email;
    private String password;

    /** Constructor */
    public User(){

    }

    public User(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // custom methods

    // getters and setters here...

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
