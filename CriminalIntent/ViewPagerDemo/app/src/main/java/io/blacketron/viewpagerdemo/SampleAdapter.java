package io.blacketron.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleAdapter extends FragmentPagerAdapter {

    public SampleAdapter (FragmentManager mgr){

        super(mgr);
    }

    @Override
    public int getCount() {

        return (10);
    }

    @Override
    public Fragment getItem(int position) {

        return (EditorFragment.newInstance(position));
    }
}
