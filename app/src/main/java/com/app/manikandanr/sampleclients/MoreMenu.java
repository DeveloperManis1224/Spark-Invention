package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
                startActivity(new Intent(MoreMenu.this,AddItems.class));
            }
        });

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreMenu.this,AlertActivity.class));
            }
        });
        btnViewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreMenu.this,StudentScanner.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MoreMenu.this, MenuActivity.class));
        finish();
    }
}
