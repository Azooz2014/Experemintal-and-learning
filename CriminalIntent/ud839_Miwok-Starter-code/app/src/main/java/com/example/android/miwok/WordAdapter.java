package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColorId;

    public WordAdapter(@NonNull Context context, @NonNull List objects, int backgroundColorId) {

        super(context, 0, objects);
        this.mBackgroundColorId = backgroundColorId;
    }

    @NonNull
    @Override

    //Overriding the getView method to use my custom listView with two TextView.
    public View getView(int position,@Nullable View convertView, @NonNull ViewGroup parent) {

        // Get the Word object located at this position in the list
        Word word = getItem(position);

        // Check if the existing view is being reused, otherwise inflate the view
        View listView = convertView;

        if(listView == null){

            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView miwokTextView = listView.findViewById(R.id.miwokText);
        TextView englishTextView = listView.findViewById(R.id.englishText);
        ImageView imageView = listView.findViewById(R.id.icon);
        LinearLayout linearLayout = listView.findViewById(R.id.linearLayout);

        miwokTextView.setText(word.getMiwokTranslation());
        englishTextView.setText(word.getDefaultTranslation());
        imageView.setImageResource(word.getmImgResourceId());


        //Checking whether the word obj has an image associated with it.
        if(word.hasImage()) {

            imageView.setVisibility(View.VISIBLE);
        }
        else{

            imageView.setVisibility(View.GONE);
        }

        //Returns a color associated with a particular resource ID
        int color = ContextCompat.getColor(getContext(), mBackgroundColorId);

        //Setting the background color.
        linearLayout.setBackgroundColor(color);

        return listView;
    }
}
