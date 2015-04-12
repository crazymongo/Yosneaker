package com.yosneaker.client.view;

import java.io.FileNotFoundException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yosneaker.client.EditCommentItemActivity;
import com.yosneaker.client.R;

public class CommentItemView extends LinearLayout {

	private Context context;
	private LayoutInflater inflater;
	
	private TextView tv_item_order;
	private TextView tv_item_name;
	private TextView tv_item_content;
	private ImageView iv_remove_item;
	private AssessStarView asv_item_assess;
	private LinearLayout fl_item_image;
	
	private Callbacks callbacks;
	
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
		inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.view_edit_comment_item_sun, this, true);
		tv_item_order = (TextView) findViewById(R.id.tv_item_order);
		tv_item_name = (TextView) findViewById(R.id.tv_item_name);
		tv_item_content = (TextView) findViewById(R.id.tv_item_content);
		iv_remove_item = (ImageView) findViewById(R.id.iv_remove_item);
		iv_remove_item.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				CommentItemView.this.removeAllViews();
				if (callbacks != null) {
					callbacks.setItemRemove(Integer.parseInt((String) tv_item_order.getText()));
				}				
			}
		});
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
		final View picView = inflater.inflate(
				R.layout.view_edit_comment_gv_item_pic, null);
		ImageButton picIBtn = (ImageButton) picView
				.findViewById(R.id.pic);
		final Bitmap bmp;
		try {
			bmp = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(imageUri)));
			if (bmp != null) {
				picIBtn.setImageBitmap(bmp);
				picIBtn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						AlertDialog.Builder builder = new Builder(
								context);
						builder.setTitle(getResources().getString(R.string.picker_image_see_detail));
						View view = inflater.inflate(
								R.layout.view_edit_comment_showmax_dialog,
								null);
						((ImageView) view.findViewById(R.id.bigPic))
								.setImageBitmap(bmp);
						builder.setView(view);
						builder.setNegativeButton(getResources().getString(R.string.picker_image_back), null);
						builder.show();
					}
				});
				picView.findViewById(R.id.delete).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								fl_item_image.removeView(picView);
							}
						});
				fl_item_image.addView(picView);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	public void setCallbacks(Callbacks callbacks) {
		this.callbacks = callbacks;
	}
	
	public interface Callbacks {
        public void setItemRemove(int item_order);// 点击删除测评项的回调
	}
	
	
}
