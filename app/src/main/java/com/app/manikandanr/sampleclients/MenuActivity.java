package com.app.manikandanr.sampleclients;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;
import com.app.manikandanr.sampleclients.Utils.SingleShortLocationProvider;
import com.google.android.gms.maps.model.LatLng;

public class MenuActivity extends AppCompatActivity {

    private Button bAdmission,bMarketing,bAttendance,bRevenue,bMore;
    private TextView txtLoggedInUserName;
    String[] perms = {"android.permission.FINE_LOCATION",
            "android.permission.CALL_PHONE",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.CAMERA"};
    int permsRequestCode = 200;
    SessionManager session = new SessionManager();

    private float distanceBetween(LatLng latLng1, LatLng latLng2) {
        Location loc1 = new Location(LocationManager.GPS_PROVIDER);
        Location loc2 = new Location(LocationManager.GPS_PROVIDER);
        loc1.setLatitude(latLng1.latitude);
        loc1.setLongitude(latLng1.longitude);
        loc2.setLatitude(latLng2.latitude);
        loc2.setLongitude(latLng2.longitude);
        return loc1.distanceTo(loc2);
    }


    public double distance (double lat_a, double lng_a, double lat_b, double lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                Math.sin(lngDiff /2) * Math.sin(lngDiff /2) ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;
        int meterConversion = 1609;
        return new Float(distance * meterConversion).floatValue();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        distanceBetween(new LatLng(13.064884,80.22537),new LatLng(13.056807,80.255341));

        distance(13.064884,80.22537,13.056807,80.255341);
        Log.e("Distance between :","sdgsdg "+distanceBetween(new LatLng(13.064884,80.22537),new LatLng(13.056807,80.255341)));
        Log.e("Distance between :","distance "+distance(13.064884,80.22537,13.056807,80.255341));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(perms, permsRequestCode);
        }


//        if(Constants.isNetworkAvailable(MenuActivity.this))
//        {
//            new AwesomeSuccessDialog(MenuActivity.this)
//                    .setTitle("No Internet Available")
//                    .setMessage("There is no Internet Connection. please turn on your Internet connection")
//                    .setColoredCircle(R.color.colorPrimary)
//                    .setDialogIconAndColor(R.drawable.ic_dialog_warning, R.color.white)
//                    .setCancelable(true)
//                    .setPositiveButtonText("Ok")
//                    .setPositiveButtonbackgroundColor(R.color.colorPrimary)
//                    .setPositiveButtonTextColor(R.color.white)
//                    .setPositiveButtonClick(new Closure() {
//                        @Override
//                        public void exec() {
//
//                            Intent in = new Intent(MenuActivity.this, SplashScreen.class);
//                            startActivity(in);
//                            finish();
//                        }
//                    })
//                    .show();
//        }
//        else {
//            init();
//        }
        init();
        //Lib- Lists:
        //https://android-arsenal.com/details/1/6109
        //https://android-arsenal.com/details/1/6929
    }



    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case 200:
                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                break;
        }
    }

        private void init() {
        bAdmission = findViewById(R.id.btn_admission);
        bMarketing = findViewById(R.id.btn_marketing);
        bAttendance = findViewById(R.id.btn_attendance);
        bRevenue = findViewById(R.id.btn_revenue);
        bMore = findViewById(R.id.btn_store);
        txtLoggedInUserName = findViewById(R.id.logged_in_by);
            String styledText = "Logged in by <font color='red'> "+ session.getPreferences(MenuActivity.this,Constants.USER_NAME)+"</font>";
            txtLoggedInUserName.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);


        SingleShortLocationProvider.requestSingleUpdate(MenuActivity.this,
                new SingleShortLocationProvider.LocationCallback() {
                    @Override public void onNewLocationAvailable(SingleShortLocationProvider.GPSCoordinates location) {
                        Log.d("Locationasdasd", "my location is " + location.latitude+"   "+location.longitude);
                    }
                });
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
//                Intent in = new Intent(MenuActivity.this,AdmissionProjectActivity.class);
//                startActivity(in);
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
//                LayoutInflater factory = LayoutInflater.from(MenuActivity.this);
//                final View deleteDialogView = factory.inflate(R.layout.mylayout, null);
//                final AlertDialog deleteDialog = new AlertDialog.Builder(MenuActivity.this).create();
//                deleteDialog.setView(deleteDialogView);
//                Button btnSchool = (Button) deleteDialogView.findViewById(R.id.btn_yes);
//
//                Button btnCollege = (Button) deleteDialogView.findViewById(R.id.btn_no);
//                Button btnProject = (Button) deleteDialogView.findViewById(R.id.btn_none);
//
//                btnSchool.setText("School");
//                btnSchool.setAllCaps(false);
//
//                btnCollege.setText("College");
//                btnCollege.setAllCaps(false);
//
//                btnProject.setText("Project / Program");
//                btnProject.setVisibility(View.GONE);
//                btnProject.setAllCaps(false);
//
//                btnSchool.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        deleteDialog.dismiss();
//                        Intent in = new Intent(MenuActivity.this, AttendanceMenuPage.class);
//                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_SCHOOL);
//                        startActivity(in);
//                    }
//                });
//                btnCollege.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        deleteDialog.dismiss();
//                        Intent in = new Intent(MenuActivity.this, AttendanceMenuPage.class);
//                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_COLLEGE);
//                        startActivity(in);
//                    }
//                });
//                btnProject.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        deleteDialog.dismiss();
//                        Intent in = new Intent(MenuActivity.this, AttendanceMenuPage.class);
//                        in.putExtra(Constants.USER_ROLE,Constants.USER_TYPE_PROJECT);
//                        startActivity(in);
//                    }
//                });
//                deleteDialog.show();
                Intent in = new Intent(MenuActivity.this, AttendanceMenuPage.class);
                startActivity(in);
            }
        });

        bRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MenuActivity.this,AccountsHome.class);
                startActivity(in);
                finish();
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

    public void onCLickLogout(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                session.setPreferences(MenuActivity.this,Constants.LAST_LOGIN_DATE,"2019/02/24");
                session.setPreferences(MenuActivity.this,Constants.USER_ID,"");
                session.setPreferences(MenuActivity.this,Constants.USER_NAME,"");
                startActivity(new Intent(MenuActivity.this,SplashScreen.class));
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setMessage("Are you sure want to logout?");
        dialog.setTitle("Logout");
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
