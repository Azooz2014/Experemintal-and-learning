package io.blacketron.fragmentimplementaiondemo;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends LifeCycleLoggingActivity {

    public static final String EXTRA_MASSAGE = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.textView);

        textView.setText(getIntent().getStringExtra(EXTRA_MASSAGE));

        //Dynamic fragment.
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content, new SecondFragment()).commit();
        }
    }
}