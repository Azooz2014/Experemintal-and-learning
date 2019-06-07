/**
 * Created by Azooz
 * 25/4/2017
 * **/

package com.example.blacketron.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override

    /*Called when the activity is starting. This is where most initialization should go:
    calling setContentView(int),
    to inflate the activity's UI, using findViewById(int),
    to programmatically interact with widgets in the UI*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Initializing edit text views*/
        final EditText mealP = (EditText) findViewById(R.id.mealPrice_id);

        final EditText taxP = (EditText) findViewById(R.id.tax_id);

        final EditText tipP = (EditText) findViewById(R.id.tip_id);

        final TextView totalp = (TextView) findViewById(R.id.total_id);

        /*Initializing the calculate button*/
        Button calB = (Button) findViewById(R.id.cal_id);

        /*Creating onClick listener for the calculate button*/
        calB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Creating new float variables , to read from the EditText view
                * because EditText view stores the variable as a string
                * to apply the arithmetic operation I needed to convert
                * the strings into float by calling Float.parseFloat method.*/
                float meal = Float.parseFloat(mealP.getText().toString());
                float tax = Float.parseFloat(taxP.getText().toString());
                float tip = Float.parseFloat(tipP.getText().toString());
                /*Creating new float variable to store the result in it*/
                float total;

                meal = meal + meal * tax;
                total = meal + meal * tip;

                /*Setting the text of the total TextView equal to the total*/
                totalp.setText("" + total);



            }
        });

        SpinnerCore();
    }

    private void SpinnerCore() {

        Spinner sp = (Spinner) findViewById(R.id.moneySymbolList_id);

        List<String> currencySymbols = new ArrayList<String>();

        currencySymbols.add("درهم");
        currencySymbols.add("﷼");
        currencySymbols.add("₹");
        currencySymbols.add("$");
        currencySymbols.add("€");
        currencySymbols.add("£");
        currencySymbols.add("¥");

        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(this, R.layout.spinner_style, currencySymbols);
        sp.setAdapter(sAdapter);

    }

}
