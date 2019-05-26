package com.app.manikandanr.sampleclients;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.app.manikandanr.sampleclients.Data.StudentData;
import com.app.manikandanr.sampleclients.Data.StudentInfoData;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

public class StudentScanner extends AppCompatActivity {
    private TextView txtResult;
    String regNumber;
    EditText mRegNumber;
    public  static StudentInfoData studentData;

    public void onCLickStudentRegQr(View v)
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    public void onClickStudentRegNumber(View v)
    {
        regNumber = mRegNumber.getText().toString().trim();
        getStudentInformation();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_scanner);
        txtResult = findViewById(R.id.txt_qrdata);
        mRegNumber = findViewById(R.id.edt_reg_number);
    }

    private void getStudentInformation() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Please wait.......");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/student-info";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT",""+ response);
                            Gson gson = new Gson();
                            ViewStudent.data = gson.fromJson(response,StudentInfoData.class);

                            Intent n = new Intent(StudentScanner.this,ViewStudent.class);
                            //n.putExtra("Students_data", (Parcelable) studentData.getStudents());
                            startActivity(n);
                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentScanner.this, "Unable to connect server" + error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("serial_no",regNumber);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("RESULT_QR", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("RESULT_QR", "Scanned");
                txtResult.setText("Scanned Qr code ::: "+result.getContents());

                regNumber = result.getContents();
                getStudentInformation();

            }
        }
    }
}
