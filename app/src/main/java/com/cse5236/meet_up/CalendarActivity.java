package com.cse5236.meet_up;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.MeetupList;
import com.cse5236.meet_up.fragments.NewGroupFragment;
import com.cse5236.meet_up.fragments.SettingsFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");
        //waiting for working getAllMeetups() method to utilize this and the commented methods in setOnDateChangeListener
        MeetupList meetupList = MeetupList.get(this);
        final List<Meetup> meets = meetupList.getMeetups();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Date d;
                String events = "";
                d = new GregorianCalendar(i, i1, i2).getTime();
                for (Meetup meet : meets) {
                    if ((meet.getDate().getDate() == d.getDate()) && (meet.getDate().getMonth() == d.getMonth()) && (meet.getDate().getYear() == d.getYear())) {
                        events += meet.getName();
                        events += ",";
                    }
                }
                dateDisplay.setText("Date: " + (i1 + 1) + " / " + i2 + " / " + i + "\n" + "Events:\n" + events + "\n");
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (id == R.id.add_meetup) {
            Meetup meetup = new Meetup();
            MeetupList.get(this).addMeetup(meetup);
            Intent intent = MeetupPagerActivity
                    .newIntent(this, meetup.getId());
            startActivity(intent);
            return true;
            //create a new meetup
        } else if (id == R.id.add_group) {
            //create a new group
            Fragment fragment = new NewGroupFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

        } else if (id == R.id.settings) {
            //go to settings
            Fragment fragment = new SettingsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }
}