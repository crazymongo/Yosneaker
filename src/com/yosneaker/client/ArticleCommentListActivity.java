package com.yosneaker.client;

import com.yosneaker.client.view.CommentItemView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * 测评评论列表标题
 * 
 * @author chendd
 *
 */
public class ArticleCommentListActivity extends BaseActivity{

	private LinearLayout ll_hot_comments;	
//	private LinearLayout ll_all_comments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_article_comments_list);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		setTitleBarText(null);
		showTextViewLeft(true);

		ll_hot_comments = (LinearLayout) findViewById(R.id.ll_hot_comments);
//		ll_all_comments = (LinearLayout) findViewById(R.id.ll_all_comments);
		
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);			
	
	}

	@Override
	public void fillDatas() {
		
		CommentItemView commentItemView = new CommentItemView(ArticleCommentListActivity.this);
		commentItemView.setCommentContent("我是评论");
		commentItemView.setUserName("可爱大二狗");
		commentItemView.setUserPortrait("drawable://" + R.drawable.list_user_head);
		commentItemView.setPraiseCount("11");
		

		
		CommentItemView commentItemView2 = new CommentItemView(ArticleCommentListActivity.this);
		commentItemView2.setCommentContent("阿萨德撒旦撒旦的撒啊是的撒大大大撒旦的撒大啊是的撒啊三大啊啊打算");
		commentItemView2.setUserName("可爱大三狗");
		commentItemView2.setUserPortrait("drawable://" + R.drawable.list_user_head);
		commentItemView2.setPraiseCount("33");
		
		ll_hot_comments.addView(commentItemView);
		ll_hot_comments.addView(commentItemView2);
		
//		CommentItemView commentItemView3 = new CommentItemView(ArticleCommentListActivity.this);
//		commentItemView3.setCommentContent("我是评论");
//		commentItemView3.setUserName("可爱大二狗");
//		commentItemView3.setLocation("1楼");
//		commentItemView3.setUserPortrait("drawable://" + R.drawable.list_user_head2);
//		commentItemView3.setPraiseCount("22");
//		ll_all_comments.addView(commentItemView3);
		
//		CommentItemView commentItemView4 = new CommentItemView(ArticleCommentListActivity.this);
//		commentItemView4.setCommentContent("阿萨德撒旦撒旦的撒啊是的撒大大大撒旦的撒大啊是的撒啊三大啊啊打算");
//		commentItemView4.setUserName("可爱大三狗");
//		commentItemView4.setLocation("2楼");
//		commentItemView4.setUserPortrait("drawable://" + R.drawable.list_user_head);
//		commentItemView4.setPraiseCount("13");	
//		ll_all_comments.addView(commentItemView4);
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}
	}
	
}
