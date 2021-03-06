package com.app.manikandanr.sampleclients.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.app.manikandanr.sampleclients.AdmissionForm;
import com.app.manikandanr.sampleclients.Data.LoginResponseData;
import com.app.manikandanr.sampleclients.MenuActivity;
import com.app.manikandanr.sampleclients.PasswordActivity;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddStudentAttendance extends AppCompatActivity {
    int department_pos;

    private SessionManager sessionManager ;
    private TextInputEditText name, dob, phone, email;
    AppCompatEditText address;
    private Spinner spin_Country, spin_State, spin_City, spin_category, spin_course,
            spin_organization, aed_department, aed_department_year, aed_section;
    private RadioButton school_radio, college_radio;
    String departmentId;
    String sectionId = "";
    String departmentYear = "0";
    ArrayList<String> countryList = new ArrayList<>();
    ArrayList<String> countryIdList = new ArrayList<>();
    ArrayList<String> stateList = new ArrayList<>();
    ArrayList<String> stateIdList = new ArrayList<>();
    ArrayList<String> cityList = new ArrayList<>();
    ArrayList<String> cityIdList = new ArrayList<>();
    ArrayList<String> categoryList = new ArrayList<>();
    ArrayList<String> categoryIdList = new ArrayList<>();
    ArrayList<String> courseList = new ArrayList<>();
    ArrayList<String> courseIdList = new ArrayList<>();
    ArrayList<String> organizationList = new ArrayList<>();
    ArrayList<String> organizationIdList = new ArrayList<>();

    ArrayList<String> costList = new ArrayList<>();

    final ArrayList<String> departmentList = new ArrayList<String>();
    final ArrayList<String> departmentIdList = new ArrayList<String>();

    int country_pos, state_pos, city_pos, category_pos, course_pos, organization_pos, standard_pos;
    Calendar myCalendar = Calendar.getInstance();

    String userRole = "" ;
    int mday = 24;
    int mmonth=10;
    int myear=1990;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_attendance);
         sessionManager = new SessionManager();
//         userRole = getIntent().getExtras().getString(Constants.USER_ROLE_ID);
         school_radio = findViewById(R.id.rad_school);
         college_radio = findViewById(R.id.rad_college);
         name  = findViewById(R.id.txt_name);
         dob = findViewById(R.id.txt_dob);
         phone = findViewById(R.id.txt_phone);
         email = findViewById(R.id.txt_email);
         address = findViewById(R.id.txt_address);
         spin_Country = findViewById(R.id.spin_country);
         spin_State = findViewById(R.id.spin_state);
         spin_City = findViewById(R.id.spin_city);
         spin_category = findViewById(R.id.spin_category);
         spin_course = findViewById(R.id.spin_course);
         spin_organization = findViewById(R.id.spin_organization);
         aed_department = findViewById(R.id.spin_department);
         aed_department_year = findViewById(R.id.spin_department_year);
         aed_section  = findViewById(R.id.spin_section);

         spin_Country.setVisibility(View.GONE);
         spin_City.setVisibility(View.GONE);
         spin_State.setVisibility(View.GONE);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddStudentAttendance.this, date, myear, mmonth,
                        mday).show();
            }
        });

        getCountry();
        getCatgories();

        if(school_radio.isChecked()) {
            getDepartments(Constants.ROLE_SCHOOL);
            ArrayList<String> sectionsList = new ArrayList<>();
            sectionsList.clear();
            sectionsList = Constants.getSections();
            aed_section.setAdapter(new ArrayAdapter<String >(this,android.R.layout.simple_spinner_dropdown_item,sectionsList));
            aed_department_year.setVisibility(View.GONE);
            userRole =Constants.ROLE_SCHOOL;
            organizationList.clear();
            organizationIdList.clear();
            getBranchOrganization(userRole);
            departmentYear ="0";
            final ArrayList<String> finalSectionsList = sectionsList;
            aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(!aed_section.getSelectedItem().toString().equalsIgnoreCase(finalSectionsList.get(0)))
                    {
                        sectionId = ""+(i-1);
                        Log.e("SECTION_ID",""+sectionId);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        school_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDepartments(Constants.ROLE_SCHOOL);
                ArrayList<String> sectionsList = new ArrayList<>();
                sectionsList.clear();
                sectionsList = Constants.getSections();
                aed_section.setAdapter(new ArrayAdapter<String >(AddStudentAttendance.this,
                        android.R.layout.simple_spinner_dropdown_item,sectionsList));
                aed_department_year.setVisibility(View.GONE);
                departmentYear ="0";
                userRole =Constants.ROLE_SCHOOL;
                organizationList.clear();
                organizationIdList.clear();
                getBranchOrganization(userRole);
                final ArrayList<String> finalSectionsList = sectionsList;
                aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(AddStudentAttendance.this, "section "+i, Toast.LENGTH_SHORT).show();
                        if(!aed_section.getSelectedItem().toString().equalsIgnoreCase(finalSectionsList.get(0)))
                        {
                            sectionId = ""+(i-1);
                            Log.e("SECTION_ID",""+sectionId);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });
        college_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aed_department_year.setVisibility(View.VISIBLE);
                departmentList.clear();
                departmentIdList.clear();
                getDepartments(Constants.ROLE_COLLEGE);
                ArrayList<String> sectionsList = new ArrayList<>();
                ArrayList<String> yearList = new ArrayList<>();
                sectionsList.clear();
                yearList.clear();
                sectionsList = Constants.getSections();
                yearList = Constants.getYears();
                aed_department_year.setAdapter(new ArrayAdapter<String >(AddStudentAttendance.this,
                        android.R.layout.simple_spinner_dropdown_item,yearList));
                userRole = Constants.ROLE_COLLEGE;
                organizationList.clear();
                organizationIdList.clear();
                getBranchOrganization(userRole);
                final ArrayList<String> finalyearList = yearList;
                aed_department_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(AddStudentAttendance.this, "year "+i, Toast.LENGTH_SHORT).show();
                        if(!aed_section.getSelectedItem().toString().equalsIgnoreCase(finalyearList.get(0))) {
                            departmentYear = "0";
                            Log.e("DEPARTMENT_YEAR", "" + departmentYear);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                aed_section.setAdapter(new ArrayAdapter<String >(AddStudentAttendance.this,
                        android.R.layout.simple_spinner_dropdown_item,sectionsList));
                final ArrayList<String> finalSectionsList = sectionsList;
                aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        Toast.makeText(AddStudentAttendance.this, "section "+i, Toast.LENGTH_SHORT).show();
                        if(!aed_section.getSelectedItem().toString().equalsIgnoreCase(finalSectionsList.get(0))) {
                            sectionId = "" + (i - 1);
                            Log.e("SECTION_ID", "" + sectionId);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });
        spin_Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spin_Country.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
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
        spin_State.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spin_State.getSelectedItem().toString().equalsIgnoreCase("Select State"))
                {
                    getCity(stateIdList.get(i));
                    state_pos = i;
                }
                // Log.e("SASASASA", "State ID :   " + stateIdList.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spin_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(!spin_City.getSelectedItem().toString().equalsIgnoreCase("Select City"))
                    {
                        getBranchOrganization(userRole);
                        city_pos = i;
                        Log.e("SSSSSSSSSS",""+cityIdList.get(i));
                    }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spin_organization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spin_organization.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
                {
                  organization_pos = i;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spin_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spin_category.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
                {
                    getCategoryCourse (categoryIdList.get(i)) ;
                    category_pos = i ;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spin_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    course_pos = i ;
                }catch (Exception ex)
                {
                    Log.e("EXCEPTION",""+ex.getMessage());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        aed_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                department_pos = i  ;
                Log.e ("SECTION_ID",""+departmentId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });

        cityList.add("Select City");
        stateList.add("Select State");
       // organizationList.add("Select Organization");
        courseList.add("Select Course");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        spin_City.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, stateList);
        spin_State.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, organizationList);
        spin_organization.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, courseList);
        spin_course.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>
                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, departmentList);
        aed_department.setAdapter(adapter5);
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

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));
    }

    private void createStudent(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/syallabus-student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT","jghj "+response);
                            LoginResponseData data = new Gson().fromJson(response, LoginResponseData.class);
                            Log.v("TTTTTTTTTTT","jghj "+response);
                            if(data.getStatus().toString().equalsIgnoreCase(Constants.RESPONSE_SUCCESS)){
                                new AwesomeNoticeDialog(AddStudentAttendance.this)
                                        .setTitle("Success!")
                                        .setMessage("Student added Successfully")
                                        .setColoredCircle(R.color.colorPrimaryDark)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setButtonText("Ok")
                                        .setButtonBackgroundColor(R.color.black)
                                        .setNoticeButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in =new Intent( AddStudentAttendance.this, AddStudentAttendance.class);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
                            }
                            else{
                                Toast.makeText(AddStudentAttendance.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT","t "+ e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //http://spark.candyrestaurant.com/api/syallabus-student
                Map<String, String> params = new HashMap<String, String>();
                Log.e("DeatilsTTTT","USERID "+sessionManager.getPreferences(AddStudentAttendance.this,Constants.USER_ID)+
                "name "+name.getText().toString().trim()+" dob"+dob.getText().toString().trim()+" phone "+phone.getText().toString().trim()+
                " email "+email.getText().toString().trim()+" address "+address.getText().toString().trim()+" ins "+userRole+" org"+organizationIdList.get(organization_pos)+
                        " dep "+departmentIdList.get(department_pos)+" cate "+categoryIdList.get(category_pos)+"yeesr : "+departmentYear+" section id :"+sectionId);
                params.put("user_id",sessionManager.getPreferences(AddStudentAttendance.this,Constants.USER_ID));
                params.put("name",name.getText().toString().trim());
                params.put("dob",dob.getText().toString().trim());
                params.put("phone",phone.getText().toString().trim());
                params.put("email",email.getText().toString().trim());
                params.put("country_id","0");
                params.put("state_id","0");
                params.put("city_id","0");
                params.put("address",address.getText().toString().trim());
                params.put("instituation_id",userRole);
                params.put("organization_id",organizationIdList.get(organization_pos));
                params.put("department_id",departmentIdList.get(department_pos));
                params.put("department_section_id",sectionId);
                params.put("department_year_id",departmentYear);
                params.put("category_id",categoryIdList.get(category_pos));



                //USERID 2name UVAN dob11/24/90 phone 9865888555 email manisuvan@gmfjm.com contry1
                // stateid 22 cirty 27 address teggggjbb ins 1 org9 dep null cate 1

               // USERID 3name ANDREW dob11/07/90 phone 9787845464 email Andrew@Gmail.com contry1 stateid 22 city 27 address Trichy ins 1 org0 dep 2 cate 1
                //"data":{"instituation_id":"1","organization_id":"9","department_id":"1","department_year_id":"0","department_section_id":"0","category_id":"1","branch_id":"2"}
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
    private boolean isValid()
    {
        dob.setError(null);
        boolean val = true;
        if(name.getText().toString().trim().isEmpty())
        {
            val =false;
            name.setError("Invalid");
        }
        else if(dob.getText().toString().trim().isEmpty())
        {
            val = false;
            dob.setError("Invalid");
        }
        else if(phone.getText().toString().trim().isEmpty())
        {
            val =false;
            phone.setError("Invalid");
        }
        else if(email.getText().toString().trim().isEmpty())
        {
            val =false;
            email.setError("Invalid");
        }
        else if(address.getText().toString().trim().isEmpty())
        {
            val =false;
            address.setError("Invalid");
        }
        else if(organizationIdList.get(organization_pos).isEmpty() || organizationIdList.get(organization_pos) == null)
        {
            val =false;
            Toast.makeText(this, "Invalid organization", Toast.LENGTH_SHORT).show();
        }
        else if(departmentIdList.get(department_pos).isEmpty() || departmentIdList.get(department_pos) == null)
        {
            val = false;
            Toast.makeText(this, "Invalid department", Toast.LENGTH_SHORT).show();
        }
        return val;
    }

    public void onClickAddStudentAttendance(View view) {
        if(isValid())
        {
            createStudent();
//            Log.e("TTT","USERID "+sessionManager.getPreferences(AddStudentAttendance.this,Constants.USER_ID)+
//                    "name "+name.getText().toString().trim()+" dob"+dob.getText().toString().trim()+" phone "+phone.getText().toString().trim()+
//                    " email "+email.getText().toString().trim()+" contry" +countryIdList.get(country_pos)+" stateid "+stateIdList.get(state_pos)+
//                    " city "+cityIdList.get(city_pos)+ " address "+address.getText().toString().trim()+" ins "+userRole+" org"+organizationIdList.get(organization_pos)+
//                    " dep "+departmentIdList.get(department_pos)+" cate "+categoryIdList.get(category_pos));
        }

    }

    private void getBranchOrganization(final String insId) {
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
                    try {
                        Log.e("RESPONSE_LIST11","jbjbjkkbk "+response);
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
                            (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, organizationList);
                    spin_organization.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
        }
    })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("user_id",sessionManager.getPreferences(AddStudentAttendance.this,Constants.USER_ID));
            params.put("instituation_id",insId);
            return params;
        }
    };
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
                                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, cityList);
                        spin_City.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
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
                            Log.v("TTTTTTTTTTTtrtydy",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, stateList);
                        spin_State.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, countryList);
                        spin_Country.setAdapter(countryAdapt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        spin_category.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void getCategoryCourse(final String categoryId) {
        courseIdList.clear();
        courseList.clear();
        costList.clear();
        courseList.add("Select Course");
        courseIdList.add("0");
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
                                Toast.makeText(AddStudentAttendance.this, "No Course Available", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                for (int i = 0; i < jary.length(); i++) {
                                    JSONObject jobj1 = jary.getJSONObject(i);
                                    costList.add(jobj1.getString("amount"));
                                    courseList.add(jobj1.getString("course") + " ( Rs." + jobj1.getString("amount") + ")");
                                    courseIdList.add(jobj1.getString("id"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, courseList);
                        spin_course.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
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

    private void getDepartments(final String institutionId) {
        departmentList.clear();
        departmentIdList.clear();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/institute-department";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("DepartmentsTTT",""+ response);
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("departments");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                departmentList.add(jobj1.getString("department"));
                                departmentIdList.add(jobj1.getString("id"));
                                Log.v("DepartmentsTTTT",jobj1.getString("department")+""+jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AddStudentAttendance.this, android.R.layout.simple_spinner_dropdown_item, departmentList);
                        aed_department.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentAttendance.this, "" + error, Toast.LENGTH_SHORT).show();
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

}
