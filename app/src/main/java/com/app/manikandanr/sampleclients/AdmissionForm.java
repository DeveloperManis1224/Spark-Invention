package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdmissionForm extends AppCompatActivity {
    int orgPosition = 0;
    int mday = 24;
    int mmonth=10;
    int myear=1990;
    int country_pos= 0;
    int state_pos = 0;
    int city_pos = 0;
    int course_pos = 0;
    int category_pos = 0;
    int cost_pos = 0;
    int cat_pos = 0;
    String BalanceAmount ="";
    String org_dis_type ="";
    String org_dis = "";
    String course_dis_type = "";
    String course_dis = "";
    String departmentId;
    private String alertDate = "";
    private String sts_joinings = "";
    private Button nextButton;
    private TextView tCouseCost,tJoinStatus,tGetOffer, offerAvailable;
    private String userRole = "";
    private String userRollNo = "";
    private EditText edtName, edtDob, edtPhone, edtEmail, edtAddress;
    private Spinner aedtCountry, aedtState, aedtCity,edtCollege, edtdepartmentYear, aedtCourse ,category_course;
    final ArrayList<String> costList = new ArrayList<String>();
    final ArrayList<String> countryList = new ArrayList<String>();
    final ArrayList<String> stateList = new ArrayList<String>();
    final ArrayList<String> cityList = new ArrayList<String>();
    final ArrayList<String> courseCatList = new ArrayList<String>();
    final ArrayList<String> collegeList = new ArrayList<>();
    final ArrayList<String> collegeIdList = new ArrayList<>();
    final ArrayList<String> countryIdList = new ArrayList<String>();
    final ArrayList<String> categoryList = new ArrayList<String>();
    final ArrayList<String> categoryIdList = new ArrayList<String>();
    final ArrayList<String> stateIdList = new ArrayList<String>();
    final ArrayList<String> cityIdList = new ArrayList<String>();
    final ArrayList<String> courseCatIdList = new ArrayList<String>();
    StringBuilder offerDetails_join = new StringBuilder();
    Calendar myCalendar = Calendar.getInstance();
    ProgressDialog pd = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_form);
        init();
    }

    private void init() {
        edtName = findViewById(R.id.edt_name);
        edtDob = findViewById(R.id.edt_dob);
        edtPhone = findViewById(R.id.edt_phone);
        edtCollege = findViewById(R.id.edt_college);
        edtEmail = findViewById(R.id.edt_email);
        aedtCountry = findViewById(R.id.edt_country);
        aedtState = findViewById(R.id.edt_state);
        aedtCity = findViewById(R.id.edt_city);
        aedtCourse = findViewById(R.id.edt_course);
        tCouseCost = findViewById(R.id.txt_course_cost);
        tJoinStatus = findViewById(R.id.txt_join_status);
        edtAddress = findViewById(R.id.edt_address);
        offerAvailable = findViewById(R.id.edt_offer_avail);
        nextButton = findViewById(R.id.btn_next);
        tGetOffer = findViewById(R.id.txt_get_offer);
        category_course = findViewById(R.id.edt_cat_course);
        userRole = getIntent().getStringExtra(Constants.USER_ROLE);
        userRollNo = getIntent().getStringExtra(Constants.USER_ROLE_ID);
        edtdepartmentYear = findViewById(R.id.edt_department_year);

        if(userRollNo.equalsIgnoreCase(Constants.ROLE_SCHOOL))
        {
            String[] departmentList = new String[]{"5th to 9th","10th to 12th"};
            edtdepartmentYear.setAdapter(new ArrayAdapter<String >(this,
                    android.R.layout.simple_spinner_dropdown_item,departmentList));
            edtdepartmentYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    departmentId = ""+i;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        else  if(userRollNo.equalsIgnoreCase(Constants.ROLE_COLLEGE))
        {
            String[] departmentList = new String[]{"1st Year","2nd Year", "3rd Year","4th Year"};
            edtdepartmentYear.setAdapter(new ArrayAdapter<String >(this,
                    android.R.layout.simple_spinner_dropdown_item,departmentList));
            edtdepartmentYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    departmentId = ""+i;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        else  if(userRollNo.equalsIgnoreCase(Constants.ROLE_PROJECT))
        {
//            String[] departmentList = new String[]{"5th to 9th","10th to 12th"};
//            edtdepartmentYear.setAdapter(new ArrayAdapter<String >(this,
//                    android.R.layout.simple_spinner_dropdown_item,departmentList));
            edtdepartmentYear.setVisibility(View.GONE);
        }
        pd = new ProgressDialog(AdmissionForm.this);

        offerDetails_join.append("Applied Offer! \n");
        pd.setMessage("Loading");
        getCountry();


        getCatgories();
        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AdmissionForm.this, date, myear, mmonth,
                       mday).show();
            }
        });
        tGetOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOffers();
            }
        });

        aedtCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aedtCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
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
        aedtState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aedtState.getSelectedItem().toString().equalsIgnoreCase("Select State"))
                {
                    getCity(stateIdList.get(i));
                    state_pos = i;
                }

                Log.e("SASASASA", "State ID :   " + stateIdList.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        aedtCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(userRollNo.equalsIgnoreCase(Constants.ROLE_PROJECT))
                {
                    edtCollege.setVisibility(View.GONE);
                    ((TextView)findViewById(R.id.txt_get_offer)).setVisibility(View.GONE);
                }
                else
                {
                    if(!aedtCity.getSelectedItem().toString().equalsIgnoreCase("Select City"))
                    {
                        getOrganization(cityIdList.get(i));
                        city_pos = i;
                        Log.e("SSSSSSSSSS",""+cityIdList.get(i));
                    }
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        edtCollege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                orgPosition = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        aedtCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aedtCourse.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
                {
                    getCategoryCourse(categoryIdList.get(i));
                    cat_pos = i;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        category_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                course_pos = i;
                cost_pos = i;
                Log.e("BALANCE_AMT",courseCatList.get(i)+"///"+courseCatIdList.get(i)+"///"+i+"///"+BalanceAmount);
                try {
                    BalanceAmount = costList.get(i);
                }catch (IndexOutOfBoundsException ex)
                {
                    ex.printStackTrace();
                }
                for (int ij =0; i<costList.size();i++)
                {
                    Log.e("BALANCE_AMT"+i,costList.get(ij));
                }

                Log.e("BALANCE_AMT",""+BalanceAmount);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtDob.setText(sdf.format(myCalendar.getTime()));
    }

    public void onNextClick(View view) {
        if(isValid()) {
            if (nextButton.getText().toString().trim().equalsIgnoreCase("Next")) {
                LayoutInflater factory = LayoutInflater.from(AdmissionForm.this);
                final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(
                        AdmissionForm.this).create();
                deleteDialog.setView(deleteDialogView);

                Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);
                Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
                Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);

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
                        Toast.makeText(AdmissionForm.this, "You are selecting Join now",
                                Toast.LENGTH_SHORT).show();
                        nextButton.setText("Submit");
                        sts_joinings = "now";
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                AdmissionForm.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        Toast.makeText(AdmissionForm.this,
                                                "" + dayOfMonth + "-" +
                                                        (monthOfYear + 1) + "-" + year,
                                                Toast.LENGTH_SHORT).show();
                                        alertDate = "" + dayOfMonth + "-" +
                                                (monthOfYear + 1) + "-" + year;
                                        nextButton.setText("Set Alert");
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                        Toast.makeText(AdmissionForm.this, "You are selecting Later",
                                Toast.LENGTH_SHORT).show();
                        sts_joinings = "later";
                        nextButton.setText("Set Alert");

                    }
                });

                deleteDialog.show();
            } else if (nextButton.getText().toString().trim().
                    equalsIgnoreCase("Set Alert")) {
                if (isValid()) {
                    setStudentAlert();
                }
            } else
                if (sts_joinings.equalsIgnoreCase("now")) {
                    if (isValid()) {
                        uploadStudentInfo();
                    }

            }
        }
    }

    private boolean isValid() {
        boolean val = true;

        if (edtName.getText().toString().trim().isEmpty()) {
            edtName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtEmail.getText().toString().trim().isEmpty()) {
            edtEmail.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if (edtDob.getText().toString().trim().isEmpty()) {
            edtDob.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtPhone.getText().toString().trim().isEmpty()) {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if (edtPhone.getText().toString().trim().isEmpty()) {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if (aedtCountry.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            val = false;
        }
        if (aedtState.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();
            val = false;
        }
        if (aedtCity.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
            val = false;
        }

        if (aedtCourse.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select one Course", Toast.LENGTH_SHORT).show();
            val = false;
        }
        return val;
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
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, collegeList);
                        edtCollege.setAdapter(adapter);

                    }


                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EEEEEEE",""+error.getMessage());
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, cityList);
                        aedtCity.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, stateList);
                        aedtState.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
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
                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, countryList);
        aedtCountry.setAdapter(countryAdapt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    //Join Now
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
                                Toast.makeText(AdmissionForm.this, "" + msg,
                                        Toast.LENGTH_SHORT).show();
                                JSONObject jobj =jsonObject.getJSONObject("student");
                                String studentId = jobj.getString("id");
                                Intent in = new Intent(AdmissionForm.this,
                                        PaymentStatus.class);
                                in.putExtra("cost", BalanceAmount);
                                in.putExtra("stud_id",studentId);

                                Log.e("RESPONSE111", studentId+"" +  tCouseCost.getText().toString().trim());
                                startActivity(in);
                                finish();

                            } else {
                                Toast.makeText(AdmissionForm.this, "Submition failed",
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
                Toast.makeText(AdmissionForm.this, "" + error,
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", edtName.getText().toString().trim());
                params.put("dob", edtDob.getText().toString().trim());
                params.put("instituation_id",getIntent().getExtras().getString(Constants.USER_ROLE_ID));
                params.put("organization_id", collegeIdList.get(orgPosition));
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("email", edtEmail.getText().toString().trim());
                params.put("country_id", countryIdList.get(country_pos));
                params.put("state_id", stateIdList.get(state_pos));
                params.put("city_id", cityIdList.get(city_pos));
                params.put("address", edtAddress.getText().toString().trim());
                params.put("course_id", courseCatIdList.get(course_pos));
                params.put("status", sts_joinings);
                params.put("department_id",departmentId);
                params.put("alert_date", alertDate);
                params.put("join_status", "1");
                params.put("role", userRollNo);
                params.put("category_id",categoryIdList.get(category_pos));
                params.put("org_discount_type",""+org_dis_type);
                params.put("org_discount",""+org_dis);
                params.put("course_discount_type",""+course_dis_type);
                params.put("course_discount",""+ course_dis);
                params.put("calc_amount",BalanceAmount);
                return params;
                //1 - percentage...
                //2 - ruppess...
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    // Join Later
    private void setStudentAlert() {
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
                                new AwesomeNoticeDialog(AdmissionForm.this)
                                        .setTitle("Success!")
                                        .setMessage("Alert Saved Successfully")
                                        .setColoredCircle(R.color.colorPrimaryDark)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setButtonText("Ok")
                                        .setButtonBackgroundColor(R.color.black)
                                        .setButtonText("Ok")
                                        .setNoticeButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in =new Intent( AdmissionForm.this, MenuActivity.class);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(AdmissionForm.this, "Submition failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", edtName.getText().toString().trim());
                params.put("dob", edtDob.getText().toString().trim());
                params.put("instituation_id",getIntent().getExtras().getString(Constants.USER_ROLE_ID));
                params.put("organization_id", collegeIdList.get(orgPosition));
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("email", edtEmail.getText().toString().trim());
                params.put("country_id", countryIdList.get(country_pos));
                params.put("state_id", stateIdList.get(state_pos));
                params.put("city_id", cityIdList.get(city_pos));
                params.put("address", edtAddress.getText().toString().trim());
                params.put("course_id", courseCatIdList.get(course_pos));
                params.put("status", sts_joinings);
                params.put("alert_date", alertDate);
                params.put("department_id",departmentId);
                params.put("join_status", "2");
                params.put("role", userRollNo);
                params.put("category_id",categoryIdList.get(category_pos));
                params.put("org_discount_type",""+org_dis_type);
                params.put("org_discount",""+org_dis);
                params.put("course_discount_type",""+course_dis_type);
                params.put("course_discount",""+ course_dis);
                params.put("calc_amount","0.0");
                return params;
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
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        aedtCourse.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
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
                                Toast.makeText(AdmissionForm.this, "No Course Available", Toast.LENGTH_SHORT).show();
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
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, courseCatList);
                        category_course.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
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

    private void getOffers() {
        final int calcAmount = 0;
       Log.e("CHECK IDS",""+courseCatIdList.get(course_pos)+"////" +
               ""+collegeIdList.get(orgPosition)+"////" +
               ""+userRollNo);
        offerDetails_join.delete(0,offerDetails_join.length());
        offerDetails_join.append("Applied Offer! \n");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/offer-details";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String sts = jobj.getString("status");
                            if(sts.equalsIgnoreCase("1"))
                            {
                                JSONObject jobjCourse = jobj.getJSONObject("course");
                                JSONObject jobjOrg = jobj.getJSONObject("organization");


                                    String courseName = jobjCourse.getString("course");
                                    String courseOfferType = jobjCourse.getString("offer_type");
                                    String courseOffer = jobjCourse.getString("offer");
                                    String courseAmount = jobjCourse.getString("amount");

                                    String OrgName = jobjOrg.getString("name");
                                    String OrgOfferType = jobjOrg.getString("offer_type");
                                    String OrgOffer = jobjOrg.getString("offer");
                                    String orgAmount = jobjCourse.getString("amount");

                                org_dis_type =OrgOfferType;
                                org_dis =OrgOffer;
                                course_dis_type = courseOfferType;
                                course_dis = courseOffer;
                                Double balAmount =0.0;
                               if(!courseOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE))
                                {
                                    String type = "Rs";
                                    if(courseOfferType.equalsIgnoreCase(Constants.OFFER_PERCENTAGE)) {
                                        type = "%";
                                        balAmount = (Double.valueOf(courseOffer)/100.f) * Double.valueOf(courseAmount);
                                        //balAmount = Double.valueOf(courseAmount) - Double.valueOf(courseAmount)* Double.valueOf(courseOffer)/100 ;
                                        offerDetails_join.append("" + courseName + "    " + courseOffer + "" + type + "\n");

                                        //amounttotal = amount*18/100;
                                    }
                                    else {
                                        balAmount = Double.valueOf(courseAmount) - Double.valueOf(courseOffer);
                                        offerDetails_join.append("" + courseName + "    " + type + "" + courseOffer + "\n");
                                    }
                                }
                                if(!OrgOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE))
                                {
                                    String type = "Rs";
                                    if(OrgOfferType.equalsIgnoreCase(Constants.OFFER_PERCENTAGE)) {
                                        type = "%";
                                        balAmount = (Double.valueOf(OrgOffer)/100.f) * Double.valueOf(orgAmount);
                                       // balAmount = balAmount * Double.valueOf(OrgOffer) / 100;
                                        offerDetails_join.append(""+OrgName+"      "+OrgOffer+" "+type+"\n");
                                    }
                                    else {
                                        balAmount = balAmount - Double.valueOf(OrgOffer);
                                        offerDetails_join.append(""+OrgName+"      "+type+" "+OrgOffer+"\n");
                                    }

                                }
                                if(OrgOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE)&&
                                        courseOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE))
                                {
                                    offerDetails_join.append("Sorry, Currently no offers");
                                    balAmount =0.0;
                                    //Toast.makeText(AdmissionForm.this, "Sorry, Currently no offers", Toast.LENGTH_SHORT).show();
                                }

                               offerAvailable.setText(""+offerDetails_join+"\n \nAmount Payable : Rs "+balAmount);
                               offerAvailable.setVisibility(View.VISIBLE);
                                BalanceAmount = String.valueOf(balAmount);



                            }
                            else
                            {
                                Toast.makeText(AdmissionForm.this, ""+jobj.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>() ;
                params.put("course_id",courseCatIdList.get(course_pos));
                params.put("organization_id",collegeIdList.get(orgPosition));
                params.put("role",userRollNo);
                return params;

            }
        };

        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {

        new AwesomeSuccessDialog(AdmissionForm.this)
                .setTitle("Exit")
                .setMessage("Are you sure want to leave the form?")
                .setColoredCircle(R.color.colorPrimary)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(true)
                .setPositiveButtonText("Yes")
                .setPositiveButtonbackgroundColor(R.color.colorPrimary)
                .setPositiveButtonTextColor(R.color.white)
                .setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        startActivity(new Intent(AdmissionForm.this, MenuActivity.class));
                        finish();
                    }
                })
                .setNegativeButtonText("Cancel")
                .setNegativeButtonTextColor(R.color.white)
                .setNegativeButtonClick(new Closure() {
                    @Override
                    public void exec() {

                    }
                })
                .show();

    }

}
