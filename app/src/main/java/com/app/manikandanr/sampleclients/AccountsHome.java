package com.app.manikandanr.sampleclients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountsHome extends AppCompatActivity {

    private EditText amountIncome, amountExpense, descIncome, descExpense, incomeName, expenseName;
    private Spinner mIncomeCategory, mExpenseCategory;
    private LinearLayout lytIncome, lytExpense ;

    ArrayList<String> incomeList = new ArrayList<>();
    ArrayList<String> expenseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_home);
        init();

    }

    private void init() {
        amountIncome = findViewById(R.id.income_amount);
        amountExpense = findViewById(R.id.expense_amount);
        descIncome = findViewById(R.id.income_desc);
        descExpense = findViewById(R.id.expense_desc);
        mIncomeCategory = findViewById(R.id.income_category);
        mExpenseCategory = findViewById(R.id.expense_category);
        incomeName = findViewById(R.id.income_name);
        expenseName = findViewById(R.id.expense_name);
        lytIncome = findViewById(R.id.income_lyt);
        lytExpense = findViewById(R.id.expense_lyt);

        incomeList.add("Part-time work");
        incomeList.add("Personal Savings");
        incomeList.add("Rents and Royalties");
        incomeList.add("Salary");

        expenseList.add("Automobile");
        expenseList.add("Entertainment");
        expenseList.add("Family");
        expenseList.add("Food & Drinks");
        expenseList.add("Gasoline");
        expenseList.add("Gift & Donations");
        expenseList.add("Groceries");
        expenseList.add("Health & Fitness");
        expenseList.add("Housing");
        expenseList.add("Medical");



        if(getIntent().getExtras().getString("account").equalsIgnoreCase("income"))
        {
            lytIncome.setVisibility(View.VISIBLE);
            ArrayAdapter incomeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,incomeList);
            mIncomeCategory.setAdapter(incomeAdapter);

        }
        else if(getIntent().getExtras().getString("account").equalsIgnoreCase("expense"))
        {
            lytExpense.setVisibility(View.VISIBLE);
            ArrayAdapter expenseAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,expenseList);
            mExpenseCategory.setAdapter(expenseAdapter);
        }
    }

    public void onClickIncome(View view) {

        if(incomeValidation())
        {
            addIncome();
        }
    }

    private boolean incomeValidation()
    {
        boolean val = true;
        if(amountIncome.getText().toString().isEmpty())
        {
            val = false;
            amountIncome.setError("Invalid");
        }
        if(incomeName.getText().toString().isEmpty())
        {
            val = false;
            incomeName.setError("Invalid");
        }
        if(descIncome.getText().toString().isEmpty())
        {
            val = false;
            descIncome.setError("Invalid");
        }
        return val;
    }

    private boolean expenseValidation()
    {
        boolean val = true;
        if(amountExpense.getText().toString().isEmpty())
        {
            val = false;
            amountExpense.setError("Invalid");
        }
        if(expenseName.getText().toString().isEmpty())
        {
            val = false;
            expenseName.setError("Invalid");
        }
        if(descExpense.getText().toString().isEmpty())
        {
            val = false;
            descExpense.setError("Invalid");
        }
        return val;
    }
    public void onCLickExpense(View view) {

        if(expenseValidation())
        {
            addExpense();
        }
    }

    private void addIncome() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/add-income";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);

                            String status = jobj.getString("status");
                            String msg = jobj.getString("message");
                            if(status.equalsIgnoreCase("1"))
                            {

                            }
                            else
                            {
                                Toast.makeText(AccountsHome.this, ""+msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AccountsHome.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_name","");
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void addExpense() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/add-expense";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);

                            String status = jobj.getString("status");
                            String msg = jobj.getString("message");
                            if(status.equalsIgnoreCase("1"))
                            {

                            }
                            else
                            {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AccountsHome.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country_name","");
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


}
