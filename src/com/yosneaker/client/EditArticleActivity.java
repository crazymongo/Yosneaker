package com.yosneaker.client;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yosneaker.client.app.YosneakerAppState;
import com.yosneaker.client.model.Article;
import com.yosneaker.client.model.ArticleItem;
import com.yosneaker.client.util.BitmapUtil;
import com.yosneaker.client.util.Constants;
import com.yosneaker.client.view.AssessStarView;
import com.yosneaker.client.view.CommentItemView;
import com.yosneaker.client.view.ProgressDialog;
import com.yosneaker.client.view.RoundImageView;

/**
 * 编辑测评
 * 
 * @author chendd
 *
 */
public class EditArticleActivity extends BaseActivity implements CommentItemView.Callbacks{
	
	private Article commentDraft;
	
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
	private LinearLayout ll_summarize_detail;
	
	// ll_edit_item_detail部分控件
	
	private ArrayList<CommentItemView> commentItemViews;//
	
	private ArrayList<Bitmap> bgBitmaps;//上面的背景
	private Bitmap defaultBitmap;
	private int itemsize;//测评项数目
	private boolean isEdit;//测评项是否处于编辑状态
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_edit_article);
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		itemsize = 0;
		isEdit = true;
		commentDraft = new Article();
		bgBitmaps = new ArrayList<Bitmap>();
		defaultBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.default_comment_bg);
		bgBitmaps.add(defaultBitmap);
		
		setTitleBarText(null);
		showTextViewLeft(true);

		commentItemViews = new ArrayList<CommentItemView>();
		
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
		ll_summarize_detail = (LinearLayout) findViewById(R.id.ll_summarize_detail);
		
		setDefaultBg();
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);			
		ll_edit_intro.setOnClickListener(this);
		ll_intro_detail.setOnClickListener(this);
		ll_edit_item.setOnClickListener(this);
		ll_edit_summarize.setOnClickListener(this);
		ll_summarize_detail.setOnClickListener(this);
		btn_save_draft.setOnClickListener(this);
		btn_publish_draft.setOnClickListener(this);
		btn_delete_draft.setOnClickListener(this);
		iv_comment_edit.setOnClickListener(this);
		iv_item_edit.setOnClickListener(this);
	}

	@Override
	public void fillDatas() {
		Intent intent = getIntent();
		Article tmpCommentDraft = (Article) intent.getSerializableExtra("CommentDraft");
		tv_comment_title.setText(tmpCommentDraft.getComment_title());
		int date = tmpCommentDraft.getComment_date();
		tv_comment_date.setText(date/10000+"-"+(date/100)%100+"-"+date%100);
		commentDraft.setComment_title(tmpCommentDraft.getComment_title());
		commentDraft.setComment_date(tmpCommentDraft.getComment_date());
		//Todo  设置iv_comment_bgr,iv_comment_user_icon
		
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			customBackPressed();
		}else if (v == ll_edit_intro||v == ll_intro_detail) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("CommentDraft",commentDraft);
			gotoExistActivityForResult(EditArticleIntroActivity.class, bundle,Constants.COMMENT_INTRO_REQUEST);
		}else if (v == ll_edit_item||commentItemViews.contains(v)) {
			Bundle bundle = new Bundle();
			int index = commentItemViews.indexOf(v);
			commentDraft.setComment_item_index(index);
			Log.d(Constants.TAG, "index:"+index);
			bundle.putSerializable("CommentDraft",commentDraft);
			gotoExistActivityForResult(EditArticleItemActivity.class, bundle,Constants.COMMENT_ITEM_REQUEST);
		}else if (v == ll_edit_summarize||v == ll_summarize_detail) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("CommentDraft",commentDraft);
			gotoExistActivityForResult(EditArticleSummarizeActivity.class, bundle,Constants.COMMENT_SUMMARIZE_REQUEST);
		}else if (v == iv_comment_edit) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("CommentDraft",commentDraft);
			gotoExistActivityForResult(AddArticleTitleActivity.class, bundle,Constants.COMMENT_TITLE_REQUEST);
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
		
		EditArticleActivity.this.finish();
	}

	/**
	 * 发布测评草稿
	 */
	private void publishDraft() {
		ProgressDialog dialog = ProgressDialog.createDialog(this);
		dialog.setMessage("发布测评中...");
		dialog.show();
//		showToast(getResources().getString(R.string.toast_draft_publish_success));
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
		Builder builder = new Builder(EditArticleActivity.this);
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
		Article tmpCommentDraft;
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case Constants.COMMENT_TITLE_REQUEST:
				tmpCommentDraft = (Article) data
				.getSerializableExtra("CommentDraft");
				tv_comment_title.setText(tmpCommentDraft.getComment_title());
				int date = tmpCommentDraft.getComment_date();
				tv_comment_date.setText(date/10000+"-"+(date/100)%100+"-"+date%100);
				// 保存数据到内存
				commentDraft.setComment_title(tmpCommentDraft.getComment_title());
				commentDraft.setComment_date(tmpCommentDraft.getComment_date());
				break;
			case Constants.COMMENT_INTRO_REQUEST:
				tmpCommentDraft = (Article) data
						.getSerializableExtra("CommentDraft");
				String brand = tmpCommentDraft.getComment_intro_brands();
				String modelText = tmpCommentDraft.getComment_intro_model();
				String introText = tmpCommentDraft.getComment_intro_assess();

				if (TextUtils.isEmpty(brand) && TextUtils.isEmpty(modelText)
						&& TextUtils.isEmpty(introText)) {
					ll_edit_intro.setVisibility(View.VISIBLE);
					ll_intro_detail.setVisibility(View.GONE);
				} else {
					ll_edit_intro.setVisibility(View.GONE);
					ll_intro_detail.setVisibility(View.VISIBLE);
					tv_intro_detail.setText(introText);
					tv_intro_brand.setText(brand);
					tv_intro_model.setText(modelText);
				}
				// 保存数据到内存
				commentDraft.setComment_intro_brands(brand);
				commentDraft.setComment_intro_model(modelText);
				commentDraft.setComment_intro_assess(introText);
				break;
			case Constants.COMMENT_ITEM_REQUEST:
				tmpCommentDraft = (Article) data
						.getSerializableExtra("CommentDraft");
				ArrayList<ArticleItem> commentItems = tmpCommentDraft.getComment_items();
				Log.d(Constants.TAG, "tmpCommentDraft.getComment_item_index():"+tmpCommentDraft.getComment_item_index());
				if (tmpCommentDraft.getComment_item_index() == -1) {
					for (ArticleItem commentItem : commentItems) {
						addCommentItem(commentItem);
					}
				}else {
					ArticleItem commentItem = commentItems.get(tmpCommentDraft.getComment_item_index());
					replaceCommentItem(commentItem,tmpCommentDraft.getComment_item_index());
				}
				
				isEdit = false;
				toogleEditTip();
				toogleDeleteTip();
				setDefaultBg();
				
				break;
			case Constants.COMMENT_SUMMARIZE_REQUEST:
				tmpCommentDraft = (Article) data
				.getSerializableExtra("CommentDraft");
				int sumStar = tmpCommentDraft.getComment_sum_star();
				String sumText = tmpCommentDraft.getComment_sum_content();
				if (TextUtils.isEmpty(sumText) && sumStar==0) {
					ll_summarize_detail.setVisibility(View.GONE);
					ll_edit_summarize.setVisibility(View.VISIBLE);
				} else {
					ll_summarize_detail.setVisibility(View.VISIBLE);
					ll_edit_summarize.setVisibility(View.GONE);
					tv_summarize_detail.setText(sumText);
					asv_sum_assess.setStarNumber(sumStar);
				}
				// 保存数据到内存
				commentDraft.setComment_sum_star(sumStar);
				commentDraft.setComment_sum_content(sumText);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 添加测评项
	 * @param commentItem
	 */
	public void addCommentItem(ArticleItem commentItem) {
		ArrayList<String> imageUris = commentItem.getItemImages();
		int itemStar = commentItem.getItemLevel();
		String itemTitleText = commentItem.getItemTitle();
		String itemContentText = commentItem.getItemContent();
		CommentItemView cItemView = new CommentItemView(this);
		cItemView.setCallbacks(EditArticleActivity.this);
		cItemView.setItemOrder(++itemsize);
		cItemView.setItemName(itemTitleText);
		cItemView.setItemContent(itemContentText);
		cItemView.setItemAssess(itemStar);
		cItemView.setDeleteVisible(isEdit?View.GONE:View.VISIBLE);	
		for (int i = 0; i < imageUris.size(); i++) {
			cItemView.addItemImage(imageUris.get(i));
			if (i==0) {
				bgBitmaps.add(BitmapUtil.getBitmapFromUri(EditArticleActivity.this, imageUris.get(i)));
			}					
		}
		if (imageUris.size() == 0) {
			bgBitmaps.add(defaultBitmap);
		}
		Log.d(Constants.TAG, "imageUris.size():"+imageUris.size());
		cItemView.setOnClickListener(this);
		commentItemViews.add(cItemView);
		// 保存数据到内存
		commentDraft.addComment_item(commentItem);
		ll_edit_item_detail.addView(cItemView);
	}
	
	/**
	 * 替换测评项
	 * @param commentItem
	 * @param comment_item_index
	 */
	private void replaceCommentItem(ArticleItem commentItem, int comment_item_index) {
		ArrayList<String> imageUris = commentItem.getItemImages();
		int itemStar = commentItem.getItemLevel();
		String itemTitleText = commentItem.getItemTitle();
		String itemContentText = commentItem.getItemContent();
		CommentItemView cItemView = commentItemViews.get(comment_item_index);
		cItemView.setCallbacks(EditArticleActivity.this);
		cItemView.setItemOrder(comment_item_index+1);
		
		cItemView.setItemName(itemTitleText);
		cItemView.setItemContent(itemContentText);
		cItemView.setItemAssess(itemStar);
		cItemView.setDeleteVisible(isEdit?View.GONE:View.VISIBLE);	
		cItemView.removeAllItemImage();
		for (int i = 0; i < imageUris.size(); i++) {
			cItemView.addItemImage(imageUris.get(i));
			if (i==0) {
				bgBitmaps.remove(comment_item_index+1);
				bgBitmaps.add(comment_item_index+1,BitmapUtil.getBitmapFromUri(EditArticleActivity.this, imageUris.get(i)));
			}					
		}
		if (imageUris.size() == 0) {
			bgBitmaps.remove(comment_item_index+1);
			bgBitmaps.add(comment_item_index+1,defaultBitmap);
		}
		cItemView.setOnClickListener(this);
		commentItemViews.set(comment_item_index, cItemView);
		// 保存数据到内存
		commentDraft.replaceComment_item(comment_item_index, commentItem);
		ll_edit_item_detail.removeViewAt(comment_item_index);
		ll_edit_item_detail.addView(cItemView, comment_item_index);
	}
	
	@Override
	public void setItemRemove(final int item_order) {
//		showToast(""+item_order);
		Builder builder = new Builder(EditArticleActivity.this);
        final String[] items = {getResources().getString(R.string.dialog_comment_delete_item),getResources().getString(R.string.dialog_comment_back_item) };
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            	if (which == 0) {
            		ll_edit_item_detail.removeViewAt(item_order-1);
            		if (bgBitmaps.size()>item_order) {
            			bgBitmaps.remove(item_order);
					}
            		commentDraft.removeComment_item(item_order-1);     
            		commentItemViews.remove(item_order-1);
            		itemsize--;
            		resetItemOrder(item_order);
            		toogleEditTip();
            		setDefaultBg();
				} else if(which == 1){
					
				}
            }

        }).show();
		
	}
	

//	@Override
//	public void setItemImageRemove(int item_order,int item_image_order) {
//		Bitmap bitmap = BitmapUtil.getBitmapFromUri(EditCommentActivity.this,commentDraft.getComment_items().get(item_order-1).getImageUris().get(item_image_order));
//		bgBitmaps.remove(item_order);
//		bgBitmaps.add(item_order,bitmap);
//		setDefaultBg();
//		commentDraft.getComment_items().get(item_order-1).getImageUris().remove(item_image_order);
//	}
	
	/**
	 * 设置默认背景
	 */
	public void setDefaultBg() {
		Bitmap bitmap = bgBitmaps.get(0);
		if (bgBitmaps.size() > 1) {//取第一张非系统默认图片
			for (int i = 0; i < bgBitmaps.size(); i++) {
				if (!defaultBitmap.equals(bgBitmaps.get(i))) {
					bitmap = bgBitmaps.get(i);
					break;
				}
			}
		}
		int width = YosneakerAppState.getInstance().mWidth;
		try {
			bitmap = BitmapUtil.getScaleBitmap(bitmap, width,Constants.BG_SCALE);
			if (bitmap != null) {
				iv_comment_bg.setImageBitmap(bitmap);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 当删除测评项时 重置序号
	 * @param item_order
	 */
	public void resetItemOrder(int item_order) {
		int size = ll_edit_item_detail.getChildCount();
		for (int i = 0; i < size; i++) {
			if (i>=item_order-1) {
				CommentItemView commentItemView = (CommentItemView) ll_edit_item_detail.getChildAt(i);
				commentItemView.setItemOrder(i+1);
			}
		}
	}
	
	/**
	 * 测评项编辑图标显示开关
	 */
	public void toogleEditTip() {
		if (itemsize == 0) {
			iv_item_edit.setVisibility(View.GONE);
		}else{
			iv_item_edit.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * 测评项删除图标显示开关
	 */
	public void toogleDeleteTip() {
		int visible = isEdit?View.VISIBLE:View.GONE;
		iv_item_edit.setBackgroundResource(isEdit?R.drawable.item_ok:R.drawable.item_edit);
		int size = ll_edit_item_detail.getChildCount();
		for (int i = 0; i < size; i++) {
			CommentItemView commentItemView = (CommentItemView) ll_edit_item_detail.getChildAt(i);
			commentItemView.setDeleteVisible(visible);
//			commentItemView.setImageDeleteVisible(visible);
		}
		isEdit = !isEdit;
	}
	
}
