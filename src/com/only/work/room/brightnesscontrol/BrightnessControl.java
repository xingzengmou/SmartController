package com.only.work.room.brightnesscontrol;

import com.only.work.room.smartcontroller.ButtonEx;
import com.only.work.room.smartcontroller.R;
import com.only.work.room.smartcontroller.ViewControllerIPConfiguration;
import com.only.work.room.controlcmd.SceneControl;
import com.only.work.room.controlcmd.SingleControl;
import com.only.work.room.net.DatagramHandle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class BrightnessControl implements OnSeekBarChangeListener {
	private static final String TAG = "BrightnessControl";
	private Dialog brightnessDialog;
	private Context ctx;
	private SeekBar mSeekBar;
	private SingleControl mSingleControl = null;
	private DatagramHandle mDatagramHandle = null;
	private ButtonEx currentBtne = null;

	
	public BrightnessControl(Context ctx) {
		this.ctx = ctx;
		brightnessDialog = new Dialog(ctx);
		brightnessDialog = new Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar);
		brightnessDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		brightnessDialog.setContentView(R.layout.light_brightness_progress);
		mSeekBar = (SeekBar) brightnessDialog.findViewById(R.id.brightness_seekbar);
		Button btnClose = (Button) brightnessDialog.findViewById(R.id.btn_close);
		btnClose.setOnClickListener(quitView);
		mSeekBar.setMax(100);
		mSeekBar.setOnSeekBarChangeListener(this);
	}
	
	public void setParams(DatagramHandle datagramHandle, SingleControl singleControl, ButtonEx be) {
		mDatagramHandle = datagramHandle;
		mSingleControl = singleControl;
		currentBtne = be;
	}
	
	private View.OnClickListener quitView = new View.OnClickListener() {
		
//		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			brightnessDialog.dismiss();
		}
	};
	
	public void show() {
		if (mSingleControl == null || mDatagramHandle ==  null || currentBtne == null) {
			AlertDialog.Builder b = new AlertDialog.Builder(ctx);
			b.setMessage("先用setParams函数设置参数先");
			b.setPositiveButton(R.string.btn_sure, null);
		} else {
			brightnessDialog.show();
		}
	}
	
	public void dismiss() {
		brightnessDialog.dismiss();
	}
	
	public void setLightHardwareID(int id) {
		mSingleControl.setHardwareId(id);
	}
	
	public void setLightTargetID(int type) {
		mSingleControl.setTargetId(type);
	}

	public void setBrightness(int brightness) {
		mSeekBar.setProgress(brightness);
	}
	
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		mSingleControl.setTargetState(progress);
		mDatagramHandle.send(mSingleControl.getCmd());
		currentBtne.setBrightness(progress);
		mSeekBar.setProgress(progress);
		Log.e(TAG, " start progress = " + progress);
	}

//	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		mSingleControl.setTargetState(mSeekBar.getProgress());
		mDatagramHandle.send(mSingleControl.getCmd());
		currentBtne.setBrightness(mSeekBar.getProgress());
		Log.e(TAG, " stop progress = " + mSeekBar.getProgress());
		mSeekBar.setProgress(mSeekBar.getProgress());
	}
	
	
}
