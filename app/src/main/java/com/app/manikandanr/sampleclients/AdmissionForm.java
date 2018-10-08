package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AdmissionForm extends AppCompatActivity {

    private Button nextButton;
    String sts_joinings="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_form);

        nextButton = findViewById(R.id.btn_next);


    }

    public void onNextClick(View view)
    {
        final Calendar myCalendar = Calendar.getInstance();
       if(nextButton.getText().toString().trim().equalsIgnoreCase("Next"))
       {
           LayoutInflater factory = LayoutInflater.from(AdmissionForm.this);
           final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
           final AlertDialog deleteDialog = new AlertDialog.Builder(AdmissionForm.this).create();
           deleteDialog.setView(deleteDialogView);

           Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes) ;
           Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no) ;
           Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none) ;

           btnSchool.setText("Join Now");
           btnSchool.setAllCaps(false);

           btnCollege.setText("Later");
           btnCollege.setAllCaps(false);

           btnProject.setText("Project / Program");
           btnProject.setVisibility(View.GONE);


           btnSchool.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   deleteDialog.dismiss();
                   Toast.makeText(AdmissionForm.this, "You are selecting Join now", Toast.LENGTH_SHORT).show();
                   nextButton.setText("Submit");
                   sts_joinings="now";
               }
           });
           btnCollege.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   deleteDialog.dismiss();
                   Toast.makeText(AdmissionForm.this, "You are selecting Later", Toast.LENGTH_SHORT).show();
                   nextButton.setText("Submit");
                   sts_joinings = "later";

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
       else
       {
           if(sts_joinings.equalsIgnoreCase("now"))
           {
               Intent in = new Intent(AdmissionForm.this,PaymentStatus.class);
               startActivity(in);
           }
           else
           {
               int mYear, mMonth, mDay;
               final Calendar c = Calendar.getInstance();
               mYear = c.get(Calendar.YEAR);
               mMonth = c.get(Calendar.MONTH);
               mDay = c.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                       new DatePickerDialog.OnDateSetListener() {

                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {

                               Toast.makeText(AdmissionForm.this, ""+dayOfMonth + "-" +
                                       (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();

                           }
                       }, mYear, mMonth, mDay);
               datePickerDialog.show();
           }


       }


    }
}
