package com.yosneaker.client;

import java.util.Arrays;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yosneaker.client.util.Constants;
import com.yosneaker.client.view.WheelView;

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
		View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
		switch (v.getId()) {
		case R.id.rl_edit_portrait:
			
			break;
		case R.id.rl_edit_nickname:
			gotoExistActivity(EditUserNicknameActivity.class, new Bundle());
			break;
		case R.id.rl_edit_gender:
            wv.setOffset(1);
            wv.setSeletion(0);
            String[] genders = getResources().getStringArray(R.array.user_info_genders);
            wv.setItems(Arrays.asList(genders));
            
            wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, String item) {
                    Log.d(Constants.TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                }
            });

            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.user_gender))
                    .setView(outerView)
                    .setPositiveButton(getResources().getString(R.string.user_ok), null)
                    .setNegativeButton(getResources().getString(R.string.user_cancle), null)
                    .show();
			break;
		case R.id.rl_edit_signature:
			gotoExistActivity(EditUserSignatrueActivity.class, new Bundle());
			break;
		case R.id.rl_edit_height:
			wv.setOffset(1);
            wv.setSeletion(20);
            String[] heights = new String[50];
            for (int i = 0; i < heights.length; i++) {
            	heights[i] = 150+i+"cm";
			}
            wv.setItems(Arrays.asList(heights));
            
            wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, String item) {
                    Log.d(Constants.TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                }
            });

            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.user_personal_seat))
                    .setView(outerView)
                    .setPositiveButton(getResources().getString(R.string.user_ok), null)
                    .setNegativeButton(getResources().getString(R.string.user_cancle), null)
                    .show();
			break;
		case R.id.rl_edit_weight:
			wv.setOffset(1);
            wv.setSeletion(20);
            String[] weights = new String[50];
            for (int i = 0; i < weights.length; i++) {
            	weights[i] = 35+i+"kg";
			}
            wv.setItems(Arrays.asList(weights));
            
            wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, String item) {
                    Log.d(Constants.TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                }
            });

            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.user_personal_seat))
                    .setView(outerView)
                    .setPositiveButton(getResources().getString(R.string.user_ok), null)
                    .setNegativeButton(getResources().getString(R.string.user_cancle), null)
                    .show();
			break;
		case R.id.rl_edit_bounce:
			wv.setOffset(1);
            wv.setSeletion(20);
            String[] bounces = new String[50];
            for (int i = 0; i < bounces.length; i++) {
            	bounces[i] = 20+i+"cm";
			}
            wv.setItems(Arrays.asList(bounces));
            
            wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, String item) {
                    Log.d(Constants.TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                }
            });

            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.user_personal_seat))
                    .setView(outerView)
                    .setPositiveButton(getResources().getString(R.string.user_ok), null)
                    .setNegativeButton(getResources().getString(R.string.user_cancle), null)
                    .show();
			break;
		case R.id.rl_edit_seat:
			wv.setOffset(1);
            wv.setSeletion(2);
            String[] seats = getResources().getStringArray(R.array.user_info_seats);
            wv.setItems(Arrays.asList(seats));
            
            wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, String item) {
                    Log.d(Constants.TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                }
            });

            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.user_personal_seat))
                    .setView(outerView)
                    .setPositiveButton(getResources().getString(R.string.user_ok), null)
                    .setNegativeButton(getResources().getString(R.string.user_cancle), null)
                    .show();
			break;
		case R.id.rl_edit_play:
			gotoExistActivity(EditUserPlayActivity.class, new Bundle());
			break;
		default:
			break;
		}
	}

}
