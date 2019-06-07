package io.blacketron.criminalintent.Controllers;

import android.support.v4.app.Fragment;

import io.blacketron.criminalintent.Utilites.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
