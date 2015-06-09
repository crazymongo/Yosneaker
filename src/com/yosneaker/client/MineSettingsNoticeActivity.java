package com.yosneaker.client;

import com.yosneaker.client.util.SettingUtils;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

/**
 * 消息推送设置
 * 
 * @author chendd
 *
 */
public class MineSettingsNoticeActivity extends BaseActivity implements OnCheckedChangeListener{

	private ToggleButton tb_notice_handpick;
	private ToggleButton tb_notice_collect;
	private ToggleButton tb_notice_wish;
	private ToggleButton tb_notice_comment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_mine_settings_notice);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_home2);
		
		tb_notice_handpick = (ToggleButton) findViewById(R.id.tb_notice_handpick);
		tb_notice_collect = (ToggleButton) findViewById(R.id.tb_notice_collect);
		tb_notice_wish = (ToggleButton) findViewById(R.id.tb_notice_wish);
		tb_notice_comment = (ToggleButton) findViewById(R.id.tb_notice_comment);
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);		
		getTextViewRight1().setOnClickListener(this);		
	
		tb_notice_handpick.setOnCheckedChangeListener(this);
		tb_notice_collect.setOnCheckedChangeListener(this);
		tb_notice_wish.setOnCheckedChangeListener(this);
		tb_notice_comment.setOnCheckedChangeListener(this);
		
	}

	@Override
	public void fillDatas() {
		tb_notice_handpick.setChecked(SettingUtils.get(this, SettingUtils.settings_notice_handpick, false));
		tb_notice_collect.setChecked(SettingUtils.get(this, SettingUtils.settings_notice_collect, false));
		tb_notice_wish.setChecked(SettingUtils.get(this, SettingUtils.settings_notice_wish, false));
		tb_notice_comment.setChecked(SettingUtils.get(this, SettingUtils.settings_notice_comment, false));
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mTextViewLeft:
			finish();
			break;
		case R.id.mTextviewRight1:
			gotoHome();
			break;
		default:
			break;
		}
	}


	@Override
	public void onCheckedChanged(CompoundButton v, boolean isChecked) {
		switch (v.getId()) {
		case R.id.tb_notice_handpick:
			SettingUtils.set(this, SettingUtils.settings_notice_handpick, isChecked);
			break;
		case R.id.tb_notice_collect:
			SettingUtils.set(this, SettingUtils.settings_notice_collect, isChecked);
			break;
		case R.id.tb_notice_wish:
			SettingUtils.set(this, SettingUtils.settings_notice_wish, isChecked);
			break;
		case R.id.tb_notice_comment:
			SettingUtils.set(this, SettingUtils.settings_notice_comment, isChecked);
			break;
		default:
			break;
		}
	}
	
}
