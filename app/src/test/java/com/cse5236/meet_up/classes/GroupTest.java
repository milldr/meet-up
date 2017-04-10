package com.cse5236.meet_up.classes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kylejosephwilliams on 4/10/17.
 */
public class GroupTest {
    @Test
    public void getId() throws Exception {
        Group grp = new Group(123, "GroupA", "This is a group");
        assertEquals(123, grp.getId());
    }

    @Test
    public void getName() throws Exception {
        Group grp = new Group(321, "Another Group", "This is another group");
        assertEquals("Another Group", grp.getName());
    }

    @Test
    public void getDescription() throws Exception {
        Group grp = new Group(007, "Bond", "James Bond");
        assertEquals("James Bond", grp.getDescription());
    }

}