package io.blacketron.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by Blacketron on 7/23/2017.
 */

public class Fighter extends AlienShip {

    public Fighter(){

        super(400);
        Log.i("Location", "Fighter constructor");
    }

    public void fireWeapon(){

        Log.i("Firing weapon", "lasers firing");
    }
}
