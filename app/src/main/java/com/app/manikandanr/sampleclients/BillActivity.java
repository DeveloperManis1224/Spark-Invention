package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

public class BillActivity extends AppCompatActivity {

    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        btnSubmit = findViewById(R.id.btn_bill);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /// Toast.makeText(BillActivity.this, "Admission Successfully", Toast.LENGTH_SHORT).show();
                new AwesomeSuccessDialog(BillActivity.this)
                        .setTitle("Admission Status")
                        .setMessage("Admission Successfully!")
                        .setColoredCircle(R.color.colorPrimary)
                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                        .setCancelable(true)
                        .setPositiveButtonText("Ok")
                        .setPositiveButtonbackgroundColor(R.color.colorPrimary)
                        .setPositiveButtonTextColor(R.color.white)
                        .setPositiveButtonClick(new Closure() {
                            @Override
                            public void exec() {

                            }
                        })
                        .show();
            }
        });
    }

}
