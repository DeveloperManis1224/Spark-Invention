package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class CashActivity extends AppCompatActivity {

    private Button bSubmit;
    private RadioButton rCash,rEmi;
    private LinearLayout lytCash,lytEmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        bSubmit = findViewById(R.id.btn_final_submit);
        rCash = findViewById(R.id.radio_cash);
        rEmi = findViewById(R.id.radio_emi);
        lytCash = findViewById(R.id.div_fullcash);
        lytEmi = findViewById(R.id.div_emi);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(CashActivity.this,BillActivity.class);
                startActivity(in);
            }
        });

        rCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lytCash.setVisibility(View.VISIBLE);
                lytEmi.setVisibility(View.GONE);
            }
        });
        rEmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lytEmi.setVisibility(View.VISIBLE);
                lytCash.setVisibility(View.GONE);
            }
        });
    }
}
