package io.blacketron.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMediaPlayer = MediaPlayer.create(this,R.raw.feels_like_summer);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPause = findViewById(R.id.btnPause);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnSkip = findViewById(R.id.btnSkip);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnSkip.setOnClickListener(this);

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Toast.makeText(MainActivity.this,"Audio Done", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnPlay:

                Toast.makeText(getApplicationContext(), "Playing song",
                        Toast.LENGTH_SHORT).show();


               mMediaPlayer.start();

                break;

            case R.id.btnPause:

                Toast.makeText(getApplicationContext(), "Pausing song",
                        Toast.LENGTH_SHORT).show();

                mMediaPlayer.pause();
                break;

            case R.id.btnStop:

                Toast.makeText(getApplicationContext(), "Song stopped",
                        Toast.LENGTH_SHORT).show();

              mMediaPlayer.reset();
              mMediaPlayer = MediaPlayer.create(this,R.raw.feels_like_summer);
                break;

            case R.id.btnSkip:

                mMediaPlayer.seekTo(mMediaPlayer.getDuration());
                break;
        }

    }
}
