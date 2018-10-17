package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Utils.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CashActivity extends AppCompatActivity {

    private Button bSubmit;
    RadioButton rb;
    RadioGroup rdoGrp ;
    private RadioButton rCash,rEmi;
    private LinearLayout lytCash,lytEmi;
    private String txtId;
    private double txtCost;
    private TextView txt_Cost;
    TextView monthEmi3,monthEmi6,monthEmi9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        bSubmit = findViewById(R.id.btn_final_submit);
        rCash = findViewById(R.id.radio_cash);
        rdoGrp = findViewById(R.id.rdo_payment);
        rEmi = findViewById(R.id.radio_emi);
        lytCash = findViewById(R.id.div_fullcash);
        lytEmi = findViewById(R.id.div_calculate);
        txt_Cost = findViewById(R.id.txt_cost);
        monthEmi3 = findViewById(R.id.txt_month3);
        monthEmi6 = findViewById(R.id.txt_month6);
        monthEmi9 = findViewById(R.id.txt_month9);

        txtCost = Double.parseDouble(getIntent().getStringExtra("cost"));
        txtId= getIntent().getStringExtra("stud_id");

       // setCalulation();

        txt_Cost .setText("Rs "+txtCost);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(rb.getText().toString().equalsIgnoreCase("FULL CASH"))
                {
                    Intent in = new Intent(CashActivity.this,BillActivity.class);
                    in.putExtra("cash_method","1");
                    startActivity(in);
                }
                else if(rb.getText().toString().equalsIgnoreCase("EMI"))
                {
                    Intent in = new Intent(CashActivity.this,BillActivity.class);
                    in.putExtra("cash_method","1");
                    startActivity(in);
                }
            }
        });
        rdoGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              rb =(RadioButton)findViewById(checkedId);
                if(rb.getText().toString().equalsIgnoreCase("FULL CASH"))
                {
                    lytCash.setVisibility(View.GONE);
                    lytEmi.setVisibility(View.GONE);
                    bSubmit.setText("Amount Paid and Submit");
                }
                else if (rb.getText().toString().equalsIgnoreCase("EMI"))
                {
                    Toast.makeText(CashActivity.this, "Please select plan", Toast.LENGTH_SHORT).show();
                    lytCash.setVisibility(View.GONE);
                    lytEmi.setVisibility(View.VISIBLE);
                }
            }
        });

//        rCash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lytCash.setVisibility(View.VISIBLE);
//                lytEmi.setVisibility(View.GONE);
//            }
//        });
//        rEmi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lytEmi.setVisibility(View.VISIBLE);
//                lytCash.setVisibility(View.GONE);
//            }
//        });
    }


//    private void setCalulation()
//    {
//        double total_amount = (int)txtCost;
//        monthEmi3 .setText( "Rs "+total_amount/3);
//        monthEmi6.setText("Rs "+total_amount/6);
//        monthEmi9.setText("Rs "+total_amount/9);
//    }

    public void onCalculateEmi(View v)
    {
        double total_amount = (int)txtCost;
        monthEmi3 .setText( "Rs "+total_amount/3);
        monthEmi6.setText("Rs "+total_amount/6);
        monthEmi9.setText("Rs "+total_amount/9);
    }

    private void completeCashMethod() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RESPONSE111", "" + response);

                            JSONObject jsonObject = new JSONObject(response);
                            String sts = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (sts.equalsIgnoreCase("1")) {
                                Toast.makeText(CashActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(CashActivity.this, AlertActivity.class);

                                startActivity(in);
                                finish();
                            } else {
                                Toast.makeText(CashActivity.this, "Submition failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CashActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        queue.add(stringRequest);
    }

}
