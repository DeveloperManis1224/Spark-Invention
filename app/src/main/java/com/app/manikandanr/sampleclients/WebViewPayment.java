package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewPayment extends AppCompatActivity {

    private WebView paymentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_payment);

        paymentView = findViewById(R.id.web_payment);
        WebSettings webSettings = paymentView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        paymentView.setWebViewClient(new WebViewClient());
        paymentView.loadUrl("https://www.instamojo.com/@SPARK_ITI");

    }
}
