/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //Declaring the TextView in the app and specifying their corresponding Id's.
        TextView mTxtNumbers = findViewById(R.id.numbers);
        TextView mTxtFamily = findViewById(R.id.family);
        TextView mTxtColors = findViewById(R.id.colors);
        TextView mTxtPhrases = findViewById(R.id.phrases);

        //Attaching the listener to the TextView in our app that we want them to respond to clicks.
        mTxtNumbers.setOnClickListener(this);
        mTxtFamily.setOnClickListener(this);
        mTxtColors.setOnClickListener(this);
        mTxtPhrases.setOnClickListener(this);
    }

    //OnClick method provided by the OnClickListener interface to handle the user clicks.
    @Override
    public void onClick(@NonNull View v) {

        Intent intent;

        //A switch-case to handle the different cases when clicking one of the TextViews we attached the Listener to.
        switch (v.getId()) {

            case R.id.numbers:

                intent = new Intent(this, NumbersActivity.class);
                startActivity(intent);
                break;

            case R.id.family:

                intent = new Intent(this, FamilyActivity.class);
                startActivity(intent);
                break;

            case R.id.colors:

                intent = new Intent(this, ColorsActivity.class);
                startActivity(intent);
                break;

            case R.id.phrases:

                intent = new Intent(this, PhrasesActivity.class);
                startActivity(intent);
                break;

        }
    }

    /*public void NumbersClicked(View view){

        mIntent = new Intent (this, NumbersActivity.class);
        startActivity(mIntent);
    }*/
}
