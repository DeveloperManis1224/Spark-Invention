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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.app.manikandanr.sampleclients.Adapters.StudentGroupCreateAdapter;
import com.app.manikandanr.sampleclients.AlertActivity;
import com.app.manikandanr.sampleclients.AttendanceActivity;
import com.app.manikandanr.sampleclients.Data.GroupCreateData;
import com.app.manikandanr.sampleclients.Data.MarketingAlertData;
import com.app.manikandanr.sampleclients.Data.Student;
import com.app.manikandanr.sampleclients.Data.StudentAlertData;
import com.app.manikandanr.sampleclients.R;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateGroupPage extends AppCompatActivity {
    private TextView txtCurrentGroup;
    private RecyclerView listStudent;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnCreate;
    private SessionManager session ;
    private String userId, groupNumber;
    private StudentGroupCreateAdapter studentGroupCreateAdapter;
    public static StringBuilder studentGroupsToCreate = null;
    public static int studentGroupsToCreateCount = 0;
    private ArrayList<String> student_lists = new ArrayList<>();
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
        getStudentLists();
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

                studentCount.setText(StudentGroupCreateAdapter.checkedStudentNames.size()+" students added.");
                StringBuilder strNames = new StringBuilder();
                for(int i = 0; i<StudentGroupCreateAdapter.checkedStudentNames.size();i++)
                {
                     strNames.append(StudentGroupCreateAdapter.checkedStudentNames.get(i)+",");
                }
                studentlst.setText(strNames);
                final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupPage.this);
                builder.setView(layout11);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setCreateGroupDetails(groupName.getText().toString().trim());
                        for(int i = 0; i<StudentGroupCreateAdapter.checkedStudentNames.size();i++)
                        {
                            Log.e("Counts ",StudentGroupCreateAdapter.checkedStudentIds.get(i)+"///"+StudentGroupCreateAdapter.checkedStudentNames.get(i));

                            studentGroupsToCreate.append(StudentGroupCreateAdapter.checkedStudentIds.get(i)+",");
                        }
                        Log.e("Counts ",StudentGroupCreateAdapter.checkedStudentIds.size()+"///"+StudentGroupCreateAdapter.checkedStudentNames.size());
                        dialog.dismiss();
                        StudentGroupCreateAdapter.checkedStudentIds.clear();
                        StudentGroupCreateAdapter.checkedStudentNames.clear();
                        studentGroupsToCreate.setLength(0);
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
        });
    }


    private void getStudentListPopup()
    {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout11 = inflater.inflate(R.layout.dialog_create_group_filter, (ViewGroup) findViewById(R.id.layout_root));

        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupPage.this);
        builder.setView(layout11);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getStudentLists();
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

    private void getStudentLists() {
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
                            if(sts.equalsIgnoreCase(Constants.RESPONSE_SUCCESS))
                            {
                                txtCurrentGroup.setText("Current group : "+groupNo);
                                groupNumber = groupNo;
                                JSONArray studentArray = jobj.getJSONArray("students");
                                String jsonResponse = studentArray.toString();
                                java.lang.reflect.Type listType = new TypeToken<ArrayList<Student>>(){}.getType();
                                ArrayList<Student> listStudents = gson.fromJson(jsonResponse,listType);
                                studentGroupCreateAdapter = new StudentGroupCreateAdapter(listStudents);
                                listStudent.setAdapter(studentGroupCreateAdapter);
                            }
                            else
                            {
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
    }

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
                params.put("students",studentGroupsToCreate.toString());
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
