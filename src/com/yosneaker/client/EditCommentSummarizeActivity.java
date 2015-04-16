package com.yosneaker.client;

import com.yosneaker.client.model.CommentDraft;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 编辑测评总评
 * 
 * @author chendd
 *
 */
public class EditCommentSummarizeActivity extends BaseActivity{

	private EditText edit_text;
	private TextView text_view;
	private RatingBar rating_bar;
	private int BigIndex;
	
	private String sumText;
	private int sumStar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_edit_comment_summarize);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		BigIndex = Integer.parseInt(getResources().getString(R.string.comment_edit_summarize_maxText));
		
		edit_text = (EditText)findViewById(R.id.edit_text);
		text_view = (TextView)findViewById(R.id.text_view);
		rating_bar = (RatingBar)findViewById(R.id.rating_bar);
		
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_ok);
	}

	@Override
	public void addListnners() {	
		getTextViewLeft().setOnClickListener(this);	
		getTextViewRight1().setOnClickListener(this);
		edit_text.addTextChangedListener(new EditTextWatcher());
	}

	@Override
	public void fillDatas() {
		Intent intent = getIntent();
		CommentDraft commentDraft = (CommentDraft) intent.getExtras().getSerializable("CommentDraft");
		edit_text.setText(commentDraft.getComment_sum_content());
		rating_bar.setRating(commentDraft.getComment_sum_star());
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			customBackPressed();
		} else if (v == getTextViewRight1()) {
			sumText = edit_text.getText().toString();
			sumStar = (int) (rating_bar.getRating());
			if (sumStar == 0) {
				showToast(getResources().getString(
						R.string.error_comment_sumstar_no_null));
			} else {
				gotoEditComment(sumStar, sumText);
			}
		}
	}

	private class EditTextWatcher implements TextWatcher {

		@Override
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

		@Override
		public void beforeTextChanged(CharSequence cs, int arg1, int arg2,int arg3) {
		}

		@Override
		public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		}

	}
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)   {  
		if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
			customBackPressed();
			return false;   
		}
        return super.onKeyDown(keyCode, event);          
    }  
	
	
	public void customBackPressed() {
		sumText = edit_text.getText().toString();
		sumStar = (int) (rating_bar.getRating());
		if ((!TextUtils.isEmpty(sumText))) {
			Builder builder = new Builder(EditCommentSummarizeActivity.this);
            final String[] items = {getResources().getString(R.string.dialog_comment_save_sum),getResources().getString(R.string.dialog_comment_drop_sum) };
            builder.setItems(items, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    
                	if (which == 0) {
                		gotoEditComment(sumStar, sumText);
					} else if(which == 1){
						setResult(RESULT_CANCELED, new Intent());
						EditCommentSummarizeActivity.this.finish();
					}
                }

            }).show();
		}else {
			super.onBackPressed();
		}
	}
	
	public void gotoEditComment(int sumStar,String sumText) {
		Intent intent = new Intent();
		CommentDraft commentDraft = new CommentDraft();
		if (sumStar != 0) {
			if (sumStar != 0) {
				commentDraft.setComment_sum_star(sumStar);
			}
			if (!TextUtils.isEmpty(sumText)) {
				commentDraft.setComment_sum_content(sumText);
			}
			intent.putExtra("CommentDraft",commentDraft);
			setResult(RESULT_OK, intent);
		}else {
			setResult(RESULT_CANCELED, intent);
		}		
		EditCommentSummarizeActivity.this.finish();
	}
	
	
	
}
