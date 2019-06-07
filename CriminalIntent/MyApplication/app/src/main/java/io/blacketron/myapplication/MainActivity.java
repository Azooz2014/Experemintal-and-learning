package io.blacketron.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Can you see me?!" , Toast.LENGTH_LONG).show();

        Log.i("Info", "Done creating the app!");
    }

    public void topClick (View v){

        Toast.makeText(this, "Top button is clicked!", Toast.LENGTH_SHORT).show();
        Log.i("Info", "topClick is working!");
    }

    public void bottomClick (View v){

        Toast.makeText(this, "Bottom button is clicked!", Toast.LENGTH_SHORT).show();
        Log.i("Info", "bottomClick is working!");
    }
}
