package com.cse5236.meet_up;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.MeetupList;
import com.cse5236.meet_up.fragments.MeetupsFragment;

import java.util.List;
import java.util.UUID;

/**
 * Created by Jeff on 3/27/2017.
 */

public class MeetupPagerActivity extends Activity {
    public static final String EXTRA_MEETUP_ID =
            "com.cse5223.meetup.meetup_id";
    public static Intent newIntent(Context packageContext, UUID meetupId) {
        Intent intent = new Intent(packageContext, MeetupActivity.class);
        intent.putExtra(EXTRA_MEETUP_ID, meetupId);
        return intent;
    }

    private ViewPager mViewPager;
    private List<Meetup> mMeetups;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup_pager);

        UUID meetupId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_MEETUP_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_meetup_pager_view_pager);
        mMeetups = MeetupList.get(this).getMeetups();
        FragmentManager fragmentManager = getFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Meetup meetup = mMeetups.get(position);
                return MeetupsFragment.newInstance(meetup.getId());
            }
            @Override
            public int getCount() {
                return mMeetups.size();
            }
        });

        for (int i = 0; i < mMeetups.size(); i++) {
            if (mMeetups.get(i).getId().equals(meetupId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
