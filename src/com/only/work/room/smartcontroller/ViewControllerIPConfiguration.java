package com.only.work.room.smartcontroller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ViewControllerIPConfiguration implements OnClickListener {
	private static final String TAG = "ViewControllerIPConfiguration";
	private View view = null;
	private View viewPasswd = null;
	private LinearLayout ly = null;
	
	/**
	 * viewPasswd views
	 * @param v
	 * @param inflater
	 */
	private EditText etPasswd = null;
	private Button btnSure = null;
	
	/**
	 * views for ip_config_layout view
	 */
	private EditText etController10IP = null;
	private EditText etController9IP = null;
	private EditText etController8IP = null;
	private EditText etController7IP = null;
	private EditText etController6IP = null;
	private EditText etController5IP = null;
	private EditText etController4IP = null;
	private EditText etController3IP = null;
	private EditText etController2IP = null;
	private EditText etController1IP = null;
	private Button btnSaveIP = null;
	
	/**
	 * const params
	 * @param v
	 * @param inflater
	 */
	private static final String CONTROLLER_10_IP = "controller_10_ip";
	private static final String CONTROLLER_9_IP = "controller_9_ip";
	private static final String CONTROLLER_8_IP = "controller_8_ip";
	private static final String CONTROLLER_7_IP = "controller_7_ip";
	private static final String CONTROLLER_6_IP = "controller_6_ip";
	private static final String CONTROLLER_5_IP = "controller_5_ip";
	private static final String CONTROLLER_4_IP = "controller_4_ip";
	private static final String CONTROLLER_3_IP = "controller_3_ip";
	private static final String CONTROLLER_2_IP = "controller_2_ip";
	private static final String CONTROLLER_1_IP = "controller_1_ip";
	
	/*
	 * 
	 */
	private SharedPreferences sp = null;
	private Editor editor = null;
	private boolean bPasswdOk = false;
	
	public ViewControllerIPConfiguration(View v, LayoutInflater inflater) {
		ly = (LinearLayout) v;
		view = (View) inflater.inflate(R.layout.ip_config_layout, null);
		sp = view.getContext().getSharedPreferences("ip_config", Context.MODE_PRIVATE);
		editor = sp.edit();
		getViewHandle();
		initEditText();
		viewPasswd = (View) inflater.inflate(R.layout.ip_configuration_password, null);
		getViewPasswdViewHandle();
	}
	
	private void getViewHandle() {
		etController10IP = (EditText) view.findViewById(R.id.et_controller_10_ip);
		etController9IP = (EditText) view.findViewById(R.id.et_controller_9_ip);
		etController8IP = (EditText) view.findViewById(R.id.et_controller_8_ip);
		etController7IP = (EditText) view.findViewById(R.id.et_controller_7_ip);
		etController6IP = (EditText) view.findViewById(R.id.et_controller_6_ip);
		etController5IP = (EditText) view.findViewById(R.id.et_controller_5_ip);
		etController4IP = (EditText) view.findViewById(R.id.et_controller_4_ip);
		etController3IP = (EditText) view.findViewById(R.id.et_controller_3_ip);
		etController2IP = (EditText) view.findViewById(R.id.et_controller_2_ip);
		etController1IP = (EditText) view.findViewById(R.id.et_controller_1_ip);
		btnSaveIP = (Button) view.findViewById(R.id.btn_save_ip);
		btnSaveIP.setOnClickListener(this);
	}
	
	private void initEditText() {
		etController10IP.setText(sp.getString(CONTROLLER_10_IP, ""));
		etController9IP.setText(sp.getString(CONTROLLER_9_IP, ""));
		etController8IP.setText(sp.getString(CONTROLLER_8_IP, ""));
		etController7IP.setText(sp.getString(CONTROLLER_7_IP, ""));
		etController6IP.setText(sp.getString(CONTROLLER_6_IP, ""));
		etController5IP.setText(sp.getString(CONTROLLER_5_IP, ""));
		etController4IP.setText(sp.getString(CONTROLLER_4_IP, ""));
		etController3IP.setText(sp.getString(CONTROLLER_3_IP, ""));
		etController2IP.setText(sp.getString(CONTROLLER_2_IP, ""));
		etController1IP.setText(sp.getString(CONTROLLER_1_IP, ""));
	}
	
	private void getViewPasswdViewHandle() {
		etPasswd = (EditText) viewPasswd.findViewById(R.id.et_passwd);
		btnSure = (Button) viewPasswd.findViewById(R.id.btn_sure);
		btnSure.setOnClickListener(this);
	}
	
	public void show() {
		ly.removeAllViews();
		if (!bPasswdOk) {
			ly.addView(viewPasswd);
		} else {
			ly.addView(view);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.equals(btnSure)) {
			if (etPasswd.getText().toString().trim().equals("666666")) {
				ly.removeAllViews();
				ly.addView(view);
				bPasswdOk = true;
			} else {
				AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
				b.setMessage(R.string.passwd_error);
				b.setPositiveButton(R.string.btn_sure, null);
				b.show();
			}
		} else if (arg0.equals(btnSaveIP)) {
			editor.putString(CONTROLLER_10_IP, etController10IP.getText().toString().trim());
			editor.putString(CONTROLLER_9_IP, etController9IP.getText().toString().trim());
			editor.putString(CONTROLLER_8_IP, etController8IP.getText().toString().trim());
			editor.putString(CONTROLLER_7_IP, etController7IP.getText().toString().trim());
			editor.putString(CONTROLLER_6_IP, etController6IP.getText().toString().trim());
			editor.putString(CONTROLLER_5_IP, etController5IP.getText().toString().trim());
			editor.putString(CONTROLLER_4_IP, etController4IP.getText().toString().trim());
			editor.putString(CONTROLLER_3_IP, etController3IP.getText().toString().trim());
			editor.putString(CONTROLLER_2_IP, etController2IP.getText().toString().trim());
			editor.putString(CONTROLLER_1_IP, etController1IP.getText().toString().trim());
			editor.commit();
		}
	}
}
