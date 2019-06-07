package io.blacketron.simplefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Blacketron on 9/14/2017.
 */

public class SimpleFragment extends Fragment {

    private String mMyString;
    private Button mMyButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMyString = "Hello from simple fragment";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        mMyButton = view.findViewById(R.id.button);

        mMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), mMyString, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
