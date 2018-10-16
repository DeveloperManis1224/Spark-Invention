package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class AdmissionForm extends AppCompatActivity {
    private Spinner aedtCourse;
    private String alertDate = "";
    private String sts_joinings = "";
    private Button nextButton;
    private TextView tCouseCost;
    private String userRole = "";
    private String userRollNo = "";
    private EditText edtName, edtDob, edtCollege, edtPhone, edtEmail, edtAddress;
    private AutoCompleteTextView aedtCountry, aedtState, aedtCity;
    final ArrayList<String> costList = new ArrayList<String>();
    final ArrayList<String> courseList = new ArrayList<String>();
    final ArrayList<String> countryList = new ArrayList<String>();
    final ArrayList<String> stateList = new ArrayList<String>();
    final ArrayList<String> cityList = new ArrayList<String>();
    Calendar myCalendar = Calendar.getInstance();

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
        edtAddress = findViewById(R.id.edt_address);
        nextButton = findViewById(R.id.btn_next);
        userRole = getIntent().getStringExtra("role");
        userRollNo = getIntent().getStringExtra("role_id");

        countryList.add("India");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (AdmissionForm.this, android.R.layout.select_dialog_item, countryList);
        aedtCountry.setThreshold(1);
        aedtCountry.setAdapter(adapter);
        getState();
        getCourseDetails();

        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AdmissionForm.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        aedtCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tCouseCost.setText("" + costList.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        aedtState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                getCity();
                Log.e("SASASASA", "" + getCategoryPos(String.valueOf(parent.getItemAtPosition(pos))));
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
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtDob.setText(sdf.format(myCalendar.getTime()));
    }

    public void onNextClick(View view) {
        if (nextButton.getText().toString().trim().equalsIgnoreCase("Next")) {
            LayoutInflater factory = LayoutInflater.from(AdmissionForm.this);
            final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
            final AlertDialog deleteDialog = new AlertDialog.Builder(AdmissionForm.this).create();
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
                    Toast.makeText(AdmissionForm.this, "You are selecting Join now", Toast.LENGTH_SHORT).show();
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(AdmissionForm.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    Toast.makeText(AdmissionForm.this, "" + dayOfMonth + "-" +
                                            (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();
                                    alertDate = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    nextButton.setText("Submit");
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                    Toast.makeText(AdmissionForm.this, "You are selecting Later", Toast.LENGTH_SHORT).show();
                    sts_joinings = "later";
                    nextButton.setText("Set Alert");

                }
            });
            btnProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteDialog.dismiss();

                }
            });
            deleteDialog.show();
        } else if (nextButton.getText().toString().trim().equalsIgnoreCase("Set Alert")) {
            if (isValid()) {
                setStudentAlert();
            }

        } else {
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
        if (edtCollege.getText().toString().trim().isEmpty()) {
            edtCollege.setError(getResources().getString(R.string.error_msg));
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

        if (aedtCountry.getText().toString().trim().isEmpty()) {
            aedtCountry.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (aedtState.getText().toString().trim().isEmpty()) {
            aedtState.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (aedtCity.getText().toString().trim().isEmpty()) {
            aedtCity.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if (aedtCourse.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select one Course", Toast.LENGTH_SHORT).show();
            val = false;
        }
        return val;
    }

    private void getCity() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/city";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("cities");
                            for (int i = 1; i <= jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                cityList.add(jobj1.getString("city"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.select_dialog_item, cityList);
                        aedtCity.setThreshold(1);
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
                params.put("state_id", "22");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void getState() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/state";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("states");
                            for (int i = 1; i <= jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                stateList.add(jobj1.getString("state"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.select_dialog_item, stateList);
                        aedtState.setThreshold(1);
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
                params.put("country_id", "1");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void uploadStudentInfo() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RESPONSE111", "" + response);

                            JSONObject jsonObject = new JSONObject(response);
                            String sts = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (sts.equalsIgnoreCase("1")) {
                                Toast.makeText(AdmissionForm.this, "" + msg, Toast.LENGTH_SHORT).show();

                                JSONObject jobj =jsonObject.getJSONObject("student");

                                String studentId = jobj.getString("serial_no");

                                Intent in = new Intent(AdmissionForm.this, PaymentStatus.class);
                                in.putExtra("cost", tCouseCost.getText().toString().trim());
                                in.putExtra("stud_id",studentId);
                                startActivity(in);
                                finish();
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
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", edtName.getText().toString().trim());
                params.put("dob", edtDob.getText().toString().trim());
                params.put("college", edtCollege.getText().toString().trim());
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("email", edtEmail.getText().toString().trim());
                params.put("country_id", aedtCountry.getText().toString().trim());
                params.put("state_id", aedtState.getText().toString().trim());
                params.put("city_id", aedtCity.getText().toString().trim());
                params.put("course_id", "1");
                params.put("student_role", userRollNo);
                params.put("status", sts_joinings);
                params.put("join_status", "1");
                params.put("role", userRollNo);
                params.put("address", edtAddress.getText().toString().trim());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void setStudentAlert() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RESPONSE111", "" + response);

                            JSONObject jsonObject = new JSONObject(response);
                            String sts = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (sts.equalsIgnoreCase("1")) {
                                Toast.makeText(AdmissionForm.this, "" + msg, Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(AdmissionForm.this, AlertActivity.class);

                                startActivity(in);
                                finish();
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
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", edtName.getText().toString().trim());
                params.put("dob", edtDob.getText().toString().trim());
                params.put("college", edtCollege.getText().toString().trim());
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("email", edtEmail.getText().toString().trim());
                params.put("country_id", aedtCountry.getText().toString().trim());
                params.put("state_id", aedtState.getText().toString().trim());
                params.put("city_id", aedtCity.getText().toString().trim());
                params.put("course_id", "1");
                params.put("student_role", userRollNo);
                params.put("status", sts_joinings);
                params.put("alert_date", alertDate);
                params.put("join_status", "2");
                params.put("role", userRollNo);
                params.put("address", edtAddress.getText().toString().trim());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void getCourseDetails() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://spark.candyrestaurant.com/api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("courses");
                            for (int i = 1; i <= jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                courseList.add(jobj1.getString("course") + " (Rs " + jobj1.getString("amount") + ")");
                                costList.add(jobj1.getString("amount"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, courseList);
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

    private int getCategoryPos(String category) {
        return stateList.indexOf(category);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdmissionForm.this, MenuActivity.class));
        finish();
    }

}
