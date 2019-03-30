package io.blacketron.adressbook;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PortraitDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portraite_layout);

        //Create new FragmentManager.
        FragmentManager fragmentManager = getFragmentManager();

        //Creating new fragment and then pass in the id of the layout that holds it.
        Fragment frag = fragmentManager.findFragmentById(R.id.detailFragmentHolder);

        //Pass the bundle onto the fragment.
        int position = 0;
        Bundle extra = getIntent().getExtras();

        if(extra != null){

            position = extra.getInt("Position");
        }

        //Check if the Fragment has not already been initialized.
        if (frag == null){

            frag = AddressDetailFragment.newInstance(position);

            fragmentManager.beginTransaction().add(R.id.detailFragmentHolder, frag).commit();
        }
    }
}
