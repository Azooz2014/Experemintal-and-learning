package io.blacketron.exploringmethodoverloading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String aString = "I'm a string";
        int anInt = 10;

        printStuff(anInt);
        printStuff(aString);
        printStuff(anInt, aString);
    }

    void printStuff(int myInt){

        Log.i("Info", "this is the int only version");
        Log.i("Info", "myInt = " + myInt);
    }

    void printStuff(String myString){

        Log.i("Info", "this is the string only version");
        Log.i("Info", "myString = " + myString);
    }

    void printStuff(int myInt, String myString){

        Log.i("Info", "this is the combined int and string version");
        Log.i("Info", "myInt =" + myInt);
        Log.i("Info", "myString = " + myString);
    }
}
