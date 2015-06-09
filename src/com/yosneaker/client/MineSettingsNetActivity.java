package com.yosneaker.client;

import com.yosneaker.client.util.SettingUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 发布测评标题
 * 
 * @author chendd
 *
 */
public class MineSettingsNetActivity extends BaseActivity implements OnCheckedChangeListener{

	private ToggleButton tb_net_bind_weibo;
	private ToggleButton tb_net_bind_qzone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_mine_settings_net);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_home2);
		
		tb_net_bind_weibo = (ToggleButton) findViewById(R.id.tb_net_bind_weibo);
		tb_net_bind_qzone = (ToggleButton) findViewById(R.id.tb_net_bind_qzone);
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);		
		getTextViewRight1().setOnClickListener(this);	
		
		tb_net_bind_weibo.setOnCheckedChangeListener(this);
		tb_net_bind_qzone.setOnCheckedChangeListener(this);
	
	}

	@Override
	public void fillDatas() {
		tb_net_bind_weibo.setChecked(SettingUtils.get(this, SettingUtils.settings_net_bind_weibo, false));
		tb_net_bind_qzone.setChecked(SettingUtils.get(this, SettingUtils.settings_net_bind_qzone, false));
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
		case R.id.tb_net_bind_weibo:
			SettingUtils.set(this, SettingUtils.settings_net_bind_weibo, isChecked);
			break;
		case R.id.tb_net_bind_qzone:
			SettingUtils.set(this, SettingUtils.settings_net_bind_qzone, isChecked);
			break;
		default:
			break;
		}
	}
}
