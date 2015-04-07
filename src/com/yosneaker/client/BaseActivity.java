package com.yosneaker.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 自定义所有Activity基类,封装常用方法
 * 
 * @author chendd
 * 
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initViews();
		addListnners() ;
		fillDatas();

	}
	
	/** 初始化所需要的控件 */
	public abstract void initViews();

	/** 添加所需要的监听器 */
	public abstract void addListnners();
	
	/** 添加所需要的数据 */
	public abstract void fillDatas();
	

	/**
	 * 从当前activity跳转到目标activity,
	 * 如果目标activity曾经打开过,就重新展现,
	 * 如果从来没打开过,就新建一个打开
	 * 
	 * @param cls bundle
	 */
	public void gotoExistActivity(Class<?> cls, Bundle bundle) {
		Intent intent;
		intent = new Intent(this, cls);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * 从当前activity跳转到目标activity,
	 * 如果目标activity曾经打开过,就重新展现,
	 * 如果从来没打开过,就新建一个打开,之后返回数据给上个activity
	 * 
	 * @param cls bundle requestCode
	 */
	public void gotoExistActivityForResult(Class<?> cls, Bundle bundle,int requestCode) {
		Intent intent;
		intent = new Intent(this, cls);
		intent.putExtras(bundle);
		startActivityForResult(intent, requestCode);
	}
		
	/**
	 * toast弹出消息
	 * @param msg
	 */
	public void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
    
    /** 
     * 得到模板上导航栏的左侧按钮，一般为[返回] 
     * @return 成功则返回TextView对象，失败则返回null。 
     */ 
    public TextView getTextViewLeft() { 
        return (TextView) findViewById(R.id.mTextViewLeft); 
    } 
    
    /** 
     * 得到模板上导航栏的靠右第一个按钮
     * @return 成功则返回TextView对象，失败则返回null。 
     */ 
    public TextView getTextViewRight1() { 
        return (TextView) findViewById(R.id.mTextviewRight1); 
    } 
    
    /** 
     * 得到模板上导航栏的靠右第二个按钮
     * @return 成功则返回TextView对象，失败则返回null。 
     */ 
    public TextView getTextViewRight2() { 
        return (TextView) findViewById(R.id.mTextviewRight2); 
    } 
    
    /** 
     * 得到模板上导航栏的靠右第三个按钮
     * @return 成功则返回TextView对象，失败则返回null。 
     */ 
    public TextView getTextViewRight3() { 
        return (TextView) findViewById(R.id.mTextviewRight3); 
    } 
    
    /** 
     * 设置模板上导航栏中间的标题文字 
     * @param titleText 
     * @return 修改成功返回true，失败返回false 
     */ 
    public boolean setTitleBarText(String titleText) { 
        TextView tv = (TextView) findViewById(R.id.mTextviewCenter); 
        if (null != tv) { 
        	if(titleText == null) {
        		tv.setText(getTitle().toString()); 
        	}else {
        		tv.setText(titleText); 
        	}
            return true; 
        } 
        return false; 
    } 
	
    /**
     * 是否显示左侧按钮(默认隐藏)
     * @param isShow
     */
    public void showTextViewLeft(boolean isShow) { 
        TextView bt = getTextViewLeft(); 
        if (null != bt) { 
        	if (isShow) 
            	bt.setVisibility(View.VISIBLE); 
            else 
            	bt.setVisibility(View.GONE);  
        } 
    } 
    
    /** 
     * 是否显示右侧按钮3(默认隐藏)
     * @param isShow
     */
    public void showTextViewRight3(boolean isShow) { 
    	TextView bt = getTextViewRight3(); 
        if (null != bt) { 
        	if (isShow) 
            	bt.setVisibility(View.VISIBLE); 
            else 
            	bt.setVisibility(View.GONE); 
        } 
    } 
    
    /** 
     * 是否显示右侧按钮2(默认隐藏)
     * @param isShow
     */
    public void showTextViewRight2(boolean isShow) { 
    	TextView bt = getTextViewRight2();
    	if (null != bt) { 
            if (isShow) 
            	bt.setVisibility(View.VISIBLE); 
            else 
            	bt.setVisibility(View.GONE); 
        } 
    } 
    
    /** 
     * 是否显示右侧按钮1(默认隐藏)
     * @param isShow
     */
    public void showTextViewRight1(boolean isShow) { 
    	TextView bt = getTextViewRight1();
    	if (null != bt) { 
            if (isShow) 
            	bt.setVisibility(View.VISIBLE); 
            else 
            	bt.setVisibility(View.GONE); 
        } 
    } 
    
    
}
