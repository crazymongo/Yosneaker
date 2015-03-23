package com.yosneaker.client.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	 
    private static final String DB_NAME = "yosneaker.db"; //数据库名称
    private static final int version = 1; //数据库版本
     
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	// 创建三个保存 编辑测评草稿的表
        String sql = "create table comment_draft(" + 
        		"comment_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
        		"comment_title varchar(50) not null , " +
        		"comment_intro_brands varchar(50) , " +
        		"comment_intro_model varchar(50) , " +
        		"comment_intro_assess varchar(1000) , " +
        		"comment_sum_star INTEGER , " +
        		"comment_sum_content varchar(1600) , " +
        		"comment_date INTEGER , "+
        		");";          
        db.execSQL(sql);
        sql = "create table comment_item_draft(" +
        		"comment_item_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
        		"comment_id INTEGER , "+
        		"comment_item_title varchar(50) , " +
        		"comment_item_star INTEGER , " +
        		"comment_item_content varchar(1600) , " +
        		");";          
        db.execSQL(sql);
        sql = "create table comment_item_image(" +
        		"comment_item_id INTEGER , "+
        		"comment_item_image_uri varchar(100) not null , " +
        		");";          
        db.execSQL(sql);
        
    }
    
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
