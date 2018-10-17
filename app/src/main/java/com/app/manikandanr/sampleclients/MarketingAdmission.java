package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketingAdmission extends AppCompatActivity {

    private EditText eInsName,eStaffName,eStaffPhone,eInsMail,eInsLandline,eInsAddress;
    private AutoCompleteTextView aeCountry,aeState,aeCity;
    private Spinner sEvent;
    final ArrayList<String> countryList = new ArrayList<String>();
    final ArrayList<String> stateList = new ArrayList<String>();
    final ArrayList<String> cityList = new ArrayList<String>();
    final ArrayList<String> eventList = new ArrayList<String>();
    String userRole,userRollNo;
    private String alertDate = "";
    private String sts_joinings = "";
    private Button btnNext;
    private String insDescrption = "";
    ProgressDialog pd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_admission);
        initD();
    }

    private void initD()
    {
        eInsName = findViewById(R.id.edt_ins_name);
        eStaffName = findViewById(R.id.edt_staff_name);
        eStaffPhone = findViewById(R.id.edt_staff_phone);
        eInsMail = findViewById(R.id.edt_ins_mail);
        eInsLandline = findViewById(R.id.edt_landline);
        eInsAddress = findViewById(R.id.edt_ins_address);
        aeCountry = findViewById(R.id.edt_country);
        aeCity = findViewById(R.id.edt_city);
        aeState = findViewById(R.id.edt_state);
        sEvent = findViewById(R.id.spi_event);
        btnNext = findViewById(R.id.btn_next_marketing);
        userRole = getIntent().getStringExtra("role");
        userRollNo = getIntent().getStringExtra("role_id");
        pd = new ProgressDialog(MarketingAdmission.this);
        pd.setMessage("loading");


        countryList.add("India");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, countryList);
        aeCountry.setAdapter(adapter);

        getCity();
        getState();
        getEventDetails();

    }
    private void getEventDetails() {

        eventList.add("Level 1");
        eventList.add("Level 2");
        eventList.add("Level 3");
//                            }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, eventList);
                        sEvent.setAdapter(adapter);
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://spark.candyrestaurant.com/api/student";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jobj = new JSONObject(response);
//                            JSONArray jary = jobj.getJSONArray("marketing");
//                            for (int i = 1; i <= jary.length(); i++) {
//                                JSONObject jobj1 = jary.getJSONObject(i);
//                                eventList.add(jobj1.getString("course"));
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, eventList);
//                        sEvent.setAdapter(adapter);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(stringRequest);
    }

    public void onMarketingSubmit(View v)
    {
        if (btnNext.getText().toString().trim().equalsIgnoreCase("Next")) {
            LayoutInflater factory = LayoutInflater.from(MarketingAdmission.this);
            final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
            final AlertDialog deleteDialog = new AlertDialog.Builder(MarketingAdmission.this).create();
            deleteDialog.setView(deleteDialogView);

            Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);
            Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
            Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);

            btnSchool.setText("Event Confirm");
            btnSchool.setAllCaps(false);

            btnCollege.setText("Not Confirm");
            btnCollege.setAllCaps(false);

            btnProject.setText("Project / Program");
            btnProject.setVisibility(View.GONE);

            btnSchool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteDialog.dismiss();
                    Toast.makeText(MarketingAdmission.this, "Event Confirmed.", Toast.LENGTH_SHORT).show();
                    btnNext.setText("Submit");
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MarketingAdmission.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    Toast.makeText(MarketingAdmission.this, "" + dayOfMonth + "-" +
                                            (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();
                                    alertDate = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    btnNext.setText("Save");
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                    Toast.makeText(MarketingAdmission.this, "You are selecting Later", Toast.LENGTH_SHORT).show();
                    sts_joinings = "later";
                    btnNext.setText("Save");

                }
            });
            btnProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteDialog.dismiss();
                }
            });
            deleteDialog.show();
        } else if (btnNext.getText().toString().trim().equalsIgnoreCase("Save")) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MarketingAdmission.this);
            final EditText et = new EditText(MarketingAdmission.this);
            et.setHint("Remarks");

            alertDialogBuilder.setView(et);

            alertDialogBuilder.setCancelable(false).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if(!et.getText().toString().trim().isEmpty())
                    {
                        insDescrption =et.getText().toString().trim();
                        if (isValid()) {
                            setInstituationDetails();
                        }
                    }
                    else
                    {
                        Toast.makeText(MarketingAdmission.this, "Please enter remarks...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            if (sts_joinings.equalsIgnoreCase("now")) {
                if (isValid()) {
                    uploadStudentInfo();
                }
            }
        }
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
                                (MarketingAdmission.this, android.R.layout.select_dialog_item, cityList);
                        aeCity.setThreshold(1);
                        aeCity.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (MarketingAdmission.this, android.R.layout.select_dialog_item, stateList);
                        aeState.setThreshold(1);
                        aeState.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
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


//Not event Confirm
    private void setInstituationDetails() {
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/marketing";
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
//                                new AwesomeInfoDialog(getApplicationContext())
//                                        .setTitle("Success")
//                                        .setMessage("Instituation Saved Successfully")
//                                        .setColoredCircle(R.color.colorPrimary)
//                                        .setDialogIconAndColor(R.drawable.ic_success,
//                                                R.color.white)
//                                        .setCancelable(true)
//                                        .show();
                                new AwesomeNoticeDialog(MarketingAdmission.this)
                                        .setTitle("Success")
                                        .setMessage("Instituation Saved Successfully")
                                        .setColoredCircle(R.color.colorPrimaryDark)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setButtonText("Ok")
                                        .setButtonBackgroundColor(R.color.black)
                                        .setButtonText("Ok")
                                        .setNoticeButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in =new Intent( MarketingAdmission.this, MenuActivity.class);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
//                                Intent in =new Intent( MarketingAdmission.this, MenuActivity.class);
//                                startActivity(in);
//                                finish();
                            } else {
                                Toast.makeText(MarketingAdmission.this, "Submition failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("RESPONSE111", "" + error);
                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("instituation", eInsName.getText().toString().trim());
                params.put("instituation_id", "1");
                params.put("serial_no", "1");
                params.put("staff_name", eStaffName.getText().toString().trim());
                params.put("phone", eStaffPhone.getText().toString().trim());
                params.put("email", eInsMail.getText().toString().trim());
                params.put("state", aeState.getText().toString().trim());
                params.put("city", aeCity.getText().toString().trim());
                params.put("country", aeCountry.getText().toString().trim());
                params.put("address", eInsAddress.getText().toString().trim());
                params.put("landline", eInsLandline.getText().toString().trim());
                params.put("event", "Nothing");
                params.put("status", userRollNo);
                params.put("set_date",alertDate);
                params.put("description",insDescrption);
                return params;
            }
        };
        queue.add(stringRequest);
    }


//event Confirm
    private void uploadStudentInfo() {
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/marketing";
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
                                //Toast.makeText(MarketingAdmission.this, "" + msg, Toast.LENGTH_SHORT).show();

                                new AwesomeNoticeDialog(MarketingAdmission.this)
                                        .setTitle("Success")
                                        .setMessage("Instituation Added Successfully")
                                        .setColoredCircle(R.color.colorPrimaryDark)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setButtonText("Ok")
                                        .setButtonBackgroundColor(R.color.black)
                                        .setButtonText("Ok")
                                        .setNoticeButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in =new Intent( MarketingAdmission.this, MenuActivity.class);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
//                                new AwesomeInfoDialog(getApplicationContext())
//                                    .setTitle("Success")
//                                    .setMessage("Instituation Added Successfully")
//                                    .setColoredCircle(R.color.colorPrimary)
//                                    .setDialogIconAndColor(R.drawable.ic_success,
//                                       R.color.white)
//                                     .setCancelable(true)
//                                     .show();
//                                Intent in = new Intent(MarketingAdmission.this,MenuActivity.class);
//                                startActivity(in);
//                                finish();

                            } else {
                                Toast.makeText(MarketingAdmission.this, "Submition failed", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("RESPONSE111", "" + error);
                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("instituation", eInsName.getText().toString().trim());
                params.put("instituation_id", "1");
                params.put("serial_no", "1");
                params.put("staff_name", eStaffName.getText().toString().trim());
                params.put("phone", eStaffPhone.getText().toString().trim());
                params.put("email", eInsMail.getText().toString().trim());
                params.put("state", aeState.getText().toString().trim());
                params.put("city", aeCity.getText().toString().trim());
                params.put("country", aeCountry.getText().toString().trim());
                params.put("address", eInsAddress.getText().toString().trim());
                params.put("landline", eInsLandline.getText().toString().trim());
                params.put("event", sEvent.getSelectedItem().toString().trim());
                params.put("status", userRollNo);
                params.put("set_date",alertDate);
                params.put("description","Nothing");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private boolean isValid() {
        boolean val = true;
        if (eInsName.getText().toString().trim().isEmpty()) {
            eInsName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (eStaffName.getText().toString().trim().isEmpty()) {
            eStaffName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (eStaffPhone.getText().toString().trim().isEmpty()) {
            eStaffPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (eInsMail.getText().toString().trim().isEmpty()) {
            eInsMail.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (eInsAddress.getText().toString().trim().isEmpty()) {
            eInsAddress.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (eInsLandline.getText().toString().trim().isEmpty()) {
            eInsLandline.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (aeCountry.getText().toString().trim().isEmpty()) {
            aeCountry.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (aeState.getText().toString().trim().isEmpty()) {
            aeState.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (aeCity.getText().toString().trim().isEmpty()) {
            aeCity.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (sEvent.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Select one Event", Toast.LENGTH_SHORT).show();
            val = false;
        }
        return val;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(MarketingAdmission.this, MenuActivity.class));
        finish();
    }
}