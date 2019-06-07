package io.blacketron.whereitssnap;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.blacketron.whereitssnap.CaptureFragment;
import io.blacketron.whereitssnap.TagsFragment;
import io.blacketron.whereitssnap.TitlesFragment;

public class MainActivity extends AppCompatActivity {

    private ListView mNavDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> mAdapter;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mActivityTitle = getTitle().toString();

        String[] navMenuTitiles = getResources().getStringArray(R.array.nav_drawer_item);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, navMenuTitiles);

        mNavDrawerList.setAdapter(mAdapter);

        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mNavDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switchFragment(i);
            }
        });

        //Load TitleFragment when the app starts.
        switchFragment(0);
    }

    public void switchFragment(int position){

        android.app.Fragment fragment = null;
        String fragmentID = "";

        switch (position){

            case 0:
                fragmentID = "TITLES";

                Bundle args = new Bundle();

                args.putString("Tag", "_NO_TAG");

                fragment = new TitlesFragment();

                fragment.setArguments(args);

                break;

            case 1:

                fragmentID = "TAGS";

                fragment = new TagsFragment();

                break;

            case 2:

                fragmentID = "CAPTURE";

                fragment = new CaptureFragment();

                break;

            default:
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentHolder, fragment, fragmentID).commit();

        //Close the drawer.

        mDrawerLayout.closeDrawer(mNavDrawerList);
    }

    public void setupDrawer(){

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close){

            //Called when drawer is open.
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);

                getSupportActionBar().setTitle("Make a selection!");

                //triggers call to onPrepareOptionsMenu.
                invalidateOptionsMenu();
        }
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);

                getSupportActionBar().setTitle(mActivityTitle);

                //triggers call to onPrepareOptionsMenu.
                invalidateOptionsMenu();
            }
    };

    mDrawerToggle.setDrawerIndicatorEnabled(true);

        //addDrawerListener replaced setDrawerListener.
    mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed(){

        //Close drawer if open.
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){

            mDrawerLayout.closeDrawer(mNavDrawerList);
        }
        else{

            // Go back to title screen.

            //Quit if already at TitlesFragment.
            android.app.Fragment f = getFragmentManager().findFragmentById(R.id.fragmentHolder);

            if(f instanceof TitlesFragment){

                finish();
                System.exit(0);
            }
            else{

                //Return to position 0 which is TitleFragment screen.
                switchFragment(0);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        /*//No inspection Simplifiable if statment.

        if(id == findViewById(R.id.action_settings)){

            return true;
        }*/

        if(mDrawerToggle.onOptionsItemSelected(item)){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
