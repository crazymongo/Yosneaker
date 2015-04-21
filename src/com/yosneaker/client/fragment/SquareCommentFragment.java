package com.yosneaker.client.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.yosneaker.client.ArticleDetailActivity;
import com.yosneaker.client.R;
import com.yosneaker.client.adapter.ArticleAdapter;
import com.yosneaker.client.model.ArticleList;
import com.yosneaker.client.view.XListView;
import com.yosneaker.client.view.XListView.IXListViewListener;

/**
 * 
 * 广场Fragment
 * 
 * @author chendd
 *
 */
public class SquareCommentFragment extends BaseFragment implements IXListViewListener{
	
	private View viewFragment;
	private XListView xListView=null;
	private ArticleAdapter mAdapter;
	private ArrayList<ArticleList> items = new ArrayList<ArticleList>();
	private Handler mHandler;
	
	// 测试数据
	int start = 1;
	ArrayList<String> heads = new ArrayList<String>();
	ArrayList<String> covers = new ArrayList<String>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		viewFragment = inflater.inflate(R.layout.fragment_square_article,
				container, false);
		
		// 模拟测试数据
		heads.add("drawable://" + R.drawable.list_user_head);
		heads.add("drawable://" + R.drawable.list_user_head2);		
		covers.add("drawable://" + R.drawable.list_bg);
		covers.add("drawable://" + R.drawable.list_bg2);
		
		geneItems();
		initViews();
		return viewFragment;
	}

	private void initViews(){
		xListView=(XListView) viewFragment.findViewById(R.id.xListView);	
		xListView.setPullLoadEnable(true);
		mAdapter = new ArticleAdapter(getActivity(),items);
		xListView.setAdapter(mAdapter);
		xListView.setXListViewListener(this);
		xListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				gotoExistActivity(ArticleDetailActivity.class, new Bundle());
			}
			
		});
		mHandler = new Handler();
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				items.clear();
				geneItems();
				mAdapter = new ArticleAdapter(getActivity(),items);
				xListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
	
	private void geneItems() {
		for (int i = 0; i != 5; ++i) {
			items.add(new ArticleList(1000,1000,"樱花AJ 测评"+start,start,start+"分钟前",start%5,heads.get(start%2),covers.get(start++%2)));
		}
	}

	private void onLoad() {
		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setRefreshTime("刚刚");
	}
}
