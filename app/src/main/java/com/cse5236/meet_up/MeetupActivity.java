package com.cse5236.meet_up;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.res.Configuration;
import android.app.FragmentManager;
import android.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.android.volley.RequestQueue;
import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.MeetupList;
import com.cse5236.meet_up.fragments.CalendarFragment;
import com.cse5236.meet_up.fragments.GroupsFragment;
import com.cse5236.meet_up.fragments.MeetupListFragment;
import com.cse5236.meet_up.fragments.MeetupsFragment;
import com.cse5236.meet_up.fragments.SettingsFragment;

import java.util.UUID;

public class MeetupActivity extends AppCompatActivity
        implements CalendarFragment.OnFragmentInteractionListener, MeetupListFragment.OnFragmentInteractionListener,
        GroupsFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, MeetupsFragment.OnFragmentInteractionListener{

    private static final String TAG = "MeetupActivity";
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private String[] mFragmentTitles = { "Home", "Calendar", "Groups", "Settings"};

    private RequestQueue requestQueue;

    public static final String EXTRA_CRIME_ID =
            "com.cse5223.meetup.meetup_id";
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, MeetupActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    /* onCreate() - create views, (re) initialize state */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        // initialize the list for navigation
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        // fill the list of items in the navigation
        addDrawerItems();
        setupDrawer();

        // set a toggle for the navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //initialize opening fragment
        selectItem(0);
        Fragment fragment = new MeetupsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void addDrawerItems() {
        // define the list of items (pages) in the navigation
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mFragmentTitles);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }

        });
    }

    private void selectItem(int position) {
        // specify the new fragment
        Fragment fragment;
        switch (position){
            case(0):
                fragment = new MeetupListFragment();
                break;
            case(1):
                fragment = new CalendarFragment();
                break;
            case(2):
                fragment = new GroupsFragment();
                break;
            case(3):
                fragment = new SettingsFragment();
                break;
            default:
                fragment = new MeetupListFragment();
                break;
        }


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mFragmentTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void setTitle(String title) {
        mActivityTitle = title;
        getActionBar().setTitle(mActivityTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void setupDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (id == R.id.add_meetup) {
            Meetup meetup = new Meetup();
            MeetupList.get(this).addMeetup(meetup);
            Intent intent = MeetupPagerActivity
                    .newIntent(this, meetup.getId());
            startActivity(intent);
            return true;
            //create a new meetup
        } else if (id == R.id.add_group) {
            //create a new group
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

    @Override
    public void onFragmentInteraction(Uri uri){
    }
}
