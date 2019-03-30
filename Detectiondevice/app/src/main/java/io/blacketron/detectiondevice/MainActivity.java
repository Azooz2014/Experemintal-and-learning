package io.blacketron.detectiondevice;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtOrientation;
    private TextView mTxtResolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtOrientation = (TextView) findViewById(R.id.txtOrientation);
        mTxtResolution = (TextView) findViewById(R.id.txtResolution);
    }

    public void detectDevice (View view){

        Display display = getWindowManager().getDefaultDisplay();

        mTxtOrientation.setText("" + display.getRotation());

        Point xy = new Point();

        display.getSize(xy);

        mTxtResolution.setText("x = " + xy.x + " y = " + xy.y);
    }
}
