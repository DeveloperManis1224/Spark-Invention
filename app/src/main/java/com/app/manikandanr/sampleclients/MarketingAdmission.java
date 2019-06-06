package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.app.manikandanr.sampleclients.Utils.SingleShortLocationProvider;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MarketingAdmission extends AppCompatActivity {

    String yearSelected;
    private EditText eInsName,eStaffName,eStaffPhone,eInsMail,eStrength, eInsLandline,eInsAddress,
            ePrincipalAoName, ePrincipalAoDetails, eOtherDetails, eWebsite;
    private Spinner aeCountry,aeState,aeCity, aeOrganization , aeYear;
    private Spinner sEvent;
    int event_pos =0;
    final ArrayList<String> countryList = new ArrayList<String>();
    final ArrayList<String> stateList = new ArrayList<String>();
    final ArrayList<String> cityList = new ArrayList<String>();
    final ArrayList<String> organizationList = new ArrayList<String>();
    final ArrayList<String> eventList = new ArrayList<String>();
    final ArrayList<String> eventIdList = new ArrayList<String>();

    final ArrayList<String> departmentList = new ArrayList<String>();
    final ArrayList<String> departmentIdList = new ArrayList<String>();


    final ArrayList<String> countryIdList = new ArrayList<String>();
    final ArrayList<String> organizationIdList = new ArrayList<String>();
    final ArrayList<String> stateIdList = new ArrayList<String>();
    final ArrayList<String> cityIdList = new ArrayList<String>();

    int country_pos= 0;
    int state_pos = 0;
    int city_pos = 0;
    int org_pos = 0;
    String userRole,userRollNo;
    private String alertDate = "";
    private String sts_joinings = "";
    private Button btnNext;
    private String insDescrption = "";
    ProgressDialog pd = null;
    ArrayList<String> yearlist = new ArrayList<>();
    GpsTracker gpsTracker;
    private String kmsFromBranch = "";

    SessionManager sessionManager = new SessionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_admission);
       // getDistance();
        initD();
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        double kmInDec = Double.parseDouble(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
       // Toast.makeText(MarketingAdmission.this, "fhgfhfhg "+km, Toast.LENGTH_SHORT).show();
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
        kmsFromBranch = String.valueOf(kmInDec);
        return Radius * c;
    }

    private void initD()
    {
        gpsTracker = new GpsTracker(MarketingAdmission.this);

        gpsTracker.getLocation();

        eInsName = findViewById(R.id.edt_ins_name);
        eStaffName = findViewById(R.id.edt_staff_name);
        eStaffPhone = findViewById(R.id.edt_staff_phone);
        eInsMail = findViewById(R.id.edt_ins_mail);
        eWebsite = findViewById(R.id.edt_ins_website);
        ePrincipalAoName = findViewById(R.id.edt_principal_ao_name);
        ePrincipalAoDetails = findViewById(R.id.edt_principal_ao_details);
        eOtherDetails = findViewById(R.id.edt_other_details);
        eInsLandline = findViewById(R.id.edt_landline);
        eInsAddress = findViewById(R.id.edt_ins_address);
        aeCountry = findViewById(R.id.edt_country);
        aeOrganization = findViewById(R.id.edt_organization);
        aeYear = findViewById(R.id.edt_year);
        eStrength = findViewById(R.id.edt_strength);
        aeCity = findViewById(R.id.edt_city);
        aeState = findViewById(R.id.edt_state);
        sEvent = findViewById(R.id.spi_event);
        btnNext = findViewById(R.id.btn_next_marketing);
        userRole = getIntent().getStringExtra("role");
        userRollNo = getIntent().getStringExtra("role_id");

        aeCountry.setVisibility(View.GONE);
        aeState.setVisibility(View.GONE);
        aeCity.setVisibility(View.GONE);
        pd = new ProgressDialog(MarketingAdmission.this);
        pd.setMessage("loading");

        getEvents();

//        = new String[]{"1st Year","2nd Year","3rd Year","4 Year"};
        if(getIntent().getExtras().getString(Constants.USER_ROLE).equalsIgnoreCase(Constants.USER_TYPE_SCHOOL))
        {
            yearlist.clear();
            yearlist.add("5th std to 9th std");
            yearlist.add("10th std to 12th std");
        }
        else if(getIntent().getExtras().getString(Constants.USER_ROLE).equalsIgnoreCase(Constants.USER_TYPE_COLLEGE))
        {
            yearlist.clear();
            yearlist.add("1st Year");
            yearlist.add("2nd Year");
            yearlist.add("3rd Year");
            yearlist.add("4th Year");
        }
        aeYear.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,yearlist));

        aeYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(aeYear.getSelectedItem().toString().equalsIgnoreCase("1st Year"))
                {
                    yearSelected ="1";
                }
                else if(aeYear.getSelectedItem().toString().equalsIgnoreCase("2nd Year"))
                {
                    yearSelected ="2";
                }
                else if (aeYear.getSelectedItem().toString().equalsIgnoreCase("3rd Year"))
                {
                    yearSelected = "3";
                }
                else if(aeYear.getSelectedItem().toString().equalsIgnoreCase("4th Year"))
                {
                    yearSelected = "4";
                }
                else if (aeYear.getSelectedItem().toString().equalsIgnoreCase("5th std to 9th std"))
                {
                    yearSelected = "5";
                }
                else if(aeYear.getSelectedItem().toString().equalsIgnoreCase("10th std to 12th std"))
                {
                    yearSelected = "6";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getAccurateDistance(13.056840,80.255169,13.055560,80.257733);
        Log.e("Distance Kms","Getrtryftryfgh:   "+getAccurateDistance(13.056840,80.255169,13.055560,80.257733));
        getCountry();
        aeCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aeCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
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

        aeOrganization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                org_pos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        aeState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aeState.getSelectedItem().toString().equalsIgnoreCase("Select State"))
                {
                    getCity(stateIdList.get(i));
                    state_pos = i;
                    Log.e("SASASASA", "State ID :   " + stateIdList.get(i));
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        aeCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aeCity.getSelectedItem().toString().equalsIgnoreCase("Select City"))
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

        sEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 event_pos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SingleShortLocationProvider.requestSingleUpdate(MarketingAdmission.this,
                new SingleShortLocationProvider.LocationCallback() {
                    @Override public void onNewLocationAvailable(SingleShortLocationProvider.GPSCoordinates location) {
                        Log.d("Locationasdasd", "my location is " + location.latitude+"   "+location.longitude);
                        lat = String.valueOf(location.latitude);
                        lon = String.valueOf(location.longitude);
                        Log.d("Locationasdasd", "my location is Current " + lat+"   "+lon);

                        CalculationByDistance(new LatLng(13.7037843,80.236423),
                                new LatLng(location.latitude,location.longitude));
                    }
                });

        cityList.add("Select City");
        stateList.add("Select State");


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        aeCity.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, stateList);
        aeState.setAdapter(adapter2);


    }

    private void getOrganization(final String cityId) {
        organizationList.clear();
        organizationIdList.clear();
        organizationList.add("Select Organization");
        organizationIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/branch-organization";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SSSSS",""+response);
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("organizations");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                organizationList.add(jobj1.getString("name"));
                                organizationIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item,
                                        organizationList);
                        aeOrganization.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EEEEEEE",""+error.getMessage());
                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("city_id",cityId);
//                params.put("role", userRollNo);
                params.put("instituation_id",userRole);
                params.put("user_id",sessionManager.getPreferences(MarketingAdmission.this,
                        Constants.USER_ID));
                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    public void setAlertDate(View v)
    {
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

                        alertDate = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        ((TextView)findViewById(R.id.alert_date)).setText("Selected date is "+alertDate+".(Click to change)");
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        Toast.makeText(MarketingAdmission.this, "Event Confirmed.", Toast.LENGTH_SHORT).show();
        btnNext.setText("Submit");
        sts_joinings = "now";
    }
    public void onMarketingSubmit(View v)
    {
        if(isValid())
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
                        setAlertDate(v);
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
//                                        Toast.makeText(MarketingAdmission.this, "" + dayOfMonth + "-" +
//                                                (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();
                                        alertDate = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                        Toast.makeText(MarketingAdmission.this, "Not Confirmed", Toast.LENGTH_SHORT).show();
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

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout11 = inflater.inflate(R.layout.dialog_remarks_lyt, (ViewGroup) findViewById(R.id.layout_root));
                final EditText et = layout11.findViewById(R.id.edt_remarks);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MarketingAdmission.this);
                builder.setView(layout11);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!et.getText().toString().isEmpty()) {
                            insDescrption = et.getText().toString().trim();
                            setInstituationDetails();
                            dialog.dismiss();
                        }
                        else
                        {
                            et.setError("Invalid");
                        }

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
            else
                if (sts_joinings.equalsIgnoreCase("now")) {
                    if (isValid()) {

                        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View layout11 = inflater.inflate(R.layout.dialog_admission_alert, null);
                        final EditText et  = layout11.findViewById(R.id.edt_txt_remarks);
                        final TextView txtTitle = layout11.findViewById(R.id.title_txt);
                        final TextView txtMessage = layout11.findViewById(R.id.messga_txt);
                        txtTitle.setText("Confirmation");
                        txtMessage.setText("Are you sure want to Submit?\nSchedule Date is : "+alertDate+"\n(Remarks is optional)");
                        if(layout11.getParent() != null) {
                            ((ViewGroup)layout11.getParent()).removeView(layout11); // <- fix
                        }

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setView(layout11);
                        alertDialog.setCancelable(false).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(!et.getText().toString().trim().isEmpty())
                                {
                                    insDescrption =et.getText().toString().trim();
                                    uploadStudentInfo();

                                }
                                else
                                {
                                    Toast.makeText(MarketingAdmission.this, "Please enter remarks...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.setCancelable(true);
                        alertDialog.show();
                    }
                }
        }

    }

//    private float distanceBetween(LatLng latLng1, LatLng latLng2) {
//
//        Location loc1 = new Location(LocationManager.GPS_PROVIDER);
//        Location loc2 = new Location(LocationManager.GPS_PROVIDER);
//
//        loc1.setLatitude(latLng1.latitude);
//        loc1.setLongitude(latLng1.longitude);
//
//        loc2.setLatitude(latLng2.latitude);
//        loc2.setLongitude(latLng2.longitude);
//
//
//        return loc1.distanceTo(loc2);
//    }

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
                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, cityList);
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
                params.put("state_id", stateId);
                return params;
            }
        };
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
                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, stateList);
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
                params.put("country_id", country_id);
                return params;
            }
        };
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

                            for (int i = 0; i < jary.length(); i++) {
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
                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, countryList);
                        aeCountry.setAdapter(countryAdapt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getEvents() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/event";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("event");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                eventList.add(jobj1.getString("name"));
                                eventIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, eventList);
                        sEvent.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MarketingAdmission.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) ;
        queue.add(stringRequest);
    }

    private double getDistance() {
//        LatLng latLngA = new LatLng(12.3456789,98.7654321);
//        LatLng latLngB = new LatLng(98.7654321,12.3456789);

        Location locationA = new Location("point A");
        locationA.setLatitude(10.789816);
        locationA.setLongitude(78.695683);
        Location locationB = new Location("point B");
        locationB.setLatitude(10.779545);
        locationB.setLongitude(78.222329);


        Log.v("Distance123",""+locationA.distanceTo(locationB));

        return locationA.distanceTo(locationB);
    }

    private void getDepartments(final String institutionId) {
        departmentList.clear();
        departmentIdList.clear();
        departmentList.add("Select");
        departmentIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/institute-department";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("departments");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                departmentList.add(jobj1.getString("department"));
                                departmentIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (MarketingAdmission.this, android.R.layout.simple_spinner_dropdown_item, departmentList);
                        //aed_department.setAdapter(adapter);

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
                params.put("instituation_id", institutionId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private double getAccurateDistance(double latA,double lngA, double latB, double lngB)
    {
        double disVal = 0;
        Location locationA = new Location("point A");
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);
        Location locationB = new Location("point B");
        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);
        disVal = locationA.distanceTo(locationB) ;
        return  disVal;
    }


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        Log.v("Distance123",""+(dist));
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
                try {
                    params.put("instituation", eInsName.getText().toString().trim());
                    params.put("instituation_id", userRollNo);
                    params.put("serial_no", "1");
                    params.put("staff_name", eStaffName.getText().toString().trim());
                    params.put("phone", eStaffPhone.getText().toString().trim());
                    params.put("email", eInsMail.getText().toString().trim());
                    params.put("country_id", "0");
                    params.put("state_id", "0");
                    params.put("city_id", "0");
                    params.put("organization_id", eInsName.getText().toString().trim());
//              params.put("organization_id",organizationIdList.get(org_pos));
                    params.put("year", yearSelected);
                    params.put("strength", "" + eStrength.getText().toString().trim());
                    params.put("address", eInsAddress.getText().toString().trim());
                    params.put("landline", eInsLandline.getText().toString().trim());
                    params.put("event_id", eventIdList.get(event_pos));
                    params.put("status", userRollNo);
                    params.put("set_date", alertDate);
                    params.put("demo_date", alertDate);
                    params.put("next_date", alertDate);
                    params.put("description", insDescrption);
                    params.put("website", eWebsite.getText().toString().trim());
                    params.put("distance", kmsFromBranch);
                    params.put("latitude", "" + lat);
                    params.put("longitude", "" + lon);
                    params.put("join_status", "2");
                    params.put("department_id", "1");
                    params.put("principal_ao_name", ePrincipalAoName.getText().toString().trim());
                    params.put("principal_ao_details", ePrincipalAoDetails.getText().toString().trim());
                    params.put("other_details", eOtherDetails.getText().toString().trim());
                    params.put("user_id", sessionManager.getPreferences(MarketingAdmission.this, Constants.USER_ID));
                }catch (Exception ex){
                    ex.printStackTrace();
                    Log.e("ERROR_MARKETING",""+ex.getMessage());
                }
                return params;

            }
        };
        queue.add(stringRequest);
    }

//Event Confirm
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
                try {
                    params.put("instituation", eInsName.getText().toString().trim());
                    params.put("instituation_id", userRollNo);
                    params.put("staff_name", eStaffName.getText().toString().trim());
                    params.put("phone", eStaffPhone.getText().toString().trim());
                    params.put("email", eInsMail.getText().toString().trim());
                    params.put("country_id", "0");
                    params.put("state_id", "0");
                    params.put("city_id", "0");
                    params.put("organization_id",  eInsName.getText().toString().trim());
                   // params.put("organization_id", organizationIdList.get(org_pos));
                    params.put("year", yearSelected);
                    params.put("strength", eStrength.getText().toString().trim());
                    params.put("address", eInsAddress.getText().toString().trim());
                    params.put("landline", eInsLandline.getText().toString().trim());
                    params.put("event_id", eventIdList.get(event_pos));
                    params.put("status", userRollNo);
                    params.put("set_date", alertDate);
                    params.put("demo_date", alertDate);
                    params.put("next_date", alertDate);
                    params.put("description", insDescrption);
                    params.put("website", eWebsite.getText().toString().trim());
                    params.put("distance", "33");
                    params.put("latitude", "" + lat);
                    params.put("longitude", "" + lon);
                    params.put("join_status", "1");
                    params.put("department_id", "1");
                    params.put("principal_ao_name", ePrincipalAoName.getText().toString().trim());
                    params.put("principal_ao_details", ePrincipalAoDetails.getText().toString().trim());
                    params.put("other_details", eOtherDetails.getText().toString().trim());
                    params.put("user_id", sessionManager.getPreferences(MarketingAdmission.this, Constants.USER_ID));

                }catch ( Exception ex)
                {
                    ex.printStackTrace();
                    Log.e("ERROR_MSG",""+ex.getMessage());
                }
                return params;
            }
        };
        queue.add(stringRequest);
    }


    String lat, lon;
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
        if (ePrincipalAoName.getText().toString().trim().isEmpty()) {
            ePrincipalAoName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (ePrincipalAoDetails.getText().toString().trim().isEmpty()) {
            ePrincipalAoDetails.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (eOtherDetails.getText().toString().trim().isEmpty()) {
            eOtherDetails.setError(getResources().getString(R.string.error_msg));
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
        if (eWebsite.getText().toString().trim().isEmpty()) {
            eWebsite.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (eStrength.getText().toString().trim().isEmpty()) {
            eStrength.setError(getResources().getString(R.string.error_msg));
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
//        if (aeCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country")) {
//            Toast.makeText(this, "Invalid Country", Toast.LENGTH_SHORT).show();
//            val = false;
//        }
//        if (aeState.getSelectedItem().toString().equalsIgnoreCase("Select State")) {
//            Toast.makeText(this, "Invalid State", Toast.LENGTH_SHORT).show();
//            val = false;
//        }
//        if (aeCity.getSelectedItem().toString().equalsIgnoreCase("Select City")) {
//            Toast.makeText(this, "Invalid City", Toast.LENGTH_SHORT).show();
//            val = false;
//        }
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