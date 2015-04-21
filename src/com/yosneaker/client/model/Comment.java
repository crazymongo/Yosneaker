package com.yosneaker.client.model;

/**
 * 评论
 * @author chendd
 *
 */
public class Comment {

	private int articleCommentArticleId; //测评ID
	private String articleCommentContent; //评论内容
	private int articleCommentAccountId; //作者ID
	private int articleCommentToUserId; //被回复人的ID
	
	public int getArticleCommentArticleId() {
		return articleCommentArticleId;
	}
	public void setArticleCommentArticleId(int articleCommentArticleId) {
		this.articleCommentArticleId = articleCommentArticleId;
	}
	public String getArticleCommentContent() {
		return articleCommentContent;
	}
	public void setArticleCommentContent(String articleCommentContent) {
		this.articleCommentContent = articleCommentContent;
	}
	public int getArticleCommentAccountId() {
		return articleCommentAccountId;
	}
	public void setArticleCommentAccountId(int articleCommentAccountId) {
		this.articleCommentAccountId = articleCommentAccountId;
	}
	public int getArticleCommentToUserId() {
		return articleCommentToUserId;
	}
	public void setArticleCommentToUserId(int articleCommentToUserId) {
		this.articleCommentToUserId = articleCommentToUserId;
	}
	
}
