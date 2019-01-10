package com.app.manikandanr.sampleclients;

import android.content.Intent;
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
import com.app.manikandanr.sampleclients.DataModels.AlertData;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AlertActivity extends AppCompatActivity {

    RecyclerView studList, clgList;
    ArrayList<AlertData> studentDataList = new ArrayList<>();
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
                            Gson gson = new Gson();
                            studentDataList.add (gson.fromJson(response,AlertData.class));
                            StudentAlertAdapter alertAdapter = new StudentAlertAdapter(studentDataList);
                            MarketingAdapter marketingAdapter = new MarketingAdapter(studentDataList);
                            studList.setAdapter(alertAdapter);
                            clgList.setAdapter(marketingAdapter);
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
