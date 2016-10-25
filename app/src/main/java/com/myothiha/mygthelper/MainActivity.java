package com.myothiha.mygthelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int homeMarker = 0;
    Calendar calendar;
    Fragment newSchedule;
    int id;
    NavigationView navigationView;
    static SharedPreferences setup1Data;
    String home;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup1Data = getSharedPreferences(SettingPage.MYPERFS, SettingPage.mode);
        home = setup1Data.getString("home", "default");
        calendar = Calendar.getInstance();;
        newSchedule = new schedule();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);;
        View header = navigationView.getHeaderView(0);

        ImageView logo = (ImageView) header.findViewById(R.id.logo);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count > 5) {
                    Intent intent = new Intent(getApplicationContext(), credits.class);
                    startActivity(intent);
                }
            }
        });
        if(home.equals("default")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new WebView_Pages()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        if(home.equals("schedule")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newSchedule).commit();
            navigationView.setCheckedItem(R.id.nav_schedule);
        }
    }


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
        id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            navigationView.setCheckedItem(R.id.nav_home);
            recreate();
        }
        if(id == R.id.action_settings){
            Intent intent = new Intent(getApplicationContext(), SettingPage.class);
            startActivity(intent);
        }
        return true;
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new home()).commit();
        } else if (id == R.id.nav_schedule) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newSchedule).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onStart(){
        super.onStart();
        if(homeMarker > 0) {
            getSupportFragmentManager().beginTransaction().detach(newSchedule);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new home()).commit();
        }
        homeMarker = 1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(id==R.id.nav_schedule) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newSchedule).commit();
            navigationView.setCheckedItem(R.id.nav_schedule);
        }
    }
}
