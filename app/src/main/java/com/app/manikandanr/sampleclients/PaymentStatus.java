package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PaymentStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);
    }

    public void onClickCash(View v)
    {
        Intent in = new Intent(PaymentStatus.this,CashActivity.class);
        startActivity(in);
        finish();
    }
}
