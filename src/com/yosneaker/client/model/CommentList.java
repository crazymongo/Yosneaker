package com.yosneaker.client.model;


/**
 * 测评列表项
 * 
 * @author chendd
 *
 */
public class CommentList {
	
	/** 测评标题 */
	private String commentTitle;
	
	/** 测评阅读数 */
	private int commentReaders;
	
	/** 测评日期 */
	private String commentDate;
	
	/** 测评星级 */
	private int commentAssessStar;

	/** 测评者头像地址 */
	private String commentHeadUrl;
	
	/** 测评锋面图片地址 */
	private String commentCoverUrl;
	
	// url 格式
//	String imageUri = "http://site.com/image.png"; // from Web  
//	String imageUri = "file:///mnt/sdcard/image.png"; // from SD card  
//	String imageUri = "content://media/external/audio/albumart/13"; // from content provider  
//	String imageUri = "assets://image.png"; // from assets  
//	String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
	
	public CommentList(String commentTitle, int commentReaders, String commentDate,int commentAssessStar,String commentHeadUrl,String commentCoverUrl) {
		super();
		this.commentTitle = commentTitle;
		this.commentReaders = commentReaders;
		this.commentDate = commentDate;
		this.commentAssessStar = commentAssessStar;
		this.commentHeadUrl = commentHeadUrl;
		this.commentCoverUrl = commentCoverUrl;
	}

	public String getCommentTitle() {
		return commentTitle;
	}
	
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}

	public int getCommentReaders() {
		return commentReaders;
	}

	public void setCommentReaders(int commentReaders) {
		this.commentReaders = commentReaders;
	}
	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public int getCommentAssessStar() {
		return commentAssessStar;
	}

	public void setCommentAssessStar(int commentAssessStar) {
		this.commentAssessStar = commentAssessStar;
	}
	

	public String getCommentHeadUrl() {
		return commentHeadUrl;
	}

	public void setCommentHeadUrl(String commentHeadUrl) {
		this.commentHeadUrl = commentHeadUrl;
	}

	public String getCommentCoverUrl() {
		return commentCoverUrl;
	}

	public void setCommentCoverUrl(String commentCoverUrl) {
		this.commentCoverUrl = commentCoverUrl;
	}

	
}
