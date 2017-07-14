package com.example.dream.thedog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dream on 6/17/17.
 */

public class Login extends AppCompatActivity {
    private TextView register;
    private Button signIn;
    private EditText username;
    private EditText password;
    private ProgressBar progressBar;

    private SharedPreferences sharedPreferences;
    private static final String MyPREFERENCES = "TheDog";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        register = (TextView) findViewById(R.id.register);
        //When Click Register Text and Start Register Page
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Register.class));
            }
        });

        signIn = (Button) findViewById(R.id.sing_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signIn();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private void signIn() throws JSONException {
        String userName = username.getText().toString();
        String passWord = password.getText().toString();

        OkHttpClient client = new OkHttpClient();
        String url = "https://daring-span-173305.appspot.com/api/login";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject member = new JSONObject();
        member.put("username", userName);
        member.put("password", passWord);
        RequestBody body = RequestBody.create(JSON, member.toString());
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
                if(response.code() == 200){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int id = jsonObject.getJSONObject("data").getInt("id");
                        String name = jsonObject.getJSONObject("data").getString("name");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("is_login", true);
                        editor.putInt("id", id);
                        editor.putString("name", name);
                        editor.commit();
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplication(), "Username Or Password Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


    }
}
