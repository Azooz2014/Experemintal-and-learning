package io.blacketron.launchweb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launch(View view) {

        EditText url = findViewById(R.id.editUrl);

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url.getText().toString())));
    }
}
