package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer = null;
    /*private AudioFocusRequest mFocusRequest = null;*/

    AudioManager am;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            switch (focusChange) {

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


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.words, container, false);

        ListView listView = rootView.findViewById(R.id.rootView);


        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


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
        numbers.add(new Word("ten", "na’aacha", R.drawable.number_ten,
                true, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(getActivity(), numbers, R.color.category_numbers);

        /*for(int index = 0; index < englishNumbers.size(); index++){

            TextView word = new TextView(this);
            word.setText(englishNumbers.get(index));
            rootView.addView(word);
        }*/

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();

                Word word = numbers.get(position);
                int result = am.requestAudioFocus(mOnAudioFocusListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Log.i("AudioFocus: ", "Granted");
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourseId());
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

        return rootView;
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            am.abandonAudioFocus(mOnAudioFocusListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }
}
