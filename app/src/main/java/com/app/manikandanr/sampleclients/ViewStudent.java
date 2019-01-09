package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.Utils.Constants;

public class ViewStudent extends AppCompatActivity {

    private TextView mBasicInfo;
    private TextView mOtherInfo;
    private Button btnPayEmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        mBasicInfo = findViewById(R.id.txt_basic_info);
        mOtherInfo = findViewById(R.id.txt_other_info);
        btnPayEmi = findViewById(R.id.btn_pay_emi);

        mBasicInfo.setText(getIntent().getExtras().getString(Constants.STUDENT_BASIC_INFO));
        mOtherInfo.setText(getIntent().getExtras().getString(Constants.STUDENT_OTHER_INFO));

        String paidStatus = getIntent().getExtras().getString(Constants.STUDENT_PAYMENT_STATUS);

        if(paidStatus.equalsIgnoreCase("1"))
        {
            btnPayEmi.setVisibility(View.VISIBLE);
        }
        else
        {
            btnPayEmi.setVisibility(View.GONE);
        }

        btnPayEmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
