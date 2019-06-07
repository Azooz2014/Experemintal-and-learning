package io.blacketron.fragmentimplementaiondemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends LifeCycleLoggingActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showOther(View view) {

        Intent other = new Intent(this, SecondActivity.class);

        other.putExtra(SecondActivity.EXTRA_MASSAGE, "This is a Massage from the 1st activity");

        startActivity(other);
    }
}
