package com.app.manikandanr.sampleclients;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {

    ProgressDialog pd;
    RecyclerView listViewStudent;
    private Spinner countrySpinner, stateSpinner, citySpinner, categorySpinner, institutionTypeSpinner,
    courseSpinner, yearSpinner, organizationSpinner;

    int countryPosition, statePosition, cityPosition, categoryPosition, institutionPosition,
            coursePosition, yearPosition ,organizationPosition;

    ArrayList<String> countrySpinnerList = new ArrayList<>();
    ArrayList<String> countryIdList = new ArrayList<>();

    ArrayList<String> stateSpinnerList = new ArrayList<>();
    ArrayList<String> stateIdList = new ArrayList<>();

    ArrayList<String> citySpinnerList = new ArrayList<>();
    ArrayList<String> cityIdList = new ArrayList<>();

    ArrayList<String> categorySpinnerList = new ArrayList<>();
    ArrayList<String> categoryIdList = new ArrayList<>();

    ArrayList<String> categoryCourseSpinnerList = new ArrayList<>();
    ArrayList<String> categoryCourseIdList = new ArrayList<>();

    ArrayList<String> yearSpinnerList = new ArrayList<>();
    ArrayList<String> yearIdList = new ArrayList<>();

    ArrayList<String> courseSpinnerList = new ArrayList<>();
    ArrayList<String> courseIdList = new ArrayList<>();

    ArrayList<String> institutionSpinnerList = new ArrayList<>();
    ArrayList<String> institutionIdList = new ArrayList<>();

    ArrayList<String> organizationSpinnerList = new ArrayList<>();
    ArrayList<String> organizationIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        init();

//
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    public void onDialogList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout11 = inflater.inflate(R.layout.dialog_sort_lyt, (ViewGroup) findViewById(R.id.layout_root));
        countrySpinner = layout11.findViewById(R.id.at_spin_country);
        stateSpinner = layout11.findViewById(R.id.at_spin_state);
        citySpinner = layout11.findViewById(R.id.at_spin_city);
        categorySpinner = layout11.findViewById(R.id.at_spin_category);
        institutionTypeSpinner = layout11.findViewById(R.id.at_spin_institution_type);
        courseSpinner = layout11.findViewById(R.id.at_spin_course);
        yearSpinner = layout11.findViewById(R.id.at_spin_year);
        organizationSpinner = layout11.findViewById(R.id.at_spin_organization);
        final AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);
        builder.setView(layout11);
        builder.setPositiveButton("Get Students", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
if(isValid())
{
    getStudentList();
}


                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    private boolean isValid()
    {
        boolean val  = true;

        if(countrySpinner.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
        {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            val = false;
        }
        else if(stateSpinner.getSelectedItem().toString().equalsIgnoreCase("Select State"))
        {
            Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();
            val = false;
        }
        else if(citySpinner.getSelectedItem().toString().equalsIgnoreCase("Select City"))
        {
            Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
            val = false;
        }
        else if(categorySpinner.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
        {
            Toast.makeText(this, "Select Category", Toast.LENGTH_SHORT).show();
            val = false;
        }


        return val ;
    }

    private void init() {
        pd = new ProgressDialog(AttendanceActivity.this);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        getCountry();
        getCatgories();
       listViewStudent = findViewById(R.id.stud_view_list);
       onDialogList();
       RecyclerView.LayoutManager lytMgr = new LinearLayoutManager(AttendanceActivity.this);
       listViewStudent.setLayoutManager(lytMgr);

       countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(!countrySpinner.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
               {
                   getState(countryIdList.get(i));
                   countryPosition = i;
               }
               Log.e("SASASASA", "Country ID  :::::" +countryIdList.get(i));
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(!stateSpinner.getSelectedItem().toString().equalsIgnoreCase("Select State"))
               {
                   getCity(stateIdList.get(i));
                   statePosition = i;
               }

               Log.e("SASASASA", "State ID :   " + stateIdList.get(i));
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(!citySpinner.getSelectedItem().toString().equalsIgnoreCase("Select City"))
               {
                   getOrganization(cityIdList.get(i),"1");
                   cityPosition = i;
                   Log.e("SSSSSSSSSS",""+cityIdList.get(i));
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       organizationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               organizationPosition = i;
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(!categorySpinner.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
               {
                   getCategoryCourse(categoryIdList.get(i));
                   categoryPosition = i;
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

    }



    private void getCategoryCourse(final String categoryId) {
        categoryCourseIdList.clear();
        categoryCourseSpinnerList.clear();
        categoryCourseIdList.add("Select Course");
        categoryCourseSpinnerList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/category-course";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("categories");
                            for (int i = 0; i <= jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                categoryCourseSpinnerList.add(jobj1.getString("course"));
                                categoryCourseIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryCourseSpinnerList);
                        categorySpinner.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AttendanceActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("category_id", categoryId);
                return  params;
            }
        };
        queue.add(stringRequest);
    }

    private void getCity(final String stateId) {
        citySpinnerList.clear();
        cityIdList.clear();
        cityIdList.add("0");
        citySpinnerList.add("Select City");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/city";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("cities");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                citySpinnerList.add(jobj1.getString("city"));
                                cityIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, citySpinnerList);
                        citySpinner.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AttendanceActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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

    private void getState(final String country_id) {
        stateIdList.clear();
        stateSpinnerList.clear();
        stateSpinnerList.add("Select State");
        stateIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/state";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("states");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                stateSpinnerList.add(jobj1.getString("state"));
                                stateIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, stateSpinnerList);
                        citySpinner.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AttendanceActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_id", country_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getCountry() {
        countryIdList.clear();
        countrySpinnerList.clear();
        countryIdList.add("0");
        countrySpinnerList.add("Select Country");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/country";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("countries");

                            for (int i = 0; i <= jary.length(); i++) {
                                JSONObject jobj11 = jary.getJSONObject(i);
                                String cuntry = jobj11.getString("country");
                                String id = jobj11.getString("id");
                                Log.v("TTTTTTTTTTT",""+ cuntry +" "+id);
                                countrySpinnerList.add(cuntry);
                                countryIdList.add(id);
                            }

                        } catch (JSONException e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> countryAdapt = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, countrySpinnerList);
                        countrySpinner.setAdapter(countryAdapt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AttendanceActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getOrganization(final String cityId, final String role) {
        organizationSpinnerList.clear();
        organizationIdList.clear();
        organizationSpinnerList.add("Select Organization");
        organizationIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/role-organization-lists";
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
                                organizationSpinnerList.add(jobj1.getString("name"));
                                organizationIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, organizationSpinnerList);
                        organizationSpinner.setAdapter(adapter);

                    }


                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EEEEEEE",""+error.getMessage());
                Toast.makeText(AttendanceActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("city_id",cityId);
                params.put("role", role);
                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getCatgories() {
        categoryIdList.clear();
        categorySpinnerList.clear();
        categorySpinnerList.add("Select Category");
        categoryIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/categroy";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("categories");
                            for (int i = 0; i <= jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                categorySpinnerList.add(jobj1.getString("category"));
                                categoryIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, categorySpinnerList);
                        categorySpinner.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AttendanceActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void getStudentList() {
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pd.dismiss();



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
