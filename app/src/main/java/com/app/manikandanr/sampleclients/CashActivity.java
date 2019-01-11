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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.manikandanr.sampleclients.Utils.Constants;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CashActivity extends AppCompatActivity {

    private Button bSubmit;
    RadioButton rb;
    RadioGroup rdoGrp ;
    private RadioButton rCash,rEmi;
    private LinearLayout lytCash,lytEmi,lytEmiPlans;
    private String txtId;
    private double txtCost;
    private Spinner emiPlans;
    private TextView txt_Cost;
    String radioStatus;
    private EditText eCaltxt;
    final ArrayList<String> emiList = new ArrayList<String>();
    private String emiSelection;
    int tenureAmount = 0;
    TextView monthEmi3,monthEmi6,monthEmi9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        bSubmit = findViewById(R.id.btn_final_submit);
        rCash = findViewById(R.id.radio_cash);
        rdoGrp = findViewById(R.id.rdo_payment);
        rEmi = findViewById(R.id.radio_emi);
        lytEmiPlans = findViewById(R.id.div_emi);
        lytCash = findViewById(R.id.div_fullcash);
        lytEmi = findViewById(R.id.div_calculate);
        txt_Cost = findViewById(R.id.txt_cost);
        monthEmi3 = findViewById(R.id.txt_month3);
        monthEmi6 = findViewById(R.id.txt_month6);
        monthEmi9 = findViewById(R.id.txt_month9);
        emiPlans = findViewById(R.id.spi_plans);
        eCaltxt = findViewById(R.id.edt_cal);
        txtCost = Double.parseDouble(getIntent().getStringExtra("cost"));
        txtId = getIntent().getStringExtra("stud_id");

        radioStatus = "FULL CASH";

        DecimalFormat df = new DecimalFormat("####0.00");
        //System.out.println("Value: " + df.format(value));
        txt_Cost.setText("Rs " + df.format(txtCost));

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioStatus.equalsIgnoreCase("FULL CASH")) {
                    Intent in = new Intent(CashActivity.this, BillActivity.class);
                    in.putExtra("stud_id",""+txtId);
                    in.putExtra("payment_mode",Constants.FULLCASH);
                    in.putExtra("initial_amount","0");
                    in.putExtra("total_amount",""+txtCost);
                    in.putExtra("tenure",""+"0");
                    in.putExtra("payment_status","2");
                    in.putExtra("tenure_amount","0");
                    in.putExtra("balance_amount","0");
                    startActivity(in);
                } else if (radioStatus.equalsIgnoreCase("EMI")) {
                    if(!eCaltxt.getText().toString().trim().isEmpty()&&!emiPlans.getSelectedItem().toString().isEmpty()&&
                            !emiPlans.getSelectedItem().toString().equalsIgnoreCase("Select One")) {
                        Intent in = new Intent(CashActivity.this, BillActivity.class);
                        in.putExtra("stud_id", "" + txtId);
                        in.putExtra("payment_mode", Constants.EMI);
                        in.putExtra("initial_amount", "" + eCaltxt.getText().toString());
                        in.putExtra("total_amount", "" + txtCost);
                        in.putExtra("tenure", "" + emiSelection);
                        in.putExtra("payment_status", "3");
                        in.putExtra("tenure_amount", "" + tenureAmount);
                        in.putExtra("balance_amount", "" + getBalanceAmount());
                        startActivity(in);
                    }
                    else
                    {
                        eCaltxt.setError("Enter Initial Amount");
                    }
                }
            }
        });
        rdoGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = (RadioButton) findViewById(checkedId);
                if (rb.getText().toString().equalsIgnoreCase("FULL CASH")) {
                    lytCash.setVisibility(View.GONE);
                    lytEmi.setVisibility(View.GONE);
                    lytEmiPlans.setVisibility(View.GONE);
                    radioStatus = "FULL CASH";
                    bSubmit.setVisibility(View.VISIBLE);
                    bSubmit.setText("Amount Paid and Submit");

                } else if (rb.getText().toString().equalsIgnoreCase("EMI")) {
                    lytCash.setVisibility(View.GONE);
                    lytEmiPlans.setVisibility(View.GONE);
                    lytEmi.setVisibility(View.VISIBLE);
                    radioStatus = "EMI";
                    bSubmit.setVisibility(View.GONE);
                    bSubmit.setText("Submit");
                }
            }
        });

    }
    //setCalulation();
    public void onCalculateEmi(View v)
    {
        emiList.clear();
        bSubmit.setVisibility(View.VISIBLE);
        double total_amount = (int) txtCost;
        double balanceAmount = total_amount - Integer.parseInt(eCaltxt.getText().toString());

        final int month3= (int) Math.round(balanceAmount)/3;
        final int month6 = (int) Math.round(balanceAmount)/6;
        final int month9 = (int) Math.round(balanceAmount)/9;

        monthEmi3 .setText("1. 3 months * Rs "+month3);
        monthEmi6.setText("2. 6 months * Rs "+month6);
        monthEmi9.setText("3. 9 months * Rs "+month9);

        emiList.add("Select One");
        emiList.add("3 months * Rs "+month3);
        emiList.add("6 months * Rs "+month6);
        emiList.add("9 months * Rs "+month9);

        lytEmiPlans.setVisibility(View.VISIBLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (CashActivity.this, android.R.layout.simple_spinner_dropdown_item,emiList);
        emiPlans.setAdapter(adapter);
        emiPlans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = i ;
                if(pos == 1)
                {
                    Toast.makeText(CashActivity.this, "3 Months"+month3, Toast.LENGTH_SHORT).show();
                    emiSelection = ""+pos;
                    tenureAmount = month3;
                }
                else if(pos == 2)
                {
                    Toast.makeText(CashActivity.this, "6 Months"+month6, Toast.LENGTH_SHORT).show();
                    emiSelection = ""+pos;
                    tenureAmount = month6;
                }
                else if (pos == 3)
                {
                    Toast.makeText(CashActivity.this, "9 Months"+month9, Toast.LENGTH_SHORT).show();
                    emiSelection = ""+pos;
                    tenureAmount = month9;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
    }

    private int getBalanceAmount()
    {
        int balance_amouunt = 0;
        int total = (int) txtCost;
        int tenure_amount = Integer.parseInt(eCaltxt.getText().toString());
        balance_amouunt = total - tenure_amount;
        return  balance_amouunt;
    }


}
