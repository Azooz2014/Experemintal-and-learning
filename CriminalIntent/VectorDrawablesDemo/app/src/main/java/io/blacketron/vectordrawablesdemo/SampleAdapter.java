package io.blacketron.vectordrawablesdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

public class SampleAdapter extends FragmentPagerAdapter {

    private final Context ctxt;

    public SampleAdapter(Context ctxt, FragmentManager mgr){

        super(mgr);

        this.ctxt = ctxt;
    }

    @Override
    public int getCount() {
        return (2);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0){

            return (new VectorFragment());
        }

        return (new VectorCompatFragment());
    }

    @Override
    public String getPageTitle(int position) {

        if (position == 0){

            return (ctxt.getString(R.string.tab_native));
        }

        return (ctxt.getString(R.string.tab_compat));
    }
}
