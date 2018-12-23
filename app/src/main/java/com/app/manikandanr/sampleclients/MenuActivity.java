package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;

public class MenuActivity extends AppCompatActivity {

    private Button bAdmission,bMarketing,bAttendance,bRevenue,bMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        //Lib- Lists:
        //https://android-arsenal.com/details/1/6109
        //https://android-arsenal.com/details/1/6929
    }
    private void init() {
        bAdmission = findViewById(R.id.btn_admission);
        bMarketing = findViewById(R.id.btn_marketing);
        bAttendance = findViewById(R.id.btn_attendance);
        bRevenue = findViewById(R.id.btn_revenue);
        bMore = findViewById(R.id.btn_store);
        bAdmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater factory = LayoutInflater.from(MenuActivity.this);
                final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MenuActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);

                Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
                Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);

                btnSchool.setText("School");
                btnSchool.setAllCaps(false);

                btnCollege.setText("College");
                btnCollege.setAllCaps(false);


                btnProject.setText("Project / Program");
                btnProject.setAllCaps(false);

                btnSchool.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, AdmissionForm.class);
                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_SCHOOL);
                        in.putExtra(Constants.USER_ROLE_ID,Constants.ROLE_SCHOOL);
                        startActivity(in);
                    }
                });
                btnCollege.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, AdmissionForm.class);
                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_COLLEGE);
                        in.putExtra(Constants.USER_ROLE_ID,Constants.ROLE_COLLEGE);
                        startActivity(in);
                    }
                });
                btnProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, AdmissionForm.class);
                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_PROJECT);
                        in.putExtra(Constants.USER_ROLE_ID,Constants.ROLE_PROJECT);
                        startActivity(in);
                    }
                });
                deleteDialog.show();
            }
        });

        bMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater factory = LayoutInflater.from(MenuActivity.this);
                final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MenuActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);
                Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
                Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);

                btnSchool.setText("School");
                btnSchool.setAllCaps(false);

                btnCollege.setText("College");
                btnCollege.setAllCaps(false);

                btnProject.setText("Project / Program");
                btnProject.setVisibility(View.GONE);
                btnProject.setAllCaps(false);

                btnSchool.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, MarketingAdmission.class);
                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_SCHOOL);
                        in.putExtra(Constants.USER_ROLE_ID,"1");
                        startActivity(in);
                    }
                });
                btnCollege.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, MarketingAdmission.class);
                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_COLLEGE);
                        in.putExtra(Constants.USER_ROLE_ID,"2");
                        startActivity(in);
                    }
                });
                btnProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                    }
                });
                deleteDialog.show();
            }
        });
        bAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent in = new Intent(MenuActivity.this,AttendanceActivity.class);
               startActivity(in);
            }
        });

        bRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MenuActivity.this,MoreMenu.class);
                startActivity(in);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
