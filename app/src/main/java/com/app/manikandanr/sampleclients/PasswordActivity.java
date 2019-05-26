package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.app.manikandanr.sampleclients.Data.LoginResponseData;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordTxt, usernameText;
    private Spinner branchRole;
    private int branch_pos;
    private ArrayList<String> branchList = new ArrayList<>();
    private ArrayList<String> branchIdList = new ArrayList<>();
    private Button loginBtn;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordTxt = findViewById(R.id.edt_password);
        loginBtn = findViewById(R.id.btn_login);
        branchRole = findViewById(R.id.spin_branch);
        usernameText = findViewById(R.id.edt_username);
        session = new SessionManager();
        getBranch();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {

                    userAuthendication();
                }
//                if(!passwordTxt.getText().toString().trim().isEmpty())
//        {
//            if(passwordTxt.getText().toString().equalsIgnoreCase("1234"))
//            {
//                Toast.makeText(PasswordActivity.this, "Login Successfull.", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(PasswordActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else
//        {
//            passwordTxt.setError("Invalid Password");
//        }
    }
});

        branchRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!branchRole.getSelectedItem().toString().equalsIgnoreCase("Select Branch"))
                {
                    branch_pos = i;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        branchRole.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,new String[]{"Select Branch"}));

    }


    private void getBranch()
    {
        branchList.clear();
        branchIdList.clear();
        branchIdList.add("0");
        branchList.add("Select Branch");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/branch-lists";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT",""+ response);
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("branches");

                            for (int i = 0; i <= jary.length(); i++) {
                                JSONObject jobj11 = jary.getJSONObject(i);
                                String cuntry = jobj11.getString("name");
                                String id = jobj11.getString("id");
                                Log.v("TTTTTTTTTTT",""+ cuntry +" "+id);
                                branchList.add(cuntry);
                                branchIdList.add(id);
                            }

                        } catch (JSONException e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> countryAdapt = new ArrayAdapter<String>
                                (PasswordActivity.this, android.R.layout.simple_spinner_dropdown_item, branchList);
                        branchRole.setAdapter(countryAdapt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PasswordActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private boolean isValid()
    {
        boolean val = true;
        if(usernameText.getText().toString().isEmpty())
        {
            val= false;
            usernameText.setError("Invalid username");
        }
        if(passwordTxt.getText().toString().isEmpty())
        {
            val = false;
            passwordTxt.setError("Invalid Password");
        }
        if(branchRole.getSelectedItem().toString().equalsIgnoreCase("Select Branch"))
        {
            val = false;
            Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
        }
        return val;
    }

    private void userAuthendication()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/login-staff";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.v("TTTTTTTTTTT",""+response);
                            LoginResponseData data = new Gson().fromJson(response, LoginResponseData.class);
                            if(data.getStatus().toString().equalsIgnoreCase(Constants.RESPONSE_SUCCESS))
                            {
                                Log.v("TTTTTTTTTTT",""+response);
                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                final String strDate = sdf.format(c.getTime());
                                Log.v("TTTTTTTTTTT","jghj"+data.getUser().getId());
                                session.setPreferences(PasswordActivity.this,Constants.USER_ID,""+data.getUser().getId());
                                session.setPreferences(PasswordActivity.this,Constants.USER_NAME,data.getUser().getName());
                                session.setPreferences(PasswordActivity.this,Constants.LAST_LOGIN_DATE, strDate);
                                Toast.makeText(PasswordActivity.this, "Login Successfully...", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(PasswordActivity.this,MenuActivity.class);
                                startActivity(in);
                            }
                            else
                            {
                                Toast.makeText(PasswordActivity.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PasswordActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_name", usernameText.getText().toString().trim());
                params.put("password", passwordTxt.getText().toString().trim());
                params.put("branch_id", branchIdList.get(branch_pos));
                Log.e("Data212",""+branchIdList.get(branch_pos));
                params.put("role", Constants.STAFF_ROLE);
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
