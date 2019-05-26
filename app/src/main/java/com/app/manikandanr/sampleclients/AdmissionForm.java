package com.app.manikandanr.sampleclients;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdmissionForm extends AppCompatActivity {
    int orgPosition = 0;
    int mday = 24;
    int mmonth=10;
    int myear=1990;
    int country_pos= 0;
    int state_pos = 0;
    int city_pos = 0;
    int course_pos = 0;
    int cost_pos = 0;
    int cat_pos = 0;
    String joiningSts;
    String BalanceAmount ="";
    String org_dis_type ="";
    String org_dis = "";
    String course_dis_type = "";
    String course_dis = "";
    SessionManager sessionManager = new SessionManager();
    String programId;
    String imageData = "";
    String sectionId;
    String departmentId;
    String departmentYear;
    //for project and program admission only
    LinearLayout lyt_project_program;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    private RadioButton rad_joinNow, rad_joinLater, radSchool,radCollege;

    private String alertDate = "";
    private String sts_joinings = "";
    private TextView tCouseCost,tGetOffer, offerAvailable;
    private String userRollNo = "";
    private String role = "";
    private EditText edtName, edtDob, edtPhone, edtEmail, edtAddress , edtParentName, edtParentPhone,
            edtParentOccupation;
    private Spinner aedtCountry, aedtState, aedtCity,edtCollege, aedtCourse
            ,category_course,aed_department_year, aed_department, aed_section;
    final ArrayList<String> costList = new ArrayList<String>();
    final ArrayList<String> countryList = new ArrayList<String>();
    final ArrayList<String> stateList = new ArrayList<String>();
    final ArrayList<String> cityList = new ArrayList<String>();
    final ArrayList<String> courseCatList = new ArrayList<String>();
    final ArrayList<String> collegeList = new ArrayList<>();
    final ArrayList<String> collegeIdList = new ArrayList<>();
    final ArrayList<String> countryIdList = new ArrayList<String>();
    final ArrayList<String> categoryList = new ArrayList<String>();
    final ArrayList<String> categoryIdList = new ArrayList<String>();
    final ArrayList<String> stateIdList = new ArrayList<String>();
    final ArrayList<String> cityIdList = new ArrayList<String>();
    final ArrayList<String> courseCatIdList = new ArrayList<String>();
    final ArrayList<String> departmentList = new ArrayList<String>();
    final ArrayList<String> departmentIdList = new ArrayList<String>();
    ImageView imgProfile;
    int department_pos;
    StringBuilder offerDetails_join = new StringBuilder();
    Calendar myCalendar = Calendar.getInstance();
    Calendar myCalendar1 = Calendar.getInstance();
    ProgressDialog pd = null;
    TextView laterDate ;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_form);
        init();
    }

    private void init() {
        edtName = findViewById(R.id.edt_name);
        edtDob = findViewById(R.id.edt_dob);
        edtPhone = findViewById(R.id.edt_phone);
        edtCollege = findViewById(R.id.edt_college);
        edtEmail = findViewById(R.id.edt_email);
        edtParentName = findViewById(R.id.edt_parent_name);
        edtParentOccupation = findViewById(R.id.edt_parent_occupation);
        edtParentPhone = findViewById(R.id.edt_parent_phone);
        aedtCountry = findViewById(R.id.edt_country);
        aedtState = findViewById(R.id.edt_state);
        aedtCity = findViewById(R.id.edt_city);
        aedtCountry.setVisibility(View.GONE);
        aedtState.setVisibility(View.GONE);
        aedtCity.setVisibility(View.GONE);
        aed_department = findViewById(R.id.edt_department);
        aed_department_year = findViewById(R.id.edt_department_year);
        aed_section = findViewById(R.id.edt_department_section);
        aedtCourse = findViewById(R.id.edt_course);
        tCouseCost = findViewById(R.id.txt_course_cost);
        edtAddress = findViewById(R.id.edt_address);
        rad_joinLater = findViewById(R.id.rad_join_later);
        radSchool = findViewById(R.id.rad_school);
        radCollege  = findViewById(R.id.rad_clg);
        rad_joinNow = findViewById(R.id.rad_join_now);
        offerAvailable = findViewById(R.id.edt_offer_avail);
        lyt_project_program = findViewById(R.id.lyt_project_program);
        tGetOffer = findViewById(R.id.txt_get_offer);
        category_course = findViewById(R.id.edt_cat_course);
        btnNext = findViewById(R.id.btn_next);
        userRollNo = getIntent().getStringExtra(Constants.USER_ROLE_ID);

        laterDate = findViewById(R.id.edt_later_date);

        imgProfile = findViewById(R.id.img_profile);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextClick();
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            }
        });

        if(rad_joinNow.isChecked())
        {
            joiningSts = "1";
        }
        else
        {
            joiningSts = "2";
            laterDate.setVisibility(View.VISIBLE);
        }

        rad_joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joiningSts = "1";
                laterDate.setVisibility(View.GONE);
            }
        });
        rad_joinLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joiningSts = "2";
                laterDate.setVisibility(View.VISIBLE);
            }
        });

        startInitalize();
        //getDepartments(userRollNo);

        Log.e("DEPARTMENT_ID",""+departmentId);
        pd = new ProgressDialog(AdmissionForm.this);
        offerDetails_join.append("Applied Offer! \n");
        pd.setMessage("Loading");
        getCountry();
        getCatgories();

        laterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int yy =  myCalendar1.get(Calendar.YEAR);
               int mm =  myCalendar1.get(Calendar.MONTH);
                int dd =  myCalendar1.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(AdmissionForm.this, dateLater, yy, mm,
                        dd).show();
            }
        });
        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AdmissionForm.this, date, myear, mmonth,
                       mday).show();
            }
        });
        tGetOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getOffers();
            }
        });

        aedtCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aedtCountry.getSelectedItem().toString().equalsIgnoreCase("Select Country"))
                {
                    getState(countryIdList.get(i));
                    country_pos = i;
                }
                Log.e("SASASASA", "Country ID  :::::" +countryIdList.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        aedtState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aedtState.getSelectedItem().toString().equalsIgnoreCase("Select State"))
                {
                    getCity(stateIdList.get(i));
                    state_pos = i;
                }

               // Log.e("SASASASA", "State ID :   " + stateIdList.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        aedtCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(userRollNo.equalsIgnoreCase(Constants.ROLE_PROJECT))
//                {
//                    ((TextView)findViewById(R.id.txt_get_offer)).setVisibility(View.GONE);
//                }
//                else
//                {
                    if(!aedtCity.getSelectedItem().toString().equalsIgnoreCase("Select City"))
                    {
                        //getOrganization(cityIdList.get(i));
                        city_pos = i;
                        Log.e("SSSSSSSSSS",""+cityIdList.get(i));
                    }
//                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        edtCollege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!edtCollege.getSelectedItem().toString().equalsIgnoreCase("Select Organization"))
                {
                    orgPosition = i;
                    //Toast.makeText(AdmissionForm.this, ""+collegeList.get(orgPosition), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        aedtCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!aedtCourse.getSelectedItem().toString().equalsIgnoreCase("Select Category"))
                {
                    getCategoryCourse(categoryIdList.get(i));
                    cat_pos = i;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        category_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    course_pos = i;
                    cost_pos = i;
                    Log.e("BALANCE_AMT", courseCatList.get(i) + "///" + courseCatIdList.get(i) + "///" + i + "///" + BalanceAmount);
                    try {
                        BalanceAmount = costList.get(i);
                    } catch (IndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                    for (int ij = 0; i < costList.size(); i++) {
                        Log.e("BALANCE_AMT" + i, costList.get(ij));
                    }

                    Log.e("BALANCE_AMT", "" + BalanceAmount);
                }catch (Exception ex)
                {
                    Log.e("EXCEPTION",""+ex.getMessage());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        aed_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                department_pos = i+1;
                Log.e("SECTION_ID",""+departmentId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        cityList.add("Select City");
        stateList.add("Select State");
        //collegeList.add("Select Organization");
        courseCatList.add("Select Course");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        aedtCity.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, stateList);
        aedtState.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, collegeList);
        edtCollege.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, courseCatList);
        category_course.setAdapter(adapter4);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            imgProfile.setImageBitmap(photo);
            imageData  = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
    }

    private void startInitalize()
    {
        if(userRollNo.equalsIgnoreCase(Constants.ROLE_SCHOOL))
        {
            aed_section.setAdapter(new ArrayAdapter<String >(this,
                    android.R.layout.simple_spinner_dropdown_item,Constants.getSections()));
            aed_department_year.setVisibility(View.GONE);
            programId = "0";
            departmentYear ="0";
            getDepartments(userRollNo);
            role = Constants.ROLE_SCHOOL;
            aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    sectionId = ""+(i+1);
                    Log.e("SECTION_ID",""+sectionId);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        else  if(userRollNo.equalsIgnoreCase(Constants.ROLE_COLLEGE))
        {
            aed_department_year.setAdapter(new ArrayAdapter<String >(this,
                    android.R.layout.simple_spinner_dropdown_item,Constants.getYears()));
            programId = "0";
            getDepartments(userRollNo);
            aed_department_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    departmentYear = ""+(i+1);
                    Log.e("DEPARTMENT_YEAR",""+departmentYear);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            aed_section.setAdapter(new ArrayAdapter<String >(this,
                    android.R.layout.simple_spinner_dropdown_item,Constants.getSections()));

            role = Constants.ROLE_COLLEGE;
            aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    sectionId = ""+(i+1);
                    Log.e("SECTION_ID",""+sectionId);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        else  if(userRollNo.equalsIgnoreCase(Constants.ROLE_PROJECT))
        {
            programId = "1";
            lyt_project_program.setVisibility(View.VISIBLE);
            if(radSchool.isChecked())
            {
                getDepartments(Constants.ROLE_SCHOOL);
                aed_department_year.setVisibility(View.GONE);
                aed_department_year.setVisibility(View.GONE);
                role = Constants.ROLE_SCHOOL;
                aed_section.setAdapter(new ArrayAdapter<String >(AdmissionForm.this,
                        android.R.layout.simple_spinner_dropdown_item,Constants.getSections()));
                aed_department_year.setVisibility(View.GONE);
                programId = "0";
                departmentYear ="0";
                role = Constants.ROLE_SCHOOL;
                aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        sectionId = ""+(i+1);
                        Log.e("SECTION_ID",""+sectionId);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
            else
            {
                getDepartments(Constants.ROLE_COLLEGE);

                aed_department_year.setVisibility(View.VISIBLE);
                role = Constants.ROLE_COLLEGE;
                aed_department_year.setAdapter(new ArrayAdapter<String >(AdmissionForm.this,
                        android.R.layout.simple_spinner_dropdown_item,Constants.getYears()));
                programId = "0";

                aed_department_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        departmentYear = ""+(i+1);
                        Log.e("DEPARTMENT_YEAR",""+departmentYear);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                aed_section.setAdapter(new ArrayAdapter<String >(AdmissionForm.this,
                        android.R.layout.simple_spinner_dropdown_item,Constants.getSections()));

                role = Constants.ROLE_COLLEGE;
                aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        sectionId = ""+(i+1);
                        Log.e("SECTION_ID",""+sectionId);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }

            radSchool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDepartments(Constants.ROLE_SCHOOL);
                    aed_department_year.setVisibility(View.GONE);
                    role = Constants.ROLE_SCHOOL;
                    aed_section.setAdapter(new ArrayAdapter<String >(AdmissionForm.this,
                            android.R.layout.simple_spinner_dropdown_item,Constants.getSections()));
                    aed_department_year.setVisibility(View.GONE);
                    programId = "0";
                    departmentYear ="0";
                    role = Constants.ROLE_SCHOOL;
                    aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sectionId = ""+(i+1);
                            Log.e("SECTION_ID",""+sectionId);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    getOrganization("string");
                }
            });
            radCollege.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDepartments(Constants.ROLE_COLLEGE);
                    aed_department_year.setVisibility(View.VISIBLE);
                    role = Constants.ROLE_COLLEGE;
                    aed_department_year.setAdapter(new ArrayAdapter<String >(AdmissionForm.this,
                            android.R.layout.simple_spinner_dropdown_item,Constants.getYears()));
                    programId = "0";

                    aed_department_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            departmentYear = ""+(i+1);
                            Log.e("DEPARTMENT_YEAR",""+departmentYear);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    aed_section.setAdapter(new ArrayAdapter<String >(AdmissionForm.this,
                            android.R.layout.simple_spinner_dropdown_item,Constants.getSections()));

                    role = Constants.ROLE_COLLEGE;
                    aed_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sectionId = ""+(i+1);
                            Log.e("SECTION_ID",""+sectionId);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    getOrganization("string");
                }
            });
        }

        getOrganization("string");
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    DatePickerDialog.OnDateSetListener dateLater = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            laterDate.setText(sdf.format(myCalendar.getTime()));
        }

    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtDob.setText(sdf.format(myCalendar.getTime()));
        edtDob.setError(null);
    }

    public void onNextClick() {
        if(isValid()) {

            uploadStudentInfo();
//            if (nextButton.getText().toString().trim().equalsIgnoreCase("Next")) {
//                LayoutInflater factory = LayoutInflater.from(AdmissionForm.this);
//                final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
//                final AlertDialog deleteDialog = new AlertDialog.Builder(
//                        AdmissionForm.this).create();
//                deleteDialog.setView(deleteDialogView);
//
//                Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);
//                Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
//                Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);
//
//                btnSchool.setText("Join Now");
//                btnSchool.setAllCaps(false);
//
//                btnCollege.setText("Later");
//                btnCollege.setAllCaps(false);
//
//                btnProject.setText("Project / Program");
//                btnProject.setVisibility(View.GONE);
//
//                btnSchool.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        deleteDialog.dismiss();
//                        Toast.makeText(AdmissionForm.this, "You are selecting Join now",
//                                Toast.LENGTH_SHORT).show();
//                        nextButton.setText("Submit");
//                        sts_joinings = "now";
//                    }
//                });
//                btnCollege.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        deleteDialog.dismiss();
//                        int mYear, mMonth, mDay;
//                        final Calendar c = Calendar.getInstance();
//                        mYear = c.get(Calendar.YEAR);
//                        mMonth = c.get(Calendar.MONTH);
//                        mDay = c.get(Calendar.DAY_OF_MONTH);
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                                AdmissionForm.this,
//                                new DatePickerDialog.OnDateSetListener() {
//                                    @Override
//                                    public void onDateSet(DatePicker view, int year,
//                                                          int monthOfYear, int dayOfMonth) {
//                                        Toast.makeText(AdmissionForm.this,
//                                                "" + dayOfMonth + "-" +
//                                                        (monthOfYear + 1) + "-" + year,
//                                                Toast.LENGTH_SHORT).show();
//                                        alertDate = "" + dayOfMonth + "-" +
//                                                (monthOfYear + 1) + "-" + year;
//                                        nextButton.setText("Set Alert");
//                                    }
//                                }, mYear, mMonth, mDay);
//                        datePickerDialog.show();
//                        Toast.makeText(AdmissionForm.this, "You are selecting Later",
//                                Toast.LENGTH_SHORT).show();
//                        sts_joinings = "later";
//                        nextButton.setText("Set Alert");
//
//                    }
//                });
//
//                deleteDialog.show();
//            } else if (nextButton.getText().toString().trim().
//                    equalsIgnoreCase("Set Alert")) {
//                if (isValid()) {
//                    setStudentAlert();
//                }
//            } else
//                if (sts_joinings.equalsIgnoreCase("now")) {
//                    if (isValid()) {
//                        uploadStudentInfo();
//                    }
//
//            }
        }
    }

    private boolean isValid() {
        boolean val = true;

        if (edtName.getText().toString().trim().isEmpty()) {
            edtName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtEmail.getText().toString().trim().isEmpty()) {
            edtEmail.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if (edtDob.getText().toString().trim().isEmpty()) {
            edtDob.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtParentName.getText().toString().trim().isEmpty()) {
            edtParentName.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtParentOccupation.getText().toString().trim().isEmpty()) {
            edtParentOccupation.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtParentPhone.getText().toString().trim().isEmpty()) {
            edtParentPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }
        if (edtPhone.getText().toString().trim().isEmpty()) {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if (edtPhone.getText().toString().trim().isEmpty()) {
            edtPhone.setError(getResources().getString(R.string.error_msg));
            val = false;
        }

        if (aedtCountry.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            val = false;
        }
        if (aedtState.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();
            val = false;
        }
        if (aedtCity.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
            val = false;
        }

        if (aedtCourse.getSelectedItem().toString().trim().isEmpty()) {
            Toast.makeText(this, "Select one Course", Toast.LENGTH_SHORT).show();
            val = false;
        }

        if(rad_joinLater.isChecked())
        {
            if(laterDate.getText().toString().equalsIgnoreCase("Select Join Date"))
            {
                val = false;
                laterDate.setError("Select Date");
            }
        }
        
        if(imageData.isEmpty() || imageData.equalsIgnoreCase(""))
        {
            val = false;
            Toast.makeText(AdmissionForm.this, "Invalid student Image", Toast.LENGTH_SHORT).show();
        }
        return val;
    }

    private void getOrganization(final String cityId) {
        collegeIdList.clear();
        collegeList.clear();
        collegeList.add("Select Organization");
        collegeIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
       // String url = Constants.BASE_URL+"api/role-organization-lists";
        String url = Constants.BASE_URL+"api/branch-organization";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SSSSS",""+response);
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("organizations");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                collegeList.add(jobj1.getString("name"));
                                collegeIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, collegeList);
                        edtCollege.setAdapter(adapter);

                    }


                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EEEEEEE",""+error.getMessage());
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("city_id",cityId);
//                params.put("role", role);
                params.put("instituation_id", role);
                params.put("user_id",sessionManager.getPreferences(AdmissionForm.this,Constants.USER_ID));
                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getCity(final String stateId) {
        cityList.clear();
        cityIdList.clear();
        cityIdList.add("0");
        cityList.add("Select City");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/city";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("cities");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                cityList.add(jobj1.getString("city"));
                                cityIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, cityList);
                        aedtCity.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("state_id", stateId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getState(final String country_id) {
        stateIdList.clear();
        stateList.clear();
        stateList.add("Select State");
        stateIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/state";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("states");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                stateList.add(jobj1.getString("state"));
                                stateIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, stateList);
                        aedtState.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_id", country_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getDepartments(final String institutionId) {
        departmentList.clear();
        departmentIdList.clear();
        departmentList.add("Select");
        departmentIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/institute-department";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("departments");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                departmentList.add(jobj1.getString("department"));
                                departmentIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, departmentList);
                        aed_department.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("instituation_id", institutionId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void getCountry() {
        countryIdList.clear();
        countryList.clear();
        countryIdList.add("0");
        countryList.add("Select Country");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/country";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("countries");

                            for (int i = 0; i <= jary.length(); i++) {
                                JSONObject jobj11 = jary.getJSONObject(i);
                                String cuntry = jobj11.getString("country");
                                String id = jobj11.getString("id");
                                Log.v("TTTTTTTTTTT",""+ cuntry +" "+id);
                                countryList.add(cuntry);
                                countryIdList.add(id);
                            }
                        } catch (JSONException e) {
                            Log.v("TTTTTTTTTTT",""+ e.getMessage());
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> countryAdapt = new ArrayAdapter<String>
                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, countryList);
        aedtCountry.setAdapter(countryAdapt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    String value = "";
    private void uploadStudentInfo() {
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + "api/student";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pd.dismiss();
                            Log.e("RESPONSE111", "" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String sts = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (sts.equalsIgnoreCase("1")) {

                                JSONObject jobj = jsonObject.getJSONObject("student");

                                String studentName = jobj.getString("name");
                                final String serialNumber = jobj.getString("serial_no");
                                String billNumber = jobj.getString("bill_no");
                                String amountPaid = jobj.getString("calc_amount");

                                value = "Student Name  : " + studentName + "\n" +
                                        "Serial Number : " + serialNumber + "\n" +
                                        "Bill Number   : " + billNumber + "\n" +
//                                        "Payment Method:  CASH\n" +
                                        "Amount Paid   : " + amountPaid;
                                final String studentId = jobj.getString("id");
                                if(rad_joinNow.isChecked()) {
                                    new AwesomeNoticeDialog(AdmissionForm.this)
                                            .setTitle("Success!")
                                            .setMessage(msg)
                                            .setColoredCircle(R.color.colorPrimaryDark)
                                            .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                            .setCancelable(true)
                                            .setButtonText("Ok")
                                            .setButtonBackgroundColor(R.color.black)
                                            .setNoticeButtonClick(new Closure() {
                                                @Override
                                                public void exec() {
                                                    Intent in =new Intent( AdmissionForm.this, ViewBill.class);
                                                    in.putExtra("detail", "" + value);
                                                    in.putExtra("stud_id", studentId);
                                                    in.putExtra("reg_num", serialNumber);
                                                    startActivity(in);
                                                    finish();
                                                }
                                            })
                                            .show();
                                }
                                else
                                {
                                    new AwesomeNoticeDialog(AdmissionForm.this)
                                            .setTitle("Success!")
                                            .setMessage("Alert Saved Successfully")
                                            .setColoredCircle(R.color.colorPrimaryDark)
                                            .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                            .setCancelable(true)
                                            .setButtonText("Ok")
                                            .setButtonBackgroundColor(R.color.black)
                                            .setNoticeButtonClick(new Closure() {
                                                @Override
                                                public void exec() {
                                                    Intent in =new Intent( AdmissionForm.this, MenuActivity.class);
                                                    startActivity(in);
                                                    finish();
                                                }
                                            })
                                            .show();
                                }
                            } else {
                                if(msg.equalsIgnoreCase("Already Inserted"))
                                {
                                    Toast.makeText(AdmissionForm.this, ""+msg, Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(AdmissionForm.this, "Submition failed ",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(AdmissionForm.this, "Couldn't connect to server" ,
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",sessionManager.getPreferences(AdmissionForm.this,Constants.USER_ID));
                params.put("parent_name",edtParentName.getText().toString().trim());
                params.put("parent_occupation",edtParentOccupation.getText().toString().trim());
                params.put("parent_phone",edtParentPhone.getText().toString().trim());
                params.put("student_image",imageData);
                params.put("name", edtName.getText().toString().trim());
                params.put("dob", edtDob.getText().toString().trim());
                params.put("instituation_id",getIntent().getExtras().getString(Constants.USER_ROLE_ID));
                params.put("organization_id", collegeIdList.get(orgPosition));
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("email", edtEmail.getText().toString().trim());
                params.put("country_id", "0");
                params.put("state_id", "0");
                params.put("city_id", "0");
                params.put("address", edtAddress.getText().toString().trim());
                params.put("course_id", courseCatIdList.get(course_pos));
                params.put("department_section_id",sectionId);
                params.put("department_year_id",departmentYear);
                params.put("department_id",departmentIdList.get(department_pos));
                params.put("alert_date", laterDate.getText().toString().trim());
                params.put("join_status", joiningSts);
                params.put("role", userRollNo);
                params.put("category_id",categoryIdList.get(cat_pos));


                Log.e("DDDDD","Ins "+getIntent().getExtras().getString(Constants.USER_ROLE_ID)+
                        " org_ "+collegeIdList.get(orgPosition)+" course "+courseCatIdList.get(course_pos)+
                " departent "+departmentId+ " join_sts "+joiningSts+" role"+userRollNo+" cate_id "+categoryIdList.get(cat_pos));

                params.put("program_id",programId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


    private void getCatgories() {
        categoryIdList.clear();
        categoryList.clear();
        categoryList.add("Select Category");
        categoryIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/categroy";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("categories");
                            for (int i = 0; i < jary.length(); i++) {
                                JSONObject jobj1 = jary.getJSONObject(i);
                                categoryList.add(jobj1.getString("category"));
                                categoryIdList.add(jobj1.getString("id"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
                        aedtCourse.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void getCategoryCourse(final String categoryId) {
        courseCatIdList.clear();
        courseCatList.clear();
        costList.clear();
        courseCatList.add("Select Course");
        courseCatIdList.add("0");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/category-course";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RESPONSE_Result",""+response);
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jary = jobj.getJSONArray("categories");

                            if(jary.length()==0)
                            {
                                Toast.makeText(AdmissionForm.this, "No Course Available", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                for (int i = 0; i < jary.length(); i++) {
                                    JSONObject jobj1 = jary.getJSONObject(i);
                                    costList.add(jobj1.getString("amount"));
                                    courseCatList.add(jobj1.getString("course") + " ( Rs." + jobj1.getString("amount") + ")");
                                    courseCatIdList.add(jobj1.getString("id"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (AdmissionForm.this, android.R.layout.simple_spinner_dropdown_item, courseCatList);
                        category_course.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("category_id", categoryId);
                return  params;
            }
        };
        queue.add(stringRequest);
    }

    private void getOffers() {

        final int calcAmount = 0;
//       Log.e("CHECK IDS",""+courseCatIdList.get(course_pos)+"////" +
//               ""+collegeIdList.get(orgPosition)+"////" +
//               ""+userRollNo);
        offerDetails_join.delete(0,offerDetails_join.length());
        offerDetails_join.append("Applied Offer! \n");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/offer-details";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String sts = jobj.getString("status");
                            if(sts.equalsIgnoreCase("1"))
                            {
                                JSONObject jobjCourse = jobj.getJSONObject("course");
                                JSONObject jobjOffer = jobj.getJSONObject("offer");
                                String courseName = jobjCourse.getString("course");
                                    String courseOfferType = jobjCourse.getString("offer_type");
                                    String courseOffer = jobjCourse.getString("offer");
                                    String courseAmount = jobjCourse.getString("amount");

                                String totalAmount = jobjOffer.getString("TotalAmount");
                                String offertaxAmount = jobjOffer.getString("offerTaxAmount");
                                String orgOfferAmount = jobjOffer.getString("organizationTaxAmount");
                                String overallDiscount = jobjOffer.getString("overallDiscount");
                                String amountToPay = jobjOffer.getString("netTotalAmount");

                                offerDetails_join.append(" Total Amount : "+totalAmount+"\n");
                                offerDetails_join.append(" Offer : "+offertaxAmount +"\n");
                                offerDetails_join.append(" Organization Offer : "+orgOfferAmount+"\n");
                                offerDetails_join.append(" OverallDiscount : "+overallDiscount+"\n");


//                                    String courseName = jobjCourse.getString("course");
//                                    String courseOfferType = jobjCourse.getString("offer_type");
//                                    String courseOffer = jobjCourse.getString("offer");
//                                    String courseAmount = jobjCourse.getString("amount");
//
//                                    String OrgName = jobjOrg.getString("name");
//                                    String OrgOfferType = jobjOrg.getString("offer_type");
//                                    String OrgOffer = jobjOrg.getString("offer");
//                                    String orgAmount = jobjCourse.getString("amount");
//
//                                org_dis_type =OrgOfferType;
//                                org_dis =OrgOffer;
//                                course_dis_type = courseOfferType;
//                                course_dis = courseOffer;
//                                Double balAmount =0.0;
//                                double balance;
//                                double orgbalance =0.0;
//                                double coursebalance = 0.0;
//                               if(!courseOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE))
//                                {
//                                    String type = "Rs";
//                                    if(courseOfferType.equalsIgnoreCase(Constants.OFFER_PERCENTAGE)) {
//                                        type = "%";
//                                        balAmount = (Double.valueOf(courseOffer) / 100.f) * Double.valueOf(courseAmount);
//                                        offerDetails_join.append("" + courseName + "    " + courseOffer + "" + type + "\n");
//                                    }
//                                    else {
//                                        balAmount = Double.valueOf(courseAmount) - Double.valueOf(courseOffer);
//                                        offerDetails_join.append("" + courseName + "    " + type + "" + courseOffer + "\n");
//                                    }
//                                }
//                                coursebalance = balAmount;
//                                if(!OrgOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE))
//                                {
//                                    String type = "Rs";
//                                    if(OrgOfferType.equalsIgnoreCase(Constants.OFFER_PERCENTAGE)) {
//                                        type = "%";
//                                        balAmount = (Double.valueOf(OrgOffer)/100.f) * Double.valueOf(orgAmount);
//                                        offerDetails_join.append(""+OrgName+"      "+OrgOffer+" "+type+"\n");
//                                    }
//                                    else {
//                                        balAmount = balAmount - Double.valueOf(OrgOffer);
//                                        offerDetails_join.append(""+OrgName+"      "+type+" "+OrgOffer+"\n");
//                                    }
//
//                                }
//                                orgbalance = balAmount;
//                                if(OrgOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE)&&
//                                        courseOffer.equalsIgnoreCase(Constants.OFFER_NOT_AVAILABLE))
//                                {
//                                    offerDetails_join.append("Sorry, Currently no offers");
//                                    balAmount =0.0;
//                                    orgbalance = 0.0;
//                                    coursebalance = 0.0;
//                                }
//
//                                Log.e("BALANCE_CECEC",orgbalance+"////"+coursebalance);
//                                balance =Double.valueOf(courseAmount) - ( orgbalance + coursebalance) ;
                               offerAvailable.setText(""+offerDetails_join+"\n \nAmount Payable : Rs "+amountToPay );
                               offerAvailable.setVisibility(View.VISIBLE);
                               BalanceAmount = String.valueOf(amountToPay);
                            }
                            else
                            {
                                Toast.makeText(AdmissionForm.this, ""+jobj.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdmissionForm.this, "Please fill all details..." , Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>() ;
                params.put("course_id",courseCatIdList.get(course_pos));
                params.put("organization_id",collegeIdList.get(orgPosition));
                params.put("role",userRollNo);
                return params;
            }
        };

        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {

        new AwesomeSuccessDialog(AdmissionForm.this)
                .setTitle("Exit")
                .setMessage("Are you sure want to leave the form?")
                .setColoredCircle(R.color.colorPrimary)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(true)
                .setPositiveButtonText("Yes")
                .setPositiveButtonbackgroundColor(R.color.colorPrimary)
                .setPositiveButtonTextColor(R.color.white)
                .setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        startActivity(new Intent(AdmissionForm.this, MenuActivity.class));
                        finish();
                    }
                })
                .setNegativeButtonText("Cancel")
                .setNegativeButtonTextColor(R.color.white)
                .setNegativeButtonClick(new Closure() {
                    @Override
                    public void exec() {

                    }
                })
                .show();
    }

    public void onClickAddOrganization(View view) {
        Intent in = new Intent(AdmissionForm.this,AddItems.class);
        startActivity(in);
    }

}
