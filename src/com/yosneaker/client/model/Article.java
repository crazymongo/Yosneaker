package com.yosneaker.client.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 测评草稿
 * @author chendd
 *
 */
public class Article implements Serializable{

	private String articleTitle;// 测评标题
	private String articleImages;// 测评缩略图
	private int articleAuthorId;// 测评作者id
	private String articleTrademarkId;// 测评简介品牌
	private String articleModelId;// 测评简介型号
	private String articleDescription;// 测评简介评价
	private int articleLevel;// 测评总评星数（取值0~5）
	private String articleComment;// 测评总评内容
	private int articleCreateTime;// 测评日期 yyyymmdd	
	private ArrayList<ArticleItem> items;// 测评项
	
	private int articleStatus;// 测评状态 0-未保存草稿，1-已保存草稿，2-已发布
	private int articleItemIndex;// 当前编辑哪个测评项(-1表示要新增)

	public Article() {
		super();
		this.articleTitle = "";
		this.articleImages = "";
		this.articleAuthorId = -1;
		this.articleTrademarkId = "";
		this.articleModelId = "";
		this.articleDescription = "";
		this.articleLevel = 0;
		this.articleComment = "";
		this.articleCreateTime = 0;
		this.items = new ArrayList<ArticleItem>();
		this.articleStatus = 0;
		this.articleItemIndex = -1;
	}
	
	public String getArticleImages() {
		return articleImages;
	}

	public void setArticleImages(String articleImages) {
		this.articleImages = articleImages;
	}

	public int getArticleAuthorId() {
		return articleAuthorId;
	}

	public void setArticleAuthorId(int articleAuthorId) {
		this.articleAuthorId = articleAuthorId;
	}
	
	public String getComment_title() {
		return articleTitle;
	}
	public void setComment_title(String comment_title) {
		this.articleTitle = comment_title;
	}
	public String getComment_intro_brands() {
		return articleTrademarkId;
	}
	public void setComment_intro_brands(String comment_intro_brands) {
		this.articleTrademarkId = comment_intro_brands;
	}
	public String getComment_intro_model() {
		return articleModelId;
	}
	public void setComment_intro_model(String comment_intro_model) {
		this.articleModelId = comment_intro_model;
	}
	public String getComment_intro_assess() {
		return articleDescription;
	}
	public void setComment_intro_assess(String comment_intro_assess) {
		this.articleDescription = comment_intro_assess;
	}
	public int getComment_sum_star() {
		return articleLevel;
	}
	public void setComment_sum_star(int comment_sum_star) {
		this.articleLevel = comment_sum_star;
	}
	public String getComment_sum_content() {
		return articleComment;
	}
	public void setComment_sum_content(String comment_sum_content) {
		this.articleComment = comment_sum_content;
	}
	public int getComment_status() {
		return articleStatus;
	}
	public void setComment_status(int comment_status) {
		this.articleStatus = comment_status;
	}
	public int getComment_date() {
		return articleCreateTime;
	}
	public void setComment_date(int comment_date) {
		this.articleCreateTime = comment_date;
	}
		
	public ArrayList<ArticleItem> getComment_items() {
		return items;
	}

	public void setComment_items(ArrayList<ArticleItem> comment_items) {
		this.items = comment_items;
	}

	public int getComment_item_index() {
		return articleItemIndex;
	}

	public void setComment_item_index(int comment_item_index) {
		this.articleItemIndex = comment_item_index;
	}
	
	public void addComment_item(ArticleItem comment_item) {
		this.items.add(comment_item);
	}
	
	public void removeComment_item(int i) {
		this.items.remove(i);
	}
	
	public void replaceComment_item(int i,ArticleItem comment_item) {
		this.items.remove(i);
		this.items.add(i, comment_item);
	}
	
}
