package io.blacketron.notetoself;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Blacketron on 7/31/2017.
 */

public class DialogShowNote extends DialogFragment {

    public Note mNote;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Variable of the type View that holds the id of the layout dialog_show_note.
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

        TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = dialogView.findViewById(R.id.txtDescription);

        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());

        ImageView ivImportant = dialogView.findViewById(R.id.imageViewImportant);
        ImageView ivTodo = dialogView.findViewById(R.id.imageViewTodo);
        ImageView ivIdea = dialogView.findViewById(R.id.imageViewIdea);

        if(!mNote.isImportant()){

            ivImportant.setVisibility(View.GONE);
        }

        if(!mNote.isIdea()){

            ivIdea.setVisibility(View.GONE);
        }

        if(!mNote.isTodo()){

            ivTodo.setVisibility(View.GONE);
        }

        Button btnOk = dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Your Note");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        return builder.create();
    }

    // Receive a note from the MainActivity class.
    public void sendNoteSelected(Note noteSelected){

        mNote = noteSelected;
    }
}
