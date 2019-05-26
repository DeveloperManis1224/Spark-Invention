package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.app.manikandanr.sampleclients.Data.StudentInfoData;
import com.app.manikandanr.sampleclients.Data.Students;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.bumptech.glide.Glide;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class ViewStudent extends AppCompatActivity {

    private TextView mBasicInfo;
    private TextView mOtherInfo;

    private ImageView studentImage;
    public static StudentInfoData data = null;
    private TextView txtSearchResultHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);


        final String studentImg = data.getStudents().getStudentImage();

        mBasicInfo = findViewById(R.id.txt_basic_info);
        mOtherInfo = findViewById(R.id.txt_other_info);

        txtSearchResultHint = findViewById(R.id.search_hint);
        studentImage = findViewById(R.id.student_image);
        Glide.with(ViewStudent.this)
                .load(Constants.IMAGE_BASE_URL+studentImg)
                .into(studentImage);

        txtSearchResultHint.setText("Search Result by "+data.getStudents().getSerialNo());
        StringBuilder basicData = new StringBuilder();
        basicData.append("Register No: "+data.getStudents().getSerialNo()+"\n");
        basicData.append("Name : "+data.getStudents().getName()+"\n");
        basicData.append("Phone No : "+data.getStudents().getPhone()+"\n");
        basicData.append("Parent Name : "+data.getStudents().getParentName()+"\n");
        basicData.append("Parent Phone : "+data.getStudents().getParentPhone()+"\n");
        basicData.append("Email : "+data.getStudents().getEmail()+"\n");
        basicData.append("Category : "+data.getStudents().getCategory().getCategory()+"\n");
        basicData.append("Course : "+data.getStudents().getCourse().getCourse()+"\n");
        String role = "";
        if(data.getStudents().getRole().equalsIgnoreCase(Constants.ROLE_SCHOOL)) {
            role = "School";
        }
        else if(data.getStudents().getRole().equalsIgnoreCase(Constants.ROLE_COLLEGE)) {
            role = "College";
        }else
        {
            role = "Project / Program";
        }
        basicData.append("Type : "+role+"\n");
        basicData.append("Organization : "+data.getStudents().getOrganization().getName()+"\n");
        basicData.append("Address : "+data.getStudents().getAddress()+"\n");
        StringBuilder otherData = new StringBuilder();

        StringBuilder details=new StringBuilder();
        details.append("Department : "+data.getStudents().getDepartment().getDepartment()+"\n");
        details.append("Paid Amount : "+data.getStudents().getCalcAmount()+"\n");
        details.append("Overall Discount : "+data.getStudents().getOverallDiscount()+"\n");
//        details.append("Payment Status : "+data.getStudents().getPaymentStatus()+"\n");
        mBasicInfo.setText(basicData+""+details);

    }

    public void onCLickBack(View view) {
        finish();
    }

//    private void payEmiMethod() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = Constants.BASE_URL + "api/payment";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ViewStudent.this, "" + error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("student_id",""+data.getId());
//              //  params.put("payment_mode",data.get);
//              //  params.put("initial_amount",""+initialAmount);
//               // params.put("quotation_id",eBillNumber.getText().toString().trim());
//                return params;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(stringRequest);
//    }
}
