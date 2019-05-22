package io.blacketron.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by Blacketron on 7/23/2017.
 */

public class Boomber extends AlienShip {

    public Boomber(){

        super(100);
        Log.i("Location", "Boobmer constructor");
    }

    public void fireWeapon(){

        Log.i("Firing weapon", "boombs awaaaay!!");
    }
}
