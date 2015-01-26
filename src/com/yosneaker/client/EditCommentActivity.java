package com.yosneaker.client;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * 编辑测评
 * 
 * @author chendd
 *
 */
public class EditCommentActivity extends BaseActivity{

	private LinearLayout ll_edit_intro;
	private LinearLayout ll_edit_item;
	private LinearLayout ll_edit_summarize;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_edit_comment);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);

		ll_edit_intro = (LinearLayout) findViewById(R.id.ll_edit_intro);
		ll_edit_item = (LinearLayout) findViewById(R.id.ll_edit_item);
		ll_edit_summarize = (LinearLayout) findViewById(R.id.ll_edit_summarize);
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);			
		ll_edit_intro.setOnClickListener(this);
		ll_edit_item.setOnClickListener(this);
		ll_edit_summarize.setOnClickListener(this);
	}

	@Override
	public void fillDatas() {
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}else if (v == ll_edit_intro) {
			gotoExistActivity(EditCommentIntroActivity.class, new Bundle());
		}else if (v == ll_edit_item) {
			gotoExistActivity(EditCommentItemActivity.class, new Bundle());
		}else if (v == ll_edit_summarize) {
			gotoExistActivity(EditCommentSummarizeActivity.class, new Bundle());
		}
	}

}
