package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddItems extends AppCompatActivity {
    private AutoCompleteTextView txtCountryName;
    ArrayList<String> countryList = new ArrayList<>();
    ArrayList<String> countryIdList = new ArrayList<>();

    int country_pos = 0;

    private Spinner mCountry;
    private AutoCompleteTextView txtStateName, txtCityName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        init();
    }

    private void init()
    {
        txtCountryName = findViewById(R.id.edt_add_country);
        mCountry = findViewById(R.id.spin_add_country);
        txtStateName = findViewById(R.id.edt_add_state);
        txtCityName = findViewById(R.id.edt_add_city);

        getCountry();
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


    public void onClickAddCountry(View v)
    {
        if(!txtCountryName.getText().toString().isEmpty())
        {
            addCountry(txtCountryName.getText().toString().trim());
        }
        else
        {
            txtCountryName.setError("Invalid Country");
        }
    }

    public void onClickAddCityState(View v)
    {
        if(!txtStateName.getText().toString().isEmpty() && !txtCityName.getText().toString().isEmpty())
        {
            addStateandCity(txtStateName.getText().toString().trim(),txtCityName.getText().toString().trim());
        }
    }

    private void addCountry(final String countryName) {
        countryIdList.clear();
        countryList.clear();
        countryIdList.add("0");
        countryList.add("Select Country");
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
        countryIdList.clear();
        countryList.clear();
        countryIdList.add("0");
        countryList.add("Select Country");
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

}
