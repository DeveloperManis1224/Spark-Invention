package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdmissionForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_form);
    }

    public void onNextClick(View view)
    {
        Intent in = new Intent(AdmissionForm.this,PaymentStatus.class);
        startActivity(in);
        finish();
    }
}
