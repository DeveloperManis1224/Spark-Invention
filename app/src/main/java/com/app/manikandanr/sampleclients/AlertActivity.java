package com.app.manikandanr.sampleclients;

import android.app.ProgressDialog;
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
import com.app.manikandanr.sampleclients.Adapters.MarketingAdapter;
import com.app.manikandanr.sampleclients.Adapters.StudentAlertAdapter;
import com.app.manikandanr.sampleclients.Data.MarketingAlertData;
import com.app.manikandanr.sampleclients.Data.StudentAlertData;
import com.app.manikandanr.sampleclients.DataModels.StudentObjectALertData;
import com.app.manikandanr.sampleclients.DataModels.Students;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlertActivity extends AppCompatActivity {

    RecyclerView studList, clgList;
    ArrayList<Students> studentDataList = new ArrayList<>();
    ArrayList<MarketingAlertData> marketingAlertData=new ArrayList<>();
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
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Please wait.......");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/alert";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT",""+ response);

                            StudentObjectALertData objectALertData  = new Gson().fromJson(response,StudentObjectALertData.class);
                            studentDataList = objectALertData.getStudents();
//                            String id = "";
//                            String studentId = "";
//                            String studentDate = "";
//                            String studentSerialNumber = "";
//                            String studentName = "";
//                            String studentDob = "";
//                            String studentInstitutionType = "";
//                            String studentCategory = "";
//                            String studentOrganization = "";
//                            String studentPhone = "";
//                            String studentEmail = "";
//                            String studentAddress = null;
//                            String studentCourse = null;
//                            JSONObject jobj = new JSONObject(response);
//                            String sts = jobj.getString("status");
//                            String msg = jobj.getString("message");
//                            JSONArray studentArray = jobj.getJSONArray("students");
//                            for(int i = 0; i< studentArray.length(); i++)
//                            {
//                                JSONObject studentObj = studentArray.getJSONObject(i);
//
//                                studentId = studentObj.getString("student_id");
//                                studentDate = studentObj.getString("date");
//                                id = studentObj.getString("id");
//
//                                JSONObject studentDataObject = studentObj.getJSONObject("student");
//
//                                studentSerialNumber = studentDataObject.getString("serial_no");
//                                studentName = studentDataObject.getString("name");
//                                studentDob = studentDataObject.getString("dob");
//                                studentInstitutionType = studentDataObject.getString("instituation_id");
//                                studentCategory = studentDataObject.getString("category_id");
//                                studentOrganization = studentDataObject.getString("organization_id");
//                                studentPhone = studentDataObject.getString("phone");
//                                studentEmail = studentDataObject.getString("email");
//                                studentAddress = studentDataObject.getString("address")+
//                                        ", "+studentDataObject.getJSONObject("city").getString("city")+", "
//                                        +studentDataObject.getJSONObject("state").getString("state")+", "
//                                        +studentDataObject.getJSONObject("country").getString("country");
//                                studentCourse = ""+studentDataObject.getJSONObject("course").getString("course");
//
//                                studentDataList.add(new StudentAlertData(id,studentId,studentDate,studentSerialNumber,studentName,studentDob,studentInstitutionType,
//                                        studentCategory,studentOrganization,studentPhone,studentEmail,studentAddress,studentCourse));
//                            }
//                            JSONArray marketingsList=jobj.getJSONArray("marketings");
//                            for (int i = 0; i < marketingsList.length(); i++) {
//                                JSONObject data = marketingsList.getJSONObject(i);
//                                String instituation_id = data.getJSONObject("organization").getString("name");
//                                String organization_id = data.getString("organization_id");
//                                String serial_no = data.getString("serial_no");
//                                String staff_name = data.getString("staff_name");
//                                String phone = data.getString("phone");
//                                String email = data.getString("email");
//                                String state_id = data.getString("state_id");
//                                String city_id = data.getString("city_id");
//                                String country_id = data.getString("country_id");
//                                String year = data.getString("year");
//                                String strength = data.getString("strength");
//                                String address = data.getString("address");
//                                String landline = data.getString("landline");
//                                String event_id = data.getJSONObject("event").getString("name");
//                                String date = data.getString("date");
//                                String next_date = data.getString("next_date");
//                                String description = data.getString("description");
//                                String status = data.getString("status");
//                                String created_at = data.getString("created_at");
//                                String updated_at = data.getString("updated_at");
//                                String website = data.getString("website");
//                                String distance = data.getString("distance");
//                                String latitude = data.getString("latitude");
//                                String longitude = data.getString("longitude");
//
//                                MarketingAlertData alertData=new MarketingAlertData();
//                                alertData.setInstituation_id(instituation_id);
//                                alertData.setOrganization_id(organization_id);
//                                alertData.setSerial_no(serial_no);
//                                alertData.setStaff_name(staff_name);
//                                alertData.setPhone(phone);
//                                alertData.setEmail(email);
//                                alertData.setState_id(state_id);
//                                alertData.setCity_id(city_id);
//                                alertData.setCountry_id(country_id);
//                                alertData.setYear(year);
//                                alertData.setStrength(strength);
//                                alertData.setAddress(address);
//                                alertData.setLandline(landline);
//                                alertData.setEvent_id(event_id);
//                                alertData.setDate(date);
//                                alertData.setNext_date(next_date);
//                                alertData.setDescription(description);
//                                alertData.setStatus(status);
//                                alertData.setWebsite(website);
//                                alertData.setDate(date);
//                                alertData.setDistance(distance);
//                                alertData.setLatitude(latitude);
//                                alertData.setLongitude(longitude);
//                                marketingAlertData.add(alertData);
//                            }
                            MarketingAdapter marketingAdapter = new MarketingAdapter(marketingAlertData);
                            clgList.setAdapter(marketingAdapter);
                            StudentAlertAdapter alertAdapter = new StudentAlertAdapter(studentDataList);
                            studList.setAdapter(alertAdapter);
                            progressDialog.dismiss();
                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlertActivity.this, "Unable to connect server" + error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
