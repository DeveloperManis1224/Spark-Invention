package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.app.manikandanr.sampleclients.Utils.Constants;
import com.app.manikandanr.sampleclients.Utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SplashScreen extends AppCompatActivity {

    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        session = new SessionManager();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String strDate = sdf.format(c.getTime());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(session.getPreferences(SplashScreen.this, Constants.LAST_LOGIN_DATE).equalsIgnoreCase(strDate))
                {
                    startActivity(new Intent(SplashScreen.this, MenuActivity.class));
                }
                else
                {
                    startActivity(new Intent(SplashScreen.this, PasswordActivity.class));
                }

            }
        },2000);
    }
}
