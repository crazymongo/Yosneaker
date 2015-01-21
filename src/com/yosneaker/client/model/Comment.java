package com.yosneaker.client.model;

/**
 * 测评项
 * 
 * @author chendd
 *
 */
public class Comment {
	
	/** 测评标题 */
	private String title;
	
	/** 测评阅读数 */
	private int reader;
	
	/** 测评日期 */
	private String date;
	
	public Comment(String title, int reader, String date) {
		super();
		this.title = title;
		this.reader = reader;
		this.date = date;
	}

	public Comment(String title, int reader) {
		super();
		this.title = title;
		this.reader = reader;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getReader() {
		return reader;
	}

	public void setReader(int reader) {
		this.reader = reader;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
