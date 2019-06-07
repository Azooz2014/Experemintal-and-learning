package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer = null;

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

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red,
                true, R.raw.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green,
                true, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown,
                true, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray,
                true, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black,
                true, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white,
                true, R.raw.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                true, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                true, R.raw.color_mustard_yellow));

        ListView rootView = findViewById(R.id.rootView);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        rootView.setAdapter(adapter);

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = words.get(position);

                int result = am.requestAudioFocus(mOnAudioFocusListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Log.i("AudioFocus: ","Granted");
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourseId());
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

}
