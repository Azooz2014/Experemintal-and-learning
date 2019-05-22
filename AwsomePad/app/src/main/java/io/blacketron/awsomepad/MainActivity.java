package io.blacketron.awsomepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static io.blacketron.awsomepad.R.id.Edit_textId1;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.Edit_textId1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save("Note.txt");
            }
        });
        editText.setText(Open("Note.txt"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent myIntent = new Intent(MainActivity.this, NoteSelect.class);
            MainActivity.this.startActivity(myIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void Save(String fileName) {

        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(editText.getText().toString());
            out.close();
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        }

        catch (Throwable t){
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public String Open(String fileName) {

        String content = "";

        if(FileExists(fileName)){
            try {
                InputStream in = openFileInput(fileName);

                if (in != null){
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buffer = new StringBuilder();

                    while ((str = reader.readLine()) != null){

                        buffer.append(str + "\n");
                    }in.close();
                    content = buffer.toString();
                }
            }
            catch (java.io.IOException e){}
            catch (Throwable t){
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }

    public boolean FileExists(String fname) {

        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}