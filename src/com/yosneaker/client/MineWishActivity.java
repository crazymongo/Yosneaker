package com.yosneaker.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yosneaker.client.model.Article;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

/**
 * 发布测评标题
 * 
 * @author chendd
 *
 */
public class MineWishActivity extends BaseActivity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_mine_wish_none);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(false);
		
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);	
	
	}

	@Override
	public void fillDatas() {

	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			finish();
		}
	}
}