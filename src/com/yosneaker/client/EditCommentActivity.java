package com.yosneaker.client;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yosneaker.client.define.Constants;
import com.yosneaker.client.model.CommentDraft;
import com.yosneaker.client.model.CommentItem;
import com.yosneaker.client.view.AssessStarView;
import com.yosneaker.client.view.CommentItemView;
import com.yosneaker.client.view.RoundImageView;

/**
 * 编辑测评
 * 
 * @author chendd
 *
 */
public class EditCommentActivity extends BaseActivity implements CommentItemView.Callbacks{
	
	private LinearLayout ll_edit_intro;
	private LinearLayout ll_edit_item;
	private LinearLayout ll_edit_summarize;
	private LinearLayout ll_edit_item_detail;
	
	private Button btn_save_draft;
	private Button btn_publish_draft;
	private Button btn_delete_draft;
	
	private ImageView iv_item_edit;
	
	// in_edit_main_img部分控件
	private ImageView iv_comment_bg;
	private RoundImageView riv_comment_user_icon;
	private TextView tv_comment_date;
	private TextView tv_comment_title;
	private ImageView iv_comment_edit;
		
	// in_edit_intro部分控件
	private TextView tv_add_intro;
	private TextView tv_intro_detail;
	private TextView tv_intro_brand;
	private TextView tv_intro_model;
	private LinearLayout ll_intro_detail;
	
	// in_edit_summarize部分控件
	private TextView tv_add_summarize;
	private TextView tv_summarize_detail;
	private AssessStarView asv_sum_assess;
	
	// ll_edit_item_detail部分控件
	
	private int itemsize;
	private boolean isEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_edit_comment);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		itemsize = 0;
		isEdit = true;
		
		setTitleBarText(null);
		showTextViewLeft(true);

		ll_edit_intro = (LinearLayout) findViewById(R.id.ll_edit_intro);
		ll_edit_item = (LinearLayout) findViewById(R.id.ll_edit_item);
		ll_edit_summarize = (LinearLayout) findViewById(R.id.ll_edit_summarize);
		ll_edit_item_detail = (LinearLayout)findViewById(R.id.ll_edit_item_detail);
		
		btn_save_draft = (Button) findViewById(R.id.btn_save_draft);
		btn_publish_draft = (Button) findViewById(R.id.btn_publish_draft);
		btn_delete_draft = (Button) findViewById(R.id.btn_delete_draft);
		
		iv_item_edit = (ImageView) findViewById(R.id.iv_item_edit);
		iv_item_edit.setBackgroundResource(R.drawable.item_edit);
		
		iv_comment_bg = (ImageView) findViewById(R.id.iv_comment_bg);
		riv_comment_user_icon = (RoundImageView) findViewById(R.id.riv_comment_user_icon);
		tv_comment_date = (TextView) findViewById(R.id.tv_comment_date);
		tv_comment_title = (TextView) findViewById(R.id.tv_comment_title);
		iv_comment_edit = (ImageView) findViewById(R.id.iv_comment_edit);
		
		tv_add_intro = (TextView) findViewById(R.id.tv_add_intro);
		tv_intro_detail = (TextView) findViewById(R.id.tv_intro_detail);
		tv_intro_brand = (TextView) findViewById(R.id.tv_intro_brand);
		tv_intro_model = (TextView) findViewById(R.id.tv_intro_model);
		ll_intro_detail = (LinearLayout) findViewById(R.id.ll_intro_detail);
		
		tv_add_summarize = (TextView) findViewById(R.id.tv_add_summarize);
		tv_summarize_detail = (TextView) findViewById(R.id.tv_summarize_detail);
		asv_sum_assess = (AssessStarView) findViewById(R.id.asv_sum_assess);
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);			
		ll_edit_intro.setOnClickListener(this);
		ll_edit_item.setOnClickListener(this);
		ll_edit_summarize.setOnClickListener(this);
		btn_save_draft.setOnClickListener(this);
		btn_publish_draft.setOnClickListener(this);
		btn_delete_draft.setOnClickListener(this);
		iv_comment_edit.setOnClickListener(this);
		iv_item_edit.setOnClickListener(this);
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
			customBackPressed();
		}else if (v == ll_edit_intro) {
			gotoExistActivityForResult(EditCommentIntroActivity.class, new Bundle(),Constants.COMMENT_INTRO_REQUEST);
		}else if (v == ll_edit_item) {
			gotoExistActivityForResult(EditCommentItemActivity.class, new Bundle(),Constants.COMMENT_ITEM_REQUEST);
		}else if (v == ll_edit_summarize) {
			gotoExistActivityForResult(EditCommentSummarizeActivity.class, new Bundle(),Constants.COMMENT_SUMMARIZE_REQUEST);
		}else if (v == iv_comment_edit) {
			gotoExistActivityForResult(AddCommentTitleActivity.class, new Bundle(),Constants.COMMENT_TITLE_REQUEST);
		}else if (v == btn_save_draft) {
			saveDraft();
		}else if (v == btn_publish_draft) {
			publishDraft();
		}else if (v == btn_delete_draft) {
			deleteDraft();
			showToast(getResources().getString(R.string.toast_draft_delete_success));
		}else if (v == iv_item_edit) {
			toogleDeleteTip();
		}
	}

	/**
	 * 删除测评草稿
	 */
	private void deleteDraft() {
		
		EditCommentActivity.this.finish();
	}

	/**
	 * 发布测评草稿
	 */
	private void publishDraft() {
		showToast(getResources().getString(R.string.toast_draft_publish_success));
	}


	/**
	 * 保存草稿
	 */
	private void saveDraft() {
		showToast(getResources().getString(R.string.toast_draft_save_success));
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
	
	private void customBackPressed() {
		Builder builder = new Builder(EditCommentActivity.this);
        final String[] items = {getResources().getString(R.string.dialog_comment_save_draft),getResources().getString(R.string.dialog_comment_drop_draft) };
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            	if (which == 0) {
					saveDraft();
				} else if(which == 1){
					deleteDraft();
				}
            }

        }).show();
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
					ll_intro_detail.setVisibility(View.GONE);
				} else {
					tv_add_intro.setText(getResources().getString(
							R.string.comment_edit_alter_intro));
					tv_intro_detail.setVisibility(View.VISIBLE);
					tv_intro_detail.setText(introText);
					ll_intro_detail.setVisibility(View.VISIBLE);
					tv_intro_brand.setText(brand);
					tv_intro_model.setText(modelText);
				}

				break;
			case Constants.COMMENT_ITEM_REQUEST:
//				ll_item_sun0.setVisibility(View.VISIBLE);
				commentDraft = (CommentDraft) data
						.getSerializableExtra("CommentDraft");
				ArrayList<CommentItem> commentItems = commentDraft.getComment_items();
				CommentItem commentItem = commentItems.get(0);
				ArrayList<String> imageUris = commentItem.getImageUris();
				int itemStar = commentItem.getComment_item_star();
				String itemTitleText = commentItem.getComment_item_title();
				String itemContentText = commentItem.getComment_item_content();
				CommentItemView cItemView = new CommentItemView(this);
				cItemView.setCallbacks(EditCommentActivity.this);
				cItemView.setItemOrder(++itemsize);
				cItemView.setItemName(itemTitleText);
				cItemView.setItemContent(itemContentText);
				cItemView.setItemAssess(itemStar);
				cItemView.setDeleteVisible(isEdit?View.GONE:View.VISIBLE);
				for (int i = 0; i < imageUris.size(); i++) {
					cItemView.addItemImage(imageUris.get(i));
					if (itemsize == 1 && i == 0) {
						setDefaultBg(imageUris.get(i));
					}
				}
			
				ll_edit_item_detail.addView(cItemView);
//				ll_edit_item_detail.removeViewAt(0);
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
					asv_sum_assess.setVisibility(View.GONE);
				} else {
					tv_add_summarize.setText(getResources().getString(
							R.string.comment_edit_alter_summarize));
					tv_summarize_detail.setVisibility(View.VISIBLE);
					tv_summarize_detail.setText(buildSumStr(sumStar, sumText));
					asv_sum_assess.setVisibility(View.VISIBLE);
					asv_sum_assess.setStarNumber(sumStar);
				}
				
				break;
			default:
				break;
			}
		}
	}


//	public String buildIntroStr(String brand,String modelText,String introText) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(getResources().getString(R.string.select_brand)).append(":")
//		.append(brand).append(";")
//		.append(getResources().getString(R.string.select_model)).append(":")
//		.append(modelText).append(";")
//		.append(getResources().getString(R.string.comment_edit_summarize_evaluate)).append(":")
//		.append(introText).append(";");
//		return sb.toString();
//	}
	
	public String buildSumStr(int sumStar,String sumText) {
		StringBuilder sb = new StringBuilder();
		sb.append(getResources().getString(R.string.comment_edit_summarize_evaluate)).append(":")
		.append(sumStar).append("星;")
		.append(getResources().getString(R.string.comment_edit_summarize_inpute)).append(":")
		.append(sumText).append(";");
		return sb.toString();
	}


	@Override
	public void setItemRemove(final int item_order) {
//		showToast(""+item_order);
		Builder builder = new Builder(EditCommentActivity.this);
        final String[] items = {getResources().getString(R.string.dialog_comment_delete_item),getResources().getString(R.string.dialog_comment_back_item) };
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            	if (which == 0) {
            		ll_edit_item_detail.removeViewAt(item_order-1);
            		itemsize--;
            		resetItemOrder(item_order);
				} else if(which == 1){
					
				}
            }

        }).show();
		
	}
	
	public void setDefaultBg(String imageUri) {
		Bitmap bmp;
		try {
			bmp = BitmapFactory.decodeStream(EditCommentActivity.this.getContentResolver().openInputStream(Uri.parse(imageUri)));
			if (bmp != null) {
				iv_comment_bg.setImageBitmap(bmp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void resetItemOrder(int item_order) {
		int size = ll_edit_item_detail.getChildCount();
		for (int i = 0; i < size; i++) {
			if (i>=item_order-1) {
				CommentItemView commentItemView = (CommentItemView) ll_edit_item_detail.getChildAt(i);
				commentItemView.setItemOrder(i+1);
			}
		}
	}
	
	public void toogleDeleteTip() {
		int visible = isEdit?View.VISIBLE:View.GONE;
		iv_item_edit.setBackgroundResource(isEdit?R.drawable.item_ok:R.drawable.item_edit);
		int size = ll_edit_item_detail.getChildCount();
		for (int i = 0; i < size; i++) {
			CommentItemView commentItemView = (CommentItemView) ll_edit_item_detail.getChildAt(i);
			commentItemView.setDeleteVisible(visible);
		}
		isEdit = !isEdit;
	}
	
}
