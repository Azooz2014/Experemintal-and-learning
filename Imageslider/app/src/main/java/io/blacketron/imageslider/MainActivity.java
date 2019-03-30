package io.blacketron.imageslider;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    PagerAdapter mPagerAdapter;
    int[] mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference the images and put them in out array.
        mImages = new int[] {R.drawable.adventure_time, R.drawable.breaking_bad, R.drawable.cat_alien, R.drawable.color_desert,
        R.drawable.do_more, R.drawable.minimal, R.drawable.owl, R.drawable.you_here};

        //Get a reference of the ViewPager in the layout.
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        //Initialize our PagerAdapter.
        mPagerAdapter = new ImagePagerAdapter(MainActivity.this, mImages);

        //Bind the PagerAdapter to the ViewPager.
        mViewPager.setAdapter(mPagerAdapter);
    }
}
