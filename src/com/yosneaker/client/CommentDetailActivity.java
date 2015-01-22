package com.yosneaker.client;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

import com.yosneaker.client.view.CustomScrollView;
import com.yosneaker.client.view.CustomScrollView.OnScrollListener;

/**
 * 测评详情
 * 
 * @author chendd
 *
 */
public class CommentDetailActivity extends BaseActivity implements OnScrollListener{

	/**  测评布局 */
	private LinearLayout mLayout;
	
	/**  自定义的ScrollView */
	private CustomScrollView mScrollView;
	/**  在MyScrollView里面的购买布局 */
	private LinearLayout mBuyLayout;
	/**  位于顶部的购买布局 */
	private LinearLayout mTopBuyLayout;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		
		setContentView(R.layout.activity_comment_detail);
		
		super.onCreate(savedInstanceState);
	}


	@Override
	public void initViews() {
		
		mLayout = (LinearLayout) findViewById(R.id.activity_comment_detail_layout);
		mScrollView = (CustomScrollView) findViewById(R.id.scrollView);
		mBuyLayout = (LinearLayout) findViewById(R.id.buy);
		mTopBuyLayout = (LinearLayout) findViewById(R.id.top_buy_layout);
		
		setTitleBarText(null);
		showTextViewLeft(true);
		showTextViewRight1(true);
		showTextViewRight2(true);
		getTextViewRight1().setBackgroundResource(R.drawable.ic_share);
		getTextViewRight2().setBackgroundResource(R.drawable.ic_unstar);
	}

	@Override
	public void addListnners() {
		
		getTextViewLeft().setOnClickListener(this);		
		getTextViewRight1().setOnClickListener(this);		
		getTextViewRight2().setOnClickListener(this);
		
		mScrollView.setOnScrollListener(this);
		mLayout.getViewTreeObserver()
		.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// 这一步很重要，使得上面的购买布局和下面的购买布局重合
				onScroll(mScrollView.getScrollY());

				System.out.println(mScrollView.getScrollY());
			}
		});
		
	}

	@Override
	public void fillDatas() {
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == getTextViewLeft()) {
			onBackPressed();
		}else if (v == getTextViewRight1()) {
			showToast("分享");
		}else if (v == getTextViewRight2()) {
			showToast("收藏");
		}
	}

	@Override
	public void onScroll(int scrollY) {
		int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());
		mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(),
				mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
	}


}
