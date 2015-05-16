package com.yosneaker.client;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 编辑用户打法
 * 
 * @author chendd
 * 
 */
public class EditUserPlayActivity extends BaseActivity{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit_user_play);

		super.onCreate(savedInstanceState);
	}

	@Override
	public void initViews() {
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_ok);

	}

	@Override
	public void addListnners() {
		getTextViewLeft().setOnClickListener(this);
		getTextViewRight1().setOnClickListener(this);
		
	}

	@Override
	public void fillDatas() {
		
	}

	@Override
	public void onClick(View v) {
		
	}

}
