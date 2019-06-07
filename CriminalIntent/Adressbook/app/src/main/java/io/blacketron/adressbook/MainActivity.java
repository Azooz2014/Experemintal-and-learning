package io.blacketron.adressbook;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ActivityComs {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dualfragment);

        getFragmentManager().beginTransaction()
                .add(R.id.listFragmentHolder, new AddressListFragment())
                .commit();
    }

    @Override
    public void onListItemSelected(int position) {

        /*
        * Is there a layout with an ID that matches the
        * detail container?
        * Crates new activity*/

        if(findViewById(R.id.detailFragmentHolder) == null){

            //If not we need to start new activity.

            Intent intent = new Intent(this, PortraitDetailActivity.class);

            /*We can't pass objects in an Intent neither do we want to,
            * so we pass it's location in the array list.*/

            intent.putExtra("Position", position);

            startActivity(intent);
        }else{

            //Fragment already exists.

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment fragOld = fragmentManager.findFragmentById(R.id.detailFragmentHolder);
            Fragment fragNew = AddressDetailFragment.newInstance(position);

            if(fragOld != null){

                fragmentTransaction.remove(fragOld);
                fragmentTransaction.add(R.id.detailFragmentHolder, fragNew, "addressDetailFragment");
                fragmentTransaction.commit();
            }
        }
    }
}
