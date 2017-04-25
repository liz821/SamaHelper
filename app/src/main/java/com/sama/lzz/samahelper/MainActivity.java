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
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sama.lzz.samahelper.db.MasterDBHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    EditText ed1, ed2, ed3, ed4;
    String name, description, amount, location;
    MasterDBHelper dbHelper;
    Button confirm, insert, delete, quirey, alter;
    TextView result;
    String CTAPE;

    //TODO 混淆
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    void findView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        confirm = (Button) findViewById(R.id.confirm);
        insert = (Button) findViewById(R.id.insert);
        delete = (Button) findViewById(R.id.delete);
        quirey = (Button) findViewById(R.id.quirey);
        alter = (Button) findViewById(R.id.alter);
        result = (TextView) findViewById(R.id.result);

    }

    void init() {
        confirm.setOnClickListener(this);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        quirey.setOnClickListener(this);
        alter.setOnClickListener(this);
        //dbHelper
        dbHelper = new MasterDBHelper(this);
        dbHelper.getWritableDatabase();
        //ToolBar
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //抽屉
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //抽屉导航
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
        Snackbar.make(getCurrentFocus(), "" + id, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void getText() {
        name = ed1.getText().toString();
        description = ed2.getText().toString();
        location = ed3.getText().toString();
        amount = ed4.getText().toString();
    }

    void insert() {
        getText();
        dbHelper.insert(name, description, location, amount);
    }

    String[] quirey(String Qname) {
        Boolean QTAG = false; //  是否查询到数据
        Cursor cursor = dbHelper.query();
        while (cursor.moveToNext()) {   //  指向第一条表中数据,逐行向下
            name = cursor.getString(cursor.getColumnIndex("name"));//该行name列
            if (Qname.equals(name)) {
                description = cursor.getString(cursor.getColumnIndex("description"));
                location = cursor.getString(cursor.getColumnIndex("location"));
                amount = cursor.getString(cursor.getColumnIndex("amount"));
                QTAG = true;
            }

        }
        Log.d("MainActivity", "quirey: " + description+location+location);
        if (QTAG) {
            return new String[]{description, location, amount};
        } else return null;
    }

    void delete(String name, String location) {
        SQLiteDatabase db = dbHelper.getDB();
        db.execSQL("DELETE FROM master WHERE name =" + name + "\tand\t" + "location =" + location);
    }

    void alter(String name, String location, String amount) {
        SQLiteDatabase db = dbHelper.getDB();
        db.execSQL("UPDATE master SET amount =" + amount + " WHERE name=" + name + "\tand\t" + "location =" + location);
    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
             popupMenu.getMenu().add("添加怪物信息");
              popupMenu.getMenu().add("添加位置信息");
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()){
                            case "添加怪物信息":
                                ed1.setVisibility(View.VISIBLE);
                                ed2.setVisibility(View.VISIBLE);
                                ed3.setVisibility(View.GONE);
                                ed4.setVisibility(View.GONE);
                                Snackbar.make(getCurrentFocus(), "添加怪物信息", 1500).setAction("Action", null).show();
                                break;
                            case "添加位置信息":
                                ed1.setVisibility(View.GONE);
                                ed2.setVisibility(View.GONE);
                                ed3.setVisibility(View.VISIBLE);
                                ed4.setVisibility(View.VISIBLE);
                                Snackbar.make(getCurrentFocus(),"添加位置信息", 1500).setAction("Action", null).show();

                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();

                CTAPE = "insert";
                Snackbar.make(v, String.valueOf(v.getId()), 1500).setAction("Action", null).show();
                break;
            case R.id.delete:
                ed1.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.VISIBLE);
                ed2.setVisibility(View.GONE);
                ed4.setVisibility(View.GONE);
                CTAPE = "delete";
                Snackbar.make(v, String.valueOf(v.getId()), 1500).setAction("Action", null).show();
                break;
            case R.id.quirey:
                ed1.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.GONE);
                ed2.setVisibility(View.GONE);
                ed4.setVisibility(View.GONE);
                CTAPE = "quirey";
                Snackbar.make(v, String.valueOf(v.getId()), 1500).setAction("Action", null).show();
                break;
            case R.id.alter:
                ed1.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.GONE);
                ed2.setVisibility(View.VISIBLE);
                ed4.setVisibility(View.VISIBLE);
                CTAPE = "alter";
                Snackbar.make(v, String.valueOf(v.getId()), 1500).setAction("Action", null).show();
                break;
            case R.id.confirm:
                switch (CTAPE) {
                    case "insert":
                        insert();
                        break;
                    case "delete":

                        break;
                    case "quirey":
                        quirey(ed1.getText().toString());
                        break;
                    case "alter":
                        break;
                }
                showResult();
                Snackbar.make(v, String.valueOf(v.getId()), 1500).setAction("Action", null).show();
                break;
        }
    }
    void showResult(){
        String[] Qresult;
        Qresult=quirey(ed1.getText().toString());
        result.setText("妖怪名\t"+Qresult[0]+"\n妖怪位置\t"+Qresult[1]+"\n妖怪数量\t"+Qresult[2]);
    }
}
