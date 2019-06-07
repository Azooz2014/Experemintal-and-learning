package io.blacketron.adressbook;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Blacketron on 9/17/2017.
 */

public class AddressDetailFragment extends Fragment {

    private ArrayList <NameAndAddress> mNamesAndAddresses;
    private NameAndAddress mCurrentNameAndAddress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNamesAndAddresses = AddressBook.getInstance().getBook();

        //Get the position from te bundle using the constant string.
        int position = getArguments().getInt("Position");

        //Initialize with current name and address.
        mCurrentNameAndAddress = mNamesAndAddresses.get(position);
    }

    public static AddressDetailFragment newInstance(int position) {

        Bundle args = new Bundle();

        args.putInt("Position", position);

        AddressDetailFragment fragment = new AddressDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtAddress1 = view.findViewById(R.id.txtAddress1);
        TextView txtAddress2 = view.findViewById(R.id.txtAddress2);
        TextView txtZipCode = view.findViewById(R.id.txtZipCode);

        txtName.setText(mCurrentNameAndAddress.getName());
        txtAddress1.setText(mCurrentNameAndAddress.getAddress1());
        txtAddress2.setText(mCurrentNameAndAddress.getAddress2());
        txtZipCode.setText(mCurrentNameAndAddress.getZipCode());
        return view;
    }
}
