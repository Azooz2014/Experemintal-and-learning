package io.blacketron.whereitssnap2;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static android.R.id.toggle;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityComs {

    public DataManager mDataManager;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        checkingPermission();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mDataManager = new DataManager(getApplicationContext());

        switchFragment(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_titles) {

            switchFragment (0);
            Toast.makeText(this, "Titles", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tags) {

            switchFragment (1);
            Toast.makeText(this, "Tags", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_capture) {

            switchFragment (2);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchFragment(int position){

        Fragment fragment = null;

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
        fragmentManager.beginTransaction().replace(R.id.fragment_holder, fragment, fragmentID).commit();

    }

    public void checkingPermission() {

        final int CAMERA_REQUEST_CODE = CaptureFragment.getCameraRequestCode();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA + Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onTitlesListItemSelected(int pos) {

        //Load up the Bundle with the row _id.
        Bundle args = new Bundle();
        args.putInt("Position", pos);

        //Create the fragment and add the bundle.
        ViewFragment viewFragment = new ViewFragment();
        viewFragment.setArguments(args);

        //Start the fragment.
        if (viewFragment != null){

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_holder, viewFragment, "VIEW").commit();
        }else {

            //Error loading up the fragment.
            Log.e("MainActivity: ", "Error in creating fragment");
        }
    }

    @Override
    public void onTagsListItemSelected(String clickedTag) {

        //We have just received a String for the TitlesFragment.
        //Prepare a new Bundle.
        Bundle args = new Bundle();

        //Pack the String into the Bundle.
        args.putString("Tag", clickedTag);

        //Create a new instance of the TitlesFragment.
        TitlesFragment titlesFragment = new TitlesFragment();

        //Load the bundle into the fragment.
        titlesFragment.setArguments(args);

        //Start the fragment.
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_holder, titlesFragment, "TAGS").commit();
    }
}
