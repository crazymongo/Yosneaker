package com.yosneaker.client.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 测评项草稿
 * @author chendd
 *
 */
public class ArticleItem implements Serializable{

	private String itemTitle;// 测评项标题
	private int itemLevel;// 测评项星数（取值0~5）
	private String itemContent;// 测评项内容
	private ArrayList<String> itemImages;// 测评项图片Uri

	public ArticleItem() {
		super();
		this.itemTitle = "";
		this.itemLevel = 0;
		this.itemContent = "";
		itemImages = new ArrayList<String>();
	}
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	public int getItemLevel() {
		return itemLevel;
	}
	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}
	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
		
	public ArrayList<String> getItemImages() {
		return itemImages;
	}

	public void setItemImages(ArrayList<String> itemImages) {
		this.itemImages = itemImages;
	}

	
}
