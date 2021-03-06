package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Data.BillData;
import com.app.manikandanr.sampleclients.Data.BillResponseData;
import com.app.manikandanr.sampleclients.Data.Payment;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BillActivity extends AppCompatActivity {

    public static int white = 0xFFFFFFFF;
    public static int black = 0xFF000000;
    public final static int WIDTH = 500;
    String value = "";

    private Button btnSubmit;
    private EditText eBillNumber;
    private String payMode = "";
    private String student_id;
    private String studId, paymentMode, initialAmount, totalAmount, tenureMonth, dueDate, paymentStatus, tenureAmount, balanceAmount;
    String serialNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        student_id = getIntent().getExtras().getString("stud_id");
//        in.putExtra("stud_id",""+txtId);
//        in.putExtra("payment_mode","2");
//        in.putExtra("initial_amount",""+eCaltxt.getText().toString());
//        in.putExtra("total_amount",""+txtCost);
//        in.putExtra("tenure",""+emiSelection);
//        in.putExtra("due_date","10-12-2018");
//        in.putExtra("payment_status","3");
//        in.putExtra("tenure_amount",""+tenureAmount);
//        in.putExtra("balance_amount",""+getBalanceAmount());

        init();
    }


    private void init() {
        eBillNumber = findViewById(R.id.edt_bill);
        studId = getIntent().getStringExtra("stud_id");
        paymentMode = getIntent().getStringExtra("payment_mode");
        initialAmount = getIntent().getStringExtra("initial_amount");
        totalAmount = getIntent().getStringExtra("total_amount");
        tenureMonth = getIntent().getStringExtra("tenure");
        paymentStatus = getIntent().getStringExtra("payment_status");
        tenureAmount = getIntent().getStringExtra("tenure_amount");
        balanceAmount = getIntent().getStringExtra("balance_amount");
        btnSubmit = findViewById(R.id.btn_bill);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (initialAmount.equalsIgnoreCase("0")) {
                    payMode = "2";
                } else {
                    payMode = "1";
                }

                if (!eBillNumber.getText().toString().isEmpty()) {
                    Log.e("RESPONSE111", "///student_id" + "" + studId +
                            "///payment_mode" + payMode +
                            "///initial_amount" + "" + initialAmount +
                            "///total_amount" + "" + totalAmount +
                            "///tenure" + "" + tenureMonth +
                            "///payment_status" + "" + paymentStatus +
                            "///tenure_amount" + "" + tenureAmount +
                            "///balance_amount" + "" + balanceAmount +
                            "///quotation_id" + eBillNumber.getText().toString().trim());
                    completeCashMethod();
                } else {
                    eBillNumber.setError("Enter Bill Number");
                }

            }
        });
    }

    private void completeCashMethod() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/payment";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final String status;
                            final String message;
                            String studentName = null;
                            String billNumber = null;
                            String paymentMethod;
                            String amountPaid = null;
                            Log.e("RESPONSE1Bill", "" + response);

                            JSONObject jobj = new JSONObject(response);
                            status = jobj.getString("status");
                            message = jobj.getString("message");
                            if (status.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)) {
                                JSONArray paymentArray = jobj.getJSONArray("payment");
                                //{"status":1,"message":"Successfully Inserted!","payment":{"student_id":"5","payment_mode":"2","initial_amount":0,"total_amount":"38500.0","tenure":0,"tenure_amount":0,"due_date":"2019-04-27","balance_amount":0,"payment_status":3,"online_transaction_id":null,"quotation_id":"94979467","updated_at":"2019-03-27 00:27:31","created_at":"2019-03-27 00:27:31","id":4}}

                                for (int i = 0; i < paymentArray.length(); i++) {
                                    JSONObject paymentObj = paymentArray.getJSONObject(i);
                                    paymentMethod = paymentObj.getString("payment_mode");
                                    amountPaid = paymentObj.getString("total_amount");
                                    billNumber = paymentObj.getString("quotation_id");

                                    JSONObject studentObj = paymentObj.getJSONObject("student");
                                    studentName = studentObj.getString("name");
                                    serialNumber = studentObj.getString("serial_no");
                                }
                                if (initialAmount.equalsIgnoreCase(Constants.FULLCASH)) {
                                    value = "Student Name  : " + studentName + "\n" +
                                            "Serial Number : " + serialNumber + "\n" +
                                            "Bill Number   : " + billNumber + "\n" +
                                            "Payment Method:  CASH\n" +
                                            "Amount Paid   : " + amountPaid;
                                } else {
                                    value = "Student Name  : " + studentName + "\n" +
                                            "Serial Number : " + serialNumber + "\n" +
                                            "Bill Number   : " + billNumber + "\n" +
                                            "Payment Method:  CASH\n" +
                                            "Amount Paid   : " + amountPaid;
                                }


                                new AwesomeSuccessDialog(BillActivity.this).setTitle("Admission Status")
                                        .setMessage("Admission Successfull.")
                                        .setColoredCircle(R.color.colorPrimary)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setPositiveButtonText("Ok")
                                        .setPositiveButtonbackgroundColor(R.color.colorPrimary)
                                        .setPositiveButtonTextColor(R.color.white)
                                        .setPositiveButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in = new Intent(BillActivity.this, ViewBill.class);
                                                in.putExtra("detail", "" + value);
                                                in.putExtra("stud_id", student_id);
                                                in.putExtra("reg_num", serialNumber);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(BillActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BillActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_id", "" + studId);
                params.put("payment_mode", payMode);// for emi
                params.put("total_amount", "" + totalAmount);
                params.put("balance_amount", "0.0");
                params.put("quotation_id", eBillNumber.getText().toString().trim());
                params.put("online_transaction_id","");

                //  params.put("initial_amount", "" + initialAmount); //
              //  params.put("tenure", "" + tenureMonth);
             //   params.put("tenure_amount", "" + tenureAmount);
             //   params.put("balance_amount", "" + balanceAmount);
               // params.put("quotation_id", eBillNumber.getText().toString().trim());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

}
