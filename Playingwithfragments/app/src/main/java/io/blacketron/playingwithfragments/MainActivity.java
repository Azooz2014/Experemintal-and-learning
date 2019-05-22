package io.blacketron.playingwithfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.fragment_holder1, new Fragment1(), "fragment1").commit();

        Button switchBtn = (Button) findViewById(R.id.replace_btn);

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_holder1, new Fragment2(), "fragment2").commit();
            }
        });

    }
}
