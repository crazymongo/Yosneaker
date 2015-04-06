
package com.yosneaker.client.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yosneaker.client.app.YosneakerAppState;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 保存本地编辑的测评草稿数据库操作类
 * @author chendd
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	 
    private static final String DB_NAME = "yosneaker.db"; //数据库名称
     
    private static final String CREATE_COMMENT_DRAFT = "create table comment_draft(" + 
    		"comment_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ //测评id
    		"comment_title varchar(50) not null , " + //测评标题
    		"comment_intro_brands varchar(50) , " + //测评简介品牌
    		"comment_intro_model varchar(50) , " + //测评简介型号
    		"comment_intro_assess varchar(1000) , " + //测评简介评价
    		"comment_sum_star INTEGER , " + //测评总评星数（1代表半星，取值0~10）
    		"comment_sum_content varchar(1600) , " + //测评总评内容
    		"comment_status INTEGER , "+ //测评状态 0-未保存草稿，1-已保存草稿，2-已发布，3-已删除
    		"comment_date INTEGER , "+ //测评日期
    		"comment_user_id INTEGER not null "+ //测评用户id
    		");" ;
    private static final String CREATE_COMMENT_ITEM_DRAFT = "create table comment_item_draft(" +
    		"comment_item_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ //测评项id
    		"comment_id INTEGER , "+ //对应的测评id
    		"comment_item_title varchar(50) , " + //测评项标题
    		"comment_item_star INTEGER , " + //测评项星数
    		"comment_item_content varchar(1600)" + //测评项内容
    		");"; 
    
    private static final String CREATE_COMMENT_ITEM_IMAGE = "create table comment_item_image(" +
    		"comment_item_id INTEGER , "+ //对应测评项id
    		"comment_item_image_uri varchar(100) not null" + //测评项图片本地uri
    		");"; 
    
    public DatabaseHelper(Context context,int version) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	// 创建三个保存 编辑测评草稿的表         
        db.execSQL(CREATE_COMMENT_DRAFT);                
        db.execSQL(CREATE_COMMENT_ITEM_DRAFT);                 
        db.execSQL(CREATE_COMMENT_ITEM_IMAGE);
        
    }
    
    /**
     * 插入测评项标题
     * @param commitTitle
     * @param userId
     */
    public boolean insertCommentTitle(String commitTitle,int commentStatus) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put("comment_title", commitTitle);
    	values.put("comment_user_id", YosneakerAppState.user_id);
    	values.put("comment_status", commentStatus);
    	values.put("comment_date", Integer.parseInt(df.format(new Date())));
    	db.insert("comment_draft", null, values);
    	return false;
	}
    
    /**
     * 修改测评项标题
     * @param commitTitle
     * @param userId
     */
    public boolean alterCommentTitle(String oldCommitTitle,String commitTitle) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put("comment_title", commitTitle);
    	values.put("comment_user_id", YosneakerAppState.user_id);
    	db.insert("comment_draft", null, values);
    	return false;
	}
    
//    /**
//     * 获取测评项标题
//     * @param commitTitle
//     * @param userId
//     */
//    public Cursor getCommentTitle(int userId,int commentStatus) {
//    	SQLiteDatabase db = this.getWritableDatabase();
//    	Cursor cursor = db.q
//	}
    
    
    /**
     * 执行 insert update delete 等语句
     * @param sql
     * @param args
     */
    public void execSQL(String sql,String[] args) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL(sql, args);
    }

    /**
     * 执行 select 语句
     * @param sql
     * @param args
     * @return
     */
    public Cursor query(String sql,String[] args) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, args);
		return cursor;
	}
    
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 产品升级，数据库修改
 
    }
 
}
