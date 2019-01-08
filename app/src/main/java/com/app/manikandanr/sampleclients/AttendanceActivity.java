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
import com.app.manikandanr.sampleclients.Adapters.StudentAttendanceAdapter;
import com.app.manikandanr.sampleclients.DataModels.AttendanceData;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {
    ProgressDialog pd;
    public static StringBuilder studentBuilder = new StringBuilder();
    public static ArrayList<String> studentPresentList = new ArrayList<>();
    public static RecyclerView listViewStudent;
    private Spinner countrySpinner, stateSpinner, citySpinner,typeYear, categorySpinner, institutionTypeSpinner,
    courseSpinner, yearSpinner, organizationSpinner, categoryCourseSpinner;

    int countryPosition, statePosition, cityPosition, categoryPosition, institutionPosition,
            coursePosition, yearPosition ,organizationPosition;

    ArrayList<AttendanceData> studentList = new ArrayList<>();
    ArrayList<String> studentIdList = new ArrayList<>();

    ArrayList<String> countrySpinnerList = new ArrayList<>();
    ArrayList<String> countryIdList = new ArrayList<>();

    ArrayList<String> stateSpinnerList = new ArrayList<>();
    ArrayList<String> stateIdList = new ArrayList<>();

    ArrayList<String> citySpinnerList = new ArrayList<>();
    ArrayList<String> cityIdList = new ArrayList<>();

    ArrayList<String> typeYearSpinnerList = new ArrayList<>();

    ArrayList<String> categorySpinnerList = new ArrayList<>();
    ArrayList<String> categoryIdList = new ArrayList<>();

    ArrayList<String> categoryCourseSpinnerList = new ArrayList<>();
    ArrayList<String> categoryCourseIdList = new ArrayList<>();

    ArrayList<String> organizationSpinnerList = new ArrayList<>();
    ArrayList<String> organizationIdList = new ArrayList<>();


    String yearOrStandard = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        init();

    }

    public void onDialogList() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout11 = inflater.inflate(R.layout.dialog_sort_lyt, (ViewGroup) findViewById(R.id.layout_root));
        countrySpinner = layout11.findViewById(R.id.at_spin_country);
        stateSpinner = layout11.findViewById(R.id.at_spin_state);
        citySpinner = layout11.findViewById(R.id.at_spin_city);
        typeYear = layout11.findViewById(R.id.at_spin_type_year);
        categorySpinner = layout11.findViewById(R.id.at_spin_category);
        //institutionTypeSpinner = layout11.findViewById(R.id.at_spin_institution_type);
        courseSpinner = layout11.findViewById(R.id.at_spin_course);

        courseSpinner = layout11.findViewById(R.id.at_spin_course);
        organizationSpinner = layout11.findViewById(R.id.at_spin_organization);


        if(getIntent().getExtras().getString(Constants.USER_ROLE).equalsIgnoreCase(Constants.USER_TYPE_SCHOOL))
        {
            String[] schoolList = new String[]{"5th to 9th","10th to 12th"};
            typeYear.setAdapter(new ArrayAdapter<String>(AttendanceActivity.this,android.R.layout.simple_spinner_dropdown_item,schoolList));
        }
        else
        {
            String[] clgList = new String[]{"1st Year","2nd Year","3rd Year","4th Year"};
            typeYear.setAdapter(new ArrayAdapter<String>(AttendanceActivity.this,android.R.layout.simple_spinner_dropdown_item,clgList));
        }
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

    private boolean isValid() {
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
                   String role = "";
                   if(getIntent().getExtras().getString(Constants.USER_ROLE).equalsIgnoreCase(Constants.USER_TYPE_SCHOOL))
                   {
                       role ="1";
                   }
                   else if(getIntent().getExtras().getString(Constants.USER_ROLE).equalsIgnoreCase(Constants.USER_TYPE_COLLEGE))
                   {
                       role = "2";
                   }
                   else
                   {
                       role = "3";
                   }
                   getOrganization(cityIdList.get(i),role);
                   cityPosition = i;
                   Log.e("SSSSSSSSSS",""+cityIdList.get(i));
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       typeYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
           {
               if(typeYear.getSelectedItem().toString().equalsIgnoreCase("5th to 9th"))
               {
                   yearOrStandard = Constants.SCHOOL_5TO9;
               }
               else if(typeYear.getSelectedItem().toString().equalsIgnoreCase("10th to 12th"))
               {
                   yearOrStandard = Constants.SCHOOL_10TO11;
               }
               else if(typeYear.getSelectedItem().toString().equalsIgnoreCase("1st Year"))
               {
                   yearOrStandard = Constants.COLLEGE_1YEAR;
               }
               else if(typeYear.getSelectedItem().toString().equalsIgnoreCase("2nd Year"))
               {
                   yearOrStandard = Constants.COLLEGE_2YEAR;
               }
               else if(typeYear.getSelectedItem().toString().equalsIgnoreCase("3rd Year"))
               {
                   yearOrStandard = Constants.COLLEGE_3YEAR;
               }
               else if(typeYear.getSelectedItem().toString().equalsIgnoreCase("4th Year"))
               {
                   yearOrStandard = Constants.COLLEGE_4YEAR;
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

       courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(!categorySpinner.getSelectedItem().toString().equalsIgnoreCase("Select Course"))
               {

                   coursePosition = i;
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
        categoryCourseSpinnerList.add("Select Course");
        categoryCourseIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/category-course";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("categories");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                categoryCourseSpinnerList.add(jobj1.getString("course"));
                                categoryCourseIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryCourseSpinnerList);
                        courseSpinner.setAdapter(adapter);

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
                                stateSpinnerList.add(jobj1.getString("state"));
                                stateIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, stateSpinnerList);
                        stateSpinner.setAdapter(adapter);

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
        String url = Constants.BASE_URL+"api/categroy";
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
        Log.e("SASASASA",categoryCourseIdList.get(coursePosition)+"///"+yearOrStandard+"///"+organizationIdList.get(organizationPosition));
        stateIdList.clear();
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/course-students";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pd.dismiss();
                            Log.e("RESPONSE_Students",""+response);
                            JSONObject jobj = new JSONObject(response);

                            String status =  jobj.getString("status");
                            String message = jobj.getString("message");

                            if(status.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)) {
                                JSONArray studentArray = jobj.getJSONArray("students");
                                if (studentArray.length() == 0) {
                                    new AwesomeSuccessDialog(AttendanceActivity.this)
                                            .setTitle("Information")
                                            .setMessage("No students found")
                                            .setColoredCircle(R.color.colorPrimary)
                                            .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                            .setCancelable(true)
                                            .setPositiveButtonText("Ok")
                                            .setPositiveButtonbackgroundColor(R.color.colorPrimary)
                                            .setPositiveButtonTextColor(R.color.white)
                                            .setPositiveButtonClick(new Closure() {
                                                @Override
                                                public void exec() {
                                                    Intent in = new Intent(AttendanceActivity.this, MenuActivity.class);
                                                    startActivity(in);
                                                    finish();
                                                }
                                            })
                                            .show();
                                } else {
                                    for (int i = 0; i < studentArray.length(); i++) {
                                        JSONObject attendanceInfo = studentArray.getJSONObject(i);
                                        String id = attendanceInfo.getString("id");
                                        String studentId = attendanceInfo.getString("student_id");
                                        String attendance_status = attendanceInfo.getString("attendance_status");
                                        JSONObject studentInfo = attendanceInfo.getJSONObject("student");
                                        String studentRegNumber = studentInfo.getString("serial_no");
                                        String studentName = studentInfo.getString("name");
                                        //String studentPymentStatus = studentInfo.getString("payment_info");
                                        String isPresent = "0";
                                        studentList.add(new AttendanceData(studentName, studentRegNumber, "", "", isPresent, id, studentId));
                                        StudentAttendanceAdapter dadAdapter = new StudentAttendanceAdapter(studentList);
                                        listViewStudent.setAdapter(dadAdapter);
                                    }
                                }
                            }
                            else if (status.equalsIgnoreCase(Constants.RESPONSE_FAILED)) {
                                    Toast.makeText(AttendanceActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                                }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("RESPONSE_Students_ERROR",""+e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("RESPONSE_Students_ERROR",""+error.getMessage());
                Toast.makeText(AttendanceActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("course_id",categoryCourseIdList.get(coursePosition));
               // params.put("department_id",yearOrStandard);
                params.put("organization_id",""+organizationIdList.get(organizationPosition));
                params.put("department_id",yearOrStandard);

               // Log.e("SASASASA",categoryCourseIdList.get(coursePosition)+"///"+yearOrStandard);
                return params;
            }
        };
        queue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void setPresentStudent()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/attendance";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT",""+response);
                            JSONObject jobj = new JSONObject(response);
                            String sts = jobj.getString("status");
                            String msg = jobj.getString("message");
                            if(sts.equalsIgnoreCase(Constants.RESPONSE_SUCCESS))
                            {
                                new AwesomeSuccessDialog(AttendanceActivity.this)
                                        .setTitle("Admission Status")
                                        .setMessage("Admission Successfull.")
                                        .setColoredCircle(R.color.colorPrimary)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setPositiveButtonText("Ok")
                                        .setPositiveButtonbackgroundColor(R.color.colorPrimary)
                                        .setPositiveButtonTextColor(R.color.white)
                                        .setPositiveButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in = new Intent(AttendanceActivity.this, ViewBill.class);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
                            }
                        } catch (Exception e) {
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
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("city_id",cityIdList.get(cityPosition));
                params.put("organization_id",organizationIdList.get(organizationPosition));
                params.put("category_id",categoryIdList.get(categoryPosition));
                params.put("department_id",yearOrStandard);
                params.put("course_id",categoryCourseIdList.get(coursePosition));
                params.put("students",""+studentBuilder);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    public void onCLickPresentStudent(View view) {
        if(studentBuilder.equals(""))
        {
            Toast.makeText(this, "No Students Selected...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            setPresentStudent();
        }
    }

}
