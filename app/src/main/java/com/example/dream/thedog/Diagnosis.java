package com.example.dream.thedog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Diagnosis extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        setTitle("วินิจฉัยโรค");

        list = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        addListToAdapter();


        recyclerView.addItemDecoration(new DividerItemDecoration(getApplication(), DividerItemDecoration.VERTICAL));


    }

    private void addListToAdapter() {
        list.add("อาเจียน");
        list.add("ซึม");
        list.add("ไอ จาม");
        list.add("ระบบขับถ่าย");
        list.add("หู");
        list.add("ผิวหนัง");

        mAdapter = new DiagnosisListAdapter(list, getApplication());
        recyclerView.setAdapter(mAdapter);
    }
}
