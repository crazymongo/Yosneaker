package com.yosneaker.client.define;

/**
 * 定义系统常量
 * 
 * @author chendd
 *
 */
public interface Constants {

	/** 服务器地址 */
	public static final String HTTP_BASE_URL = "http://yosneaker.com/upload/";  
	
	/** 网络超时(毫秒) */
	public static final int HTTP_TIME_OUT = 20000;
	
	/** 日志Tag */
	public static final String TAG = "Yosneaker";
	
	//requestCode 10000+
	public static final int COMMENT_TITLE_REQUEST_CODE = 10001;
	public static final int COMMENT_INTRO_REQUEST_CODE = 10002;
	public static final int COMMENT_ITEM_REQUEST_CODE = 10003;
	public static final int COMMENT_SUMMARIZE_REQUEST_CODE = 10004;
	
}
