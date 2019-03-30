package io.blacketron.listviewselectiondemo;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends ListActivity {

    private final static String item[] = {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur"
    , "adipiscing", "elit", "Integer", "nec", "odio", "Praesent", "libero", "Sed", "cursus"};

    private TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(android.R.id.list);
        counter = findViewById(R.id.textView);

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, item));

    }

    /*@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        counter.setText(item[position]);
    }*/


}
