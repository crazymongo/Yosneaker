package com.yosneaker.client.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 测评草稿
 * @author lenovo
 *
 */
public class CommentDraft implements Serializable{

	private String comment_title;// 测评标题
	private String comment_intro_brands;// 测评简介品牌
	private String comment_intro_model;// 测评简介型号
	private String comment_intro_assess;// 测评简介评价
	private int comment_sum_star;// 测评总评星数（1代表半星，取值0~10）
	private String comment_sum_content;// 测评总评内容
	private int comment_status;// 测评状态 0-未保存草稿，1-已保存草稿，2-已发布，3-已删除
	private int comment_date;// 测评日期 yyyymmdd
	
	private ArrayList<CommentItem> comment_items;// 测评项
	private int comment_item_index;// 当前编辑哪个测评项(-1表示要新增)

	public CommentDraft() {
		super();
		this.comment_title = "";
		this.comment_intro_brands = "";
		this.comment_intro_model = "";
		this.comment_intro_assess = "";
		this.comment_sum_star = 0;
		this.comment_sum_content = "";
		this.comment_status = 0;
		this.comment_date = 0;
		this.comment_items = new ArrayList<CommentItem>();
		this.comment_item_index = -1;
	}
	
	public String getComment_title() {
		return comment_title;
	}
	public void setComment_title(String comment_title) {
		this.comment_title = comment_title;
	}
	public String getComment_intro_brands() {
		return comment_intro_brands;
	}
	public void setComment_intro_brands(String comment_intro_brands) {
		this.comment_intro_brands = comment_intro_brands;
	}
	public String getComment_intro_model() {
		return comment_intro_model;
	}
	public void setComment_intro_model(String comment_intro_model) {
		this.comment_intro_model = comment_intro_model;
	}
	public String getComment_intro_assess() {
		return comment_intro_assess;
	}
	public void setComment_intro_assess(String comment_intro_assess) {
		this.comment_intro_assess = comment_intro_assess;
	}
	public int getComment_sum_star() {
		return comment_sum_star;
	}
	public void setComment_sum_star(int comment_sum_star) {
		this.comment_sum_star = comment_sum_star;
	}
	public String getComment_sum_content() {
		return comment_sum_content;
	}
	public void setComment_sum_content(String comment_sum_content) {
		this.comment_sum_content = comment_sum_content;
	}
	public int getComment_status() {
		return comment_status;
	}
	public void setComment_status(int comment_status) {
		this.comment_status = comment_status;
	}
	public int getComment_date() {
		return comment_date;
	}
	public void setComment_date(int comment_date) {
		this.comment_date = comment_date;
	}
		
	public ArrayList<CommentItem> getComment_items() {
		return comment_items;
	}

	public void setComment_items(ArrayList<CommentItem> comment_items) {
		this.comment_items = comment_items;
	}
	
	public void addComment_item(CommentItem comment_item) {
		this.comment_items.add(comment_item);
	}
	
	public void removeComment_item(int i) {
		this.comment_items.remove(i);
	}
	
	public void replaceComment_item(int i,CommentItem comment_item) {
		this.comment_items.remove(i);
		this.comment_items.add(i, comment_item);
	}
	
	public int getComment_item_index() {
		return comment_item_index;
	}

	public void setComment_item_index(int comment_item_index) {
		this.comment_item_index = comment_item_index;
	}
	
}
