package io.blacketron.whereitssnap2;

import android.app.Fragment;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Blacketron on 11/4/2017.
 */

public class ViewFragment extends Fragment {

    private Cursor mCursor;
    private ImageView mImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Where is the photo object we want to show?
        int position = getArguments().getInt("Position");

        //Load the appropriate photo from db.
        DataManager d = new DataManager(getActivity().getApplicationContext());
        mCursor = d.getPhoto(position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view, container, false);

        TextView textView = view.findViewById(R.id.textView);

        Button buttonShowLocation = view.findViewById(R.id.buttonShowLocation);

        mImageView = view.findViewById(R.id.imageView);

        //TODO: the app crashes when switching to this view possibly because of getting the data from the database or it has to do with getColumnIndex().
        //Set the text from the title column of the data.
        textView.setText(mCursor.getString(mCursor.getColumnIndex(DataManager.TABLE_ROW_TITLE)));

        //Load the image into the ImageView via the URI.
        mImageView.setImageURI(Uri.parse(mCursor.getString(mCursor.getColumnIndex(DataManager.TABLE_ROW_URI))));

        return view;
    }//End of onCreateView.

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Make sure we don't run out of memory.
        BitmapDrawable db = (BitmapDrawable) mImageView.getDrawable();
        db.getBitmap().recycle();
        mImageView.setImageBitmap(null);
    }
}
