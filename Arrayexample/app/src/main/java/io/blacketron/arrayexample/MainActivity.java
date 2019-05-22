package io.blacketron.arrayexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.transition.Fade.IN;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int [] ourArray = new int [5];

        ourArray[0] = 25;
        ourArray[1] = 50;
        ourArray[2] = 125;
        ourArray[3] = 68;
        ourArray[4] = 47;

        Log.i("Info", "[0] = " + ourArray[0]);
        Log.i("Info", "[1] = " + ourArray[1]);
        Log.i("Info", "[2] = " + ourArray[2]);
        Log.i("Info", "[3] = " + ourArray[3]);
        Log.i("Info", "[4] = " + ourArray[4]);

        int answer = ourArray[0] + ourArray[1] + ourArray[2] + ourArray[3] + ourArray[4];

        Log.i("Info", "Answer is " + answer);
    }
}
