package com.app.manikandanr.sampleclients.Activities;

import android.content.DialogInterface;
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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Adapters.StudentGroupCreateAdapter;
import com.app.manikandanr.sampleclients.AdmissionForm;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendancePage extends AppCompatActivity {

    private Spinner spinSyallabus, spinClass, spinCategory, spinDepartment, spinOrganization, spinYear, spinSection;
    private int syallabus_pos, class_pos, category_pos, department_pos, organization_pos, year_pos, section_pos;
    private String instituation_id;

    private RadioButton radioSchool, radioCollege;
    private RecyclerView listStudents;
    private RecyclerView.LayoutManager lytMgr;
    private Button btnAttendance;
    private ArrayList<String> categoryList = new ArrayList<>();
    private ArrayList<String> categoryIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_page_student);

        initComponent();
        showFilterAlert();

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
        instituation_id = Constants.ROLE_SCHOOL;

        getCatgories();
        getBranchOrganization();

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




        final AlertDialog.Builder builder = new AlertDialog.Builder(AttendancePage.this);
        builder.setView(layout11);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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

    private void initComponent() {
        spinClass = findViewById(R.id.spiner_class);
        spinSyallabus = findViewById(R.id.spiner_syallbus);
        listStudents = findViewById(R.id.student_list);
        lytMgr = new LinearLayoutManager(this);
        listStudents.setLayoutManager(lytMgr);
        btnAttendance = findViewById(R.id.btn_attendance);


        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                                (AttendancePage.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        spinCategory.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AttendancePage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
    private void getBranchOrganization() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/branch-organization";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RESPONSE_LIST",""+response);
                            JSONObject jobj = new JSONObject(response);
//                            JSONArray jary = jobj.getJSONArray("categories");
//                            for (int i = 0; i < jary.length(); i++) {
//                                JSONObject jobj1 = jary.getJSONObject(i);
//                                categoryList.add(jobj1.getString("category"));
//                                categoryIdList.add(jobj1.getString("id"));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AttendancePage.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        spinCategory.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AttendancePage.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id","2");
                params.put("instituation_id","2");
                return super.getParams();
            }
        };
        queue.add(stringRequest);
    }
}
