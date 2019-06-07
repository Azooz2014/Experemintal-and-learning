package io.blacketron.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditorFragment extends Fragment {

    private static final String KEY_POSITION = null;
    static EditorFragment newInstance(int position){

        EditorFragment editorFragment = new EditorFragment();

        Bundle args = new Bundle();

        args.putInt(KEY_POSITION, position);

        editorFragment.setArguments(args);

        return editorFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.editor, container, false);

        int position = getArguments().getInt(KEY_POSITION, -1);

        EditText editText = layout.findViewById(R.id.editText);

        editText.setHint(String.format(getString(R.string.hint), position + 1));

        return layout;
    }
}
