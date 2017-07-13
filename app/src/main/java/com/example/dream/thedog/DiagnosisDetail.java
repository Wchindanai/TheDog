package com.example.dream.thedog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DiagnosisDetail extends AppCompatActivity {
    private TextView diagnosis, title, symptom, medication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_detail);
        Bundle bundle = getIntent().getExtras();

        String titleText = bundle.getString("title");
        String diagnosisText = bundle.getString("diagnosis");
        String symptomText = bundle.getString("symptom");
        String medicationText = bundle.getString("medication");
        setTitle("ผลการวินิจฉัย");

        title = (TextView) findViewById(R.id.title);
        diagnosis = (TextView) findViewById(R.id.diagnosis);
        symptom = (TextView) findViewById(R.id.symptom);
        medication = (TextView) findViewById(R.id.medication);

        title.setText(titleText);
        diagnosis.setText(diagnosisText);
        symptom.setText(symptomText);
        medication.setText(medicationText);




    }
}
