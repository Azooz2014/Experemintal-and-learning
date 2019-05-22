package io.blacketron.database;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import static android.R.id.edit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editName;
    EditText editAge;
    EditText editDelete;
    EditText editSearch;

    Button btnInsert;
    Button btnDelete;
    Button btnSearch;
    Button btnSelect;

    DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dm = new DataManager(this);

        //Get reference to the UI item.
        editName = (EditText) findViewById(R.id.editName);
        editAge = (EditText) findViewById(R.id.editAge);
        editDelete = (EditText) findViewById(R.id.editDelete);
        editSearch = (EditText) findViewById(R.id.editSearch);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        //Register MainActivity as listener.
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnInsert:

                dm.insert(editName.getText().toString(), editAge.getText().toString());
                break;

            case R.id.btnSelect:

                showData(dm.selectAll());
                break;

            case R.id.btnSearch:

                showData(dm.searchName(editName.getText().toString()));
                break;

            case R.id.btnDelete:

                dm.delete(editDelete.getText().toString());
                break;
        }
    }

    //Output the Cursor content to the log.
    public void showData(Cursor c){

        while(c.moveToNext()){

            Log.i(c.getString(1), c.getString(2));
        }
    }
}
