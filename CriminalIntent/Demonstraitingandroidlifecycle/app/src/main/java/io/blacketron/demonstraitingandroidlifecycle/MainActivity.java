package io.blacketron.demonstraitingandroidlifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "In onCreate" ,Toast.LENGTH_SHORT).show();
        Log.i("Info", "In onCreate");

    }

    @Override
    protected void onStart(){
        super.onStart();

        Toast.makeText(this, "In onStart" ,Toast.LENGTH_SHORT).show();
        Log.i("Info", "In onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();

        Toast.makeText(this, "In onResume" ,Toast.LENGTH_SHORT).show();
        Log.i("Info", "In onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();

        Toast.makeText(this, "In onPause" ,Toast.LENGTH_SHORT).show();
        Log.i("Info", "In onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();

        Toast.makeText(this, "In onStop" ,Toast.LENGTH_SHORT).show();
        Log.i("Info", "In onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Toast.makeText(this, "In onDestroy" ,Toast.LENGTH_SHORT).show();
        Log.i("Info", "In onDestroy");
    }
}
