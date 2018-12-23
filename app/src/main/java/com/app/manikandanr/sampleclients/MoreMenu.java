package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoreMenu extends AppCompatActivity {

    Button btnAlert, btnViewStudent, btnAddItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_menu);

        init();
    }

    private void init()
    {
        btnAddItems = findViewById(R.id.btn_add_items);
        btnAlert = findViewById(R.id.btn_alert);
        btnViewStudent = findViewById(R.id.btn_view_student);


        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnViewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
