package com.example.dream.thedog;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
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

    private int _day;
    private int _month;
    private int _birthYear;

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
        birthDate.setFocusable(false);

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        List<String> titleList = new ArrayList<String>();
        titleList.add("Male");
        titleList.add("Female");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_spinner_dropdown_item, titleList);
        dropDown.setAdapter(arrayAdapter);

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    register();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // Listener
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            birthDate.setText(day1 + "/" + month1 + "/" + year1);

        }
    };

    private void datePicker() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date

// Create the DatePickerDialog instance
        DatePickerDialog datePicker = new DatePickerDialog(this,
                datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        datePicker.setCancelable(false);
        datePicker.setTitle("Select the Birth Date");
        datePicker.show();
    }


    private void register() throws JSONException {
        if(!validateData()){
            return;
        }
        OkHttpClient client = new OkHttpClient();
        String url = "https://daring-span-173305.appspot.com/api/members";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject user = new JSONObject();
        user.put("first_name", firstName.getText().toString());
        user.put("last_name", lastName.getText().toString());
        user.put("username", username.getText().toString());
        user.put("password", password.getText().toString());
        user.put("title", dropDown.getSelectedItem().toString());
        user.put("birth_date", birthDate.getText().toString());
        user.put("mobile_no", mobileNo.getText().toString());
        user.put("email", email.getText().toString());
        RequestBody body = RequestBody.create(JSON, user.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    finish();
                }
            }
        });

    }

    private boolean validateData() {
        boolean validate = true;
        if(TextUtils.isEmpty(username.getText().toString())){
            username.setError("Not Empty");
            validate = false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Not Empty");
            validate = false;
        }
        else if (TextUtils.isEmpty(firstName.getText().toString())){
            firstName.setError("Not Empty");
            validate = false;
        }
        else if (TextUtils.isEmpty(lastName.getText().toString())){
            lastName.setError("Not Empty");
            validate = false;
        }

        else if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("Not Empty");
            validate = false;
        }

        return validate;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, dayOfMonth);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(c.getTime());

        birthDate.setText(formattedDate);
    }
}
