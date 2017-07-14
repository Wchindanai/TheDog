package com.example.dream.thedog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DiagnosisStep3 extends AppCompatActivity {
    private int yes, no;
    private TextView question;
    private Button yesBtn, noBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_step3);
        final Bundle bundle = getIntent().getExtras();
        yes = bundle.getInt("yes");
        no = bundle.getInt("no");
        setTitle("วินิจฉัยโรค");
        String questionString = bundle.getString("question");

        question = (TextView) findViewById(R.id.question);
        question.setText(questionString);
        yesBtn = (Button) findViewById(R.id.yes);
        noBtn = (Button) findViewById(R.id.no);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResultFromAPI(yes);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResultFromAPI(no);
            }
        });

    }

    private void getResultFromAPI(int id) {
        OkHttpClient client = new OkHttpClient();
        if(id > 200){
            getNextQuestion(id);
        }
        else{
            getResult(id);
        }
    }

    private void getResult(int id) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://daring-span-173305.appspot.com/api/diagnosis/"+id;
        Request request = new Request.Builder()
                .url(url)
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
                if(response.code() == 200){
                    try {
                        JSONObject responseJson = new JSONObject(response.body().string());
                        JSONObject data = responseJson.getJSONObject("data");
                        String diagnosis = data.getString("diagnosis");
                        String symptom = data.getString("symptom");
                        String medication = data.getString("medication");
                        int id = data.getInt("id");
                        Intent intent = new Intent(getApplication(), DiagnosisDetail.class);
                        intent.putExtra("diagnosis", diagnosis);
                        intent.putExtra("symptom", symptom);
                        intent.putExtra("medication", medication);
                        if(id < 100){
                            intent.putExtra("title", "สุนัขของท่านมีโอกาศที่จะเป็นโรค");
                        }
                        else {
                            intent.putExtra("title", "สุนัขของท่าน");
                        }
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void getNextQuestion(int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://daring-span-173305.appspot.com/api/question/"+id;
        Request request = new Request.Builder()
                .url(url)
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
                if(response.code() == 200){
                    try {
                        JSONObject responseJson = new JSONObject(response.body().string());
                        String question = responseJson.getJSONObject("data").getString("question");
                        int yes = responseJson.getJSONObject("data").getInt("yes");
                        int no = responseJson.getJSONObject("data").getInt("no");
                        Intent intent = new Intent(getApplication(), DiagnosisStep3.class);
                        intent.putExtra("question", question);
                        intent.putExtra("yes", yes);
                        intent.putExtra("no", no);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
