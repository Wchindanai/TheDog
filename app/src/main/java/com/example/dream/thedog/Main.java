package com.example.dream.thedog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Main extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private static final String MyPREFERENCES = "TheDog";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return showHomePage();
                case R.id.navigation_hospital:
                    return showListHospital();
                case R.id.navigation_near_by:
                    return showNearByPage();
                case R.id.navigation_member:
                    return getAuthen();
            }
            return false;
        }

    };

    private boolean showListHospital() {
        getFragmentManager().beginTransaction().replace(R.id.content, new ListHospital()).commit();
        return true;
    }

    private boolean showHomePage() {
        getFragmentManager().beginTransaction().replace(R.id.content, new Home()).commit();
        return true;
    }

    private boolean showNearByPage() {
        getFragmentManager().beginTransaction().replace(R.id.content, new NearBy()).commit();
        return true;
    }


    private boolean getAuthen() {
        boolean isLogin = sharedPreferences.getBoolean("is_login", false);
        if (!isLogin) {
            startActivity(new Intent(getApplication(), Login.class));
            return false;
        } else {
            int userId = sharedPreferences.getInt("user_id", 0);
            Bundle bundle = new Bundle();
            bundle.putInt("userId", userId);
            Member member = new Member();
            member.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.content, new Member()).commit();
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemBackgroundResource(R.color.colorPrimary);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getFragmentManager().beginTransaction().replace(R.id.content, new Home()).commit();
    }

}
