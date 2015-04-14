package com.yosneaker.client;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import com.yosneaker.client.fragment.CommentFragment;
import com.yosneaker.client.fragment.MineFragment;

/**
 * 
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 * 
 * @author chendd
 * 
 */
public class MainActivity extends BaseActivity implements OnClickListener{

	/** 用于展示评测的Fragment */
	private CommentFragment mCommentFragment;

	/** 用于展示鉴定的Fragment */
//	private IdentifyFragment mIdentifyFragment;

	/** 用于展示消息的Fragment */
//	private MessageFragment mMessageFragment;

	/** 用于展示"我的"的Fragment */
	private MineFragment mMineFragment;
	
	/** 用于展示"发布"的Fragment */
//	private AddFragment mAddFragment;

	/** 评测界面布局 */
	private View commentView;

	/** 鉴定界面布局 */
//	private View identifyView;

	/** 发布界面布局 */
	private View addView;
	
	/** 消息界面布局 */
//	private View mssageView;

	/** "我的"界面布局 */
	private View mineView;

	/** 在Tab布局上显示评测图标的控件 */
	private ImageView commentImage;

	/** 在Tab布局上显示鉴定图标的控件 */
//	private ImageView identifyImage;

	/** 在Tab布局上显示消息图标的控件 */
//	private ImageView messageImage;

	/** 在Tab布局上显示"我的"图标的控件 */
	private ImageView mineImage;

	/** 在Tab布局上显示评测标题的控件 */
//	private TextView commentText;

	/** 在Tab布局上显示鉴定标题的控件 */
//	private TextView identifyText;

	/** 在Tab布局上显示消息标题的控件 */
//	private TextView messageText;

	/** 在Tab布局上显示"我的"标题的控件 */
//	private TextView mineText;

	/** 用于对Fragment进行管理 */
	private FragmentManager fragmentManager;

	private ColorStateList tabTextSelectedColor;
	private ColorStateList tabTextNormalColor;
	
	private long firstTime = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_main);
		
		super.onCreate(savedInstanceState);			
	}


	@Override
	public void initViews() {
		
		fragmentManager = getSupportFragmentManager();
		
		tabTextSelectedColor = getResources().getColorStateList(R.color.tab_text_selected);
		tabTextNormalColor = getResources().getColorStateList(R.color.tab_text_normal);
		
		commentView = findViewById(R.id.comment_layout);
//		identifyView = findViewById(R.id.identify_layout);
		addView = findViewById(R.id.add_layout);
//		mssageView = findViewById(R.id.message_layout);
		mineView = findViewById(R.id.mine_layout);
		commentImage = (ImageView) findViewById(R.id.comment_image);
//		identifyImage = (ImageView) findViewById(R.id.identify_image);
//		messageImage = (ImageView) findViewById(R.id.message_image);
		mineImage = (ImageView) findViewById(R.id.mine_image);
//		commentText = (TextView) findViewById(R.id.comment_text);
//		identifyText = (TextView) findViewById(R.id.identify_text);
//		messageText = (TextView) findViewById(R.id.message_text);
//		mineText = (TextView) findViewById(R.id.mine_text);

	}

	@Override
	public void addListnners() {
		
		commentView.setOnClickListener(this);
//		identifyView.setOnClickListener(this);
//		mssageView.setOnClickListener(this);
		mineView.setOnClickListener(this);
		addView.setOnClickListener(this);
		
	}

	@Override
	public void fillDatas() {
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.comment_layout:
			setTabSelection(0);
			break;
//		case R.id.identify_layout:
//			setTabSelection(1);
//			break;
		case R.id.add_layout:
			
//			Builder builder = new Builder(MainActivity.this);
//			builder.setTitle(getResources().getString(R.string.dialog_add_what));
//            final String[] items = {getResources().getString(R.string.dialog_add_comment),getResources().getString(R.string.dialog_add_identify)};
//            builder.setItems(items, new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    
//                	if (which == 0) {
						Bundle bundle = new Bundle();
						bundle.putInt("action", 1);
                		MainActivity.this.gotoExistActivity(AddCommentTitleActivity.class, bundle);
//					} else if(which == 1){
//
//					}
//                }
//
//            }).show();
			break;
//		case R.id.message_layout:
//			setTabSelection(2);
//			break;
		case R.id.mine_layout:
			setTabSelection(3);
			break;
		default:
			break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 *            每个tab页对应的下标。
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			//设置导航栏标题
//			setTitleBarText(getResources().getString(R.string.tab_comment));
			// 当点击了消息tab时，改变控件的图片和文字颜色
			commentImage.setImageResource(R.drawable.tab_comment_selected);
//			commentText.setTextColor(tabTextSelectedColor);
			if (mCommentFragment == null) {
				// 如果mCommentFragment为空，则创建一个并添加到界面上
				mCommentFragment = new CommentFragment();
				transaction.add(R.id.content, mCommentFragment);
			} else {
				// 如果mCommentFragment不为空，则直接将它显示出来
				transaction.show(mCommentFragment);
			}
			break;
//		case 1:
//			setTitleBarText(getResources().getString(R.string.tab_identify));
//			identifyImage.setImageResource(R.drawable.tab_identify_selected);
//			identifyText.setTextColor(tabTextSelectedColor);
//			if (mIdentifyFragment == null) {
//				mIdentifyFragment = new IdentifyFragment();
//				transaction.add(R.id.content, mIdentifyFragment);
//			} else {
//				transaction.show(mIdentifyFragment);
//			}
//			break;
//		case 2:
//			setTitleBarText(getResources().getString(R.string.tab_message));
//			messageImage.setImageResource(R.drawable.tab_message_selected);
//			messageText.setTextColor(tabTextSelectedColor);
//			if (mMessageFragment == null) {
//				mMessageFragment = new MessageFragment();
//				transaction.add(R.id.content, mMessageFragment);
//			} else {
//				transaction.show(mMessageFragment);
//			}
//			break;
		case 3:
		default:
//			setTitleBarText(getResources().getString(R.string.tab_mine));
			// 当点击了设置tab时，改变控件的图片和文字颜色
			mineImage.setImageResource(R.drawable.tab_mine_selected);
//			mineText.setTextColor(tabTextSelectedColor);
			if (mMineFragment == null) {
				// 如果SettingFragment为空，则创建一个并添加到界面上
				mMineFragment = new MineFragment();
				transaction.add(R.id.content, mMineFragment);
			} else {
				// 如果SettingFragment不为空，则直接将它显示出来
				transaction.show(mMineFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		commentImage.setImageResource(R.drawable.tab_comment_normal);
//		commentText.setTextColor(tabTextNormalColor);
//		identifyImage.setImageResource(R.drawable.tab_identify_normal);
//		identifyText.setTextColor(tabTextNormalColor);
//		messageImage.setImageResource(R.drawable.tab_message_normal);
//		messageText.setTextColor(tabTextNormalColor);
		mineImage.setImageResource(R.drawable.tab_mine_normal);
//		mineText.setTextColor(tabTextNormalColor);
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mCommentFragment != null) {
			transaction.hide(mCommentFragment);
		}
//		if (mIdentifyFragment != null) {
//			transaction.hide(mIdentifyFragment);
//		}
//		if (mAddFragment != null) {
//			transaction.hide(mAddFragment);
//		}
//		if (mMessageFragment != null) {
//			transaction.hide(mMessageFragment);
//		}
		if (mMineFragment != null) {
			transaction.hide(mMineFragment);
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			long secondTime = System.currentTimeMillis();
			if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
				showToast(getResources().getString(
						R.string.toast_twice_press_exict));
				firstTime = secondTime;// 更新firstTime
				return true;
			} else { // 两次按键小于2秒时，退出应用
				System.exit(0);
			}
			break;
		}
		return super.onKeyUp(keyCode, event);
	}

}
