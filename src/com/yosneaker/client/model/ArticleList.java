package com.yosneaker.client.model;


/**
 * 测评列表项
 * 
 * @author chendd
 *
 */
public class ArticleList {
	
	/** 测评id */
	private int articleId;

	/** 测评作者id */
	private int articleAuthorId;
	
	/** 测评标题 */
	private String articleTitle;
	
	/** 测评阅读数 */
	private int articleViews;
	
	/** 测评日期 */
	private String articleCreateTime;
	
	/** 测评星级 */
	private int articleLevel;

	/** 测评者头像地址 */
	private String articlePortrait;
	
	/** 测评锋面图片地址 */
	private String articleImages;

	public ArticleList(int articleId,int articleAuthorId,String articleTitle, int articleViews, String articleCreateTime,int articleLevel,String articlePortrait,String articleImages) {
		super();
		this.articleId = articleId;
		this.articleAuthorId = articleAuthorId;
		this.articleTitle = articleTitle;
		this.articleViews = articleViews;
		this.articleCreateTime = articleCreateTime;
		this.articleLevel = articleLevel;
		this.articlePortrait = articlePortrait;
		this.articleImages = articleImages;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getArticleAuthorId() {
		return articleAuthorId;
	}

	public void setArticleAuthorId(int articleAuthorId) {
		this.articleAuthorId = articleAuthorId;
	}

	
	public String getArticleTitle() {
		return articleTitle;
	}
	
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public int getArticleViews() {
		return articleViews;
	}

	public void setArticleViews(int articleViews) {
		this.articleViews = articleViews;
	}
	public String getArticleCreateTime() {
		return articleCreateTime;
	}

	public void setArticleCreateTime(String articleCreateTime) {
		this.articleCreateTime = articleCreateTime;
	}

	public int getArticleLevel() {
		return articleLevel;
	}

	public void setArticleLevel(int articleLevel) {
		this.articleLevel = articleLevel;
	}
	

	public String getArticlePortrait() {
		return articlePortrait;
	}

	public void setArticlePortrait(String articlePortrait) {
		this.articlePortrait = articlePortrait;
	}

	public String getArticleImages() {
		return articleImages;
	}

	public void setArticleImages(String articleImages) {
		this.articleImages = articleImages;
	}

	
}
