package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Adapters.StudentAlertAdapter;
import com.app.manikandanr.sampleclients.Data.StudentAlertData;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlertActivity extends AppCompatActivity {

    RecyclerView studList, clgList;
    ArrayList<StudentAlertData> studentDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        studList = findViewById(R.id.alert_stud_list);
        clgList = findViewById(R.id.alert_clg_list);
        RecyclerView.LayoutManager lytstud = new LinearLayoutManager(AlertActivity.this);
        studList.setLayoutManager(lytstud);
        RecyclerView.LayoutManager lytClg = new LinearLayoutManager(AlertActivity.this);
        clgList.setLayoutManager(lytClg);
        getAlertDetails();
    }

    private void getAlertDetails() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/alert";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT",""+ response);
                            String studentId = "";
                             String studentDate = "";
                              String studentSerialNumber = "";
                             String studentName = "";
                             String studentDob = "";
                             String studentInstitutionType = "";
                             String studentCategory = "";
                             String studentOrganization = "";
                             String studentPhone = "";
                             String studentEmail = "";
                             String studentAddress = null;
                             String studentCourse = null;
                            JSONObject jobj = new JSONObject(response);
                            String sts = jobj.getString("status");
                            String msg = jobj.getString("message");
                            JSONArray studentArray = jobj.getJSONArray("students");
                            for(int i = 0; i< studentArray.length(); i++)
                            {
                                JSONObject studentObj = studentArray.getJSONObject(i);
                                studentId = studentObj.getString("student_id");
                                studentDate = studentObj.getString("date");
                                JSONObject studentDataObject = studentObj.getJSONObject("student");

                                    studentSerialNumber = studentDataObject.getString("serial_no");
                                    studentName = studentDataObject.getString("name");
                                    studentDob = studentDataObject.getString("dob");
                                    studentInstitutionType = studentDataObject.getString("instituation_id");
                                    studentCategory = studentDataObject.getString("category_id");
                                    studentOrganization = studentDataObject.getString("organization_id");
                                    studentPhone = studentDataObject.getString("phone");
                                    studentEmail = studentDataObject.getString("email");
                                    studentAddress = studentDataObject.getString("address")+
                                            ""+studentDataObject.getJSONObject("city").getString("city")+""
                                    +studentDataObject.getJSONObject("state").getString("state")+""
                                            +studentDataObject.getJSONObject("country").getString("country");
                                    studentCourse = ""+studentDataObject.getJSONObject("course").getString("course");

                                studentDataList.add(new StudentAlertData(studentId,studentDate,studentSerialNumber,studentName,studentDob,studentInstitutionType,
                                        studentCategory,studentOrganization,studentPhone,studentEmail,studentAddress,studentCourse));

                            }

                            StudentAlertAdapter alertAdapter = new StudentAlertAdapter(studentDataList);
                            studList.setAdapter(alertAdapter);
                            //clgList.setAdapter(marketingAdapter);
                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlertActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}
