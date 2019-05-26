package com.app.manikandanr.sampleclients.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.Type;
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
import com.app.manikandanr.sampleclients.Adapters.MarketingAdapter;
import com.app.manikandanr.sampleclients.Adapters.StudentAlertAdapter;
import com.app.manikandanr.sampleclients.Adapters.StudentAttendanceAdapter;
import com.app.manikandanr.sampleclients.Adapters.StudentGroupCreateAdapter;
import com.app.manikandanr.sampleclients.AlertActivity;
import com.app.manikandanr.sampleclients.AttendanceActivity;
import com.app.manikandanr.sampleclients.Data.GetStudentFilter;
import com.app.manikandanr.sampleclients.Data.GetStudentFilterClass;
import com.app.manikandanr.sampleclients.Data.GroupCreateData;
import com.app.manikandanr.sampleclients.Data.MarketingAlertData;
import com.app.manikandanr.sampleclients.Data.Student;
import com.app.manikandanr.sampleclients.Data.StudentAlertData;
import com.app.manikandanr.sampleclients.Data.SyllabusClassList;
import com.app.manikandanr.sampleclients.Data.SyllabusList;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CreateGroupPage extends AppCompatActivity {
    private TextView txtCurrentGroup;
    private StringBuilder strNames = new StringBuilder();

    private RecyclerView listStudent;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnCreate;
    private SessionManager session ;
    private String userId, groupNumber;
    private StudentGroupCreateAdapter studentGroupCreateAdapter;
    public static StringBuilder studentGroupsToCreate = null;
    public static int studentGroupsToCreateCount = 0;
    private ArrayList<String> student_lists = new ArrayList<>();

    private Spinner spinSyallabus, spinClass, spinCategory, spinDepartment, spinOrganization, spinYear, spinSection;
    private int syallabus_pos, class_pos, category_pos, department_pos, organization_pos, year_pos, section_pos;

    private String instituation_id;
    private SessionManager sessionManager;
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

    private StringBuilder attendance_lists = new StringBuilder();
    private StringBuilder studentIds = new StringBuilder();
    private StringBuilder studentPresentList = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_page);
        initComponent();
    }

    private void initComponent() {
        txtCurrentGroup = findViewById(R.id.group_hint);
        listStudent = findViewById(R.id.student_create_group_list);
        btnCreate = findViewById(R.id.btn_create_group);
        session = new SessionManager();
        userId = session.getPreferences(CreateGroupPage.this, Constants.USER_ID);
        studentGroupsToCreate = new StringBuilder();
//        getStudentListPopup();
//        getStudentLists();

        showFilterAlert();
        //layout initialization
        layoutManager =  new LinearLayoutManager(this);
        listStudent.setLayoutManager(layoutManager);
        listStudent.setHasFixedSize(false);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout11 = inflater.inflate(R.layout.dialog_create_group, (ViewGroup) findViewById(R.id.layout_root));
                final TextView grupNo = layout11.findViewById(R.id.group_number);
                final EditText groupName = layout11.findViewById(R.id.group_name);
                final TextView studentlst = layout11.findViewById(R.id.student_names);
                final TextView studentCount = layout11.findViewById(R.id.student_counts);
                grupNo.setText(groupNumber);
                int studentCounts = 0;
                for (Student student : studentGroupCreateAdapter.getObj_arr()) {
                    if(student.isPresent())
                    {
                        studentCounts++;
                    }
                }
                studentCount.setText(studentCounts+" students added.");
final StringBuilder strstudents = new StringBuilder();
                for (Student student : studentGroupCreateAdapter.getObj_arr()) {
                    if (student.isPresent()) {

                        strstudents.append(student.getName()+",");
                    }

                }
                strNames = strstudents;


                studentlst.setText(strNames);
                final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupPage.this);
                builder.setView(layout11);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Student student : studentGroupCreateAdapter.getObj_arr()) {
                            if (student.isPresent()) {
                                studentIds.append(student.getId()+",");
                            }

                        }
                        Log.e("Counts ",StudentGroupCreateAdapter.checkedStudentIds.size()+"///"+StudentGroupCreateAdapter.checkedStudentNames.size());
                        setCreateGroupDetails(groupName.getText().toString().trim());
                        dialog.dismiss();
                        StudentGroupCreateAdapter.checkedStudentIds.clear();
                        StudentGroupCreateAdapter.checkedStudentNames.clear();
                        studentGroupsToCreate.setLength(0);
                        strNames.setLength(0);
                        studentIds.setLength(0);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        strNames.setLength(0);
                        studentIds.setLength(0);
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    private void showFilterAlert() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout11 = inflater.inflate(R.layout.dialog_create_group_filter_attendance, (ViewGroup) findViewById(R.id.layout_root));

        spinCategory = layout11.findViewById(R.id.spinner_category);
        spinOrganization = layout11.findViewById(R.id.spinner_organization);
        spinDepartment = layout11.findViewById(R.id.spinner_department);
        spinYear = layout11.findViewById(R.id.spinner_year);
        spinSection = layout11.findViewById(R.id.spinner_section);
        radioSchool = layout11.findViewById(R.id.radio_school);
        radioCollege = layout11.findViewById(R.id.radio_college);
        sessionManager = new SessionManager();
        instituation_id = Constants.ROLE_SCHOOL;

        getCatgories();
        getBranchOrganization();
        getDepartments(instituation_id);
        yearList = Constants.getYears();
        sectionList = Constants.getSections();

        spinSection.setAdapter(new ArrayAdapter<String >(CreateGroupPage.this,
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
                spinSection.setAdapter(new ArrayAdapter<String >(CreateGroupPage.this,
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
                spinYear.setAdapter(new ArrayAdapter<String >(CreateGroupPage.this,
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
                spinSection.setAdapter(new ArrayAdapter<String >(CreateGroupPage.this,
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

        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupPage.this);
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

    private void getStudentListPopup(){

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout11 = inflater.inflate(R.layout.dialog_create_group_filter, (ViewGroup) findViewById(R.id.layout_root));

        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupPage.this);
        builder.setView(layout11);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // getStudentLists();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

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
                                (CreateGroupPage.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        spinCategory.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateGroupPage.this, "" + error, Toast.LENGTH_SHORT).show();
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
                                (CreateGroupPage.this, android.R.layout.simple_spinner_dropdown_item, branchList);
                        spinOrganization.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateGroupPage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",sessionManager.getPreferences(CreateGroupPage.this,Constants.USER_ID));
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
                                (CreateGroupPage.this, android.R.layout.simple_spinner_dropdown_item, departmentList);
                        spinDepartment.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateGroupPage.this, "" + error, Toast.LENGTH_SHORT).show();
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
        String url = Constants.BASE_URL+"api/syallabus-student-lists";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE_DATA",""+response);
                        JSONObject jobj = null;
                        try {
                            jobj = new JSONObject(response);
                            String sts = jobj.getString("status");
                            String msg = jobj.getString("message");
                            groupNumber = jobj.getString("group_no");
                            if(sts.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)){
                                txtCurrentGroup.setText("Current group : "+groupNumber);
                                GetStudentFilter studentFilter = new Gson().fromJson(response,GetStudentFilter.class);
                                StudentGroupCreateAdapter adapter = new StudentGroupCreateAdapter(studentFilter.getStudents());
                                listStudent.setAdapter(adapter);
                            }
                            else{
                                Toast.makeText(CreateGroupPage.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



//                        try {
//                            Log.v("RESPONSE_STUDENTS",""+response);
//                            Gson gson = new Gson();
//                            JSONObject jobj = new JSONObject(response);
//                            String sts = jobj.getString("status");
//                            String msg = jobj.getString("message");
//                            String groupNo = jobj.getString("group_no");
//                            if(sts.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)){
//                                txtCurrentGroup.setText("Current group : "+groupNo);
//                                groupNumber = groupNo;
//                                JSONArray studentArray = jobj.getJSONArray("students");
//                                String jsonResponse = studentArray.toString();
//                                java.lang.reflect.Type listType = new TypeToken<ArrayList<Student>>(){}.getType();
//                                ArrayList<Student> listStudents = gson.fromJson(jsonResponse,listType);
//                                studentGroupCreateAdapter = new StudentGroupCreateAdapter(listStudents);
//                                listStudent.setAdapter(studentGroupCreateAdapter);
//                            }
//                            else{
//                                Toast.makeText(CreateGroupPage.this, ""+msg, Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            Log.v("TTTTTTTTTTT","dfghdfh "+ e.getMessage());
//                            e.printStackTrace();
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateGroupPage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",sessionManager.getPreferences(CreateGroupPage.this,Constants.USER_ID));
                params.put("category_id",categoryIdList.get(category_pos));
                params.put("institude_id",instituation_id);
                params.put("organization_id",branchIdList.get(organization_pos));
                params.put("department_id",departmentIdList.get(department_pos));
                params.put("department_year_id", ""+year_pos);
                params.put("department_section_id", ""+section_pos);


                //USERID 3name JANU dob11/24/90 phone 9484644648 email manshsusjsnsn@hsjsns. contry1 stateid 22 city 27 address trichfhdndbdb ins 1 org9 dep 1 cate 1yeesr : 0 section id :2


                Log.e("DeatilsTTTT","catId "+categoryIdList.get(category_pos)+" insId "+instituation_id+" orgId "+branchIdList.get(organization_pos)+
                        " depaId "+departmentIdList.get(department_pos)+" depYear "+year_pos+" sectionId "+section_pos);
//                params.put("user_id",sessionManager.getPreferences(AttendancePage.this,Constants.USER_ID));
//                params.put("category_id","1");
//                params.put("institude_id","2");
//                params.put("organization_id","2");
//                params.put("department_id","1");
//                params.put("department_year_id", "1");
//                params.put("department_section_id", "1");
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
                        GetStudentFilterClass studentFilter = new Gson().fromJson(response,GetStudentFilterClass.class);
                        for(SyllabusClassList str : studentFilter.getSyllabusClassLists())
                        {
                            classList.add(str.getClass_());
                            classIdList.add(""+str.getId());
                        }
                        spinClass.setAdapter(new ArrayAdapter<String>(CreateGroupPage.this,android.R.layout.simple_spinner_dropdown_item,
                                classList ));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateGroupPage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",sessionManager.getPreferences(CreateGroupPage.this,Constants.USER_ID));
                params.put("category_id",categoryIdList.get(category_pos));
                params.put("institude_id",instituation_id);
                params.put("organization_id",branchIdList.get(organization_pos));
                params.put("department_id",departmentIdList.get(department_pos));
                params.put("department_year_id", ""+year_pos);
                params.put("department_section_id", ""+section_pos);
                params.put("syllabus_id", ""+syallabus_pos);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    /*private void getStudentLists() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Please wait.......");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/syallabus-student-lists";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            Log.v("RESPONSE_STUDENTS",""+response);
                            Gson gson = new Gson();
                            JSONObject jobj = new JSONObject(response);
                            String sts = jobj.getString("status");
                            String msg = jobj.getString("message");
                            String groupNo = jobj.getString("group_no");
                            if(sts.equalsIgnoreCase(Constants.RESPONSE_SUCCESS)){
                                txtCurrentGroup.setText("Current group : "+groupNo);
                                groupNumber = groupNo;
                                JSONArray studentArray = jobj.getJSONArray("students");
                                String jsonResponse = studentArray.toString();
                                java.lang.reflect.Type listType = new TypeToken<ArrayList<Student>>(){}.getType();
                                ArrayList<Student> listStudents = gson.fromJson(jsonResponse,listType);
                                studentGroupCreateAdapter = new StudentGroupCreateAdapter(listStudents);
                                listStudent.setAdapter(studentGroupCreateAdapter);
                            }
                            else{
                                Toast.makeText(CreateGroupPage.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT","dfghdfh "+ e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateGroupPage.this, "Unable to connect server" + error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",userId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }*/

    private void setCreateGroupDetails(final String grpName) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Please wait.......");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/syallabus-group-student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            Log.v("RESPONSE_STUDENTS",""+response);
                            Gson gson = new Gson();
                            JSONObject jobj = new JSONObject(response);
                            String sts = jobj.getString("status");
                            String msg = jobj.getString("message");
                            if(sts.equalsIgnoreCase(Constants.RESPONSE_SUCCESS))
                            {
                                new AwesomeNoticeDialog(CreateGroupPage.this)
                                        .setTitle("Success!")
                                        .setMessage("Student Group Created Successfully")
                                        .setColoredCircle(R.color.colorPrimaryDark)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setButtonText("Ok")
                                        .setButtonBackgroundColor(R.color.black)
                                        .setNoticeButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                finish();
                                            }
                                        })
                                        .show();
                                Log.v("TTTTTTTTTTT","jghj   "+response);
                            }
                            else
                            {
                                Toast.makeText(CreateGroupPage.this, ""+msg, Toast.LENGTH_SHORT).show();
                                Log.v("TTTTTTTTTTT","jghj   "+response);
                            }

                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT","dfghdfh "+ e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateGroupPage.this, "Unable to connect server" + error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",userId);
                params.put("group_name",grpName);
                params.put("group_no",groupNumber);
                params.put("students",studentIds.toString());
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
