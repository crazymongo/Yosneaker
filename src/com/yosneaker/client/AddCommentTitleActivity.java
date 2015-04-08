package com.yosneaker.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yosneaker.client.model.CommentDraft;

import android.R.integer;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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
public class AddCommentTitleActivity extends BaseActivity{

	private EditText et_comment_title;
	private String commentTitle;
	private int action;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_add_comment_title);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_next);
		
		et_comment_title = (EditText) findViewById(R.id.et_comment_title);
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);		
		getTextViewRight1().setOnClickListener(this);		
	
	}

	@Override
	public void fillDatas() {
		action = getIntent().getIntExtra("action", 0);
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}else if (v == getTextViewRight1()) {
			commentTitle = et_comment_title.getText().toString();
			if (TextUtils.isEmpty(commentTitle)) {
				et_comment_title.setError(getResources().getString(R.string.error_comment_title_no_null));
			}else {				
				CommentDraft commentDraft = new CommentDraft();
				commentDraft.setComment_title(commentTitle);
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				commentDraft.setComment_date(Integer.parseInt(df.format(new Date())));
				if (action == 0) {
					Intent intent = new Intent();
					intent.putExtra("CommentDraft",commentDraft);
					setResult(RESULT_OK, intent);
				}else {
					Intent intent = new Intent(AddCommentTitleActivity.this,EditCommentActivity.class);
					intent.putExtra("CommentDraft",commentDraft);
					startActivity(intent);
				}
				AddCommentTitleActivity.this.finish();
			}
		}
	}

	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)   {  
		customBackPressed();
        return false;           
    }  
	
	
	public void customBackPressed() {
		commentTitle = et_comment_title.getText().toString();
		if (!TextUtils.isEmpty(commentTitle)) {
			CommentDraft commentDraft = new CommentDraft();
			commentDraft.setComment_title(commentTitle);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			commentDraft.setComment_date(Integer.parseInt(df.format(new Date())));
			Intent intent = new Intent();
			intent.putExtra("CommentDraft",commentDraft);
			setResult(RESULT_OK, intent);
			AddCommentTitleActivity.this.finish();
		}else {
			AddCommentTitleActivity.this.finish();
		}
	}
	
}
