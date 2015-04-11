package com.yosneaker.client.model;

import java.io.Serializable;
import java.util.ArrayList;

import android.net.Uri;

/**
 * 测评草稿
 * @author lenovo
 *
 */
public class CommentItem implements Serializable{

	private String comment_item_title;// 测评项标题
	private int comment_item_star;// 测评项星数（1代表半星，取值0~10）
	private String comment_item_content;// 测评项内容
	private ArrayList<String> imageUris;

	public CommentItem() {
		super();
		this.comment_item_title = "";
		this.comment_item_star = 0;
		this.comment_item_content = "";
		imageUris = new ArrayList<String>();
	}
	
	public String getComment_item_title() {
		return comment_item_title;
	}
	public void setComment_item_title(String comment_item_title) {
		this.comment_item_title = comment_item_title;
	}
	
	public int getComment_item_star() {
		return comment_item_star;
	}
	public void setComment_item_star(int comment_item_star) {
		this.comment_item_star = comment_item_star;
	}
	public String getComment_item_content() {
		return comment_item_content;
	}
	public void setComment_item_content(String comment_item_content) {
		this.comment_item_content = comment_item_content;
	}
		
	public ArrayList<String> getImageUris() {
		return imageUris;
	}

	public void setImageUris(ArrayList<String> imageUris) {
		this.imageUris = imageUris;
	}

	
}
