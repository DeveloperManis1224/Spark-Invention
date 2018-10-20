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
        dueDate = getIntent().getStringExtra("due_date");
        paymentStatus = getIntent().getStringExtra("payment_status");
        tenureAmount = getIntent().getStringExtra("tenure_amount");
        balanceAmount = getIntent().getStringExtra("balance_amount");
        btnSubmit = findViewById(R.id.btn_bill);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!eBillNumber.getText().toString().isEmpty())
                {

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
        value = "Bill number : "+eBillNumber.getText().toString().trim()+
                "\n  Student Roll Number : "+studId+"\n  Payment Mode : "+paymentMode+"\n  Initial Amount : "+
                initialAmount+"\n  Total amount : "+totalAmount+"\n Tenure month : "+ tenureMonth
                +"\n Next Due Date : "+dueDate+"\n Payment Status : "+paymentStatus+"\n Tenure Amount : "+tenureAmount+
                "\n Balance Amount : "+balanceAmount;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/payment";
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
                               // Toast.makeText(BillActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                                new AwesomeSuccessDialog(BillActivity.this)
                                        .setTitle("Admission Status")
                                        .setMessage(""+"bill number"+eBillNumber.getText().toString().trim()+
                                                "\n  studentId = "+studId+"\n  payment Mode = "+paymentMode+"\n  initial Amount ="+
                                                initialAmount+"\n  toal amount = "+totalAmount+"\n tenureMonth = "+ tenureMonth
                                                +"\n dueDate ="+dueDate+"\n paymentStatus = "+paymentStatus+"\n tenureAmount = "+tenureAmount+
                                                "\n balanceAmount = "+balanceAmount)
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
                params.put("student_id","1");
                params.put("payment_mode","2");
                params.put("initial_amount",""+initialAmount);
                params.put("total_amount",""+totalAmount);
                params.put("tenure",""+tenureMonth);
                params.put("payment_status","3");
                params.put("tenure_amount",""+tenureAmount);
                params.put("balance_amount",""+balanceAmount);
                params.put("quotation_id",eBillNumber.getText().toString().trim());
                return params;
            }
        };
        queue.add(stringRequest);
    }


    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        Bitmap bitmap=null;
        try
        {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);

            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? black:white;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        } catch (Exception iae) {
            iae.printStackTrace();
            return null;
        }
        return bitmap;
    }
}
