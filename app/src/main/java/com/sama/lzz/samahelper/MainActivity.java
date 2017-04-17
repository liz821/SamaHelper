package com.sama.lzz.samahelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sama.lzz.samahelper.db.MasterDBHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//TODO 混淆
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    EditText ed1,ed2,ed3,ed4;
    String name,description,amount,location;
    MasterDBHelper dbHelper;
    void  findView(){
        //ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        //抽屉
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //抽屉导航
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);


    }
    void init() {

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        Snackbar.make(getCurrentFocus(), ""+id, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void insert() {
        name = ed1.getText().toString();
        description = ed2.getText().toString();
        location = ed3.getText().toString();
        amount = ed4.getText().toString();
        dbHelper = new MasterDBHelper(this);
        dbHelper.insert(name,description,location,amount);
    }

    String[] quirey(String Qname) {
        Boolean QTAG=false; //  是否查询到数据
        Cursor cursor = dbHelper.query();
        while (cursor.moveToNext()) {   //  指向第一条表中数据,逐行向下
            name = cursor.getString(cursor.getColumnIndex("name"));//该行name列
            if (Qname.equals(name)){
                description = cursor.getString(cursor.getColumnIndex("description"));
                location = cursor.getString(cursor.getColumnIndex("location"));
                amount = cursor.getString(cursor.getColumnIndex("amount"));
                QTAG=true;
            }

        }
        if (QTAG){
            return new String[]{description,location,amount};
        }else  return  null;
    }

    void delete(String name,String location) {
        SQLiteDatabase db = dbHelper.getDB();
        db.execSQL("DELETE FROM master WHERE name ="+name+"\tand\t"+"location ="+location);
    }

    void alter(String name,String location,String amount) {
        SQLiteDatabase db = dbHelper.getDB();
        db.execSQL("UPDATE master SET amount ="+amount+" WHERE name="+name+"\tand\t"+"location ="+location);
    }
}
