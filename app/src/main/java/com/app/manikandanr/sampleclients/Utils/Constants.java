package com.app.manikandanr.sampleclients.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class Constants {
    public static final String BASE_URL = "http://api.spark-invention.com/";
    public static final String IMAGE_BASE_URL = "http://api.spark-invention.com/students/";
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
    public static final String LAST_LOGIN_DATE = "lastLoginDate";
    public static final String LOGIN_STATUS = "login_sts";
    public static final String LOGIN = "in";
    public static final String APP_NAME = "spark-invention";

    public static final String PAYMENT_CASH = "1";
    public static final String PAYMENT_Bank = "2";

    public static final String ACCOUNT_INCOME = "1";
    public static final String ACCOUNT_EXPENSE = "2";


    public static final String SCHOOL_5TO9 = "1";
    public static final String SCHOOL_10TO11 = "2";

    public static final String COLLEGE_1YEAR = "1";
    public static final String COLLEGE_2YEAR = "2";
    public static final String COLLEGE_3YEAR = "3";
    public static final String COLLEGE_4YEAR = "4";

    public static  final String RESPONSE_SUCCESS ="1";
    public static  final String RESPONSE_FAILED = "0";
    public static final String STUDENT_BASIC_INFO = "basicInfo";
    public static final String STUDENT_OTHER_INFO = "otherInfo";
    public static final String STUDENT_PAYMENT_STATUS = "paymentSts";
    public static final String STUDENT_ALL_INFO = "allInfo";


    public static final String FULLCASH = "1";
    public static final String EMI = "2";
    public static final String ONLINE_PAYMENT = "3";
    public static final String STUDENT_ID = "stud_id";
    public static final String STUDENT_SERIAL_NUMBER = "reg_num";

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static  final String USER_MAIL = "user_mail";
    public static  final  String USER_PHONE = "user_phone";
    public  static  final  String USER_ROLE_LOGIN = "user_role_login";
    public static  final  String USER_BRANCH_ID = "branch_id";
    public static final String STAFF_ROLE = "4";

    public static final String ATTENDANCE_PRESENT = "1";
    public static final String ATTENDANCE_ABSENT = "0";

    public static final String STATUS_ADMISSION = "1";
    public static final String STATUS_FEEDBACK = "2";
    public static final String STATUS_CANCEL = "3";
    public static final String PAGE_FROM_GROUP = "group";


    public static boolean isNetworkAvailable(Context cntx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) cntx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static ArrayList<String> getSections()
    {
        ArrayList<String> str = new ArrayList<String>();
        str.add("Select Section");
        str.add("A");
        str.add("B");
        str.add("C");
        str.add("D");
        str.add("E");
        str.add("F");
        str.add("G");
        str.add("H");
        str.add("I");
        str.add("J");
        str.add("K");
        return str;
    }
    public static ArrayList<String> getYears()
    {
        ArrayList<String> str = new ArrayList<String>();
        str.add("Select Year");
        str.add("1 Year");
        str.add("2 Year");
        str.add("3 Year");
        str.add("4 Year");
        str.add("5 Year");
        return str;
    }



}
