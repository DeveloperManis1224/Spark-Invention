package com.app.manikandanr.sampleclients;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AdmissionProjectActivity extends AppCompatActivity {
    private TextInputEditText txtName, txtDob, txtPhone, txtEmail;
    private EditText txtAddress, txtRemarks;
    private AutoCompleteTextView txtOrganization;
    private Spinner spinCountry, spinState, spinCity, spinCategory, spinCourse;
    private RadioButton radSchool, radCollege, radJoinNow, radJoinLater;
    private ArrayList<String> countryList = new ArrayList<>();
    private ArrayList<String> stateList = new ArrayList<>();
    private ArrayList<String> cityList = new ArrayList<>();
    private ArrayList<String> categoryList = new ArrayList<>();
    private ArrayList<String> courseList = new ArrayList<>();
    private ArrayList<String> countryIdList = new ArrayList<>();
    private ArrayList<String> stateIdList = new ArrayList<>();
    private ArrayList<String> cityIdList = new ArrayList<>();
    private ArrayList<String> categoryIdList = new ArrayList<>();
    private ArrayList<String> courseIdList = new ArrayList<>();

    private int country_pos, state_pos, city_pos;

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
        spinCategory = findViewById(R.id.spin_category);
        spinCourse = findViewById(R.id.spin_course);
        spinCity = findViewById(R.id.spin_city);
        radSchool = findViewById(R.id.rad_school);
        radCollege = findViewById(R.id.rad_college);
        radJoinNow = findViewById(R.id.rad_join_now);
        radJoinLater = findViewById(R.id.rad_join_later);

        countryList.add("Select Country");
        stateList.add("Select State");
        cityList.add("Select City");
        categoryList.add("Select Category");
        countryList.add("Select Course");

        countryIdList.add("0");
        stateIdList.add("0");
        cityIdList.add("0");
        categoryIdList.add("0");
        courseIdList.add("0");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, countryList);
        spinCountry.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, stateList);
        spinState.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        spinCity.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        spinCategory.setAdapter(adapter3);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>
                (AdmissionProjectActivity.this, android.R.layout.simple_spinner_dropdown_item, courseList);
        spinCourse.setAdapter(adapter3);
    }


    private boolean isValid()
    {
        boolean val  = true;
        if(txtName.getText().toString().isEmpty())
        {
            val = false;
            txtName.setError("Invalid");
        }
        if(txtDob.getText().toString().isEmpty())
        {
            val = false;
            txtDob.setError("Invalid");
        }
        if(txtPhone.getText().toString().isEmpty())
        {
            val = false;
            txtPhone.setError("Invalid");
        }
        if(txtEmail.getText().toString().isEmpty())
        {
            val = false;
            txtEmail.setError("Invalid");
        }
        if(txtAddress.getText().toString().isEmpty())
        {
            val = false;
            txtAddress.setError("Invalid");
        }
        if(txtRemarks.getText().toString().isEmpty())
        {
            val = false;
            txtRemarks.setError("Invalid");
        }
        if(txtOrganization.getText().toString().isEmpty())
        {
            val = false;
            txtOrganization.setError("Invalid");
        }
        if(spinCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
        {
            val = false;
            Toast.makeText(this, "Invalid Country", Toast.LENGTH_SHORT).show();
        }
        if(spinState.getSelectedItem().toString().equalsIgnoreCase("Select State"))
        {
            val = false;
            Toast.makeText(this, "Invalid State", Toast.LENGTH_SHORT).show();
        }
        if(spinCity.getSelectedItem().toString().equalsIgnoreCase("Select City"))
        {
            val = false;
            Toast.makeText(this, "Invalid City", Toast.LENGTH_SHORT).show();
        }
        return val;
    }


    public void onClickAdmissionProject(View view) {
        if(isValid())
        {

        }
    }
}
