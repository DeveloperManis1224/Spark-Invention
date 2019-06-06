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
import com.app.manikandanr.sampleclients.DataModels.Marketing;
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
    ArrayList<Marketing> marketingAlertData=new ArrayList<>();
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
                            progressDialog.dismiss();
                            StudentObjectALertData objectALertData  = new Gson().fromJson(response,StudentObjectALertData.class);
                            studentDataList = objectALertData.getStudents();
                            marketingAlertData = objectALertData.getMarketings();
                            MarketingAdapter marketingAdapter = new MarketingAdapter(marketingAlertData);
                            clgList.setAdapter(marketingAdapter);
                            StudentAlertAdapter alertAdapter = new StudentAlertAdapter(studentDataList);
                            studList.setAdapter(alertAdapter);
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
