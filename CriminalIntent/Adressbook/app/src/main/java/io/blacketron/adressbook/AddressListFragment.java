package io.blacketron.adressbook;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Blacketron on 9/16/2017.
 */

public class AddressListFragment extends ListFragment {

    private  ActivityComs mActivityComs;

    private ArrayList<NameAndAddress> mNamesAndAddresses;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNamesAndAddresses = AddressBook.getInstance().getBook();

        AddressListAdapter adapter = new AddressListAdapter(mNamesAndAddresses);


        setListAdapter(adapter);
    }


    private class AddressListAdapter extends ArrayAdapter<NameAndAddress> {

        public AddressListAdapter(ArrayList<NameAndAddress> namesAndAddresses){

            super(getActivity(), R.layout.list_fragment_layout, namesAndAddresses);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){

                LayoutInflater inflater = getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_fragment_layout, parent, false);
            }

            NameAndAddress tempNameAndAddress = getItem(position);

            TextView txtName = convertView.findViewById(R.id.txtName);

            txtName.setText(tempNameAndAddress.getName());

            return convertView;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivityComs = (ActivityComs) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mActivityComs = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Pass the position to MainActivity.

        mActivityComs.onListItemSelected(position);
    }
}
