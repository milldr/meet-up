package com.cse5236.meet_up;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cse5236.meet_up.classes.User;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by daniel on 4/2/17.
 */

public class userListAdapter extends BaseAdapter {

        Context context;
        List<User> data;
        private static LayoutInflater inflater = null;

        public userListAdapter(Context context, List<User> data) {
            // TODO Auto-generated constructor stub
            this.context = context;
            this.data = data;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public User getItem(int position) {
            // TODO Auto-generated method stub
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi = convertView;
            if (vi == null)
                vi = inflater.inflate(R.layout.user_list_item, null);
            TextView text = (TextView) vi.findViewById(R.id.name);
            text.setText(data.get(position).getName());
            text = (TextView) vi.findViewById(R.id.email);
            text.setText(data.get(position).getEmail());
            return vi;
        }
    }