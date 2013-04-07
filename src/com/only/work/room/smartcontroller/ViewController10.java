package com.only.work.room.smartcontroller;

import java.util.List;

import com.only.work.room.brightnesscontrol.BrightnessControl;
import com.only.work.room.controlcmd.QueryCmd;
import com.only.work.room.controlcmd.SceneControl;
import com.only.work.room.controlcmd.SingleControl;
import com.only.work.room.controlcmd.SingleQueryCmd;
import com.only.work.room.net.DatagramHandle;
import com.only.work.room.net.OnUDPReceiveFinishListener;

import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
	private BrightnessControl mBrightnessControl = null;
	private static ButtonEx brightnessCurrentBtn = null;
	private SingleControl mSingleControl = null;
	private SceneControl mSceneControl = null;
	private UIHandler uiHandler;
	private String cmdName;
	private int qListIndex = 0;
	private List<QueryCmd> qList;
	
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
		qList = mViewAnalyze.getQueryCmd(listBtns);
		mDatagramHandle = new DatagramHandle();
		mDatagramHandle.setOnUDPReceivFinishListener(this);
		mBrightnessControl = new BrightnessControl(v.getContext());
		
		SingleQueryCmd.setHardwareID(qList.get(qListIndex).hardwareID);
		mDatagramHandle.receiver(qList.get(qListIndex).cmdName, SingleQueryCmd.getCmd());
		Log.e(TAG, " qc.cmdName = " + qList.get(qListIndex).cmdName + " qc.hardwraeid = " + qList.get(qListIndex).hardwareID);
		qListIndex ++;
	}
	
	public void show() {
		lyContent.removeAllViews();
		lyContent.addView(lyTemp);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		for (ButtonEx be: listBtns) {
			if (be.equals(arg0)) {
				if (be.getCmdName().equals("light")) {
					mBrightnessControl.setLightHardwareID(be.getHardwareId());
					mBrightnessControl.setLightTargetID(be.getTargetCode());
					mBrightnessControl.setBrightness(be.getBrightness());
					mBrightnessControl.show();
					brightnessCurrentBtn = be;
				} else if (be.getCmdName().equals("single")) {
					if (be.getEnabled()) {
						be.setEnabled(false);
						mSingleControl.setHardwareId(be.getHardwareId());
						mSingleControl.setTargetId(be.getTargetCode());
						mSingleControl.setTargetState(SingleControl.DISABLED);
						mDatagramHandle.send(mSingleControl.getCmd());
						be.setBackgroundResource(R.drawable.btn_click_state);
					} else {
						be.setEnabled( true);
						mSingleControl.setHardwareId(be.getHardwareId());
						mSingleControl.setTargetId(be.getTargetCode());
						mSingleControl.setTargetState(SingleControl.ENABLED);
						mDatagramHandle.send(mSingleControl.getCmd());
						be.setBackgroundResource(R.drawable.btn_click_state);
					}
				} else if (be.getCmdName().equals("scene")) {
					mSceneControl.setSceneCode(be.getTargetCode());
					mDatagramHandle.send(mSceneControl.getCmd());
				}
				Log.e(TAG, " hardid = " + be.getHardwareId() + " targetid = " + be.getTargetCode());
			}
		}
	}
	
	class UIHandler extends Handler {
		public static final int MSG_UDP_DATA_RECEIVED = 0X01;
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_UDP_DATA_RECEIVED:
				byte[] data = (byte[]) msg.obj;
				if (cmdName.equals("single")) {
					int hardwareID = data[22];
					for (int i = 1; i <= data[26]; i ++) {
						int index = i + 26;
//						Log.e(TAG, " data[" + i + "] = " + data[i + 26] + " hardwareID = " + hardwareID);
						for (ButtonEx be: listBtns) {
//							Log.e(TAG, " be.getHardwareID = " + be.getHardwareId() + " be.getCmdName = " + be.getCmdName());
							if (be.getCmdName().equals("single") && (be.getHardwareId() == hardwareID)) {
								if (be.getTargetCode() == i) {
									if (data[index] == 1) {
										be.setBackgroundResource(R.drawable.btn_d);
//										Log.e(TAG, " i = " + i + " index = " + index + " button name = " + be.getText().toString() + " targetcode = " + be.getTargetCode());
										be.setEnabled(true);
									} else {
										be.setBackgroundResource(R.drawable.btn_n);
										be.setEnabled(false);
									}
//									Log.e(TAG, " be cmdname = " +be.getCmdName() + " targetcode= " + be.getTargetCode() + " i = " + i + " data = " + data[index]);
								}
							}
						}
					}
				} else if (cmdName.equals("light")) {
					int hardwareID = data[22];
					for (int i = 1; i <= data[26]; i ++) {
						int index = i + 26;
						for (ButtonEx be: listBtns) {
							Log.e(TAG, "  hardwareID = " + hardwareID + " be.gethardwareid = " + be.getHardwareId() + " be.getCmdName = " + be.getCmdName());
							if (be.getCmdName().equals("light") && (be.getHardwareId() == hardwareID)) {
								Log.e(TAG, " be.getTargetCode = " + be.getTargetCode() + " i = " + i);
								if (be.getTargetCode() == i) {
									be.setBrightness(data[index]);
									Log.e(TAG, " data = " + data[index]);
								}
							}
						}
					}
				}
				if (qListIndex < qList.size()) {
					Log.e(TAG, " q.cmdName = " + qList.get(qListIndex).cmdName + " qc.hardwareid = " + qList.get(qListIndex).hardwareID);
					SingleQueryCmd.setHardwareID(qList.get(qListIndex).hardwareID);
					mDatagramHandle.receiver(qList.get(qListIndex).cmdName, SingleQueryCmd.getCmd());
					qListIndex ++;
					Log.e(TAG, " qListIndex = " + qListIndex + " qList.size = " + qList.size());
				}
				break;
			}
		}
	};

	@Override
	public void onUDPReceiveFinish(String cmdType, byte[] msg) {
		// TODO Auto-generated method stub
		Log.e(TAG, " cmdType = " + cmdType);
		String temp = "";
		for (byte b: msg) {
			temp += Integer.toHexString((b & 0xff));
		}
		Log.e(TAG, " cmdName = " + cmdType + " msg = " + temp);
		cmdName = cmdType;
		Message m = uiHandler.obtainMessage(UIHandler.MSG_UDP_DATA_RECEIVED, msg);
		uiHandler.sendMessage(m);
	}
}
