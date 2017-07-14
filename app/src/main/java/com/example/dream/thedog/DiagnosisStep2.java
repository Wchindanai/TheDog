package com.example.dream.thedog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DiagnosisStep2 extends AppCompatActivity {
    private static final String TAG = "DiagnosisStep2";
    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<QuestionModel> questionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_step2);
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("type");
        setTitle("วินิจฉัยโรค");
        questionModelList = new ArrayList<>();

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        fetchQuestionFromApi(type);
        rv.addItemDecoration(new DividerItemDecoration(getApplication(), DividerItemDecoration.VERTICAL));

    }

    private void fetchQuestionFromApi(String type) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://daring-span-173305.appspot.com/api/question?type=" + type;
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
                if (response.code() == 200) {
                    try {
                        JSONObject responseJson = new JSONObject(response.body().string());
                        JSONArray dataArray = responseJson.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++){
                            JSONObject questionJson = dataArray.getJSONObject(i);
                            int id = questionJson.getInt("id");
                            String question = questionJson.getString("question");
                            int yes = questionJson.getInt("yes");
                            int no = questionJson.getInt("no");
                            String type = questionJson.getString("type");
                            QuestionModel questionModel = new QuestionModel(id, yes, no, question, type);
                            questionModelList.add(questionModel);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new DiagnosisAdapter(getApplication(), questionModelList);
                                rv.setAdapter(mAdapter);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
