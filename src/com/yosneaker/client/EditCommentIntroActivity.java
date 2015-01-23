package com.yosneaker.client;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 编辑测评简介
 * 
 * @author chendd
 *
 */
public class EditCommentIntroActivity extends BaseActivity{

	private EditText edit_text;
	private TextView text_view;
	private int BigIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_edit_comment_intro);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		BigIndex = Integer.parseInt(getResources().getString(R.string.comment_edit_intro_maxText));
		
		edit_text = (EditText)findViewById(R.id.edit_text);
		text_view = (TextView)findViewById(R.id.text_view);
		edit_text.addTextChangedListener(new EditTextWatcher());
		
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
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}
	}

	public class EditTextWatcher implements TextWatcher {

		public void afterTextChanged(Editable arg0) {
			 String edit = edit_text.getText().toString();
			 
			edit_text.setVisibility(View.VISIBLE);
			if (edit.length() <= BigIndex) {
				text_view.setText(""+(BigIndex - edit.length()));
			} else {
				 edit_text.setText(edit.substring(0,BigIndex));
				 edit_text.setSelection(edit.substring(0,BigIndex).length());
				 String tmp = getResources().getString(R.string.toast_comment_edit_maxText);
				 String toastStr=String.format(tmp,getResources().getString(R.string.comment_detail_intro),BigIndex);
				 showToast(toastStr);
			}
		}

		public void beforeTextChanged(CharSequence cs, int arg1, int arg2,int arg3) {
		}

		public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		}

	}
	
}
