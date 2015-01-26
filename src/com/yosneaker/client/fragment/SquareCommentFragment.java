package com.yosneaker.client.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.yosneaker.client.CommentDetailActivity;
import com.yosneaker.client.R;
import com.yosneaker.client.adapter.CommentAdapter;
import com.yosneaker.client.model.CommentList;
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
	private CommentAdapter mAdapter;
	private ArrayList<CommentList> items = new ArrayList<CommentList>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		viewFragment = inflater.inflate(R.layout.fragment_square_comment,
				container, false);
		geneItems();
		initViews();
		return viewFragment;
	}

	private void initViews(){
		xListView=(XListView) viewFragment.findViewById(R.id.xListView);	
		xListView.setPullLoadEnable(true);
		mAdapter = new CommentAdapter(getActivity(),items);
		xListView.setAdapter(mAdapter);
		xListView.setXListViewListener(this);
		xListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				gotoExistActivity(CommentDetailActivity.class, new Bundle());
			}
			
		});
		mHandler = new Handler();
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				mAdapter = new CommentAdapter(getActivity(),items);
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
			items.add(new CommentList("樱花AJ 测评"+(++start),(++start),"五分钟以前"));
		}
	}

	private void onLoad() {
		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setRefreshTime("刚刚");
	}
}
