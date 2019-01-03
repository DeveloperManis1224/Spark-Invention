package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ViewStudent extends AppCompatActivity {

    RecyclerView viewStudent;
    RecyclerView.LayoutManager lytMgr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        viewStudent = findViewById(R.id.view_student_list);
        lytMgr = new LinearLayoutManager(ViewStudent.this);
        viewStudent.setLayoutManager(lytMgr);

    }
}
