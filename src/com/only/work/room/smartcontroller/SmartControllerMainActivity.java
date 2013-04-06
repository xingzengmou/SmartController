package com.only.work.room.smartcontroller;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartControllerMainActivity extends Activity implements OnClickListener {
	private static final String TAG = "SmartControllerMainActivity";
	
	private LinearLayout lyController;
	private LinearLayout lyLeft;
	private View preView = null;	
	private Activity thisActivity;
	
	/**
	 * views
	 */
	private ViewControllerIPConfiguration mViewControllerIPConfiguration;
	private ViewController10 mViewController10;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;
        setContentView(R.layout.activity_smart_controller_main);
        getViewHandle();
        setViewListener();
        initView();
    }
    
    private void initView() {
    	mViewControllerIPConfiguration = new ViewControllerIPConfiguration(lyLeft, this.getLayoutInflater());
    	mViewController10 = new ViewController10(lyLeft, this.getLayoutInflater());
    }
    
    private void getViewHandle() {
    	lyController = (LinearLayout) this.findViewById(R.id.controller_ly);
    	lyLeft = (LinearLayout) this.findViewById(R.id.left_ly);
    	loadControllersView();
    }
    
    private void setViewListener() {
    }
    
    private void loadControllersView() {
    	View view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(10);
    	view.setBackgroundResource(R.drawable.click_state);
    	TextView tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_10);
    	TextView tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_10_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(9);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_9);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_9_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(8);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_8);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_8_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(7);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_7);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_7_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(6);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_6);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_6_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(5);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_5);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_5_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(4);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_4);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_4_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(3);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_3);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_3_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(2);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_2);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_2_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(1);
    	view.setBackgroundResource(R.drawable.click_state);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_controller_1);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setText(R.string.tv_controller_1_tip);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(11);
    	view.setBackgroundResource(R.drawable.click_state);
    	LinearLayout lyContent = (LinearLayout) view.findViewById(R.id.ly_content);
    	lyContent.setPadding(0, 5, 0, 5);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_ip_configuration);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setVisibility(View.GONE);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    	
    	view = (View) this.getLayoutInflater().inflate(R.layout.btn_controller_layout, null);
    	view.setId(12);
    	view.setBackgroundResource(R.drawable.click_state);
    	LinearLayout lyContent1 = (LinearLayout) view.findViewById(R.id.ly_content);
    	lyContent1.setPadding(0, 5, 0, 5);
    	tvTitle = (TextView) view.findViewById(R.id.tv_controller_title);
    	tvTitle.setText(R.string.tv_quit_program);
    	tvTitleTip = (TextView) view.findViewById(R.id.tv_controller_tip);
    	tvTitleTip.setVisibility(View.GONE);
    	view.setOnClickListener(this);
    	lyController.addView(view);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (preView != null) {
			preView.setBackgroundResource(R.drawable.click_state);
		}
		arg0.setBackgroundResource(R.color.focus_d_color);
		preView = arg0;
		if (arg0.getId() == 11) { //ip configuration
			mViewControllerIPConfiguration.show();
		} else if (arg0.getId() == 12) {
			AlertDialog.Builder b = new AlertDialog.Builder(this);
			b.setMessage(R.string.quit_tip);
			b.setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					thisActivity.finish();
				}
			});
			b.setNegativeButton(R.string.btn_no, null);
			b.show();
		} else if (arg0.getId() == 10) {
			mViewController10.show();
		}
	}
    
	
}
