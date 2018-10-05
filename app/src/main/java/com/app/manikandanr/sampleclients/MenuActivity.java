package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button bAdmission,bMarketing,bAttendance,bRevenue,bStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }
    private void init()
    {

        bAdmission  = findViewById(R.id.btn_admission);
        bMarketing = findViewById(R.id.btn_marketing);
        bAttendance = findViewById(R.id.btn_attendance);
        bRevenue = findViewById(R.id.btn_revenue);
        bStore = findViewById(R.id.btn_store);
        bAdmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MenuActivity.this,AdmissionForm.class);
                startActivity(in);
            }
        });

        bMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MenuActivity.this,AdmissionForm.class);
                startActivity(in);
            }
        });
        bAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MenuActivity.this,AdmissionForm.class);
                startActivity(in);
            }
        });

    }



    @Override
    public void onBackPressed() {
        finish();
    }
}
