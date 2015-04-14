package com.yosneaker.client.util;

/**
 * 定义系统常量
 * 
 * @author chendd
 *
 */
public interface Constants {

	/** 服务器地址 */
	public static final String HTTP_BASE_URL = "http://yosneaker.com/";  
	
	/** 网络超时(毫秒) */
	public static final int HTTP_TIME_OUT = 20000;
	
	/** 日志Tag */
	public static final String TAG = "Yosneaker";
	
	//requestCode 10000+
	public static final int COMMENT_TITLE_REQUEST = 10001;
	public static final int COMMENT_INTRO_REQUEST = 10002;
	public static final int COMMENT_ITEM_REQUEST = 10003;
	public static final int COMMENT_SUMMARIZE_REQUEST = 10004;
	public static final int PHOTO_CROP_REQUEST = 10005;
	public static final int PHOTO_CAREMA_REQUEST = 10006;// 拍照
	public static final int PHOTO_GALLERY_REQUEST = 10007;// 从相册中选择
	
}
