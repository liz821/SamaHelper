package com.sama.lzz.samahelper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lzz on 2017/3/23.
 * 冰冻三尺,非一日之寒
 */

public class MasterDBHelper extends SQLiteOpenHelper{
    String TAG=getClass().getName();
   static String DBName="master_db.db";
   private SQLiteDatabase db;
    public MasterDBHelper(Context context) {
        super(context, DBName, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
//        db.execSQL("create table "+"master(name varchar(30) primary key,description text)");
     //若有同名表不会重复创建
        db.execSQL("create table "+"master(name varchar(30) primary key,description text)");
//        db.execSQL("create table "+"appear(location varchar(18) primary key,amount varchar(26),name varchar(30), FOREIGN KEY REFERENCES master(name))");
        db.execSQL("create table "+"appear(location varchar(18) primary key,amount varchar(26),name varchar(30), FOREIGN KEY(name) REFERENCES master(name))");
        Log.d(TAG, "onCreate: ````````````DB```````````");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
   public void insert(String name,String description,String location,String amount){
       if (db == null) {
           //获得SQLiteDatabase实例
           db = getWritableDatabase();
       }
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("description",description);
        values.put("location",location);
        values.put("amount",amount);
        db.insert("master",null, values);
    }


    public Cursor query() {
        if (db == null) {
            //获得SQLiteDatabase实例
            db = getWritableDatabase();
        }
        //查询获得Cursor
        Cursor c = db.query("master", null, null, null, null, null, null);
        return c;
    }

    public void delete(int id) {//删除对应行
        if (db == null) {
            db = getWritableDatabase();
        }
        //执行删除
        db.delete("master", "_id=?", new String[]{String.valueOf(id)});
    }
    public SQLiteDatabase getDB(){
        return db;
    }
    public void initDB(){

    }

}
