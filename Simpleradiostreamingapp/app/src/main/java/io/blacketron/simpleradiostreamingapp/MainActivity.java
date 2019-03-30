package io.blacketron.simpleradiostreamingapp;

import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mPlayer;
    private FloatingActionButton mPlayButton;
    private ProgressBar mloadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeMediaPlayer();
        initializeUIElements();
    }



    public void onClick(View view){
        if(!mPlayer.isPlaying()){

            startPlaying();
        }else{

            stopPlaying();
        }

    }

    private void startPlaying(){

        mloadingBar.setVisibility(View.VISIBLE);

        mPlayer.prepareAsync();

        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mPlayer.start();
            }
        });

        mPlayButton.setImageResource(android.R.drawable.ic_media_pause);
    }

    private void stopPlaying(){

        if(mPlayer.isPlaying()){

            mPlayer.stop();
            mPlayer.release();
            InitializeMediaPlayer();
        }

        mPlayButton.setImageResource(android.R.drawable.ic_media_play);
    }

    private void initializeUIElements(){

        mPlayButton = findViewById(R.id.playButton);
        mPlayButton.setOnClickListener(this);
        mloadingBar = findViewById(R.id.loadingBar);
        mloadingBar.setMax(100);
        mloadingBar.setVisibility(View.INVISIBLE);
    }

    private void InitializeMediaPlayer(){

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource("http://hyades.shoutca.st:8043/stream");
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }catch(IllegalStateException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
                mloadingBar.setSecondaryProgress(percent);
                Log.i("Buffering", "Buffering... " + percent);
            }
        });
    }

    //Pauses Media when the app is in the background.
    //Hint: uncomment the onPause method of you don't want background media playing feature.
    /*@Override
    protected void onPause() {
        super.onPause();

        if(mPlayer.isPlaying()){

            mPlayer.stop();
        }
    }*/
}
