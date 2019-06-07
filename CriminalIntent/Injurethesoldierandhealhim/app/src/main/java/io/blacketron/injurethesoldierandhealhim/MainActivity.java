package io.blacketron.injurethesoldierandhealhim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    //Start of class.

    //Soldier class object.
    Soldier mSoldier = new Soldier();

    //Member variable.
    int currentHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the views.
        Button healBtn = (Button) findViewById(R.id.heal_btn_id);
        Button injureBtn = (Button) findViewById(R.id.injure_btn_id);
        final TextView healthTxt = (TextView) findViewById(R.id.health_id);

        //Calling the setCurrentHealth method.
        setCurrentHealth();

        //Setting the text view of the health to be equal to the currentHealth value.
        healthTxt.setText(String.valueOf(getCurrentHealth()));

        //Heal button control.
        healBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Calling the heal method from Soldier class through mSoldier object.
                mSoldier.heal();

                //Calling the setCurrentHealth method to update the currentHealth value.
                setCurrentHealth();

                //Setting the text view to the new currentHealth value.
                healthTxt.setText(String.valueOf(getCurrentHealth()));
            }
        });

        //Injure button control.
        injureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Calling the injure method from our Soldier class through mSoldier object.
                mSoldier.injure();

                //Calling the setCurrentHealth method to update the currentHealth value.
                setCurrentHealth();

                //Setting the text view to the new currentHealth value.
                healthTxt.setText(String.valueOf(getCurrentHealth()));
            }
        });
    }

    /* Setter method to set the currentHealth value to be equal to
    * the solHealth value from Soldier class*/
    private int setCurrentHealth(){

        this.currentHealth = mSoldier.getHealth();

        // Log used for debugging.
        Log.i("Info", "current health" + currentHealth);

        return this.currentHealth;
    }

    //getter method used to return the currentHealth value.
    public int getCurrentHealth(){

        return this.currentHealth;
    }

    //End of class.
}
