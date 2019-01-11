package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                payEmiMethod();
            }
        });
    }

    private void payEmiMethod() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/payment";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewStudent.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("student_id",""+studId);
//                params.put("payment_mode",payMode);
//                params.put("initial_amount",""+initialAmount);
//                params.put("total_amount",""+totalAmount);
//                params.put("tenure",""+tenureMonth);
//                params.put("payment_status",""+paymentStatus);
//                params.put("tenure_amount",""+tenureAmount);
//                params.put("balance_amount",""+balanceAmount);
//                params.put("quotation_id",eBillNumber.getText().toString().trim());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent n = new Intent(ViewStudent.this,StudentScanner.class);
        startActivity(n);
        finish();
    }
}
