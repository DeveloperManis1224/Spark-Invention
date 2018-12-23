package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AlertActivity extends AppCompatActivity {

    RecyclerView studList, clgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        studList = findViewById(R.id.alert_stud_list);
        clgList = findViewById(R.id.alert_clg_list);

        RecyclerView.LayoutManager lytstud = new LinearLayoutManager(AlertActivity.this);
        studList.setLayoutManager(lytstud);

        RecyclerView.LayoutManager lytClg = new LinearLayoutManager(AlertActivity.this);
        clgList.setLayoutManager(lytClg);


    }
}
