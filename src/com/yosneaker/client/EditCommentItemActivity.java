package com.yosneaker.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yosneaker.client.define.Constants;
import com.yosneaker.client.util.AsyncHttpClientUtil;
import com.yosneaker.client.util.BitmapUtil;
import com.yosneaker.client.util.PickerImageUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
	
	private EditText edit_text;
	private TextView text_view;
	private int BigIndex;
	
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
		
		edit_text = (EditText)findViewById(R.id.edit_text);
		text_view = (TextView)findViewById(R.id.text_view);
		edit_text.addTextChangedListener(new EditTextWatcher());
		
		// 图库照相机BMP业务
		sendmoreLyt = (LinearLayout) findViewById(R.id.layout_sendmore);
		sendPic = (ImageButton) findViewById(R.id.sendPic);
		sendCamera = (ImageButton) findViewById(R.id.sendCamera);
		
		gv = (GridView) this.findViewById(R.id.gridView);
		viewList = new ArrayList<Bitmap>();
		viewList.add(null);
		adapter = new AddPicGridViewAdapter();
		gv.setAdapter(adapter);
		
		setTitleBarText(null);
		showTextViewLeft(true);
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);			
		sendPic.setOnClickListener(this);
		sendCamera.setOnClickListener(this);
		
	}

	@Override
	public void fillDatas() {
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}else if (v == sendCamera) {
			mPickerImageUtil.OpenCamera();
			sendmoreLyt.setVisibility(View.GONE);
		}else if (v == sendPic) {
			mPickerImageUtil.OpenGallery();
			sendmoreLyt.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String picPath = mPickerImageUtil.getBitmapFilePath(requestCode,
				resultCode, data);
		Log.d(Constants.TAG, "picPath:"+picPath);
		Bitmap bmp = mPickerImageUtil.getBitmapByOpt(picPath);
		if (bmp != null) {
			viewList.add(0, bmp);
			adapter.notifyDataSetChanged();
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
		RequestParams params = new RequestParams();  
        params.put("image", BitmapUtil.bitmap2Base64Str(getApplicationContext(), bmp));
		AsyncHttpClientUtil.post("resources/picture.json", params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				String responseStr = new String(arg2);
				Log.d(Constants.TAG, "post image success:"+responseStr);
				showToast("post image success:"+responseStr);
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				String responseStr = new String(arg2);
				Log.d(Constants.TAG, "post image failure:"+responseStr);
				showToast("post image failure:"+responseStr);
			}
		});
        
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
	
}
