package com.yosneaker.client.fragment;

import com.yosneaker.client.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 发布Fragment
 * 
 * @author chendd
 *
 */
public class AddFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_add,
				container, false);
		return view;
	}

}
