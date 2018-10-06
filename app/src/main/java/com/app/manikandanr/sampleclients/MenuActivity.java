package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private void init()
    {

        new AwesomeInfoDialog(this)
                .setTitle(R.string.app_name)
                .setMessage(R.string.app_name)
                .setColoredCircle(R.color.colorPrimary)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setCancelable(true)
                .show();

        bAdmission  = findViewById(R.id.btn_admission);
        bMarketing = findViewById(R.id.btn_marketing);
        bAttendance = findViewById(R.id.btn_attendance);
        bRevenue = findViewById(R.id.btn_revenue);
        bStore = findViewById(R.id.btn_store);
        bAdmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MenuActivity.this,AdmissionForm.class);
                startActivity(in);
            }
        });

        bMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in = new Intent(MenuActivity.this,AdmissionForm.class);
//                startActivity(in);
                Toast.makeText(MenuActivity.this,"Coming Soon...",Toast.LENGTH_LONG
                ).show();
            }
        });
        bAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in = new Intent(MenuActivity.this,AdmissionForm.class);
//                startActivity(in);
                Toast.makeText(MenuActivity.this,"Coming Soon...",Toast.LENGTH_LONG
                ).show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        finish();
    }
}
