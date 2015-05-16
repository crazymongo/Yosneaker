package com.yosneaker.client;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 编辑用户个人信息
 * 
 * @author chendd
 * 
 */
public class EditUserInfoActivity extends BaseActivity{

	private RelativeLayout rl_edit_portrait;
	private RelativeLayout rl_edit_nickname;
	private RelativeLayout rl_edit_gender;
	private RelativeLayout rl_edit_signature;
	private RelativeLayout rl_edit_height;
	private RelativeLayout rl_edit_weight;
	private RelativeLayout rl_edit_bounce;
	private RelativeLayout rl_edit_seat;
	private RelativeLayout rl_edit_play;
	
	private ImageView iv_portrait;
	private TextView tv_nickname;
	private TextView tv_gender;
	private TextView tv_signature;
	private TextView tv_height;
	private TextView tv_weight;
	private TextView tv_bounce;
	private TextView tv_seat;
	private TextView tv_play;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit_user_info);

		super.onCreate(savedInstanceState);
	}

	@Override
	public void initViews() {
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_ok);
		
		rl_edit_portrait = (RelativeLayout) findViewById(R.id.rl_edit_portrait);
		rl_edit_nickname = (RelativeLayout) findViewById(R.id.rl_edit_nickname);
		rl_edit_gender = (RelativeLayout) findViewById(R.id.rl_edit_gender);
		rl_edit_signature = (RelativeLayout) findViewById(R.id.rl_edit_signature);
		rl_edit_height = (RelativeLayout) findViewById(R.id.rl_edit_height);
		rl_edit_weight = (RelativeLayout) findViewById(R.id.rl_edit_weight);
		rl_edit_bounce = (RelativeLayout) findViewById(R.id.rl_edit_bounce);
		rl_edit_seat = (RelativeLayout) findViewById(R.id.rl_edit_seat);
		rl_edit_play = (RelativeLayout) findViewById(R.id.rl_edit_play);
		
		iv_portrait = (ImageView) findViewById(R.id.iv_portrait);
		tv_nickname = (TextView) findViewById(R.id.tv_nickname);
		tv_gender = (TextView) findViewById(R.id.tv_gender);
		tv_signature = (TextView) findViewById(R.id.tv_signature);
		tv_height = (TextView) findViewById(R.id.tv_height);
		tv_weight = (TextView) findViewById(R.id.tv_weight);
		tv_bounce = (TextView) findViewById(R.id.tv_bounce);
		tv_seat = (TextView) findViewById(R.id.tv_seat);
		tv_play = (TextView) findViewById(R.id.tv_play);
		
	}

	@Override
	public void addListnners() {
		getTextViewLeft().setOnClickListener(this);
		getTextViewRight1().setOnClickListener(this);
		rl_edit_portrait.setOnClickListener(this);
		rl_edit_nickname.setOnClickListener(this);
		rl_edit_gender.setOnClickListener(this);
		rl_edit_signature.setOnClickListener(this);
		rl_edit_height.setOnClickListener(this);
		rl_edit_weight.setOnClickListener(this);
		rl_edit_bounce.setOnClickListener(this);
		rl_edit_seat.setOnClickListener(this);
		rl_edit_play.setOnClickListener(this);
	}

	@Override
	public void fillDatas() {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_edit_portrait:
			
			break;
		case R.id.rl_edit_nickname:
			gotoExistActivity(EditUserNicknameActivity.class, new Bundle());
			break;
		case R.id.rl_edit_gender:
	
			break;
		case R.id.rl_edit_signature:
			gotoExistActivity(EditUserSignatrueActivity.class, new Bundle());
			break;
		case R.id.rl_edit_height:
	
			break;
		case R.id.rl_edit_weight:
	
			break;
		case R.id.rl_edit_bounce:
	
			break;
		case R.id.rl_edit_seat:
	
			break;
		case R.id.rl_edit_play:
			gotoExistActivity(EditUserPlayActivity.class, new Bundle());
			break;
		default:
			break;
		}
	}

}
