package com.only.work.room.smartcontroller;

import java.util.List;

import com.only.work.room.net.DatagramHandle;
import com.only.work.room.net.OnUDPReceiveFinishListener;

import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;

public class ViewController10 implements OnClickListener, OnUDPReceiveFinishListener {
	private static final String TAG = "ViewController10";
	
	private List<ButtonEx> listBtns;
	private LinearLayout lyTemp = null;
	private LinearLayout lyContent = null;
	private DatagramHandle mDatagramHandle;
	
	public ViewController10(View v, LayoutInflater inflater) {
		lyContent = (LinearLayout) v;
		ViewAnalyze mViewAnalyze = new ViewAnalyze(v.getContext());
		listBtns = mViewAnalyze.loadButtonsSpecifyFile("controller10", "specify_file_name.txt");
		if (listBtns != null) {
			lyTemp = new LinearLayout(v.getContext());
			lyTemp.setOrientation(LinearLayout.VERTICAL);
			LinearLayout ly = new LinearLayout(v.getContext());
			int i = 0;
			for (i = 1; i <= listBtns.size(); i ++) {
				ButtonEx btn = listBtns.get(i - 1);
				LayoutParams btnLp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
				btnLp.setMargins(5, 5, 5, 5);
				btn.setOnClickListener(this);
				ly.addView(btn, btnLp);
				if (i % 4 == 0) {
					LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					lyTemp.addView(ly, lp);
					ly = new LinearLayout(v.getContext());
				}
			}
			if (i % 4 != 0) {
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				lyTemp.addView(ly, lp);
			}
		}
		mDatagramHandle = new DatagramHandle();
		mDatagramHandle.setOnUDPReceivFinishListener(this);
	}
	
	public void show() {
		lyContent.removeAllViews();
		lyContent.addView(lyTemp);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUDPReceiveFinish(String cmdType, byte[] msg) {
		// TODO Auto-generated method stub
		
	}
}
