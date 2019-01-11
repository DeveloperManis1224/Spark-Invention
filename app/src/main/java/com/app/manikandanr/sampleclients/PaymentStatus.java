package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.manikandanr.sampleclients.Utils.Constants;

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
        LayoutInflater factory = LayoutInflater.from(PaymentStatus.this);
        final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(PaymentStatus.this).create();
        deleteDialog.setView(deleteDialogView);
        Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);
        Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
        Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);

        btnSchool.setText("Pay Money");
        btnSchool.setAllCaps(false);

        btnCollege.setText("Complete Payment");
        btnCollege.setAllCaps(false);

        btnProject.setText("Project / Program");
        btnProject.setVisibility(View.GONE);
        btnProject.setAllCaps(false);

        btnSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
                Intent in = new Intent(PaymentStatus.this, WebViewPayment.class);
                startActivity(in);
            }
        });
        btnCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
                Intent in = new Intent(PaymentStatus.this, CompletePayment.class);
                in.putExtra("cost",getIntent().getStringExtra("cost"));
                in.putExtra(Constants.STUDENT_ID,studentId);
                startActivity(in);
            }
        });
        btnProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });
        deleteDialog.show();
        //Toast.makeText(this, "Online Payment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaymentStatus.this, MenuActivity.class));
        finish();
    }
}
