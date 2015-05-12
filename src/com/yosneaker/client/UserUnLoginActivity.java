package com.yosneaker.client;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 注册/登录界面
 * 
 * @author chendd
 *
 */
public class UserUnLoginActivity extends BaseActivity{
	
	private ImageView iv_qq_login;
	private ImageView iv_weixin_login;
	private ImageView iv_weibo_login;
	
	private TextView tv_mobile_login;
	private TextView tv_mobile_register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_user_unlogin);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		iv_qq_login = (ImageView) findViewById(R.id.iv_qq_login);
		iv_weixin_login = (ImageView) findViewById(R.id.iv_weixin_login);
		iv_weibo_login = (ImageView) findViewById(R.id.iv_weibo_login);
		
		tv_mobile_login = (TextView) findViewById(R.id.tv_mobile_login);
		tv_mobile_register = (TextView) findViewById(R.id.tv_mobile_register);
	}

	@Override
	public void addListnners() {	
		iv_qq_login.setOnClickListener(this);
		iv_weixin_login.setOnClickListener(this);
		iv_weibo_login.setOnClickListener(this);
		tv_mobile_login.setOnClickListener(this);
		tv_mobile_register.setOnClickListener(this);
	}

	@Override
	public void fillDatas() {

	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_qq_login:
			showToast("调用QQ sdk登录");
			break;
		case R.id.iv_weixin_login:
			showToast("调用微信 sdk登录");
			break;
		case R.id.iv_weibo_login:
			showToast("调用微博 sdk登录");
			break;
		case R.id.tv_mobile_login:
			gotoExistActivity(UserLoginActivity.class, new Bundle());
			break;
		case R.id.tv_mobile_register:
			gotoExistActivity(UserRegisterActivity.class, new Bundle());
			break;
		default:
			break;
		}
	}
	
}
