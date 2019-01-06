package com.app.manikandanr.sampleclients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountsHome extends AppCompatActivity implements  View.OnClickListener {

    private Spinner mSPinnerCategory;
    private RadioButton mRadioIncome, mRadioExpense, mRadioAdvance, mRadioFull,
            mRadioPart, mRadioOthers, mCash, mBank;
    private EditText mName, mPhone, mAmount, mReferance;

    String AccountType = "";
    String PaymentMode = "";
    String PaymentType = "";

    int categoryPos = 0;

    Button btnSave;

    ArrayList<String> incomeList = new ArrayList<>();
    ArrayList<String> expenseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_home);
        init();

    }


    private void init() {
        btnSave = findViewById(R.id.btnSave);

        mSPinnerCategory = findViewById(R.id.category);
        mRadioIncome = findViewById(R.id.radio_income);
        mRadioExpense = findViewById(R.id.radio_expense);
        mRadioAdvance = findViewById(R.id.radio_advance);
        mRadioFull = findViewById(R.id.radio_full);
        mRadioPart = findViewById(R.id.radio_part);
        mRadioOthers = findViewById(R.id.radio_others);
        mCash = findViewById(R.id.radio_cash);
        mBank = findViewById(R.id.radio_bank);

        mBank.setOnClickListener(this);
        mCash.setOnClickListener(this);
        mRadioOthers.setOnClickListener(this);
        mRadioPart.setOnClickListener(this);
        mRadioFull.setOnClickListener(this);
        mRadioAdvance.setOnClickListener(this);


        mName = findViewById(R.id.acc_txt_name);
        mPhone = findViewById(R.id.acc_txt_phone);
        mAmount = findViewById(R.id.acc_txt_amount);
        mReferance = findViewById(R.id.acc_txt_referance);


        incomeList.add("Select Income");
        incomeList.add("Part-time work");
        incomeList.add("Personal Savings");
        incomeList.add("Rents and Royalties");
        incomeList.add("Salary");

        expenseList.add("Select Expense");
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


        AccountType = Constants.ACCOUNT_INCOME;
        PaymentMode = Constants.PAYMENT_CASH;

        mSPinnerCategory.setAdapter(new ArrayAdapter<String>(AccountsHome.this,
                android.R.layout.simple_spinner_dropdown_item, incomeList));

      mRadioIncome.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              mSPinnerCategory.setAdapter(new ArrayAdapter<String>(AccountsHome.this,
                      android.R.layout.simple_spinner_dropdown_item, incomeList));
              AccountType = Constants.ACCOUNT_INCOME;
          }
      });


      btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Log.e("RESULT123","//"+PaymentMode+""+PaymentType+"");
              if(isValid())
              {
                  insertData();
              }
          }
      });


      if(mRadioAdvance.isChecked())
      {
          PaymentType = mRadioAdvance.getText().toString().trim();
      }





      mRadioExpense.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              mSPinnerCategory.setAdapter(new ArrayAdapter<String>(AccountsHome.this,
                      android.R.layout.simple_spinner_dropdown_item, expenseList));
              AccountType = Constants.ACCOUNT_EXPENSE;
          }
      });

      mSPinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              if(!mSPinnerCategory.getSelectedItem().toString().equalsIgnoreCase("Select Income") ||
                      !mSPinnerCategory.getSelectedItem().toString().equalsIgnoreCase("Select Expense"))
              {
categoryPos = i;
              }
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {
          }
      });
    }


    private void insertData() {

        Log.e("RESULT123","//"+PaymentMode+""+PaymentType+"");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL+"api/accounts";
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
                                new AwesomeSuccessDialog(AccountsHome.this)
                                        .setTitle("Successful")
                                        .setMessage(""+msg)
                                        .setColoredCircle(R.color.colorPrimary)
                                        .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                        .setCancelable(true)
                                        .setPositiveButtonText("Close")
                                        .setPositiveButtonbackgroundColor(R.color.colorPrimary)
                                        .setPositiveButtonTextColor(R.color.white)
                                        .setPositiveButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                Intent in = new Intent(AccountsHome.this,MenuActivity.class);
                                                startActivity(in);
                                                finish();
                                            }
                                        })
                                        .show();

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
                params.put("account_type",AccountType);
                params.put("name",mName.getText().toString().trim());
                params.put("phone",mPhone.getText().toString().trim());
                params.put("amount",mAmount.getText().toString().trim());
                params.put("referance",mReferance.getText().toString().trim());
                params.put("payment_mode",PaymentMode);
                params.put("payment_type",PaymentType);
                params.put("category_id",""+categoryPos);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }



    private boolean isValid()
    {
        boolean val = true;
        if(AccountType.isEmpty() || AccountType =="")
        {
            Toast.makeText(this, "Invalid Account Type", Toast.LENGTH_SHORT).show();
            val = false;
        }
        if(mName.getText().toString().isEmpty())
        {
            mName.setError("Invalid Name");
            val = false;
        }
        if(mPhone.getText().toString().isEmpty())
        {
            mPhone.setError("Invalid Phone");
            val = false;
        }
        if(mAmount.getText().toString().isEmpty())
        {
            mAmount.setError("Invalid Amount");
            val = false;
        }
        if(mReferance.getText().toString().isEmpty())
        {
            mReferance.setError("Invalid Reference & Description");
            val = false;
        }
        if(PaymentType.isEmpty() || PaymentType =="")
        {
            Toast.makeText(this, "Invalid Payment Type", Toast.LENGTH_SHORT).show();
            val = false;
        }
        if(PaymentMode.isEmpty() || PaymentMode =="")
        {
            Toast.makeText(this, "Invalid Payment Mode", Toast.LENGTH_SHORT).show();
            val = false;
        }
        if(mSPinnerCategory.getSelectedItem().toString().trim().equalsIgnoreCase("Select Income") ||
                mSPinnerCategory.getSelectedItem().toString().equalsIgnoreCase("Select Expense"))
        {
            Toast.makeText(this, "Invalid Category", Toast.LENGTH_SHORT).show();
            val = false;
        }
        return val;
    }



    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.radio_cash)
        {
            PaymentMode = Constants.PAYMENT_CASH;
        }
        else if(view.getId() == R.id.radio_bank)
        {
            PaymentMode = Constants.PAYMENT_Bank;
        }

        if(view.getId() == R.id.radio_advance)
        {
                PaymentType = mRadioAdvance.getText().toString().trim();
        }
        else
            if(view.getId() == R.id.radio_full)
            {
                PaymentType = mRadioFull.getText().toString();
            }

            else
                if(view.getId() == R.id.radio_part)
                {
                    PaymentType = mRadioPart.getText().toString();
                }
                else
                    if(view.getId() == R.id.radio_others)
                    {
                        PaymentType = mRadioOthers.getText().toString();
                    }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(AccountsHome.this,MenuActivity.class);
        startActivity(in);
        finish();
    }
}
