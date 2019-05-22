package io.blacketron.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by Blacketron on 7/19/2017.
 */

public abstract class AlienShip {

    //Start of class.

    private static int numShips;
    private int shieldStrength;
    public String shipName;

    //Constructor
    public AlienShip(int shieldStrength){

        Log.i("Location", "AlienShip constructor");
        numShips++;
        setShieldStrength(shieldStrength);
    }

    public abstract void fireWeapon();

    public static int getNumShips(){

        return numShips;
    }

    private void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public int getShieldStrength(){

        return this.shieldStrength;
    }

    public void hitDetected(){

        shieldStrength -= 25;

        Log.i("Incoming", "Baam!!");

        if(shieldStrength == 0){

            destroyShip();
        }
    }

    private void destroyShip() {
        numShips--;

        Log.i("Explosion", "" + this.shipName + " destroyed");
    }

    //End of class.
}
