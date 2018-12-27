package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordTxt;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordTxt = findViewById(R.id.edt_password);
        loginBtn = findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!passwordTxt.getText().toString().trim().isEmpty())
                {
                    if(passwordTxt.getText().toString().equalsIgnoreCase("1234"))
                    {
                        Toast.makeText(PasswordActivity.this, "Login Successfull.", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(PasswordActivity.this,MenuActivity.class);
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(PasswordActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    passwordTxt.setError("Invalid Password");
                }
            }
        });

    }
}
