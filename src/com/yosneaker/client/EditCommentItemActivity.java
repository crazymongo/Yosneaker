package com.yosneaker.client;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yosneaker.client.define.Constants;
import com.yosneaker.client.model.CommentDraft;
import com.yosneaker.client.model.CommentItem;
import com.yosneaker.client.util.PickerImageUtil;

/**
 * 编辑测评简介
 * 
 * @author chendd
 *
 */
public class EditCommentItemActivity extends BaseActivity{

	/** 网络，用于动态显示添加删除图片 */
	private GridView gv;
	/** 选取图片的工具 */
	private PickerImageUtil mPickerImageUtil;
	/** 适配器 */
	private AddPicGridViewAdapter adapter;
	/** 数据列表 */
	private List<Bitmap> viewList;
	/** 布局填充器 */
	private LayoutInflater inflater;
	/** 发送更多的布局，用于提供相册，照相机的操作选项。 */
	private LinearLayout sendmoreLyt;
	
	private ImageButton sendPic;
	private ImageButton sendCamera;
	
	private EditText et_item_intro;
	private TextView tv_item_intro;
	private EditText et_item_title;
	private RatingBar rb_item_star;
	private int BigIndex;
	
	private String itemTitleText;
	private String itemIntroText;
	private int itemStar;
	
	private Uri imageUri;
	private ArrayList<String> imageUris;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_edit_comment_item);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		mPickerImageUtil = new PickerImageUtil(this);
		BigIndex = Integer.parseInt(getResources().getString(R.string.comment_edit_intro_maxText));
		
		et_item_intro = (EditText)findViewById(R.id.et_item_intro);
		tv_item_intro = (TextView)findViewById(R.id.tv_item_intro);
		et_item_title = (EditText)findViewById(R.id.et_item_title);
		rb_item_star = (RatingBar)findViewById(R.id.rb_item_star);

		// 图库照相机BMP业务
		sendmoreLyt = (LinearLayout) findViewById(R.id.layout_sendmore);
		sendPic = (ImageButton) findViewById(R.id.sendPic);
		sendCamera = (ImageButton) findViewById(R.id.sendCamera);
		
		gv = (GridView) this.findViewById(R.id.gridView);
		viewList = new ArrayList<Bitmap>();
		imageUris = new ArrayList<String>();
		viewList.add(null);
//		imageUris.add(null);
		adapter = new AddPicGridViewAdapter();
		gv.setAdapter(adapter);
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_ok);
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);			
		getTextViewRight1().setOnClickListener(this);	
		sendPic.setOnClickListener(this);
		sendCamera.setOnClickListener(this);
		et_item_intro.addTextChangedListener(new EditTextWatcher());
	}

	@Override
	public void fillDatas() {
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			customBackPressed();
		}else if (v == getTextViewRight1()) {
			itemTitleText = et_item_title.getText().toString();
			itemIntroText = et_item_intro.getText().toString();
			itemStar = (int) (rb_item_star.getRating());
			if (TextUtils.isEmpty(itemTitleText)) {
				et_item_title.setError(getResources().getString(
						R.string.error_comment_item_title_no_null));
			}else if (itemStar == 0) {
				showToast(getResources().getString(
						R.string.error_comment_item_sumstar_no_null));
			}else if (TextUtils.isEmpty(itemIntroText)) {
				et_item_intro.setError(getResources().getString(
						R.string.error_comment_item_intro_no_null));
			}else {
				gotoEditComment(itemStar, itemTitleText, itemIntroText,imageUris);
			}
		}else if (v == sendCamera) {
			mPickerImageUtil.OpenCamera();
			sendmoreLyt.setVisibility(View.GONE);
		}else if (v == sendPic) {
			mPickerImageUtil.OpenGallery();
			sendmoreLyt.setVisibility(View.GONE);
		}
	}

	private void gotoEditComment(int itemStar, String itemTitleText,
			String itemIntroText,ArrayList<String> images) {
		Intent intent = new Intent();
		CommentDraft commentDraft = new CommentDraft();
		CommentItem commentItem = new CommentItem();
		itemTitleText = et_item_title.getText().toString();
		itemIntroText = et_item_intro.getText().toString();
		itemStar = (int) (rb_item_star.getRating());
		if (itemStar != 0||!TextUtils.isEmpty(itemTitleText)||!TextUtils.isEmpty(itemIntroText)) {
			if (itemStar != 0) {
				commentItem.setComment_item_star(itemStar);
			}
			if (!TextUtils.isEmpty(itemTitleText)) {
				commentItem.setComment_item_title(itemTitleText);
			}
			if (!TextUtils.isEmpty(itemIntroText)) {
				commentItem.setComment_item_content(itemIntroText);
			}
			commentItem.setImageUris(imageUris);
			commentDraft.addComment_item(commentItem);
			intent.putExtra("CommentDraft",commentDraft);
			setResult(RESULT_OK, intent);
		}else {
			setResult(RESULT_CANCELED, intent);
		}
		EditCommentItemActivity.this.finish();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case Constants.PHOTO_CAREMA_REQUEST:
			case Constants.PHOTO_GALLERY_REQUEST:
				imageUri = data.getData();
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, Constants.PHOTO_CROP_REQUEST);
				break;
			case Constants.PHOTO_CROP_REQUEST:
//				String picPath = mPickerImageUtil.getBitmapFilePath(requestCode,
//						resultCode, data);
//				Log.d(Constants.TAG, "picPath:"+picPath);
//				Bitmap bmp = mPickerImageUtil.getBitmapByOpt(picPath);
				Bitmap bmp;
				try {
					bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
					if (bmp != null) {
						viewList.add(0, bmp);
						imageUris.add(0,imageUri.toString());
						adapter.notifyDataSetChanged();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				break;
			default:
				break;
			}
		}
		
		
		
		// 测试get json请求
//		AsyncHttpClientUtil.get("resources/json", null, new AsyncHttpResponseHandler() {
//							
//				@Override
//				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//					String responseStr = new String(arg2);
//					Log.d(Constants.TAG, "get json success:"+responseStr);
//					showToast("get json success:"+responseStr);
//				}
//							
//				@Override
//				public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//					String responseStr = new String(arg2);
//					Log.d(Constants.TAG, "get json failure:"+responseStr);
//					showToast("get json failure:"+responseStr);
//				}
//			});
		
		// 测试post图片到服务器
//		RequestParams params = new RequestParams();  
//        params.put("image", BitmapUtil.bitmap2Base64Str(getApplicationContext(), bmp));
//		AsyncHttpClientUtil.post("resources/picture.json", params, new AsyncHttpResponseHandler() {
//			
//			@Override
//			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//				String responseStr = new String(arg2);
//				Log.d(Constants.TAG, "post image success:"+responseStr);
//				showToast("post image success:"+responseStr);
//			}
//			
//			@Override
//			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//				String responseStr = new String(arg2);
//				Log.d(Constants.TAG, "post image failure:"+responseStr);
//				showToast("post image failure:"+responseStr);
//			}
//		});
        
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public class AddPicGridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (viewList == null) {
				return 0;
			}
			return viewList.size();
		}

		@Override
		public Object getItem(int position) {
			return viewList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			inflater = LayoutInflater.from(EditCommentItemActivity.this);
			if (position == viewList.size() - 1) {
				View addView = inflater.inflate(
						R.layout.view_edit_comment_gv_item_add, null);
				addView.findViewById(R.id.add).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {								
								if (viewList.size() == 4) {
									showToast(getResources().getString(R.string.toast_picker_image_over_max));
									return;
								} else {
									if(sendmoreLyt.getVisibility() == View.VISIBLE) {
										sendmoreLyt.setVisibility(View.GONE);
									}else if(sendmoreLyt.getVisibility() == View.GONE){
										sendmoreLyt.setVisibility(View.VISIBLE);
									}
								}
							}
						});
				return addView;
			} else {
				View picView = inflater.inflate(
						R.layout.view_edit_comment_gv_item_pic, null);
				ImageButton picIBtn = (ImageButton) picView
						.findViewById(R.id.pic);
				picIBtn.setImageBitmap(viewList.get(position));
				picIBtn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						AlertDialog.Builder builder = new Builder(
								EditCommentItemActivity.this);
						builder.setTitle(getResources().getString(R.string.picker_image_see_detail));
						View view = inflater.inflate(
								R.layout.view_edit_comment_showmax_dialog,
								null);
						((ImageView) view.findViewById(R.id.bigPic))
								.setImageBitmap(viewList.get(position));
						builder.setView(view);
						builder.setNegativeButton(getResources().getString(R.string.picker_image_back), null);
						builder.show();
					}
				});
				picView.findViewById(R.id.delete).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								viewList.remove(position);
								imageUris.remove(position);
								adapter.notifyDataSetChanged();
							}
						});
				return picView;
			}
		}
	}
	
	public class EditTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable arg0) {
			 String edit = et_item_intro.getText().toString();
			 
			et_item_intro.setVisibility(View.VISIBLE);
			if (edit.length() <= BigIndex) {
				tv_item_intro.setText(""+(BigIndex - edit.length()));
			} else {
				 et_item_intro.setText(edit.substring(0,BigIndex));
				 et_item_intro.setSelection(edit.substring(0,BigIndex).length());
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
		itemTitleText = et_item_title.getText().toString();
		itemIntroText = et_item_intro.getText().toString();
		itemStar = (int) (rb_item_star.getRating());
		if (!TextUtils.isEmpty(itemTitleText)||!TextUtils.isEmpty(itemIntroText)) {
			Builder builder = new Builder(EditCommentItemActivity.this);
            final String[] items = {getResources().getString(R.string.dialog_comment_save_item),getResources().getString(R.string.dialog_comment_drop_item) };
            builder.setItems(items, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    
                	if (which == 0) {
                		gotoEditComment(itemStar, itemTitleText, itemIntroText, imageUris);
					} else if(which == 1){
						setResult(RESULT_CANCELED, new Intent());
						EditCommentItemActivity.this.finish();
					}
                }

            }).show();
		}else {
			super.onBackPressed();
		}
	}
	
}
