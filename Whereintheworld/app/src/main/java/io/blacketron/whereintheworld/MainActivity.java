package io.blacketron.whereintheworld;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView mTxtLat;
    private TextView mTxtLong;
    private TextView mTxtSource;

    private LocationManager mLocationManager;

    private String mProvider;

    private String[] mRequiredPermissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtLat = findViewById(R.id.txtLat);
        mTxtLong = findViewById(R.id.txtLong);
        mTxtSource = findViewById(R.id.txtSource);

        //Initialize locationManager.
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        mProvider = mLocationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            ActivityCompat.requestPermissions(this, mRequiredPermissions, 1);

            // here to request the missing permissions, and then overriding
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location location = mLocationManager.getLastKnownLocation(mProvider);

        //Initialize the location.
        if (location != null) {

            mTxtSource.setText("Source = " + mProvider);

            onLocationChanged(location);
        }
    }

    //Start updates when the app starts/resume.
    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            ActivityCompat.requestPermissions(this, mRequiredPermissions, 1);

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mLocationManager.requestLocationUpdates(mProvider, 500, 1, this);
    }

    //pause the location when the app stops/pause.

    @Override
    protected void onPause() {
        super.onPause();

        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        double lat = location.getLatitude();
        double lng = location.getLongitude();

        mTxtLat.setText(String.valueOf(lat));
        mTxtLong.setText(String.valueOf(lng));
        mTxtSource.setText("Source = " + mProvider);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

        mTxtSource.setText("Source = " + mProvider);
    }

    @Override
    public void onProviderEnabled(String s) {

        mTxtSource.setText("Source = " + mProvider);
    }

    @Override
    public void onProviderDisabled(String s) {

        mTxtSource.setText("Source = " + mProvider);
    }
}
