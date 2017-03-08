package com.cse5236.meet_up;

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

import com.cse5236.meet_up.fragments.CalendarFragment;
import com.cse5236.meet_up.fragments.GroupsFragment;
import com.cse5236.meet_up.fragments.MainFragment;
import com.cse5236.meet_up.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
    implements CalendarFragment.OnFragmentInteractionListener, MainFragment.OnFragmentInteractionListener,
    GroupsFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener{

    private static final String TAG = "MainActivity";
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private String[] mFragmentTitles = { "Home", "Calendar", "Groups", "Settings"};

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
                fragment = new MainFragment();
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
                fragment = new MainFragment();
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri){
    }

}
