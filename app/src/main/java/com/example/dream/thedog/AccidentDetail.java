package com.example.dream.thedog;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class AccidentDetail extends AppCompatActivity {
    private TextView accident;
    private TextView symptom;
    private TextView medication;
    private TextView note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_detail);
        setTitle("ผลลัพธ์");
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setTitle("Accident");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        accident = (TextView) findViewById(R.id.title);
        symptom = (TextView) findViewById(R.id.symptom);
        medication = (TextView) findViewById(R.id.medication);
        note = (TextView) findViewById(R.id.note);
        Bundle buddle = getIntent().getExtras();
        if(buddle != null){
            accident.setText(buddle.getString("accident"));
            symptom.setText(buddle.getString("symptom"));
            medication.setText(buddle.getString("medication"));
            note.setText(buddle.getString("note"));
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
