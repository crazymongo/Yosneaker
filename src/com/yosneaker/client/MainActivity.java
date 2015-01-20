package com.yosneaker.client;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yosneaker.client.fragment.AddFragment;
import com.yosneaker.client.fragment.CommentFragment;
import com.yosneaker.client.fragment.IdentifyFragment;
import com.yosneaker.client.fragment.MessageFragment;
import com.yosneaker.client.fragment.MineFragment;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 * 
 * @author chendd
 */
public class MainActivity extends BaseActivity implements OnClickListener {

	/**
	 * 用于展示消息的Fragment
	 */
	private CommentFragment messageFragment;

	/**
	 * 用于展示联系人的Fragment
	 */
	private IdentifyFragment contactsFragment;

	/**
	 * 用于展示动态的Fragment
	 */
	private MessageFragment newsFragment;

	/**
	 * 用于展示设置的Fragment
	 */
	private MineFragment settingFragment;
	
	/**
	 * 用于展示添加的Fragment
	 */
	private AddFragment addFragment;

	/**
	 * 消息界面布局
	 */
	private View messageLayout;

	/**
	 * 联系人界面布局
	 */
	private View contactsLayout;

	/**
	 * 添加界面布局
	 */
	private View addLayout;
	
	/**
	 * 动态界面布局
	 */
	private View newsLayout;

	/**
	 * 设置界面布局
	 */
	private View settingLayout;

	/**
	 * 在Tab布局上显示消息图标的控件
	 */
	private ImageView messageImage;

	/**
	 * 在Tab布局上显示联系人图标的控件
	 */
	private ImageView contactsImage;

	/**
	 * 在Tab布局上显示动态图标的控件
	 */
	private ImageView newsImage;

	/**
	 * 在Tab布局上显示设置图标的控件
	 */
	private ImageView settingImage;

	/**
	 * 在Tab布局上显示消息标题的控件
	 */
	private TextView messageText;

	/**
	 * 在Tab布局上显示联系人标题的控件
	 */
	private TextView contactsText;

	/**
	 * 在Tab布局上显示动态标题的控件
	 */
	private TextView newsText;

	/**
	 * 在Tab布局上显示设置标题的控件
	 */
	private TextView settingText;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	private ColorStateList tabTextSelectedColor;
	private ColorStateList tabTextNormalColor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化布局元素
		initViews();
		fragmentManager = getSupportFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		
		tabTextSelectedColor = getResources().getColorStateList(R.drawable.tab_text_selected);
		tabTextNormalColor = getResources().getColorStateList(R.drawable.tab_text_normal);
		
		messageLayout = findViewById(R.id.comment_layout);
		contactsLayout = findViewById(R.id.identify_layout);
		addLayout = findViewById(R.id.add_layout);
		newsLayout = findViewById(R.id.message_layout);
		settingLayout = findViewById(R.id.mine_layout);
		messageImage = (ImageView) findViewById(R.id.comment_image);
		contactsImage = (ImageView) findViewById(R.id.identify_image);
		newsImage = (ImageView) findViewById(R.id.message_image);
		settingImage = (ImageView) findViewById(R.id.mine_image);
		messageText = (TextView) findViewById(R.id.comment_text);
		contactsText = (TextView) findViewById(R.id.identify_text);
		newsText = (TextView) findViewById(R.id.message_text);
		settingText = (TextView) findViewById(R.id.mine_text);
		messageLayout.setOnClickListener(this);
		contactsLayout.setOnClickListener(this);
		newsLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);
		addLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.comment_layout:
			// 当点击了消息tab时，选中第1个tab
			setTabSelection(0);
			break;
		case R.id.identify_layout:
			// 当点击了联系人tab时，选中第2个tab
			setTabSelection(1);
			break;
		case R.id.add_layout:
			// 当点击了联系人tab时，选中第3个tab
			setTabSelection(2);
			break;
		case R.id.message_layout:
			// 当点击了动态tab时，选中第4个tab
			setTabSelection(3);
			break;
		case R.id.mine_layout:
			// 当点击了设置tab时，选中第5个tab
			setTabSelection(4);
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
			setTitleBarText(getResources().getString(R.string.tab_comment));
			// 当点击了消息tab时，改变控件的图片和文字颜色
			messageImage.setImageResource(R.drawable.tab_comment_selected);
			messageText.setTextColor(R.drawable.tab_text_normal);
			if (messageFragment == null) {
				// 如果MessageFragment为空，则创建一个并添加到界面上
				messageFragment = new CommentFragment();
				transaction.add(R.id.content, messageFragment);
			} else {
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(messageFragment);
			}
			break;
		case 1:
			setTitleBarText(getResources().getString(R.string.tab_identify));
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			contactsImage.setImageResource(R.drawable.tab_identify_selected);
			contactsText.setTextColor(tabTextSelectedColor);
			if (contactsFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				contactsFragment = new IdentifyFragment();
				transaction.add(R.id.content, contactsFragment);
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(contactsFragment);
			}
			break;
		case 2:
			setTitleBarText(getResources().getString(R.string.tab_add));
			// 当点击了动态tab时，改变控件的图片和文字颜色
//			newsImage.setImageResource(R.drawable.tab_message_selected);
//			newsText.setTextColor(tabTextSelectedColor);
			if (addFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				addFragment = new AddFragment();
				transaction.add(R.id.content, addFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(addFragment);
			}
			break;
		case 3:
			setTitleBarText(getResources().getString(R.string.tab_message));
			// 当点击了动态tab时，改变控件的图片和文字颜色
			newsImage.setImageResource(R.drawable.tab_message_selected);
			newsText.setTextColor(tabTextSelectedColor);
			if (newsFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				newsFragment = new MessageFragment();
				transaction.add(R.id.content, newsFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(newsFragment);
			}
			break;
		case 4:
		default:
			setTitleBarText(getResources().getString(R.string.tab_mine));
			// 当点击了设置tab时，改变控件的图片和文字颜色
			settingImage.setImageResource(R.drawable.tab_mine_selected);
			settingText.setTextColor(tabTextSelectedColor);
			if (settingFragment == null) {
				// 如果SettingFragment为空，则创建一个并添加到界面上
				settingFragment = new MineFragment();
				transaction.add(R.id.content, settingFragment);
			} else {
				// 如果SettingFragment不为空，则直接将它显示出来
				transaction.show(settingFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		messageImage.setImageResource(R.drawable.tab_comment_normal);
		messageText.setTextColor(tabTextNormalColor);
		contactsImage.setImageResource(R.drawable.tab_identify_normal);
		contactsText.setTextColor(tabTextNormalColor);
		newsImage.setImageResource(R.drawable.tab_message_normal);
		newsText.setTextColor(tabTextNormalColor);
		settingImage.setImageResource(R.drawable.tab_mine_normal);
		settingText.setTextColor(tabTextNormalColor);
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (contactsFragment != null) {
			transaction.hide(contactsFragment);
		}
		if (addFragment != null) {
			transaction.hide(addFragment);
		}
		if (newsFragment != null) {
			transaction.hide(newsFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}
}
