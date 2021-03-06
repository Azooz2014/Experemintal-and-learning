package io.blacketron.empublite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.karim.MaterialTabs;

public class MainActivity extends AppCompatActivity {

    private static final String MODEL = "model";
    private static final String PREF_LAST_POSITION="lastPosition";
    private static final String PREF_SAVE_LAST_POSITION="saveLastPosition";
    private static final String PREF_KEEP_SCREEN_ON="keepScreenOn";

    private ViewPager pager;
    private ContentsAdapter adapter;
    private ModelFragment mFrag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupStrictMode();
        pager = findViewById(R.id.pager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);

        if (adapter == null){

            mFrag = (ModelFragment) getFragmentManager().findFragmentByTag(MODEL);

            if(mFrag == null){

                mFrag = new ModelFragment();

                getFragmentManager().beginTransaction().add(new ModelFragment(), MODEL).commit();
            }

            else if (mFrag.getBook() != null){

                setupPager (mFrag.getBook());
            }
        }

        if (mFrag.getPrefs()!= null) {
            pager.setKeepScreenOn(mFrag.getPrefs()
                    .getBoolean(PREF_KEEP_SCREEN_ON, false));
        }
    }

    @Override
    protected void onStop() {

        EventBus.getDefault().unregister(this);
        super.onStop();

        if(mFrag.getPrefs() != null){

            int position = pager.getCurrentItem();

            mFrag.getPrefs().edit().putInt(PREF_LAST_POSITION, position).apply();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.about:
                Intent i = new Intent(this, SimpleContentActivity.class)
                        .putExtra(SimpleContentActivity.EXTRA_FILE, "file:///android_asset/misc/about.html");

                startActivity(i);

                return (true);

            case R.id.help:

                i = new Intent(this, SimpleContentActivity.class)
                        .putExtra(SimpleContentActivity.EXTRA_FILE, "file:///android_asset/misc/help.html");

                startActivity(i);

            case R.id.settings:
                startActivity(new Intent(this, Preferences.class));

                return (true);
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBookLoaded(BookLoadedEvent event){

        setupPager(event.getBook());
    }

    private void setupPager(BookContents contents) {

        adapter = new ContentsAdapter(this, contents);
        pager.setAdapter(adapter);

        MaterialTabs tabs = findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        SharedPreferences prefs = mFrag.getPrefs();

        if(prefs != null){

            if(prefs.getBoolean(PREF_SAVE_LAST_POSITION, false)){

                pager.setCurrentItem(prefs.getInt(PREF_LAST_POSITION, 0));
            }

            pager.setKeepScreenOn(prefs.getBoolean(PREF_KEEP_SCREEN_ON, false));
        }
    }

    private void setupStrictMode() {

        StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog();

        if(BuildConfig.DEBUG){

            builder.penaltyFlashScreen();
        }

        StrictMode.setThreadPolicy(builder.build());
    }

}