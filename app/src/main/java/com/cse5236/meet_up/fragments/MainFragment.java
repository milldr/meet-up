package com.cse5236.meet_up.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cse5236.meet_up.R;


import com.cse5236.meet_up.classes.Group;
import com.cse5236.meet_up.classes.Helpers;
import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.User;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Create buttons and on click listeners
        Button committedBtn = (Button) view.findViewById(R.id.committed_meetups);
        committedBtn.setOnClickListener(this);
        Button tentativeBtn = (Button) view.findViewById(R.id.tentative_meetups);
        tentativeBtn.setOnClickListener(this);
        Button pendingBtn = (Button) view.findViewById(R.id.pending_meetups);
        pendingBtn.setOnClickListener(this);
        Button pastBtn = (Button) view.findViewById(R.id.past_meetups);
        pastBtn.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
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

    @Override
    public void onStart() {
        super.onStart();


        // ***********************    EXAMPLE OF HOW TO SAVE / GET OBJECTS      ***********************

        Log.d(TAG, "onStart() of MainFragment...");

        // context needed for db
        Context ctx = this.getActivity();

        // create example user
        String name = "test mctestface";
        List<String> groups = new ArrayList<>();
        List<String> meetups = new ArrayList<>();

        // create the user objects first
        User user = new User("1", name, groups, meetups);
        Helpers.setUser(ctx, user);
        User user2 = new User("2", "test2", groups, meetups);
        Helpers.setUser(ctx, user2);

 /*       Log.d(TAG, user.getName());
        Log.d(TAG, user2.getName());

        // test getting it back
        User user3 = Helpers.getUser(ctx, "1");
        Log.d(TAG, user3.getName());
        User user4 = Helpers.getUser(ctx, "2");
        Log.d(TAG, user4.getName());*/

        // groups
        List<String> userKeyList = new ArrayList<>();
        userKeyList.add(user.getId());
        userKeyList.add(user2.getId());

        Group group = new Group("3", "test group", "testing group description", userKeyList);
        Helpers.setGroup(ctx, group);
        Log.d(TAG, group.getName());
        // test getting users from groups
        for (String userKey : group.getUsers()){
            Log.d(TAG, userKey);
            User usertest = Helpers.getUser(ctx, userKey);
            Log.d(TAG, usertest.getName());
        }

        Group check = Helpers.getGroup(ctx, "3");
        Log.d(TAG, check.getName());

    }

    /**
     * Responds to clicks on each button in the fragment.
     * When a click is detected, replaces the content with the appropriate fragment.
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.committed_meetups:
                //open page containing committed meetups or display committed meetups
                break;
            case R.id.tentative_meetups:
                //open page containing tentative meetups or display
                break;
            case R.id.pending_meetups:
                //open page containing pending meetups or display
                break;
            case R.id.past_meetups:
                //open page containing past meetups or display
                break;
            default:
                //do nothing
                break;
        }
    }
}
