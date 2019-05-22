package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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

        words.add(new Word("father", "әpә", R.drawable.family_father,
                true, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother,
                true, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son,
                true, R.raw.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter,
                true, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,
                true, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,
                true, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,
                true, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,
                true, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother,
                true, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,
                true, R.raw.family_grandfather));

        ListView rootView = findViewById(R.id.rootView);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

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
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourseId());
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
