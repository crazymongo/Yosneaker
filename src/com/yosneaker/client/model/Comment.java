package com.yosneaker.client.model;


import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
    private Integer articleCommentId;

    private Integer articleCommentArticleId;

    private String articleCommentContent;

    private Integer articleCommentAccountId;

    private Date articleCommentPublishTime;

    private Integer articleCommentToUserId;

    private Integer articleCommentTopNumber;
    
    private Account account;

    public Integer getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Integer articleCommentId) {
        this.articleCommentId = articleCommentId;
    }

    public Integer getArticleCommentArticleId() {
        return articleCommentArticleId;
    }

    public void setArticleCommentArticleId(Integer articleCommentArticleId) {
        this.articleCommentArticleId = articleCommentArticleId;
    }

    public String getArticleCommentContent() {
        return articleCommentContent;
    }

    public void setArticleCommentContent(String articleCommentContent) {
        this.articleCommentContent = articleCommentContent == null ? null : articleCommentContent.trim();
    }

    public Integer getArticleCommentAccountId() {
        return articleCommentAccountId;
    }

    public void setArticleCommentAccountId(Integer articleCommentAccountId) {
        this.articleCommentAccountId = articleCommentAccountId;
    }

    public Date getArticleCommentPublishTime() {
        return articleCommentPublishTime;
    }

    public void setArticleCommentPublishTime(Date articleCommentPublishTime) {
        this.articleCommentPublishTime = articleCommentPublishTime;
    }

    public Integer getArticleCommentToUserId() {
        return articleCommentToUserId;
    }

    public void setArticleCommentToUserId(Integer articleCommentToUserId) {
        this.articleCommentToUserId = articleCommentToUserId;
    }

    public Integer getArticleCommentTopNumber() {
        return articleCommentTopNumber;
    }

    public void setArticleCommentTopNumber(Integer articleCommentTopNumber) {
        this.articleCommentTopNumber = articleCommentTopNumber;
    }

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Comment [articleCommentId=" + articleCommentId
				+ ", articleCommentArticleId=" + articleCommentArticleId
				+ ", articleCommentContent=" + articleCommentContent
				+ ", articleCommentAccountId=" + articleCommentAccountId
				+ ", articleCommentPublishTime=" + articleCommentPublishTime
				+ ", articleCommentToUserId=" + articleCommentToUserId
				+ ", articleCommentTopNumber=" + articleCommentTopNumber
				+ ", account=" + account + "]";
	}
}