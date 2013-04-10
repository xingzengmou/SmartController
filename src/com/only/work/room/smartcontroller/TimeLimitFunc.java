package com.only.work.room.smartcontroller;

import com.only.work.room.brightnesscontrol.SharedPreferenceConfig;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

public class TimeLimitFunc {
	private static final String TAG = "TimeLimitFunc";

	public static boolean timeLimit(final Activity thiz) {
		boolean overTime = false;
		SharedPreferenceConfig sp = new SharedPreferenceConfig(thiz);
		java.util.Calendar c=java.util.Calendar.getInstance();    
        java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyyMMdd");
        String today = f.format(c.getTime());
        Log.e(TAG, "time = " + today);
        String time = sp.getString("date_time_key", "");
        if (!time.equals(today)) {
        	int dateCount = sp.getInt("time_count_key", 0);
        	if (dateCount == 0) {
        		sp.writeInt("time_count_key", 1);
//        		view.setVisibility(View.VISIBLE);
        	} else if (dateCount >= 16){
        		overTime = true;
        		AlertDialog.Builder b = new AlertDialog.Builder(thiz);
        		b.setMessage("对不起，软件的试用期已满，请联系当地的商家索取正式版软件，谢谢！");
        		b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						thiz.finish();
					}
				});
        		b.show();
        	} else {
        		sp.writeInt("time_count_key", dateCount + 1);
//        		view.setVisibility(View.VISIBLE);
        	}
        	if (!overTime) {
        		sp.writeString("date_time_key", today);
        	} else {
        		return false;
        	}
        } else {
//        	view.setVisibility(View.VISIBLE);
        }
        Log.e(TAG, " today = " + today + " time = " + time);
        return true;
	}
}
