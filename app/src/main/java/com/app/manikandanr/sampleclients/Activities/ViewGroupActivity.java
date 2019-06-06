package com.app.manikandanr.sampleclients.Activities;

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
import android.widget.Button;
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
import com.app.manikandanr.sampleclients.Adapters.GroupListAdapter;
import com.app.manikandanr.sampleclients.Adapters.StudentAttendanceAdapter;
import com.app.manikandanr.sampleclients.Adapters.StudentGroupViewAdapter;
import com.app.manikandanr.sampleclients.Data.AttendancePresentData;
import com.app.manikandanr.sampleclients.Data.GetStudentFilter;
import com.app.manikandanr.sampleclients.Data.GetStudentFilterClass;
import com.app.manikandanr.sampleclients.Data.LoginResponseData;
import com.app.manikandanr.sampleclients.Data.SyllabusClassList;
import com.app.manikandanr.sampleclients.Data.SyllabusList;
import com.app.manikandanr.sampleclients.DataModels.GroupsData;
import com.app.manikandanr.sampleclients.MenuActivity;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewGroupActivity extends AppCompatActivity {
    private Spinner spinSyallabus, spinClass, spinCategory, spinDepartment, spinOrganization, spinYear, spinSection;
    private int syallabus_pos, class_pos, category_pos, department_pos, organization_pos, year_pos, section_pos;

    private String instituation_id;
    private SessionManager sessionManager = new SessionManager();
    private RadioButton radioSchool, radioCollege;
    private RecyclerView listStudents;
    private RecyclerView.LayoutManager lytMgr;
    private Button btnAttendance;

    private ArrayList<String> categoryList = new ArrayList<>();
    private ArrayList<String> categoryIdList = new ArrayList<>();
    private ArrayList<String> branchList = new ArrayList<>();
    private ArrayList<String> branchIdList = new ArrayList<>();
    private ArrayList<String> departmentList = new ArrayList<>();
    private ArrayList<String> departmentIdList = new ArrayList<>();
    private ArrayList<String> syallabusList = new ArrayList<>();
    private ArrayList<String> syallabusIdList = new ArrayList<>();
    private ArrayList<String> classList = new ArrayList<>();
    private ArrayList<String> classIdList = new ArrayList<>();
    private ArrayList<String> yearList = new ArrayList<>();
    private ArrayList<String> sectionList = new ArrayList<>();

    private StringBuilder student_lists = new StringBuilder();
    private StringBuilder attendance_lists = new StringBuilder();
    private StringBuilder studentIds = new StringBuilder();
    private StringBuilder studentPresentList = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        initComponent();
        showFilterAlert();
        //getAllStudentList();
    }


    private void showFilterAlert() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout11 = inflater.inflate(R.layout.dialog_create_group_filter_attendance,
                (ViewGroup) findViewById(R.id.layout_root));
        spinCategory = layout11.findViewById(R.id.spinner_category);
        spinOrganization = layout11.findViewById(R.id.spinner_organization);
        spinDepartment = layout11.findViewById(R.id.spinner_department);
        spinYear = layout11.findViewById(R.id.spinner_year);
        spinSection = layout11.findViewById(R.id.spinner_section);
        radioSchool = layout11.findViewById(R.id.radio_school);
        radioCollege = layout11.findViewById(R.id.radio_college);
        instituation_id = Constants.ROLE_SCHOOL;

        getCatgories();
        getBranchOrganization();
        getDepartments(instituation_id);
        yearList = Constants.getYears();
        sectionList = Constants.getSections();

        spinSection.setAdapter(new ArrayAdapter<String >(ViewGroupActivity.this,
                android.R.layout.simple_spinner_dropdown_item,sectionList));
        spinYear.setVisibility(View.GONE);

        radioSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yearList.clear();
                sectionList.clear();
                yearList = Constants.getYears();
                sectionList = Constants.getSections();
                instituation_id = Constants.ROLE_SCHOOL;
                getDepartments(instituation_id);
                getBranchOrganization();
                spinSection.setAdapter(new ArrayAdapter<String >(ViewGroupActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,sectionList));
                spinYear.setVisibility(View.GONE);
                year_pos = 0;
                spinSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        section_pos = i+1;
                        Log.e("SECTION_ID",""+section_pos);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });
        radioCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yearList.clear();
                sectionList.clear();
                yearList = Constants.getYears();
                sectionList = Constants.getSections();
                instituation_id = Constants.ROLE_COLLEGE;
                getDepartments(instituation_id);
                spinYear.setVisibility(View.VISIBLE);
                getBranchOrganization();
                spinYear.setAdapter(new ArrayAdapter<String >(ViewGroupActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,yearList));
                spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        year_pos  = (i+1);
                        Log.e("DEPARTMENT_YEAR",""+year_pos);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                spinSection.setAdapter(new ArrayAdapter<String >(ViewGroupActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,sectionList));

                spinSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        section_pos = (i+1);
                        Log.e("SECTION_ID",""+section_pos);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinCategory.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
                {
                    category_pos = i;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinDepartment.getSelectedItem().toString().equalsIgnoreCase("Select Department"))
                {
                    Log.e("IDS_POS","position "+departmentIdList.get(i));
                    department_pos = i;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinOrganization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinOrganization.getSelectedItem().toString().equalsIgnoreCase("Select Organization"))
                {
                    Log.e("IDS_POS","position "+departmentIdList.get(i));
                    organization_pos = i;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(ViewGroupActivity.this);
        builder.setView(layout11);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getSudentsList();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    private void initComponent() {
        spinClass = findViewById(R.id.spiner_class);
        spinSyallabus = findViewById(R.id.spiner_syallbus);
        listStudents = findViewById(R.id.student_list);
        lytMgr = new LinearLayoutManager(this);
        listStudents.setLayoutManager(lytMgr);
        btnAttendance = findViewById(R.id.btn_attendance);
        spinSyallabus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinSyallabus.getSelectedItem().toString().equalsIgnoreCase("Select Syallabus"))
                {
                    Log.e("IDS_POS","position "+syallabusIdList.get(i));
                    syallabus_pos = i;
                    getSudentsClass();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinClass.getSelectedItem().toString().equalsIgnoreCase("Select Class"))
                {
                    Log.e("IDS_POS","position "+classIdList.get(i));
                    class_pos = i;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentIds.setLength(0);
                studentPresentList.setLength(0);
                for(AttendancePresentData data : StudentAttendanceAdapter.attedanceData){
                    studentIds.append(""+data.getStudentId()+",");
                    studentPresentList.append(""+data.getIsPresent()+",");
                }
                Log.e("DATA1233","StudentId "+studentIds+"//name "+studentPresentList);
                setAttendanceStudent();

            }
        });
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
                                (ViewGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        spinCategory.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewGroupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void getBranchOrganization() {
        branchList.clear();
        branchIdList.clear();
        branchList.add("Select Organization");
        branchIdList.add("0");
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
                                branchList.add(jobj1.getString("name"));
                                branchIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (ViewGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, branchList);
                        spinOrganization.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewGroupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",sessionManager.getPreferences(ViewGroupActivity.this,Constants.USER_ID));
                params.put("instituation_id",instituation_id);
                return params;
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
                            Log.v("TTTTTTTTTTT_DEP",""+ response);
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
                                (ViewGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, departmentList);
                        spinDepartment.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewGroupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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

    private void getSudentsList() {
        syallabusIdList.clear();
        syallabusList.clear();
        syallabusIdList.add("0");
        syallabusList.add("Select Syallabus");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/groups/filter";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE_DATA123",""+response);
                        GetStudentFilter studentFilter = new Gson().fromJson(response,GetStudentFilter.class);
                        if(studentFilter.getStudents().size() == 0) {
                            Toast.makeText(ViewGroupActivity.this, "No Students", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            StudentGroupViewAdapter adapter = new StudentGroupViewAdapter(studentFilter.getStudents(), Constants.PAGE_FROM_GROUP);
                            listStudents.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewGroupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",sessionManager.getPreferences(ViewGroupActivity.this,Constants.USER_ID));
                params.put("category_id",categoryIdList.get(category_pos));
                params.put("institude_id",instituation_id);
                params.put("organization_id",branchIdList.get(organization_pos));
                params.put("department_id",departmentIdList.get(department_pos));
                params.put("department_year_id", ""+year_pos);
                params.put("department_section_id", ""+section_pos);
                Log.e("DeatilsTTTT","catId "+categoryIdList.get(category_pos)+" insId "+instituation_id+" orgId "+branchIdList.get(organization_pos)+
                        " depaId "+departmentIdList.get(department_pos)+" depYear "+year_pos+" sectionId "+section_pos);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getAllStudentList() {
        syallabusIdList.clear();
        syallabusList.clear();
        syallabusIdList.add("0");
        syallabusList.add("Select Syallabus");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/attendance-students";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE_DATA",""+response);
                        GroupsData studentFilter = new Gson().fromJson(response,GroupsData.class);
                        if(studentFilter.getGroups().getGroups().size() == 0) {
                            Toast.makeText(ViewGroupActivity.this, "No Students", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            GroupListAdapter adapter = new GroupListAdapter(studentFilter.getGroups().getGroups(), Constants.PAGE_FROM_GROUP);
                            listStudents.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE_DATA_ERROR",""+error.getMessage());
                Toast.makeText(ViewGroupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",sessionManager.getPreferences(ViewGroupActivity.this,Constants.USER_ID));
                params.put("category_id","0");
                params.put("institude_id","0");
                params.put("organization_id","0");
                params.put("department_id","0");
                params.put("department_year_id", "0");
                params.put("department_section_id", "0");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getSudentsClass() {
        classList.clear();
        classIdList.clear();
        classIdList.add("0");
        classList.add("Select Class");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/attendance-syllabus-classes";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE_UIU",""+response+ syallabusIdList.get(syallabus_pos));
                        GetStudentFilterClass studentFilter = new Gson().fromJson(response,GetStudentFilterClass.class);


                        for(SyllabusClassList str : studentFilter.getSyllabusClassLists())
                        {
                            classList.add(str.getClass_());
                            classIdList.add(""+str.getId());
                        }
                        spinClass.setAdapter(new ArrayAdapter<String>(ViewGroupActivity.this,android.R.layout.simple_spinner_dropdown_item,
                                classList ));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewGroupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",sessionManager.getPreferences(ViewGroupActivity.this,Constants.USER_ID));
                params.put("category_id",categoryIdList.get(category_pos));
                params.put("institude_id",instituation_id);
                params.put("organization_id",branchIdList.get(organization_pos));
                params.put("department_id",departmentIdList.get(department_pos));
                params.put("department_year_id", ""+year_pos);
                params.put("department_section_id", ""+section_pos);
                params.put("syllabus_id", ""+syallabusIdList.get(syallabus_pos));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void setAttendanceStudent() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/attendance";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT","jghj "+response+studentIds);
                            LoginResponseData data = new Gson().fromJson(response, LoginResponseData.class);
                            Log.v("TTTTTTTTTTT","jghj "+response);
                            if(data.getStatus().toString().equalsIgnoreCase(Constants.RESPONSE_SUCCESS))
                            {
                                studentIds.setLength(0);
                                student_lists.setLength(0);
                                studentIds.delete(0, studentIds.length());
                                student_lists.delete(0,student_lists.length());
                                studentIds = new StringBuilder();
                                student_lists = new StringBuilder();
                                new AwesomeNoticeDialog(ViewGroupActivity.this)
                                        .setTitle("Success!")
                                        .setMessage("Attendance Successfully")
                                        .setColoredCircle(R.color.colorPrimaryDark)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setButtonText("Ok")
                                        .setButtonBackgroundColor(R.color.black)
                                        .setNoticeButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in =new Intent( ViewGroupActivity.this, MenuActivity.class);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();
                            }
                            else
                            {
                                Toast.makeText(ViewGroupActivity.this, "asda"+data.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT","t "+ e.getMessage());
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewGroupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",sessionManager.getPreferences(ViewGroupActivity.this,Constants.USER_ID));
                params.put("category_id",categoryIdList.get(category_pos));
                params.put("instituation_id",instituation_id);
                params.put("organization_id",branchIdList.get(organization_pos));
                params.put("department_id",departmentIdList.get(department_pos));
                params.put("department_year_id", ""+year_pos);
                params.put("department_section_id", ""+section_pos);
                params.put("syllabi_id", ""+syallabusIdList.get(syallabus_pos));
                params.put("syllabus_class_id", ""+classIdList.get(class_pos));
                params.put("students", ""+studentIds);
                params.put("attendance_status", ""+studentPresentList);
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
