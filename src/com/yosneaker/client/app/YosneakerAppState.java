package com.yosneaker.client.app;

import com.yosneaker.client.define.Constants;

import android.content.Context;
import android.util.Log;

/**
 * 单例模式，主要在启动的时候用，他初始化了一些对象
 */
public class YosneakerAppState {

	private static YosneakerAppState INSTANCE;
	private static Context mContext;
	private float mScreenDensity;//屏幕密度

	public static YosneakerAppState getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new YosneakerAppState();
		}
		return INSTANCE;
	}
	
	public static void setApplicationContext(Context context) {
		if (mContext != null) {
			Log.w(Constants.TAG,"setApplicationContext called twice!");
		}
		mContext = context.getApplicationContext();
	}
	
	private YosneakerAppState() {
		if (mContext == null) {
			throw new IllegalStateException("YosneakerAppState inited before app context set");
		}
		
		mScreenDensity = mContext.getResources().getDisplayMetrics().density;
	}
	
	public Context getContext() {
        return mContext;
    }
	
	public float getmScreenDensity() {
		return mScreenDensity;
	}
 	
}
