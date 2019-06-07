package io.blacketron.injurethesoldierandhealhim;

import android.util.Log;

/**
 * Created by Blacketron on 7/19/2017.
 */

public class Soldier {
    //Start of the class.

    //public static String solName;
    private int health;

   /* public static String getSolName(){

        return solName;
    }*/

   /* Soldier class constructor with the code to set the soldier health to 100
   * when ever we create a soldier object.*/
    public Soldier(){

        this.health = 100;
    }

    //getter method to get the solHealth value.
    public int getHealth(){

        return this.health;
    }

    //Main method to heal the soldier by 25 every time this method is called.
    public int heal(){

        this.health += 25;

        //Log used for debugging.
        Log.i("Info", "" + health);

        return health;
    }

    //Main method to injure the soldier by 25 every time this method is called.
    public int injure(){

        this.health -= 25;

        //Log used for debugging.
        Log.i("Info", "" + health);

        return health;
    }

    //End of the class.
}
