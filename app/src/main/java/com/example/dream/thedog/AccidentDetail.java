package com.example.dream.thedog;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class AccidentDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_detail);
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setTitle("Accident");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
