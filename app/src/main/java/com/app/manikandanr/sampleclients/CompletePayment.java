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
        if(!mBill.getText().toString().isEmpty()||!mTransNumber.getText().toString().isEmpty())
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
                            Log.e("RESPONSE1Bill", "" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String sts = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (sts.equalsIgnoreCase("1")) {
                                JSONArray jry = jsonObject.getJSONArray("payment");

                                for(int i = 0; i< jry.length(); i++) {
                                    JSONObject jobj = jry.getJSONObject(i);
                                    billQuationId = jobj.getString("quotation_id");
                                    JSONObject studentObject = jobj.getJSONObject("student");
                                    student_id = studentObject.getString("id");
                                    billStudentName = studentObject.getString("name");
                                    billStudentRoll = studentObject.getString("serial_no");
                                    //billInitialAmount = studentObject.getString("initial_amount");
                                    // billTotalAmount = studentObject.getString("total_amount");
                                    try {
                                        JSONObject jobj1 = jobj.getJSONObject("payment_plan");
                                        dueDate = jobj1.getString("due_date");
                                        billInitialAmount = jobj1.getString("initial_amount");
                                        billTotalAmount = jobj1.getString("total_amount");
                                    } catch (Exception ex) {
                                        Log.e("ERROR", ex.getMessage());
                                        ex.printStackTrace();
                                    }
                                }



//                                value = "Bill number : "+eBillNumber.getText().toString().trim()+
//                                        "\n Student Roll Number : "+studId+"\n Payment Mode : "+paymentMode+"\n Initial Amount : "+
//                                        initialAmount+"\n Total amount : "+totalAmount+"\n Tenure month : "+ tenureMonth
//                                        +"\n Next Due Date : "+dueDate+"\n Payment Status : "+paymentStatus+"\n Tenure Amount : "+tenureAmount+
//                                        "\n Balance Amount : "+balanceAmount;

                                if(initialAmount.equalsIgnoreCase("0"))
                                {
                                    //full cash
//                                    value = "Bill number : "+eBillNumber.getText().toString().trim()+
//                                            "\n Student Roll Number : "+studId+"\n Payment Mode : "+paymentMode+"\n Initial Amount : "+
//                                            initialAmount+"\n Total amount : "+totalAmount+"\n Tenure month : "+ tenureMonth
//                                            +"\n Next Due Date : "+dueDate+"\n Payment Status : "+paymentStatus+"\n Tenure Amount : "+tenureAmount+
//                                            "\n Balance Amount : "+balanceAmount;

                                    value = "Student Name  : "+billStudentName+"\n" +
                                            "Serial Number : "+billStudentRoll+"\n"+
                                            "Bill Number   : "+billQuationId+"\n"+
                                            "Payment Status:  Paid\n"+
                                            "Payment Method:  Cash\n"+
                                            "Amount Paid   : "+billTotalAmount;

                                }
                                else
                                {
                                    //emi
//                                    value = "Bill number : "+eBillNumber.getText().toString().trim()+
//                                            "\n Student Roll Number : "+studId+"\n Payment Mode : "+paymentMode+"\n Initial Amount : "+
//                                            initialAmount+"\n Total amount : "+totalAmount+"\n Tenure month : "+ tenureMonth
//                                            +"\n Next Due Date : "+dueDate+"\n Payment Status : "+paymentStatus+"\n Tenure Amount : "+tenureAmount+
//                                            "\n Balance Amount : "+balanceAmount;

                                    value = "Student Name  : "+billStudentName+"\n" +
                                            "Serial Number : "+billStudentRoll+"\n"+
                                            "Bill Number   : "+billQuationId+"\n"+
                                            "Payment Method:  Online Payment\n";
                                }



                                new AwesomeSuccessDialog(CompletePayment.this)
                                        .setTitle("Admission Status")
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
                                                in.putExtra("detail",""+ value);
                                                in.putExtra("stud_id",student_id);
                                                in.putExtra("reg_num",billStudentRoll);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();



//                                Toast.makeText(BillActivity.this, "Payment registered successfully", Toast.LENGTH_SHORT).show();
//                                Intent in = new Intent(BillActivity.this, ViewBill.class);
//                                in.putExtra("detail",""+ value);
//                                startActivity(in);
//                                finish();
                                Gson billdata = new Gson();
                                billdata.fromJson(response,BillData.class);
                                final Student student = new Student();
                                final Payment payment = new Payment();

//                                value = "Student Name  : "+student.getStudent().getName()+"\n" +
//                                        "Serial Number : "+student.getStudent().getSerialNo()+"\n"+
//                                        "Bill Number   : "+payment.getQuotationId()+"\n"+
//                                        "Payment Method:  ONLINE PAYMENT\n"+
//                                        "Amount Paid   : "+initialAmount;
//
//                                new AwesomeSuccessDialog(CompletePayment.this)
//                                        .setTitle("Admission Status")
//                                        .setMessage("Admission Successfull.")
//                                        .setColoredCircle(R.color.colorPrimary)
//                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
//                                        .setCancelable(true)
//                                        .setPositiveButtonText("Ok")
//                                        .setPositiveButtonbackgroundColor(R.color.colorPrimary)
//                                        .setPositiveButtonTextColor(R.color.white)
//                                        .setPositiveButtonClick(new Closure() {
//                                            @Override
//                                            public void exec() {
//                                                Intent in = new Intent(CompletePayment.this, ViewBill.class);
//                                                in.putExtra("detail",""+ value);
//                                                in.putExtra("stud_id",student.getId());
//                                                in.putExtra("reg_num",student.getStudent().getSerialNo());
//                                                startActivity(in);
//                                                finish();
//                                            }
//                                        })
//                                        .show();


                            } else {
                                Toast.makeText(CompletePayment.this, "Submition failed", Toast.LENGTH_SHORT).show();
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
