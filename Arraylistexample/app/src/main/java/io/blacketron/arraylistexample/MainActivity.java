package io.blacketron.arraylistexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> myList = new ArrayList<>();

        myList.add("Donald Knuth");
        myList.add("Ramsus Lerdorf");
        myList.add(1, "James Gosling");

        int numItems = myList.size();

        if(myList.isEmpty()){

            Log.w("Warning", "Array is empty!");
        }
        else{
            Log.i("Info", "Array list size is " + numItems);
        }

        Log.i("Info", "Location of James Gosling is " + myList.indexOf("James Gosling"));
    }
}
