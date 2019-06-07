package io.blacketron.sounddemo;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    SoundPool mSp;

    int mIdFX1 = -1;
    int mIdFX2 = -1;
    int mIdFX3 = -1;
    int mNowPlaying = -1;
    int mRepeats;
    int mSeekBarVolume;

    float mVolume = .1f;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                mVolume = i / 10f;
                mSp.setVolume(mNowPlaying,mVolume,mVolume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.spinner_options));
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String temp = String.valueOf(spinner.getSelectedItem());
                mRepeats = Integer.valueOf(temp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnFX1 = (Button) findViewById(R.id.btnFX1);
        Button btnFX2 = (Button) findViewById(R.id.btnFX2);
        Button btnFX3 = (Button) findViewById(R.id.btnFX3);
        Button btnStop = (Button) findViewById(R.id.btnStop);

        btnFX1.setOnClickListener(this);
        btnFX2.setOnClickListener(this);
        btnFX3.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        /*
        *Checks which version of android is running in order to
        *implement the sound handle by the old way or the new way.
         */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

            mSp = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioAttributes).build();
        } else{

            mSp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        /*
        * Loads th sound files and de-compress them*/
        try{

            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("fx1.ogg");
            mIdFX1 = mSp.load(descriptor,0);
            descriptor = assetManager.openFd("fx2.ogg");
            mIdFX2 = mSp.load(descriptor,0);
            descriptor = assetManager.openFd("fx3.ogg");
            mIdFX3 = mSp.load(descriptor, 0);
        } catch(IOException e){

            Log.e("Error ", "Failed to load sound files " + e);
        }

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnFX1:

                mSp.stop(mNowPlaying);
               mNowPlaying = mSp.play(mIdFX1, mVolume, mVolume, 0, mRepeats, 1);
                break;
            case R.id.btnFX2:

                mSp.stop(mNowPlaying);
                mNowPlaying = mSp.play(mIdFX2, mVolume, mVolume, 0, mRepeats, 1);
                break;
            case R.id.btnFX3:

                mSp.stop(mNowPlaying);
                mNowPlaying = mSp.play(mIdFX3, mVolume, mVolume, 0, mRepeats, 1);
                break;
            case R.id.btnStop:

                mSp.stop(mNowPlaying);
                break;
        }
    }

    /*
    * Controlling  the seekBar value using the volume keys*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

            mSeekBarVolume = seekBar.getProgress();
            seekBar.setProgress(mSeekBarVolume + 1);
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {

            mSeekBarVolume = seekBar.getProgress();
            seekBar.setProgress(mSeekBarVolume - 1);
        }

        return super.onKeyDown(keyCode, event);
    }
}
