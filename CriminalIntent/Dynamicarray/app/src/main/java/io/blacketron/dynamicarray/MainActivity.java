package io.blacketron.dynamicarray;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] ourArray = new int[1000];

        for (int i = 0; i < 1000; i++){

            ourArray[i] = i * 5;

            Log.i("Info", "i = " + i);
            Log.i("Info", "ourArray[i] = " + ourArray[i]);
        }
    }
}
