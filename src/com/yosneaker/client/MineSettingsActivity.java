package com.yosneaker.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yosneaker.client.model.Article;
import com.yosneaker.client.util.Validation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 用户登录界面
 * 
 * @author chendd
 *
 */
public class MineSettingsActivity extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_mine_settings);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);
		
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
		switch (v.getId()) {
		case R.id.mTextViewLeft:
			finish();
			break;
		default:
			break;
		}
	}
	
}
