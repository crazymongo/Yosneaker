package com.yosneaker.client;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yosneaker.client.define.Constants;
import com.yosneaker.client.model.CommentDraft;
import com.yosneaker.client.view.RoundImageView;

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
	private LinearLayout ll_item_sun0;
	
	// in_edit_main_img部分控件
	private ImageView iv_comment_bg;
	private RoundImageView riv_comment_user_icon;
	private TextView tv_comment_date;
	private TextView tv_comment_title;
	private ImageView iv_comment_edit;
		
	// in_edit_intro部分控件
	private TextView tv_add_intro;
	private TextView tv_intro_detail;
	
	// in_edit_summarize部分控件
	private TextView tv_add_summarize;
	private TextView tv_summarize_detail;
	
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
		ll_item_sun0 = (LinearLayout)findViewById(R.id.ll_item_sun0);
		
		iv_comment_bg = (ImageView) findViewById(R.id.iv_comment_bg);
		riv_comment_user_icon = (RoundImageView) findViewById(R.id.riv_comment_user_icon);
		tv_comment_date = (TextView) findViewById(R.id.tv_comment_date);
		tv_comment_title = (TextView) findViewById(R.id.tv_comment_title);
		iv_comment_edit = (ImageView) findViewById(R.id.iv_comment_edit);
		
		tv_add_intro = (TextView) findViewById(R.id.tv_add_intro);
		tv_intro_detail = (TextView) findViewById(R.id.tv_intro_detail);
		
		tv_add_summarize = (TextView) findViewById(R.id.tv_add_summarize);
		tv_summarize_detail = (TextView) findViewById(R.id.tv_summarize_detail);
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);			
		ll_edit_intro.setOnClickListener(this);
		ll_edit_item.setOnClickListener(this);
		ll_edit_summarize.setOnClickListener(this);
		iv_comment_edit.setOnClickListener(this);
	}

	@Override
	public void fillDatas() {
		Intent intent = getIntent();
		CommentDraft commentDraft = (CommentDraft) intent.getSerializableExtra("CommentDraft");
		tv_comment_title.setText(commentDraft.getComment_title());
		int date = commentDraft.getComment_date();
		tv_comment_date.setText(date/10000+"-"+(date/100)%100+"-"+date%100);
		//Todo  设置iv_comment_bgr,iv_comment_user_icon
		
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}else if (v == ll_edit_intro) {
			gotoExistActivityForResult(EditCommentIntroActivity.class, new Bundle(),Constants.COMMENT_INTRO_REQUEST);
		}else if (v == ll_edit_item) {
			gotoExistActivityForResult(EditCommentItemActivity.class, new Bundle(),Constants.COMMENT_ITEM_REQUEST);
		}else if (v == ll_edit_summarize) {
			gotoExistActivityForResult(EditCommentSummarizeActivity.class, new Bundle(),Constants.COMMENT_SUMMARIZE_REQUEST);
		}else if (v == iv_comment_edit) {
			gotoExistActivityForResult(AddCommentTitleActivity.class, new Bundle(),Constants.COMMENT_TITLE_REQUEST);
		}
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			CommentDraft commentDraft;
			switch (requestCode) {
			case Constants.COMMENT_TITLE_REQUEST:
				commentDraft = (CommentDraft) data
				.getSerializableExtra("CommentDraft");
				tv_comment_title.setText(commentDraft.getComment_title());
				int date = commentDraft.getComment_date();
				tv_comment_date.setText(date/10000+"-"+(date/100)%100+"-"+date%100);
				break;
			case Constants.COMMENT_INTRO_REQUEST:
				commentDraft = (CommentDraft) data
						.getSerializableExtra("CommentDraft");
				String brand = commentDraft.getComment_intro_brands();
				String modelText = commentDraft.getComment_intro_model();
				String introText = commentDraft.getComment_intro_assess();

				if (TextUtils.isEmpty(brand) && TextUtils.isEmpty(modelText)
						&& TextUtils.isEmpty(introText)) {
					tv_add_intro.setText(getResources().getString(
							R.string.comment_edit_add_intro));
					tv_intro_detail.setVisibility(View.GONE);
				} else {
					tv_add_intro.setText(getResources().getString(
							R.string.comment_edit_alter_intro));
					tv_intro_detail.setVisibility(View.VISIBLE);
					tv_intro_detail.setText(buildIntroStr(brand, modelText,
							introText));
				}

				break;
			case Constants.COMMENT_ITEM_REQUEST:
				ll_item_sun0.setVisibility(View.VISIBLE);
				break;
			case Constants.COMMENT_SUMMARIZE_REQUEST:
				commentDraft = (CommentDraft) data
				.getSerializableExtra("CommentDraft");
				int sumStar = commentDraft.getComment_sum_star();
				String sumText = commentDraft.getComment_sum_content();
				if (TextUtils.isEmpty(sumText) && sumStar==0) {
					tv_add_summarize.setText(getResources().getString(
							R.string.comment_edit_add_summarize));
					tv_summarize_detail.setVisibility(View.GONE);
				} else {
					tv_add_summarize.setText(getResources().getString(
							R.string.comment_edit_alter_summarize));
					tv_summarize_detail.setVisibility(View.VISIBLE);
					tv_summarize_detail.setText(buildSumStr(sumStar, sumText));
				}
				
				break;
			default:
				break;
			}
		}
	}


	public String buildIntroStr(String brand,String modelText,String introText) {
		StringBuilder sb = new StringBuilder();
		sb.append(getResources().getString(R.string.select_brand)).append(":")
		.append(brand).append(";")
		.append(getResources().getString(R.string.select_model)).append(":")
		.append(modelText).append(";")
		.append(getResources().getString(R.string.comment_edit_summarize_evaluate)).append(":")
		.append(introText).append(";");
		return sb.toString();
	}
	
	public String buildSumStr(int sumStar,String sumText) {
		StringBuilder sb = new StringBuilder();
		sb.append(getResources().getString(R.string.comment_edit_summarize_evaluate)).append(":")
		.append(sumStar).append("星;")
		.append(getResources().getString(R.string.comment_edit_summarize_inpute)).append(":")
		.append(sumText).append(";");
		return sb.toString();
	}
	
}
