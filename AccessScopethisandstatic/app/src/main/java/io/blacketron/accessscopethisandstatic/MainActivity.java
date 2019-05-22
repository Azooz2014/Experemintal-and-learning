package io.blacketron.accessscopethisandstatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fighter aFighter = new Fighter();
        Boomber aBoomber = new Boomber();

        aBoomber.shipName = "Newell Boobmer";
        aFighter.shipName = "Meier Fighter";

        Log.i("aFighter Shield", "" + aFighter.getShieldStrength());
        Log.i("aBoomber Shield", "" + aBoomber.getShieldStrength());

        aFighter.fireWeapon();
        aBoomber.fireWeapon();

        aBoomber.hitDetected();
        aBoomber.hitDetected();
        aBoomber.hitDetected();
        aBoomber.hitDetected();
    }
}
