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

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new Word("Where are you going?", "minto wuksus",
                false, R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",
                false, R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",
                false, R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",
                false, R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",
                false, R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",
                false, R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",
                false, R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",
                false, R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",
                false, R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",
                false, R.raw.phrase_come_here));

        ListView rootView = findViewById(R.id.rootView);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

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
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourseId());
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
