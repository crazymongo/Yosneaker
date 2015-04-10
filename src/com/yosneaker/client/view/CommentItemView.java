package com.yosneaker.client.view;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yosneaker.client.R;

public class CommentItemView extends LinearLayout {

	private Context context;
	
	private TextView tv_item_order;
	private TextView tv_item_name;
	private TextView tv_item_content;
	private AssessStarView asv_item_assess;
	private FlowLayout fl_item_image;
	
	public CommentItemView(Context context) {
		super(context);
		this.context = context;
	}

	public CommentItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.view_edit_comment_item_sun, this, true);
		tv_item_order = (TextView) findViewById(R.id.tv_item_order);
		tv_item_name = (TextView) findViewById(R.id.tv_item_name);
		tv_item_content = (TextView) findViewById(R.id.tv_item_content);
		asv_item_assess = (AssessStarView) findViewById(R.id.asv_item_assess);
		fl_item_image = (FlowLayout) findViewById(R.id.fl_item_image);
		
	}

	public void setItemOrder(String item_order){
		tv_item_order.setText(item_order);
	}
	
	public void setItemName(String item_name) {
		tv_item_name.setText(item_name);
	}
	
	public void setItemContent(String item_content) {
		tv_item_content.setText(item_content);
	}
	
	public void setItemAssess(int assessStarNum) {
		asv_item_assess.setStarNumber(assessStarNum);
	}
	
	public void addItemImage() {
		ImageView iv = new ImageView(context);
		iv.setImageResource(R.drawable.default_comment_bg);
		fl_item_image.addView(iv);
	}
	
}
