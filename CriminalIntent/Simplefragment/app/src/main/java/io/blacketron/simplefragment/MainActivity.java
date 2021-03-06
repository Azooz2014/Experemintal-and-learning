package io.blacketron.simplefragment;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.app.FragmentManager fragmentManager = getFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentHolder);

        if (fragment == null){

            fragment = new SimpleFragment();

            fragmentManager.beginTransaction().add(R.id.fragmentHolder, fragment).commit();
        }
    }
}
