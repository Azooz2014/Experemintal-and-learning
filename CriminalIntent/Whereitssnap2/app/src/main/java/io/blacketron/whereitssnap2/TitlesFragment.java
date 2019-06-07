package io.blacketron.whereitssnap2;

import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Blacketron on 10/5/2017.
 */

public class TitlesFragment extends ListFragment {

    private Cursor mCursor;
    private ActivityComs mActivityComs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the tag to search for.
        String tag = getArguments().getString("Tag");

        //Get an instance of DataManger.
        DataManager d = new DataManager(getActivity().getApplicationContext());

        if(tag == "_NO_TAG"){

            //Get all the titles.
            mCursor = d.getTitles();
        }else {

            //Get the titles with a specific related tag.
            mCursor = d.getTitlesWithTags(tag);
        }

        //Create a new adapter.
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, mCursor
        , new String[]{DataManager.TABLE_ROW_TITLE}, new int[]{android.R.id.text1}, 0);

        //Attach the adapter to the ListView.
        setListAdapter(cursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        //Move the cursor into the clicked item in the list.
        mCursor.moveToPosition(position);

        //What is the database id for this item?
        int dBID = mCursor.getColumnIndex(DataManager.TABLE_ROW_ID);

        //Use the interface to send the clicked _id.
        mActivityComs.onTitlesListItemSelected(dBID);
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