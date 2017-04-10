package com.cse5236.meet_up.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.cse5236.meet_up.MeetUp;
import com.cse5236.meet_up.R;
import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.MeetupList;
import com.cse5236.meet_up.classes.User;
import com.cse5236.meet_up.classes.database.DatabaseHandler;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CalendarView calendarView;
    TextView dateDisplay;

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
;
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        dateDisplay = (TextView) view.findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");
        //waiting for working getAllMeetups() method to utilize this and the commented methods in setOnDateChangeListener
        DatabaseHandler db = new DatabaseHandler(this.getActivity());
        User u = ((MeetUp) getActivity().getApplication()).getCurrentUser();
        List<Meetup> meetups = db.getAllMeetups(u);
        final List<Meetup> meets = meetups;

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
                if (events.length() != 0) {
                    //if there are meetups, remove the extra comma from the end.
                    events = events.substring(0, events.length() - 1);
                }
                dateDisplay.setText("Date: " + (i1 + 1) + " / " + i2 + " / " + i + "\n" + "Events:\n" + events + "\n");
            }
        });
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

}
