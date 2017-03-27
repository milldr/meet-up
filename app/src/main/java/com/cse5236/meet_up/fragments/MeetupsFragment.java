package com.cse5236.meet_up.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cse5236.meet_up.R;
import com.cse5236.meet_up.classes.Meetup;
import com.cse5236.meet_up.classes.DatePickerFragment;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetupsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetupsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int MEETUP_DATE = 0;
    private Meetup mMeetup;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mAttendingCheckBox;
    private Button mNotAttendingCheckBox;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MeetupsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetupsFragment newInstance(String param1, String param2) {
        MeetupsFragment fragment = new MeetupsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            mMeetup = new Meetup();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meetups, container, false);

        mTitleField = (EditText)v.findViewById(R.id.meetup_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                    // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mMeetup.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            // This one too
            }
        });

        mDateButton = (Button)v.findViewById(R.id.meetup_date);
        mDateButton.setText(mMeetup.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mMeetup.getDate());
                dialog.setTargetFragment(MeetupsFragment.this, MEETUP_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mAttendingCheckBox = (Button)v.findViewById(R.id.meetup_attending);
        mAttendingCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the meetups's attending property to true
                mMeetup.setAttending(true);
            }
        });

        mNotAttendingCheckBox = (Button)v.findViewById(R.id.meetup_notattending);
        mNotAttendingCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the meetups's attending property to false
                mMeetup.setAttending(false);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == MEETUP_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mMeetup.setDate(date);
            mDateButton.setText(mMeetup.getDate().toString());
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
