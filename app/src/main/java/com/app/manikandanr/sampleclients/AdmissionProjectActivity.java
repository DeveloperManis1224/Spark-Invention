package com.app.manikandanr.sampleclients;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.HashMap;

public class AdmissionProjectActivity extends AppCompatActivity {
    private TextInputEditText txtName, txtDob, txtPhone, txtEmail;
    private EditText txtAddress, txtRemarks;
    private AutoCompleteTextView txtOrganization;
    private Spinner spinCountry, spinState, spinCity;
    private RadioButton radSchool, radCollege, radJoinNow, radJoinLater;
    private HashMap<String,String> countryList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_project);
        InitComponent();
    }

    private void InitComponent() {
        txtName = findViewById(R.id.txt_name);
        txtDob = findViewById(R.id.txt_dob);
        txtPhone = findViewById(R.id.txt_phone);
        txtEmail = findViewById(R.id.txt_email);
        txtAddress = findViewById(R.id.txt_address);
        txtRemarks = findViewById(R.id.txt_remarks);
        txtOrganization = findViewById(R.id.txt_organization);
        spinCountry = findViewById(R.id.spin_country );
        spinState = findViewById(R.id.spin_state);
        spinCity = findViewById(R.id.spin_city);
        radSchool = findViewById(R.id.rad_school);
        radCollege = findViewById(R.id.rad_college);
        radJoinNow = findViewById(R.id.rad_join_now);
        radJoinLater = findViewById(R.id.rad_join_later);
        for(int i = 0; i<10; i++) {
            countryList.put(""+i,i+" into "+i);
        }
        getId();
    }

    private void getId()
    {
        for(String str : countryList.values())
        {
            Log.e("Valuesssss",""+str);
        }
    }
}
