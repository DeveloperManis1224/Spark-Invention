package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.app.manikandanr.sampleclients.Activities.AddStudentAttendance;
import com.app.manikandanr.sampleclients.Activities.CreateGroupPage;
import com.app.manikandanr.sampleclients.Utils.Constants;

public class AttendancePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_page);


    }

    public void onCLickStudentAdd(View view) {
//        LayoutInflater factory = LayoutInflater.from(AttendancePage.this);
//        final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
//        final AlertDialog deleteDialog = new AlertDialog.Builder(AttendancePage.this).create();
//        deleteDialog.setView(deleteDialogView);
//        Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);
//
//        Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
//        Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);
//
//        btnSchool.setText("School");
//        btnSchool.setAllCaps(false);
//
//        btnCollege.setText("College");
//        btnCollege.setAllCaps(false);
//
//        btnProject.setText("Project / Program");
//        btnProject.setVisibility(View.GONE);
//        btnProject.setAllCaps(false);
//
//        btnSchool.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteDialog.dismiss();
//                Intent in = new Intent(AttendancePage.this, AddStudentAttendance.class);
//
//                in.putExtra(Constants.USER_ROLE_ID,Constants.ROLE_SCHOOL);
//                startActivity(in);
//            }
//        });
//        btnCollege.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteDialog.dismiss();
//                Intent in = new Intent(AttendancePage.this, AddStudentAttendance.class);
//                in.putExtra(Constants.USER_ROLE_ID,Constants.ROLE_COLLEGE);
//                startActivity(in);
//            }
//        });
//        btnProject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteDialog.dismiss();
//                Intent in = new Intent(AttendancePage.this, AddStudentAttendance.class);
//                in.putExtra(Constants.USER_ROLE_ID,Constants.ROLE_PROJECT);
//                startActivity(in);
//            }
//        });
//        deleteDialog.show();

                Intent in = new Intent(AttendancePage.this, AddStudentAttendance.class);
                startActivity(in);
    }

    public void onClickCreateGroup(View view) {
        Intent in = new Intent(AttendancePage.this, CreateGroupPage.class);
        startActivity(in);
    }

    public void onClickViewStudent(View view) {
        Intent in = new Intent(AttendancePage.this, com.app.manikandanr.sampleclients.Activities.AttendancePage.class);
        startActivity(in);
    }
}
