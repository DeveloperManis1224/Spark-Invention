package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.app.manikandanr.sampleclients.Data.Payment;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CompletePayment extends AppCompatActivity {
    String value = "";
    private EditText mBill, mTransNumber;
    private String payMode="";
    private String student_id, billStudentName,billStudentRoll,billQuationId,billInitialAmount,billTotalAmount;
    private String studId,paymentMode,initialAmount,totalAmount,tenureMonth,dueDate,paymentStatus,tenureAmount,balanceAmount;
    String serialNumber;
    //8610853621
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
        mBill = findViewById(R.id.edt_bill);
        mTransNumber = findViewById(R.id.edt_trans_num);
        totalAmount = getIntent().getStringExtra("cost");
        student_id = getIntent().getStringExtra(Constants.STUDENT_ID);

    }

    public void onClickSumbitPayment(View v)
    {
        if(!mBill.getText().toString().isEmpty()&&!mTransNumber.getText().toString().isEmpty())
        {
            completeCashMethod();
        }
        else
        {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
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

                                for (int i = 0; i < paymentArray.length(); i++) {
                                    JSONObject paymentObj = paymentArray.getJSONObject(i);
                                    paymentMethod = paymentObj.getString("payment_mode");
                                    amountPaid = paymentObj.getString("initial_amount");
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
                                            "Payment Method:  EMI\n" +
                                            "Amount Paid   : " + amountPaid;
                                }

                                new AwesomeSuccessDialog(CompletePayment.this).setTitle("Admission Status")
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
                                                Intent in = new Intent(CompletePayment.this, ViewBill.class);
                                                in.putExtra("detail", "" + value);
                                                in.putExtra("stud_id", student_id);
                                                in.putExtra("reg_num", serialNumber);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(CompletePayment.this, "" + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompletePayment.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_id",""+studId);
                params.put("payment_mode",Constants.ONLINE_PAYMENT);// for emi
//                params.put("initial_amount",""+initialAmount); //
//                params.put("total_amount",""+totalAmount);
//                params.put("tenure","0");
//                params.put("payment_status","0");
//                params.put("tenure_amount","0.0");
 params.put("balance_amount","0.0");
                params.put("quotation_id",mBill.getText().toString().trim());
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
