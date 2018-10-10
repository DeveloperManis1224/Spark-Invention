package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AdmissionForm extends AppCompatActivity {

    private Button nextButton;
    String sts_joinings="";
    final ArrayList<String> stateList = new ArrayList<String>();
    final ArrayList<String> cityList = new ArrayList<String>();
    private EditText edtName,edtDob,edtCollege,edtPhone,edtEmail,edtAddress;
    private AutoCompleteTextView aedtCountry,aedtState,aedtCity,aedtCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_form);
        init();
    }

    private void init()
    {
        edtName = findViewById(R.id.edt_name);
        edtDob = findViewById(R.id.edt_dob);
        edtPhone = findViewById(R.id.edt_phone);
        edtCollege = findViewById(R.id.edt_college);
        edtEmail = findViewById(R.id.edt_email);
        aedtCountry = findViewById(R.id.edt_country);
        aedtState = findViewById(R.id.edt_state);
        aedtCity = findViewById(R.id.edt_city);
        aedtCourse = findViewById(R.id.edt_course);

        nextButton = findViewById(R.id.btn_next);
        getState();

        getCourseDetails();

        aedtState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {

               // Toast.makeText(AdmissionForm.this," selected  "+parent.getItemAtPosition(pos), Toast.LENGTH_LONG).show();

           getCity(getCategoryPos(String.valueOf(parent.getItemAtPosition(pos))));


            }
        });
    }

    public void onNextClick(View view)
    {
       if(nextButton.getText().toString().trim().equalsIgnoreCase("Next"))
       {
           LayoutInflater factory = LayoutInflater.from(AdmissionForm.this);
           final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
           final AlertDialog deleteDialog = new AlertDialog.Builder(AdmissionForm.this).create();
           deleteDialog.setView(deleteDialogView);

           Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes) ;
           Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no) ;
           Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none) ;

           btnSchool.setText("Join Now");
           btnSchool.setAllCaps(false);

           btnCollege.setText("Later");
           btnCollege.setAllCaps(false);

           btnProject.setText("Project / Program");
           btnProject.setVisibility(View.GONE);


           btnSchool.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   deleteDialog.dismiss();
                   Toast.makeText(AdmissionForm.this, "You are selecting Join now", Toast.LENGTH_SHORT).show();
                   nextButton.setText("Submit");
                   sts_joinings="now";
               }
           });
           btnCollege.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   deleteDialog.dismiss();
                   int mYear, mMonth, mDay;
                   final Calendar c = Calendar.getInstance();
                   mYear = c.get(Calendar.YEAR);
                   mMonth = c.get(Calendar.MONTH);
                   mDay = c.get(Calendar.DAY_OF_MONTH);
                   DatePickerDialog datePickerDialog = new DatePickerDialog(AdmissionForm.this,
                           new DatePickerDialog.OnDateSetListener() {
                               @Override
                               public void onDateSet(DatePicker view, int year,
                                                     int monthOfYear, int dayOfMonth) {
                                   Toast.makeText(AdmissionForm.this, ""+dayOfMonth + "-" +
                                           (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();
                                   nextButton.setText("Submit");
                               }
                           }, mYear, mMonth, mDay);
                   datePickerDialog.show();
                   Toast.makeText(AdmissionForm.this, "You are selecting Later", Toast.LENGTH_SHORT).show();
                   sts_joinings = "later";
               }
           });
           btnProject.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   deleteDialog.dismiss();

               }
           });
           deleteDialog.show();
       }
       else
       {
           if(sts_joinings.equalsIgnoreCase("now"))
           {
               if(isValid())
               {
                   uploadStudentInfo();
               }
           }
           else
           {
               Intent in = new Intent(AdmissionForm.this,AlertActivity.class);
               startActivity(in);
           }
       }
    }

    private boolean isValid()
    {
        boolean val = true;

        if(edtName.getText().toString().trim().isEmpty())
        {
            edtName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if(edtEmail.getText().toString().trim().isEmpty())
        {
            edtEmail.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if(edtCollege.getText().toString().trim().isEmpty())
        {
            edtCollege.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtDob.getText().toString().trim().isEmpty())
        {
            edtDob.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if( edtPhone.getText().toString().trim().isEmpty())
        {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if( edtPhone.getText().toString().trim().isEmpty())
        {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if( aedtCountry.getText().toString().trim().isEmpty())
        {
            aedtCountry.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if( aedtState.getText().toString().trim().isEmpty())
        {
            aedtState.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if( aedtCity.getText().toString().trim().isEmpty())
        {
            aedtCity.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if( aedtCourse.getText().toString().trim().isEmpty())
        {
            aedtCourse.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        return val;
    }

    private void getCity(final int stateId)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://spark.candyrestaurant.com/api/city";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("cities");
                            for(int i = 1 ; i <= jary.length(); i++)
                            {
                                JSONObject jobj1 =jary.getJSONObject(i);
                                cityList.add(jobj1.getString("city"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this,android.R.layout.select_dialog_item, cityList);
                        aedtCity.setThreshold(1);
                        aedtCity.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("state_id", ""+stateId);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void getState()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://spark.candyrestaurant.com/api/state";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("states");
                            for(int i = 1 ; i <= jary.length(); i++)
                            {
                                JSONObject jobj1 =jary.getJSONObject(i);
                                stateList.add(jobj1.getString("state"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this,android.R.layout.select_dialog_item, stateList);
                        aedtState.setThreshold(1);
                        aedtState.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_id", "1");
                return params;
            }
        };
        queue.add(stringRequest);
    }


    private void uploadStudentInfo()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://spark.candyrestaurant.com/api/state";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("states");
                            for(int i = 1 ; i <= jary.length(); i++)
                            {
                                JSONObject jobj1 =jary.getJSONObject(i);
                                stateList.add(jobj1.getString("state"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this,android.R.layout.select_dialog_item, stateList);
                        aedtState.setThreshold(1);
                        aedtState.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_id", "1");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void getCourseDetails()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://spark.candyrestaurant.com/api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("courses");
                            for(int i = 1 ; i <= jary.length(); i++)
                            {
                                JSONObject jobj1 =jary.getJSONObject(i);
                                stateList.add(jobj1.getString("course"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this,android.R.layout.select_dialog_item, stateList);
                        aedtState.setThreshold(1);
                        aedtState.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }



    private int getCategoryPos(String category) {
        return stateList.indexOf(category);
    }
}
