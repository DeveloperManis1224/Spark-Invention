package com.app.manikandanr.sampleclients;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AdmissionForm extends AppCompatActivity {

    private Button nextButton;
    String sts_joinings="";

    private EditText edtName,edtDob,edtCollege,edtPhone,edtEmail,edtAddress;
    private AutoCompleteTextView aedtCountry,aedtState,aedtCity,aedtCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_form);
        init();
    }

    private void init()
    {
        edtName = findViewById(R.id.edt_name);
        edtDob = findViewById(R.id.edt_dob);
        edtPhone = findViewById(R.id.edt_phone);
        edtCollege = findViewById(R.id.edt_college);
        edtEmail = findViewById(R.id.edt_email);
        aedtCountry = findViewById(R.id.edt_country);
        aedtState = findViewById(R.id.edt_state);
        aedtCity = findViewById(R.id.edt_city);
        aedtCourse = findViewById(R.id.edt_course);


        nextButton = findViewById(R.id.btn_next);
    }

    public void onNextClick(View view)
    {
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
                   int mYear, mMonth, mDay;
                   final Calendar c = Calendar.getInstance();
                   mYear = c.get(Calendar.YEAR);
                   mMonth = c.get(Calendar.MONTH);
                   mDay = c.get(Calendar.DAY_OF_MONTH);
                   DatePickerDialog datePickerDialog = new DatePickerDialog(AdmissionForm.this,
                           new DatePickerDialog.OnDateSetListener() {

                               @Override
                               public void onDateSet(DatePicker view, int year,
                                                     int monthOfYear, int dayOfMonth) {

                                   Toast.makeText(AdmissionForm.this, ""+dayOfMonth + "-" +
                                           (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();
                                   nextButton.setText("Submit");

                               }
                           }, mYear, mMonth, mDay);
                   datePickerDialog.show();
                   Toast.makeText(AdmissionForm.this, "You are selecting Later", Toast.LENGTH_SHORT).show();
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
               Intent in = new Intent(AdmissionForm.this,AlertActivity.class);
               startActivity(in);
           }
       }
    }

    private boolean isValid()
    {
        boolean val = true;

        if(edtName.getText().toString().trim().isEmpty())
        {
            edtName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if(edtEmail.getText().toString().trim().isEmpty())
        {
            edtEmail.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if(edtCollege.getText().toString().trim().isEmpty())
        {
            edtCollege.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtDob.getText().toString().trim().isEmpty())
        {
            edtDob.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if( edtPhone.getText().toString().trim().isEmpty())
        {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if( edtPhone.getText().toString().trim().isEmpty())
        {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if( aedtCountry.getText().toString().trim().isEmpty())
        {
            aedtCountry.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if( aedtState.getText().toString().trim().isEmpty())
        {
            aedtState.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if( aedtCity.getText().toString().trim().isEmpty())
        {
            aedtCity.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if( aedtCourse.getText().toString().trim().isEmpty())
        {
            aedtCourse.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        return val;
    }
}
