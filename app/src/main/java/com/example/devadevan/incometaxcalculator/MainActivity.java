package com.example.devadevan.incometaxcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCalculate;
    EditText editNetIncome;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        editNetIncome = (EditText) findViewById(R.id.editNetIncome);
        txtResult = (TextView) findViewById(R.id.txtTax);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int income, tax=0, cess=0, rebate=0;
                String s = editNetIncome.getText().toString();
                if(s.length()>0)
                {
                    income=Integer.parseInt(s);
                    if (income > 1000000)
                        tax = 125000 + (int) ((income - 1000000) * 0.3);
                    else if (income > 500000)
                        tax = 25000 + (int) ((income - 500000) * 0.2);
                    else if (income > 250000) {
                        tax = (int) ((income - 250000) * 0.1);
                        rebate = (tax < 5000) ? tax : 5000;
                    }
                    tax = tax - rebate;
                    if (tax > 0) {
                        cess = (int) (tax * 0.03);
                        tax = tax + cess;
                    }
                    if (tax == 0 && rebate > 0)
                        txtResult.setText("Your income tax of Rs." + rebate + " is exempted U/s 87A");
                    else if (tax == 0)
                        txtResult.setText("Your income is within non-taxable limit");
                    else
                        txtResult.setText("Income tax payable is \nRs." + tax);
                }
                else {
                    Toast.makeText(getBaseContext(), "Enter your Net Taxable Income", Toast.LENGTH_LONG).show();
                    txtResult.setText("");
                }
            }
        });
     }
}
