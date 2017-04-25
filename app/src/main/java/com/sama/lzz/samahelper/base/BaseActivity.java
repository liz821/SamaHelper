package com.sama.lzz.samahelper.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sama.lzz.samahelper.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 所有活动界面的父类
 * @author  ZhangChi
 * @category  2016年1月8日
 */
public class BaseActivity extends Activity {
	/*Toast提示*/
	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}


    public Boolean goToActivityDelay(final Class<?extends Activity> activity,int delay) {

		Timer time = new Timer();
		TimerTask tk = new TimerTask() {
			@Override
			public void run() {
                Intent intent = new Intent(getApplicationContext(),activity);
				startActivity(intent);
				finish();
			}
		};time.schedule(tk, delay);
		return  true;
	}


}
