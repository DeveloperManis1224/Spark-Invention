package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentStatus extends AppCompatActivity {
    String studentId = "";
    private TextView txtCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        txtCost = findViewById(R.id.txt_cost_payment);
        txtCost.setText("Rs "+getIntent().getStringExtra("cost"));
        studentId = getIntent().getStringExtra("stud_id");

    }

    public void onClickCash(View v)
    {
        Intent in = new Intent(PaymentStatus.this,CashActivity.class);
        in.putExtra("cost", getIntent().getStringExtra("cost"));
        in.putExtra("stud_id",studentId);
        startActivity(in);
        finish();
    }

    public void onClickPayment(View v)
    {
        Toast.makeText(this, "Online Payment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaymentStatus.this, MenuActivity.class));
        finish();
    }
}
