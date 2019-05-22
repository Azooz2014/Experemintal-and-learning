package io.blacketron.whereitssnap2;

import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Blacketron on 10/5/2017.
 */

public class TagsFragment extends ListFragment {

    private ActivityComs mActivityComs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataManager d = new DataManager(getActivity().getApplicationContext());

        Cursor c = d.getTags();

        //Create new adapter.
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1
        , c, new String[]{DataManager.TABLE_ROW_TAG}, new int[]{android.R.id.text1}, 0);

        //Attach the Cursor to the adapter.
        setListAdapter(cursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //What tag has just been clicked?
        Cursor c = ((SimpleCursorAdapter) l.getAdapter()).getCursor();
        c.moveToPosition(position);

        String clickedTag = c.getString(1); // 1 is the position of the string.

        Log.e("clickedTag =", " " + clickedTag );

        mActivityComs.onTagsListItemSelected(clickedTag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivityComs = (ActivityComs) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mActivityComs = null;
    }
}
