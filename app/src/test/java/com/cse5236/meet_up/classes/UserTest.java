package com.cse5236.meet_up.classes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kylejosephwilliams on 4/10/17.
 */
public class UserTest {
    @Test
    public void getId() throws Exception {
        User user1 = new User();
        user1.setId(123);
        assertEquals(123, user1.getId());
    }

    @Test
    public void getName() throws Exception {
        User user1 = new User();
        user1.setName("Brutus Buckeye");
        assertEquals("Brutus Buckeye", user1.getName());
    }

    @Test
    public void getEmail() throws Exception {
        User user1 = new User();
        user1.setEmail("BrutusBuckeye@osu.edu");
        assertEquals("BrutusBuckeye@osu.edu", user1.getEmail());
    }

    @Test
    public void getPassword() throws Exception {
        User user1 = new User();
        user1.setPassword("#GoBucks");
        assertEquals("#GoBucks", user1.getPassword());
    }

}