package io.blacketron.basicclasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Soldier rambo = new Soldier();
        rambo.mHealth = 150;
        rambo.mSoldierType = "Green baret";

        Soldier vassily = new Soldier();
        vassily.mHealth = 50;
        vassily.mSoldierType = "Sniper";

        Soldier wellington = new Soldier();
        wellington.mHealth = 100;
        wellington.mSoldierType = "Sailor";


        Log.i("Rambo's health is", "" + rambo.mHealth);
        Log.i("Vassily's health is", "" + vassily.mHealth);
        Log.i("Wellington's health is", "" + wellington.mHealth);

        rambo.shootEnemy();
        vassily.shootEnemy();
        wellington.shootEnemy();
    }
}
