package io.blacketron.fragmentslider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SimpleFragmentPagerAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize a list of 3 fragments.
        List<android.support.v4.app.Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>();

        //Add 3 new fragments to the list.
        fragmentList.add(SimpleFragment.newInstance("1"));
        fragmentList.add(SimpleFragment.newInstance("2"));
        fragmentList.add(SimpleFragment.newInstance("3"));

        pageAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(pageAdapter);
    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        //A list to hold our fragments.
        private List<android.support.v4.app.Fragment> fragments;

        //A constructor to receive a FragmentManager and a list.
        public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {

            //Call the super version of this constructor.
            super(fm);

            this.fragments = fragments;
        }

        /*Just 2 overridden methods to get the current position of the adapter and the size
        of the list.*/
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}