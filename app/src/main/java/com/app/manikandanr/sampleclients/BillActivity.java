package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class BillActivity extends AppCompatActivity {

    public static int white = 0xFFFFFFFF;
    public static int black = 0xFF000000;
    public final static int WIDTH=500;
    String value = "";
    private Button btnSubmit;
    private EditText eBillNumber;
    private String payMode="";
    private String billStudentName,billStudentRoll,billQuationId,billInitialAmount,billTotalAmount;
    private String studId,paymentMode,initialAmount,totalAmount,tenureMonth,dueDate,paymentStatus,tenureAmount,balanceAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

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


    private void init()
    {
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

                if(initialAmount.equalsIgnoreCase("0"))
                {
                    payMode = "2";
                }
                else
                {
                    payMode = "1";
                }

                if(!eBillNumber.getText().toString().isEmpty())
                {
                    Log.e("RESPONSE111","student_id"+""+studId+
                 "payment_mode"+payMode+
                    "initial_amount"+""+initialAmount+
                    "total_amount"+""+totalAmount+
                    "tenure"+""+tenureMonth+
                   "payment_status"+""+paymentStatus+
                    "tenure_amount"+""+tenureAmount+
                   "balance_amount"+""+balanceAmount+
                   "quotation_id"+eBillNumber.getText().toString().trim());
                    completeCashMethod();
                }
                else
                {
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
                            Log.e("RESPONSE1Bill", "" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String sts = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (sts.equalsIgnoreCase("1")) {
                                JSONObject jobj = jsonObject.getJSONObject("payment");
                                JSONObject jobj1 = jobj.getJSONObject("payment_plan");
                                dueDate = jobj1.getString("due_date");
                                JSONObject studentObject = jobj.getJSONObject("student");
                                billStudentName = studentObject.getString("name");
                                billStudentRoll = studentObject.getString("serial_no");
                                billQuationId = jobj.getString("quotation_id");
                                billInitialAmount = jobj1.getString("initial_amount");
                                billTotalAmount = jobj1.getString("total_amount");
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
                                            "Payment Method:  EMI\n"+
                                            "Amount Paid   : "+billInitialAmount;
                                }



                                new AwesomeSuccessDialog(BillActivity.this)
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
                                Intent in = new Intent(BillActivity.this, ViewBill.class);
                                in.putExtra("detail",""+ value);
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
                                Log.e("RESPONSE111","bill number"+eBillNumber.getText().toString().trim()+
                                        "  studentId = "+studId+"  payment Mode = "+paymentMode+"  initial Amount ="+
                                        initialAmount+"  toal amount = "+totalAmount+" tenureMonth = "+ tenureMonth
                                        +" dueDate ="+dueDate+" paymentStatus = "+paymentStatus+" tenureAmount = "+tenureAmount+
                                        "balanceAmount = "+balanceAmount);

                            } else {
                                Toast.makeText(BillActivity.this, "Submition failed", Toast.LENGTH_SHORT).show();
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
                params.put("student_id",""+studId);
                params.put("payment_mode",payMode);
                params.put("initial_amount",""+initialAmount);
                params.put("total_amount",""+totalAmount);
                params.put("tenure",""+tenureMonth);
                params.put("payment_status",""+paymentStatus);
                params.put("tenure_amount",""+tenureAmount);
                params.put("balance_amount",""+balanceAmount);
                params.put("quotation_id",eBillNumber.getText().toString().trim());
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
