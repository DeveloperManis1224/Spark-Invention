package com.app.manikandanr.sampleclients.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {
    public static final String APP_NAME = "Spark-Invention";
    public static final String BASE_URL = "http://api.spark-invention.com/";

    //variable keys
    public static final String USER_ROLE = "role";
    public static final String USER_ROLE_ID= "role_id";
    public static final String USER_TYPE_SCHOOL = "school";
    public static final String USER_TYPE_COLLEGE = "college";
    public static final String USER_TYPE_PROJECT = "project";

    public static final String ROLE_SCHOOL = "1";
    public static final String ROLE_COLLEGE = "2";
    public static final String ROLE_PROJECT = "3";

    public static final String OFFER_NOT_AVAILABLE = "0.00";

    public static final String OFFER_PERCENTAGE = "1";
    public static final String OFFER_RUPEES = "2";

    public static final String LOGIN_STATUS = "login_status";
    public static final String LOGIN = "in";
    public static final String LOGOUT = "out";

    public static final String LAST_LOGIN_DATE = "last_login_date";



    public static boolean isNetworkAvailable(Context cntx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) cntx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
