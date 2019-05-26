package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.app.manikandanr.sampleclients.Utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddItems extends AppCompatActivity {
    private Spinner mCountry, mColCountry, txtColStateName, txtColCityName;
    private AutoCompleteTextView txtStateName, txtCityName, txtClgSchool ;
    ArrayList<String> countryIdList = new ArrayList<>();
    ArrayList<String> countryList = new ArrayList<>();
    private AutoCompleteTextView txtCountryName;
    SessionManager session = new SessionManager();
    private RadioButton mSchool, mCollege;
    private String userRollNo;
    int country_pos = 0;
    int state_pos = 0;
    int city_pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void init()
    {
        txtCountryName = findViewById(R.id.edt_add_country);
        mCountry = findViewById(R.id.spin_add_country);
        mSchool = findViewById(R.id.radio_school);
        mCollege = findViewById(R.id.radio_clg);
        txtClgSchool = findViewById(R.id.edt_add_clg);
        mColCountry = findViewById(R.id.spin_add_cuntry_college);
        txtColStateName = findViewById(R.id.edt_add_state_clg);
        txtColCityName = findViewById(R.id.edt_add_city_clg);
        txtStateName = findViewById(R.id.edt_add_state);
        txtCityName = findViewById(R.id.edt_add_city);


        mSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRollNo = Constants.ROLE_SCHOOL;
            }
        });
        mCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRollNo = Constants.ROLE_COLLEGE;
            }
        });
        if(mSchool.isChecked())
        {
            userRollNo = Constants.ROLE_SCHOOL;
        }
        else
        {
            userRollNo = Constants.ROLE_COLLEGE;
        }
        getCountry();
        mCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!mCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
                {
                    country_pos = i;
                    getState(String.valueOf(country_pos));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mColCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!mColCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
                {
                    country_pos = i;
                    getStateCol(String.valueOf(country_pos));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        txtColStateName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!txtColStateName.getSelectedItem().toString().equalsIgnoreCase("Select State"))
                {
                    state_pos = i;
                    getCity(stateIdList.get(state_pos));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        txtColCityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!txtColCityName.getSelectedItem().toString().equalsIgnoreCase("Select City"))
                {
                    city_pos = i;
                    getOrganization(cityIdList.get(city_pos));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    private void getCountry() {
        countryIdList.clear();
        countryList.clear();
        countryIdList.add("0");
        countryList.add("Select Country");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/country";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("countries");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                countryList.add(jobj1.getString("country"));
                                countryIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddItems.this, android.R.layout.simple_spinner_dropdown_item, countryList);
                        mCountry.setAdapter(adapter);
                        mColCountry.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
    final ArrayList<String> stateIdList = new ArrayList<String>();
    final ArrayList<String> stateList = new ArrayList<String>();

    private void getState(final String country_id) {
        stateIdList.clear();
        stateList.clear();
        stateList.add("Select State");
        stateIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/state";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("states");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                stateList.add(jobj1.getString("state"));
                                stateIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddItems.this, android.R.layout.simple_spinner_dropdown_item, stateList);
                        txtStateName.setAdapter(adapter);
                        txtStateName.setThreshold(2);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_id", countryIdList.get(country_pos));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


    private void getStateCol(final String country_id) {
        stateIdList.clear();
        stateList.clear();
        stateList.add("Select State");
        stateIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/state";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("states");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                stateList.add(jobj1.getString("state"));
                                stateIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddItems.this, android.R.layout.simple_spinner_dropdown_item, stateList);
                        txtColStateName.setAdapter(adapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_id", countryIdList.get(country_pos));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
    final ArrayList<String> cityList = new ArrayList<String>();
    final ArrayList<String> cityIdList = new ArrayList<String>();
    private void getCity(final String stateId) {
        cityList.clear();
        cityIdList.clear();
        cityIdList.add("0");
        cityList.add("Select City");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/city";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("cities");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                cityList.add(jobj1.getString("city"));
                                cityIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddItems.this, android.R.layout.simple_spinner_dropdown_item, cityList);
                        txtColCityName.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("state_id", stateId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    final ArrayList<String> collegeIdList = new ArrayList<String>();
    final ArrayList<String> collegeList = new ArrayList<String>();

    private void getOrganization(final String cityId) {
        collegeIdList.clear();
        collegeList.clear();
        collegeList.add("Select Organization");
        collegeIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/role-organization-lists";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SSSSS",""+response);
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("organization");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                collegeList.add(jobj1.getString("name"));
                                collegeIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddItems.this, android.R.layout.simple_spinner_dropdown_item, collegeList);
                        txtClgSchool.setAdapter(adapter);
                        txtClgSchool.setThreshold(3);
                    }


                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EEEEEEE",""+error.getMessage());
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("city_id",cityId);
                params.put("role", userRollNo);
                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    public void onClickAddCountry(View v){
        if(!txtCountryName.getText().toString().isEmpty())
        {
            addCountry(txtCountryName.getText().toString().trim());
        }
        else
        {
            txtCountryName.setError("Invalid Country");
        }
    }

    public void onClickAddCityState(View v){
        if(!mCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country")||
                !txtStateName.getText().toString().isEmpty() &&
                        !txtCityName.getText().toString().isEmpty())
        {
            addStateandCity(txtStateName.getText().toString().trim(),txtCityName.getText().toString().trim());
        }
        else
        {
            Toast.makeText(this, "Please fill all fields.All fields are Mandatory", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickAddOrg(View v){
        if(!txtColCityName.getSelectedItem().toString().equalsIgnoreCase("Select City")||
                !txtClgSchool.getText().toString().isEmpty() &&
                        !userRollNo.isEmpty())
        {
            addOrganization();
        }
        else
        {
            Toast.makeText(this, "Please fill all fields.All fields are Mandatory", Toast.LENGTH_SHORT).show();
        }
    }

    private void addCountry(final String countryName) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/country-create";
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
                                Toast.makeText(AddItems.this, "Country Added", Toast.LENGTH_SHORT).show();
                                txtCountryName.setText("");
                            }
                            else
                            {
                                Toast.makeText(AddItems.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_name",countryName);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void addStateandCity(final String stateName, final String cityName) {
        Log.e("STATE_CITY",countryIdList.get(country_pos)+"///"+stateName+"///"+cityName);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/state-city-create";
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
                                Toast.makeText(AddItems.this, "State and City Added", Toast.LENGTH_SHORT).show();
                                txtCityName.setText("");
                                txtStateName.setText("");

                            }
                            else
                            {
                                Toast.makeText(AddItems.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_id",countryIdList.get(country_pos));
                params.put("state_name",stateName);
                params.put("city_name",cityName);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void addOrganization() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/organization";
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
                                Toast.makeText(AddItems.this, "Organization added successfully.", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AddItems.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddItems.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("city_id",cityIdList.get(city_pos));
                params.put("organization",txtClgSchool.getText().toString().trim());
                params.put("user_id",session.getPreferences(AddItems.this,Constants.USER_ID));
                params.put("role",userRollNo);
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
