package com.cse5236.meet_up.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cse5236.meet_up.LoginActivity;
import com.cse5236.meet_up.MainActivity;
import com.cse5236.meet_up.MeetupActivity;
import com.cse5236.meet_up.R;
import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.MeetupList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetupListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetupListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetupListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mMeetupRecyclerView;
    private MeetupAdapter mAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MeetupListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetupListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetupListFragment newInstance(String param1, String param2) {
        MeetupListFragment fragment = new MeetupListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetup_list, container, false);
        mMeetupRecyclerView = (RecyclerView) view
                .findViewById(R.id.meetup_recycler_view);
        mMeetupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        MeetupList meetupList = MeetupList.get(getActivity());
        List<Meetup> meetups = meetupList.getMeetups();

        if (mAdapter == null) {
        mAdapter = new MeetupAdapter(meetups);
        mMeetupRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MeetupHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        private TextView mDateTextView;
        private CheckBox mAttendingCheckBox;
        private Meetup mMeetup;

        public MeetupHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView)
                    itemView.findViewById(R.id.list_item_meetup_name_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_meetup_date_text_view);
            mAttendingCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_meetup_attending_checkbox);
        }

        @Override
        public void onClick(View v) {
            Intent intent = MeetupActivity.newIntent(getActivity(), mMeetup.getId());
            startActivity(intent);
        }

        public void bindMeetup(Meetup meetup) {
            mMeetup = meetup;
            mNameTextView.setText(mMeetup.getName());
            mDateTextView.setText(mMeetup.getDate().toString());
            mAttendingCheckBox.setChecked(mMeetup.isAttending());
        }
    }

    private class MeetupAdapter extends RecyclerView.Adapter<MeetupHolder> {
        private List<Meetup> mMeetups;
        public MeetupAdapter(List<Meetup> meetups) {
            mMeetups = meetups;
        }
        @Override
        public MeetupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_meetup, parent, false);
            return new MeetupHolder(view);
        }
        @Override
        public void onBindViewHolder(MeetupHolder holder, int position) {
            Meetup meetup = mMeetups.get(position);
            holder.bindMeetup(meetup);
        }
        @Override
        public int getItemCount() {
            return mMeetups.size();
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}