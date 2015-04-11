package com.yosneaker.client.view;

import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
	private LinearLayout fl_item_image;
	
	public CommentItemView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public CommentItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
		
	}

	private void init() {		
		LayoutInflater.from(context).inflate(R.layout.view_edit_comment_item_sun, this, true);
		tv_item_order = (TextView) findViewById(R.id.tv_item_order);
		tv_item_name = (TextView) findViewById(R.id.tv_item_name);
		tv_item_content = (TextView) findViewById(R.id.tv_item_content);
		asv_item_assess = (AssessStarView) findViewById(R.id.asv_item_assess);
		fl_item_image = (LinearLayout) findViewById(R.id.fl_item_image);
	}
	
	public void setItemOrder(int item_order){
		tv_item_order.setText(item_order+"");
	}
	
	public void setItemName(String itemsize) {
		tv_item_name.setText(itemsize);
	}
	
	public void setItemContent(String item_content) {
		tv_item_content.setText(item_content);
	}
	
	public void setItemAssess(int assessStarNum) {
		asv_item_assess.setStarNumber(assessStarNum);
	}
	
	public void addItemImage(String imageUri) {
		ImageView iv = new ImageView(context);
		Bitmap bmp;
		try {
			bmp = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(imageUri)));
			if (bmp != null) {
				iv.setImageBitmap(bmp);
				fl_item_image.addView(iv);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
