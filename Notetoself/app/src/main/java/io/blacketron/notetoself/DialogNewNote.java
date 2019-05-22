package io.blacketron.notetoself;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import static android.os.Build.VERSION_CODES.O;

/**
 * Created by Blacketron on 7/30/2017.
 */

public class DialogNewNote extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkBoxImportant = dialogView.findViewById(R.id.checkBoxImportant);

        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOk = dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Add a new note");

        //Handles the Cancel button.
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Calls the dismiss() method which is a public method of DialogFragment,
                * to  close the dialog window.*/
                dismiss();
            }
        }); //End of btnCancel.

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creating Note object called newNote.
                Note newNote = new Note();

                //Setting Note class member variables to match the user entries.
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());
                newNote.setIdea(checkBoxIdea.isChecked());
                newNote.setTodo(checkBoxTodo.isChecked());
                newNote.setImportant(checkBoxImportant.isChecked());

                //Get a reference to MainActivity class.

                MainActivity callingActivity = (MainActivity) getActivity();

                //Pass newNote back to MainActivity.
                callingActivity.createNewNote(newNote);

                //Quit the dialog.
                dismiss();
            }
        }); // End of btnOK.

        return builder.create();
    }
}
