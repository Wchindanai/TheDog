package com.example.dream.thedog;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Acident extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<AccidentModel> accidentList;
    private static final String TAG = "Acident";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acident);
        setTitle("อุบติเหตุ");
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
        }


        accidentList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        getAccidentFromApi();


        recyclerView.addItemDecoration(new DividerItemDecoration(getApplication(), DividerItemDecoration.VERTICAL));

    }

    private void getAccidentFromApi() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://daring-span-173305.appspot.com/api/accident";
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
                        JSONObject jsonResponse = new JSONObject(response.body().string());
                        JSONArray data = jsonResponse.getJSONArray("data");
                        for(int i = 0; i < data.length(); i++){
                            JSONObject dataObject = data.getJSONObject(i);
                            String accident = dataObject.getString("accident");
                            String symptom = dataObject.getString("symptom");
                            String medication = dataObject.getString("medication");
                            String note = dataObject.getString("note");
                            if(!note.isEmpty()){
                                note = "-";
                            }
                            AccidentModel accidentModel = new AccidentModel(accident, symptom, medication, note);
                            accidentList.add(accidentModel);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new AccidentAdapter(accidentList ,getApplication());
                                recyclerView.setAdapter(mAdapter);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

}
