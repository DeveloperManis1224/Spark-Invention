package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.LayoutInflater;
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
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;

public class MenuActivity extends AppCompatActivity {

    private Button bAdmission,bMarketing,bAttendance,bRevenue,bStore;
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
        bStore = findViewById(R.id.btn_store);
        bAdmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                new AwesomeInfoDialog(getApplicationContext())
//                        .setTitle("Select")
//                        .setMessage(R.string.app_name)
//                        .setColoredCircle(R.color.colorPrimary)
//                        .setDialogIconAndColor(R.drawable.ic_dialog_info,
//                                R.color.white)
//                        .setCancelable(true)
//                        .show();


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
                        in.putExtra("role","school");
                        in.putExtra("role_id","1");
                        startActivity(in);
                    }
                });
                btnCollege.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, AdmissionForm.class);
                        in.putExtra("role","college");
                        in.putExtra("role_id","2");
                        startActivity(in);
                    }
                });
                btnProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, AdmissionForm.class);
                        in.putExtra("role","project");
                        in.putExtra("role_id","3");
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
                        in.putExtra("role","school");
                        in.putExtra("role_id","1");
                        startActivity(in);
                    }
                });
                btnCollege.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, MarketingAdmission.class);
                        in.putExtra("role","college");
                        in.putExtra("role_id","2");
                        startActivity(in);
                    }
                });
                btnProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                        Intent in = new Intent(MenuActivity.this, MarketingAdmission.class);
                        in.putExtra("role","project");
                        in.putExtra("role_id","3");
                        startActivity(in);
                    }
                });
                deleteDialog.show();
            }
        });
        bAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this, "Coming Soon...", Toast.LENGTH_LONG).show();
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
