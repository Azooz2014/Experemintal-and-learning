package io.blacketron.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SamplePagerAdapter extends FragmentPagerAdapter {
    public SamplePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return EditorFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }
}
