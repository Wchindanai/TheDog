package com.example.dream.thedog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText mobileNo;
    private EditText birthDate;
    private Spinner dropDown;
    private Button singUp;
    private TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        mobileNo = (EditText) findViewById(R.id.mobile_number);
        email = (EditText) findViewById(R.id.email);
        birthDate = (EditText) findViewById(R.id.birth_date);
        dropDown = (Spinner) findViewById(R.id.spinner);
        singUp = (Button) findViewById(R.id.signUp);
        cancel = (TextView) findViewById(R.id.cancel);

        List<String> titleList = new ArrayList<String>();
        titleList.add("Male");
        titleList.add("Female");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_spinner_dropdown_item, titleList);
        dropDown.setAdapter(arrayAdapter);

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void register() {
    }

}
