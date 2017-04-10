package com.cse5236.meet_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cse5236.meet_up.classes.Group;
import com.cse5236.meet_up.classes.User;

import java.util.List;

/**
 * Created by jeff on 4/9/17.
 */

public class groupListAdapter extends BaseAdapter {

        Context context;
        List<Group> data;
        private static LayoutInflater inflater = null;

        public groupListAdapter(Context context, List<Group> data) {
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
        public Group getItem(int position) {
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
                vi = inflater.inflate(R.layout.group_list_item, null);
            TextView text = (TextView) vi.findViewById(R.id.grpname);
            text.setText(data.get(position).getName());
            text = (TextView) vi.findViewById(R.id.grpdescrip);
            text.setText(data.get(position).getDescription());
            return vi;
        }
    }