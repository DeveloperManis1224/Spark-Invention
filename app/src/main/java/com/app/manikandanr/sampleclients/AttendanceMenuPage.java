package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.manikandanr.sampleclients.Activities.AddStudentAttendance;
import com.app.manikandanr.sampleclients.Activities.AttendancePage;
import com.app.manikandanr.sampleclients.Activities.CreateGroupPage;
import com.app.manikandanr.sampleclients.Activities.ViewGroupActivity;

public class AttendanceMenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_page);
    }

    public void onCLickStudentAdd(View view) {
        Intent in = new Intent(AttendanceMenuPage.this, AddStudentAttendance.class);
        startActivity(in);
    }

    public void onClickCreateGroup(View view) {
        Intent in = new Intent(AttendanceMenuPage.this, CreateGroupPage.class);
        startActivity(in);
    }

    public void onClickViewStudent(View view) {
        Intent in = new Intent(AttendanceMenuPage.this, AttendancePage.class);
        startActivity(in);
    }

    public void onCLickViewGroup(View view) {
        Intent in = new Intent(AttendanceMenuPage.this, ViewGroupActivity.class);
        startActivity(in);
    }
}
