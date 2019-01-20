package com.app.manikandanr.sampleclients;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.app.manikandanr.sampleclients.Data.StudentData;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewBill extends AppCompatActivity {
    String regNumber =null;
    String invoiceNumber =  "";
    String studentId = "";
    public static int white = 0xFFFFFFFF;
    public static int black = 0xFF000000;
    public final static int WIDTH=500;
    ImageView imgView ;
    TextView txtInvoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);
        try {
           regNumber = getIntent().getExtras().getString(Constants.STUDENT_SERIAL_NUMBER);
        }catch (Exception ex)
        {
            Log.e("ERROR_MSG",""+ex.getMessage());
            ex.printStackTrace();
        }
        invoiceNumber = getIntent().getStringExtra("detail");
        studentId = getIntent().getExtras().getString("stud_id");
        imgView = findViewById(R.id.img_bill);
        txtInvoice = findViewById(R.id.txt_invoice);
        txtInvoice.setText("Bill Details : \n"+invoiceNumber);
        try
        {
            Bitmap bmp =  encodeAsBitmap(getIntent().getExtras().getString("reg_num"));
            imgView.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  void onCloseClick(View v)
    {
        onBackPressed();
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

    @Override
    public void onBackPressed() {
        Intent in = new Intent(ViewBill.this,MenuActivity.class);
        startActivity(in);
        finish();
    }

    public void onClickSave(View v)
    {
        addImage("");
    }

    private void addImage(final String countryName) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);

                            String status = jobj.getString("status");
                            String msg = jobj.getString("message");
                            if(status.equalsIgnoreCase("1"))
                            {
                                Toast.makeText(ViewBill.this, "Qr code Saved Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ViewBill.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewBill.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_id",studentId);
                params.put("qr_code",countryName);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    public void onViewStudentInfo(View view) {
        getStudentInformation();
    }

    private void getStudentInformation() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Please wait.......");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/emi";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT",""+ response);
                            Gson gson = new Gson();
                            StudentData studentData = gson.fromJson(response,StudentData.class);
                            StringBuilder basicData = new StringBuilder();
                            basicData.append("Register No: "+studentData.getStudents().getSerialNo()+"\n");
                            basicData.append("Name : "+studentData.getStudents().getName()+"\n");
                            basicData.append("Phone No : "+studentData.getStudents().getPhone()+"\n");
                            basicData.append("Email : "+studentData.getStudents().getEmail()+"\n");
                            basicData.append("Country : "+studentData.getStudents().getCountry().getCountry()+"\n");
                            basicData.append("State : "+studentData.getStudents().getState().getState()+"\n");
                            basicData.append("City : "+studentData.getStudents().getCity().getCity()+"\n");
                            basicData.append("Category : "+studentData.getStudents().getCategoryId()+"\n");
                            basicData.append("Course : "+studentData.getStudents().getCourse().getCourse()+"\n");
                            basicData.append("Type : "+studentData.getStudents().getRole()+"\n");
                            basicData.append("Organization : "+studentData.getStudents().getOrganizationId()+"\n");
                            basicData.append("Address : "+studentData.getStudents().getAddress()+"\n");
                            StringBuilder otherData = new StringBuilder();
                            Intent n = new Intent(ViewBill.this,ViewStudent.class);
                            n.putExtra(Constants.STUDENT_BASIC_INFO,""+basicData);
                            n.putExtra(Constants.STUDENT_OTHER_INFO,""+otherData);
                            n.putExtra(Constants.STUDENT_PAYMENT_STATUS,"1");
                            n.putExtra("Students",studentData.getStudents());
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
                Toast.makeText(ViewBill.this, "Unable to connect server" + error, Toast.LENGTH_SHORT).show();
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
}
