package com.yosneaker.client;

import android.os.Bundle;
import android.text.TextUtils;
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
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}else if (v == getTextViewRight1()) {
			String commentTitle = et_comment_title.getText().toString();
			if (TextUtils.isEmpty(commentTitle)) {
				et_comment_title.setError(getResources().getString(R.string.error_comment_title_no_null));
			}else {
//				YosneakerAppState.db.insertCommentTitle(commentTitle,0);
				Bundle bundle = new Bundle();
				bundle.putString("commentTitle", commentTitle);
				gotoExistActivity(EditCommentActivity.class, bundle);
				AddCommentTitleActivity.this.finish();
			}
			
		}
	}

}
