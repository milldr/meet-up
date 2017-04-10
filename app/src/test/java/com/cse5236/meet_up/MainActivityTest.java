package com.cse5236.meet_up;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kylejosephwilliams on 4/10/17.
 */
public class MainActivityTest {
    @Test
    public void selectItem() throws Exception {
        MainActivity main = new MainActivity();
        MainActivity main2 = new MainActivity();
        main.selectItem(1);
        main2.selectItem(2);
        assertEquals("Calendar", main.opened);
        assertEquals("Groups", main2.opened);
    }

    @Test
    public void setTitle() throws Exception {
        MainActivity main = new MainActivity();
        MainActivity main2 = new MainActivity();
        main.setTitle("Main");
        main2.setTitle("Main2");
        assertEquals("Main", main.mActivityTitle);
        assertEquals("Main2", main2.mActivityTitle);
    }

}