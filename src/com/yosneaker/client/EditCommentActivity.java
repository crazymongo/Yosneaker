package com.yosneaker.client;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 编辑测评
 * 
 * @author chendd
 *
 */
public class EditCommentActivity extends BaseActivity{


	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		setContentView(R.layout.activity_add_comment_title);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_next);
		
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
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}else if (v == getTextViewRight1()) {
			showToast("下一步");
		}
	}

}
