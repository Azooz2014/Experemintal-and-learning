package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity{


    private MediaPlayer mMediaPlayer = null;
    /*private AudioFocusRequest mFocusRequest = null;*/

    AudioManager am;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            switch(focusChange) {

                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:

                    mMediaPlayer.start();

                    Log.i("AudioFocus", "AudioFocus Gained");
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:

                    mMediaPlayer.pause();
                    Log.i("AudioFocus", "AudioFocus Lost");
                    break;

                case AudioManager.AUDIOFOCUS_LOSS:

                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                    Log.i("AudioFocus", "AudioFocus Lost");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        /*String[] englishNumbers = new String[10];

        englishNumbers[0] = "One";
        englishNumbers[1] = "Two";
        englishNumbers[2] = "Three";
        englishNumbers[3] = "Four";
        englishNumbers[4] = "Five";
        englishNumbers[5] = "Six";
        englishNumbers[6] = "Seven";
        englishNumbers[7] = "Eight";
        englishNumbers[8] = "Nine";
        englishNumbers[9] = "Ten";*/

        final ArrayList<Word> numbers = new ArrayList<>();

        numbers.add(new Word("one", "lutti", R.drawable.number_one,
                true, R.raw.number_one));
        numbers.add(new Word("two", "otiiko", R.drawable.number_two,
                true, R.raw.number_two));
        numbers.add(new Word("three", "tolookosu", R.drawable.number_three,
                true, R.raw.number_three));
        numbers.add(new Word("four", "oyyisa", R.drawable.number_four,
                true, R.raw.number_four));
        numbers.add(new Word("five", "massokka", R.drawable.number_five,
                true, R.raw.number_five));
        numbers.add(new Word("six", "temmokka", R.drawable.number_six,
                true, R.raw.number_six));
        numbers.add(new Word("seven", "kenekaku", R.drawable.number_seven,
                true, R.raw.number_seven));
        numbers.add(new Word("eight", "kawinta", R.drawable.number_eight,
                true, R.raw.number_eight));
        numbers.add(new Word("nine", "wo’e", R.drawable.number_nine,
                true, R.raw.number_nine));
        numbers.add(new Word("ten", "na’aacha",R.drawable.number_ten,
                true, R.raw.number_ten));


       /* Log.i("NumbersActivity", "Index 0 is : " + englishNumbers.get(0));
        Log.i("NumbersActivity", "Index 1 is : " + englishNumbers.get(1));
        Log.i("NumbersActivity", "Index 2 is : " + englishNumbers.get(2));
        Log.i("NumbersActivity", "Index 3 is : " + englishNumbers.get(3));
        Log.i("NumbersActivity", "Index 4 is : " + englishNumbers.get(4));
        Log.i("NumbersActivity", "Index 5 is : " + englishNumbers.get(5));
        Log.i("NumbersActivity", "Index 6 is : " + englishNumbers.get(6));
        Log.i("NumbersActivity", "Index 7 is : " + englishNumbers.get(7));
        Log.i("NumbersActivity", "Index 8 is : " + englishNumbers.get(8));
        Log.i("NumbersActivity", "Index 9 is : " + englishNumbers.get(9));*/

        ListView rootView = findViewById(R.id.rootView);

        /*for(int index = 0; index < englishNumbers.size(); index++){

            TextView word = new TextView(this);
            word.setText(englishNumbers.get(index));
            rootView.addView(word);
        }*/

        WordAdapter adapter = new WordAdapter(this, numbers, R.color.category_numbers);

         rootView.setAdapter(adapter);

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = numbers.get(position);
                int result = am.requestAudioFocus(mOnAudioFocusListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Log.i("AudioFocus: ","Granted");
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourseId());
                    mMediaPlayer.start();

                    //Recovering from Error state.
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {

                            if (mMediaPlayer != null) {

                                mMediaPlayer.release();
                                mMediaPlayer = null;

                            }
                        }
                    });
                }
            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();

        if (mMediaPlayer != null) {

            mMediaPlayer.release();
        }

        am.abandonAudioFocus(mOnAudioFocusListener);
    }

   /* public boolean requestAudioFocus() {

        if(!mAudioFocusGranted){

            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

            int result = am.requestAudioFocus(mOnAudioFocusListener,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                mAudioFocusGranted = true;
            }
        }else{

            Log.i("audioFocus", "Failed to request Audio Focus");
        }

        return mAudioFocusGranted;
    }*/
}
