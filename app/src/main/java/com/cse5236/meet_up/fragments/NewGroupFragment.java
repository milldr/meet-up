package com.cse5236.meet_up.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cse5236.meet_up.MeetUp;
import com.cse5236.meet_up.R;
import com.cse5236.meet_up.classes.database.DatabaseHandler;
import com.cse5236.meet_up.classes.Group;
import com.cse5236.meet_up.classes.User;
import com.cse5236.meet_up.userListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewGroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView lv;
    private List<User> friendList;
    private userListAdapter ula;
    private DatabaseHandler db;
    private EditText mName;
    private EditText mDescription;



    public NewGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewGroupFragment newInstance(String param1, String param2) {
        NewGroupFragment fragment = new NewGroupFragment();
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

        db = new DatabaseHandler(this.getActivity());
        friendList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_group, container, false);
        Context context = getActivity().getApplicationContext();

        List<User> userList = db.getAllUsers();

        lv = (ListView) rootView.findViewById(R.id.user_list);
        ula = new userListAdapter(context, userList);
        lv.setAdapter(ula);


        // ListView on item selected listener.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                User friend = ula.getItem(position);
                if (!friendList.contains(friend)){
                    friendList.add(friend);
                }
            }
        });

        Button save = (Button) rootView.findViewById(R.id.save_group);
        mName   = (EditText) rootView.findViewById(R.id.group_name);
        mDescription   = (EditText) rootView.findViewById(R.id.group_description);
        save.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String description = mDescription.getText().toString();
                Group group = new Group(name, description);
                db.addGroup(group);
                User currentUser = ((MeetUp) getActivity().getApplication()).getCurrentUser();
                db.addUserToGroup(currentUser, group);
                for (User friend : friendList){
                    db.addUserToGroup(friend, group);
                }

            }
        });

        return rootView;
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
