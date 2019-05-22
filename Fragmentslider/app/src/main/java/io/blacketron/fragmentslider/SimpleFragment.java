package io.blacketron.fragmentslider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Blacketron on 9/26/2017.
 */

public class SimpleFragment extends Fragment {

    //Holds the fragment id passed in when created.

    public final static String MESSAGE = "";

    //newInstance method which we call to make a new fragment.

    public static SimpleFragment newInstance (String message){

        //Create the fragment.
        SimpleFragment fragment = new SimpleFragment();

        //Create a bundle for out message/id.
        Bundle bundle = new Bundle(1);

        //Load up the bundle.
        bundle.putString(MESSAGE, message);

        //Call setArguments ready for when onCreate is called.
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Get message/id from bundle.
        String message = getArguments().getString(MESSAGE);

        //Inflate the view as normal.
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        //Get a reference to TextView.
        TextView messageTextView = (TextView) view.findViewById(R.id.textView);

        //Display the id in the TextView.
        messageTextView.setText(message);

        return view;



    }
}
