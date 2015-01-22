package com.yosneaker.client.fragment;

import com.yosneaker.client.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 鉴定Fragment
 * 
 * @author chendd
 *
 */
public class IdentifyFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_identify,
				container, false);
		return view;
	}

}
