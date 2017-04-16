package com.sama.lzz.samahelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.sama.lzz.samahelper.base.BaseActivity;

/**
 * Created by lzz on 2017/4/16.
 * 冰冻三尺,非一日之寒
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.splash_activity);
        goToActivityDelay(MainActivity.class,2000);
    }
}
