package io.blacketron.basicclasses;

import android.util.Log;

/**
 * Created by Blacketron on 7/17/2017.
 */

public class Soldier {

    int mHealth;
    String mSoldierType;

    void shootEnemy(){

        Log.i(mSoldierType, "is shooting");
    }
}
