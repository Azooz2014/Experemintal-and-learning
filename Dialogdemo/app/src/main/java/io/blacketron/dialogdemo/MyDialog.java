package io.blacketron.dialogdemo;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Blacketron on 7/30/2017.
 */

public class MyDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Chaining methods together.
        // Dialog will have "Make a selection" as the title.
        builder.setMessage("Make a selection")
                //An ok button that does nothing.
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Nothing in here.
            }
        })
                //A cancel button that does nothing.
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }); // End of chaining.

        return builder.create();
    } // End of onCreateDialog.

}
