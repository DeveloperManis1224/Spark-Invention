package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdmissionProjectActivity extends AppCompatActivity {
    String departmentId;
    private TextInputEditText txtName, txtDob, txtPhone, txtEmail;
    ProgressDialog pd = null;
    private String userRollNo = "";
    String sts_joinings;
    private String alertDate = "";
    private EditText txtAddress, txtRemarks;
    private AutoCompleteTextView txtOrganization;
    private Spinner spinCountry, spinState, spinCity, spinCollege, spinStandard, spinCategory, spinCourse;
    private RadioButton radSchool, radCollege, radProjectProgram, radJoinNow, radJoinLater;
    private ArrayList<String> countryList = new ArrayList<>();
    final ArrayList<String> collegeList = new ArrayList<>();
    final ArrayList<String> collegeIdList = new ArrayList<>();
    private ArrayList<String> stateList = new ArrayList<>();
    private ArrayList<String> cityList = new ArrayList<>();
    private ArrayList<String> categoryList = new ArrayList<>();
    private ArrayList<String> courseList = new ArrayList<>();
    private ArrayList<String> countryIdList = new ArrayList<>();
    final ArrayList<String> courseCatIdList = new ArrayList<String>();
    private ArrayList<String> stateIdList = new ArrayList<>();
    private ArrayList<String> cityIdList = new ArrayList<>();
    private ArrayList<String> categoryIdList = new ArrayList<>();
    private ArrayList<String> courseIdList = new ArrayList<>();
    Calendar myCalendar = Calendar.getInstance();
    int mday = 24;
    int mmonth=10;
    int myear=1990;
    final ArrayList<String> costList = new ArrayList<String>();
    final ArrayList<String> courseCatList = new ArrayList<String>();


    //
    private int country_pos = 0;
    private int state_pos = 0;
    private int city_pos = 0;
    private int coll_scl_pos = 0;
    private int cat_pos = 0;
    private int course_pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_project);
        InitComponent();
    }

    private void InitComponent() {

        txtName = findViewById(R.id.txt_name);
        txtDob = findViewById(R.id.txt_dob);
        txtPhone = findViewById(R.id.txt_phone);
        txtEmail = findViewById(R.id.txt_email);
        txtAddress = findViewById(R.id.txt_address);
        txtRemarks = findViewById(R.id.txt_remarks);
        txtOrganization = findViewById(R.id.txt_organization);
        spinCountry = findViewById(R.id.spin_country );
        spinState = findViewById(R.id.spin_state);
        spinCategory = findViewById(R.id.spin_category);
        spinCourse = findViewById(R.id.spin_course);
        spinCity = findViewById(R.id.spin_city);
        spinCollege = findViewById(R.id.spin_organization);
        spinStandard = findViewById(R.id.spin_standard);
        radSchool = findViewById(R.id.rad_school);
        radCollege = findViewById(R.id.rad_college);
        radProjectProgram = findViewById(R.id.rad_project);
        radJoinNow = findViewById(R.id.rad_join_now);
        radJoinLater = findViewById(R.id.rad_join_later);

        txtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(AdmissionProjectActivity.this, date, myear, mmonth, mday).show();

            }
        });



        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
                {
                    getState(countryIdList.get(i));
                    country_pos = i;
                }
                Log.e("SASASASA", "Country ID  :::::" +countryIdList.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinState.getSelectedItem().toString().equalsIgnoreCase("Select State"))
                {
                    getCity(stateIdList.get(i));
                    state_pos = i;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(!spinCity.getSelectedItem().toString().equalsIgnoreCase("Select City"))
                    {
                        getOrganization(cityIdList.get(i));
                        city_pos = i;
                        Log.e("SSSSSSSSSS",""+cityIdList.get(i));
                    }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinCollege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                coll_scl_pos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinCategory.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
                {
                    getCategoryCourse(categoryIdList.get(i));
                    cat_pos = i;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        countryList.add("Select Country");
        stateList.add("Select State");
        cityList.add("Select City");
        categoryList.add("Select Category");
        courseList.add("Select Course");

        countryIdList.add("0");
        stateIdList.add("0");
        cityIdList.add("0");
        categoryIdList.add("0");
        courseIdList.add("0");

        ArrayList<String> standardListSchool = new ArrayList<>();
        ArrayList<String> standardListCollege = new ArrayList<>();



        if(radSchool.isChecked())
        {
            ArrayAdapter<String> std = new ArrayAdapter<String>
                    (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, standardListSchool);
            spinStandard.setAdapter(std);
        }
        else
            if(radCollege.isChecked())
            {
                ArrayAdapter<String> std = new ArrayAdapter<String>
                        (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, standardListCollege);
                spinStandard.setAdapter(std);
            }
            else
            {
                ArrayAdapter<String> std = new ArrayAdapter<String>
                        (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, countryList);
                spinStandard.setAdapter(std);
            }


        ArrayAdapter<String> std = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, countryList);
        spinStandard.setAdapter(std);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, countryList);
        spinCountry.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, stateList);
        spinState.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        spinCity.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        spinCategory.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, courseList);
        spinCourse.setAdapter(adapter3);
        getCountry();
        getCatgories();
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txtDob.setText(sdf.format(myCalendar.getTime()));
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
                            for (int i = 0; i <= jary.length(); i++) {
                                JSONObject jobj11 = jary.getJSONObject(i);
                                String cuntry = jobj11.getString("country");
                                String id = jobj11.getString("id");
                                Log.v("TTTTTTTTTTT",""+ cuntry +" "+id);
                                countryList.add(cuntry);
                                countryIdList.add(id);
                            }

                        } catch (JSONException e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }

//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, courseList);
//                        aedtCourse.setAdapter(adapter);

                        ArrayAdapter<String> countryAdapt = new ArrayAdapter<String>
                                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, countryList);
                        spinCountry.setAdapter(countryAdapt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionProjectActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
    private void getCatgories() {
        categoryIdList.clear();
        categoryList.clear();
        categoryList.add("Select Category");
        categoryIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/categroy";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("categories");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                categoryList.add(jobj1.getString("category"));
                                categoryIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        spinCategory.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionProjectActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }


    private boolean isValid()
    {
        boolean val  = true;
        if(txtName.getText().toString().isEmpty())
        {
            val = false;
            txtName.setError("Invalid");
        }
        if(txtDob.getText().toString().isEmpty())
        {
            val = false;
            txtDob.setError("Invalid");
        }
        if(txtPhone.getText().toString().isEmpty())
        {
            val = false;
            txtPhone.setError("Invalid");
        }
        if(txtEmail.getText().toString().isEmpty())
        {
            val = false;
            txtEmail.setError("Invalid");
        }
        if(txtAddress.getText().toString().isEmpty())
        {
            val = false;
            txtAddress.setError("Invalid");
        }
        if(txtRemarks.getText().toString().isEmpty())
        {
            val = false;
            txtRemarks.setError("Invalid");
        }
        if(txtOrganization.getText().toString().isEmpty())
        {
            val = false;
            txtOrganization.setError("Invalid");
        }
        if(spinCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
        {
            val = false;
            Toast.makeText(this, "Invalid Country", Toast.LENGTH_SHORT).show();
        }
        if(spinState.getSelectedItem().toString().equalsIgnoreCase("Select State"))
        {
            val = false;
            Toast.makeText(this, "Invalid State", Toast.LENGTH_SHORT).show();
        }
        if(spinCity.getSelectedItem().toString().equalsIgnoreCase("Select City"))
        {
            val = false;
            Toast.makeText(this, "Invalid City", Toast.LENGTH_SHORT).show();
        }
        return val;
    }


    public void onClickAdmissionProject(View view) {
        if(isValid())
        {
            uploadStudentInfo();
        }
    }

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
                                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, stateList);
                        spinState.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionProjectActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, cityList);
                        spinCity.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionProjectActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, collegeList);
                        spinCollege.setAdapter(adapter);

                    }


                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EEEEEEE",""+error.getMessage());
                Toast.makeText(AdmissionProjectActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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

    private void uploadStudentInfo() {
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pd.dismiss();
                            Log.e("RESPONSE111", "" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String sts = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (sts.equalsIgnoreCase("1")) {
                                Toast.makeText(AdmissionProjectActivity.this, "" + msg,
                                        Toast.LENGTH_SHORT).show();
                                JSONObject jobj =jsonObject.getJSONObject("student");
                                String studentId = jobj.getString("id");
                                Intent in = new Intent(AdmissionProjectActivity.this,
                                        PaymentStatus.class);
                                in.putExtra("cost", "");
                                in.putExtra("stud_id",studentId);
                                startActivity(in);
                                finish();

                            } else {
                                Toast.makeText(AdmissionProjectActivity.this, "Submition failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(AdmissionProjectActivity.this, "" + error,
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", txtName.getText().toString().trim());
                params.put("dob", txtDob.getText().toString().trim());
                params.put("instituation_id",getIntent().getExtras().getString(Constants.USER_ROLE_ID));
                params.put("organization_id", collegeIdList.get(coll_scl_pos));
                params.put("phone", txtPhone.getText().toString().trim());
                params.put("email", txtEmail.getText().toString().trim());
                params.put("country_id", countryIdList.get(country_pos));
                params.put("state_id", stateIdList.get(state_pos));
                params.put("city_id", cityIdList.get(city_pos));
                params.put("address", txtAddress.getText().toString().trim());
                params.put("course_id", courseCatIdList.get(course_pos));
                params.put("status", sts_joinings);
                params.put("department_id",departmentId);
                params.put("alert_date", alertDate);
                params.put("join_status", "1");
                params.put("role", userRollNo);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getCategoryCourse(final String categoryId) {
        courseCatIdList.clear();
        courseCatList.clear();
        costList.clear();
        courseCatList.add("Select Course");
        courseCatIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/category-course";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RESPONSE_Result",""+response);
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("categories");

                            if(jary.length()==0)
                            {
                                Toast.makeText(AdmissionProjectActivity.this, "No Course Available", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                for (int i = 0; i < jary.length(); i++) {
                                    JSONObject jobj1 = jary.getJSONObject(i);
                                    costList.add(jobj1.getString("amount"));
                                    courseCatList.add(jobj1.getString("course") + " ( Rs." + jobj1.getString("amount") + ")");
                                    courseCatIdList.add(jobj1.getString("id"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, courseCatList);
                        spinCourse.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionProjectActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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

}
