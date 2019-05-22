package io.blacketron.vectordrawablesdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.karim.MaterialTabs;

public class MainActivity extends AppCompatActivity {

    private SampleAdapter mAdapter;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mPager = findViewById(R.id.pager);
        mAdapter = new SampleAdapter(this, getFragmentManager());
        mPager.setAdapter(mAdapter);

        MaterialTabs tabs = findViewById(R.id.tabs);
        tabs.setViewPager(mPager);
    }
}
