package com.yosneaker.client.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yosneaker.client.R;
import com.yosneaker.client.model.Comment;

public class CommentAdapter extends ArrayAdapter<Comment> {
	
	private LayoutInflater mInflater;
	
    public CommentAdapter(Context context, ArrayList<Comment> Articles) {
        super(context, android.R.layout.simple_list_item_1, Articles);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // if we weren't given a view, inflate one
    	Log.i("==========",""+convertView);
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.xlistview_comment_item, null);
        }

        // configure the view for this Article
        Comment c = getItem(position);

        TextView titleTextView =
            (TextView)convertView.findViewById(R.id.comment_title);
        titleTextView.setText(c.getTitle());
        TextView reader =
                (TextView)convertView.findViewById(R.id.comment_count);
        	reader.setText(""+c.getReader());
        	TextView date =
        			(TextView)convertView.findViewById(R.id.comment_date);
        	date.setText(""+c.getDate());
        return convertView;
    }
}
