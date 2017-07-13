package com.example.dream.thedog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HospitalDetail extends AppCompatActivity {
    private TextView name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);
        Bundle bundle = getIntent().getExtras();
        String nameText = bundle.getString("name");
        String addressText = bundle.getString("address");
        setTitle("รายละเอียดโรงพยาบาล");

        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);

        name.setText(nameText);
        address.setText(addressText);
    }
}
