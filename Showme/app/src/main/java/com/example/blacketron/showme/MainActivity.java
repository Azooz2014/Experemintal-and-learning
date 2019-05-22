package com.example.blacketron.showme;

import android.content.Context;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Handler;


public class MainActivity extends AppCompatActivity {

    int total = 0;
    float ftotal = 0f;
    int rn1 = 0;
    int op = 0;
    int rn2 = 0;

    String opType = null;

   public SecureRandom secureRandom = new SecureRandom();

    /* Method of determining the question */
    public void decision (){

        rn1 = secureRandom.nextInt(11);
        op = secureRandom.nextInt(4);
        rn2 = secureRandom.nextInt(11);

        /*Determining the operator.*/
        switch (op) {

            case 0:
                opType = "+";
                total = rn1 + rn2;
                break;

            case 1:
                opType = "-";
                total = rn1 - rn2;
                break;

            case 2:
                opType = "*";
                total = rn1 * rn2;
                break;

            case 3:
                opType = "÷";
                ftotal = (float)rn1 / (float)rn2;
                break;

            default:
                opType = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText input = (EditText) findViewById(R.id.mathA);
        final ImageView img = (ImageView)findViewById(R.id.imageView);
        final Button btnOn = (Button)findViewById(R.id.on);
        final Button btnOff = (Button)findViewById(R.id.off);
        final TextView quest = (TextView) findViewById(R.id.mathQ);
        final android.os.Handler handler = new android.os.Handler();

            /* Calling the decision() function*/
            decision();

            quest.setText("" + rn1 + opType + rn2);


        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*Checks wither the input is equal to the total by transforming EditText to String
                then checks is equals to */
                if (input.getText().toString().equals("" + total) || input.getText().toString().equals("" + ftotal)) {
                    /* replaces the current image view resource with another one.*/
                    img.setImageResource(R.drawable.lightbulbon);

                    handler.postDelayed(new Runnable() {


                        @Override
                        public void run() {

                            decision();

                            quest.setText("" + rn1 + opType + rn2);

                            img.setImageResource(R.drawable.lightbulboff);
                        }
                    }, 1000);
                    btnOn.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    ftotal = 0f;
                }

                /*Displays small alert dialog called "Toast" if the editText field was empty.*/
                else if (input.getText().toString().equals("")) {

                    Context context = getApplicationContext();
                    String errorText = "Enter an answer, please";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, errorText, duration);
                    toast.show();

                }

                /*Displays small alert dialog called "Toast" if the answer is wrong.*/
                else {

                    Context context = getApplicationContext();
                    String errorText = "Wrong answer, Try again!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, errorText, duration);
                    toast.show();
                }
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                img.setImageResource(R.drawable.lightbulboff);
                btnOff.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });
    }
}

//TODO: Find a way to make the float total shows the first 3 digits.
//TODO: Replace button off with useful thing.

