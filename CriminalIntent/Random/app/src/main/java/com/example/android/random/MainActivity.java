package com.example.android.random;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Random;

import static android.os.Build.VERSION_CODES.M;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Generate(View v){

        TextView rnView = (TextView) findViewById(R.id.rn_view);
        rnView.setText(""+RNG()+RNG2());
    }

    public String RNG(){

    String[] set1 ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B"
    ,"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] set2 ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B"
            ,"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] set3 ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B"
            ,"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] set4 ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B"
            ,"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] set5 ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B"
            ,"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    int rn1 = new Random().nextInt(set1.length);
    int rn2 = new Random().nextInt(set2.length);
    int rn3 = new Random().nextInt(set3.length);
    int rn4 = new Random().nextInt(set4.length);
    int rn5 = new Random().nextInt(set5.length);

    return set1[rn1]+set2[rn2]+set3[rn3]+set4[rn4]+set5[rn5];
    }

    public String RNG2(){

    String[] set1 ={"1","2","3","4","5","6","7","8","9","0"};
    String[] set2 ={"1","2","3","4","5","6","7","8","9","0"};
    String[] set3 ={"1","2","3","4","5","6","7","8","9","0"};
    String[] set4 ={"1","2","3","4","5","6","7","8","9","0"};
    String[] set5 ={"1","2","3","4","5","6","7","8","9","0"};

    int rn1 = new Random().nextInt(set1.length);
    int rn2 = new Random().nextInt(set2.length);
    int rn3 = new Random().nextInt(set3.length);
    int rn4 = new Random().nextInt(set4.length);
    int rn5 = new Random().nextInt(set5.length);

    return set1[rn1]+set2[rn2]+set3[rn3]+set4[rn4]+set5[rn5];
    }

    //TODO: Make the user select the number of characters to generate.
}
