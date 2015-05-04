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
import com.yosneaker.client.model.IdentifyList;

/**
 * 鉴定adapter(暂时不用)
 * @author chendd
 */
public class IdentifyAdapter extends ArrayAdapter<IdentifyList> {
	
	private LayoutInflater mInflater;
	
    public IdentifyAdapter(Context context, ArrayList<IdentifyList> Articles) {
        super(context, android.R.layout.simple_list_item_1, Articles);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // if we weren't given a view, inflate one
    	Log.i("==========",""+convertView);
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.xlistview_identify_item, null);
        }

        // configure the view for this Article
        IdentifyList identifyList = getItem(position);

        TextView titleTextView =
            (TextView)convertView.findViewById(R.id.tv_title);
        titleTextView.setText(identifyList.getTitle());

        TextView date =
        	(TextView)convertView.findViewById(R.id.tv_date);
        date.setText(""+identifyList.getDate());
        
        return convertView;
    }
}
