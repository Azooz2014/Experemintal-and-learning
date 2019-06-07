package io.blacketron.actionbardemoactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String[] mItems = {"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    private ArrayAdapter<String> mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);


        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.add:

                addWord();

                return true;

            case R.id.reset:

                initAdapter();

                return true;

            case R.id.about:

                Toast.makeText(this, R.string.about_msg, Toast.LENGTH_LONG).show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initAdapter() {

        ArrayList<String> mWords = new ArrayList<>();

        for (int word = 0; word < 5; word++) {

            mWords.add(mItems[word]);
        }

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mWords);

        ListView mListView = findViewById(R.id.list);

        mListView.setAdapter(mAdapter);
    }

    public void addWord() {

        if (mAdapter.getCount() < mItems.length) {

            mAdapter.add(mItems[mAdapter.getCount()]);
        }
    }
}
