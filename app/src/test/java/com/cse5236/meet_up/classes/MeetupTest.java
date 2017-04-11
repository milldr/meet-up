package com.cse5236.meet_up.classes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kylejosephwilliams on 4/10/17.
 */
public class MeetupTest {
    @Test
    public void isAttending() throws Exception {
        Meetup meet = new Meetup();
        Meetup meet2 = new Meetup();
        meet.setAttending(true);
        meet2.setAttending(false);
        assertEquals(true, meet.isAttending());
        assertEquals(false, meet2.isAttending());
    }

    @Test
    public void getName() throws Exception {
        Meetup meet = new Meetup();
        Meetup meet2 = new Meetup();
        meet.setName("Meetup A");
        meet2.setName("Meetup B");
        assertEquals("Meetup A", meet.getName());
        assertEquals("Meetup B", meet2.getName());
    }

}