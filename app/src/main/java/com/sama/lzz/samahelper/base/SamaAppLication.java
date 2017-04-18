package com.sama.lzz.samahelper.base;

import android.annotation.SuppressLint;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.sama.lzz.samahelper.db.MasterDBHelper;


/***
 * app 应用开始运行时  数据加载
 * @author zhangchi
 */
@SuppressLint({ "HandlerLeak", "NewApi" }) public class SamaAppLication extends Application {
SQLiteDatabase db;


	@Override
	public void onCreate() {
		super.onCreate();
		// 捕捉全局异常
//		CrashHandler crashHandler = CrashHandler.getInstance();
//		crashHandler.init(getApplicationContext());

		//数据库初始化
		initDatabase();
	}







	/**
	 * 数据库初始化
	 */
	private void initDatabase() {

		if (db==null){
			db=new MasterDBHelper(this).getDB();
		}
	}

	/**
	 * 关闭数据库
	 */
	public void closeDB() {
//		try {
//			if (liteOrm != null) {
//				liteOrm.close();
//				liteOrm = null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTerminate() {
		closeDB();
		super.onTerminate();
		//整体摧毁的时候调用这个方法
//		AppManager.getAppManager().AppExit(this);
	}


}
