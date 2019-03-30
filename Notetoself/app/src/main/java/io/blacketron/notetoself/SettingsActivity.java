package io.blacketron.notetoself;

import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import static android.os.Build.VERSION_CODES.N;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private boolean mSound;
    public static final int mFast = 0;
    public static final int mSlow = 1;
    public static final int mNone = 2;
    private int mAnimOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPrefs = getSharedPreferences("Note to self", MODE_PRIVATE);
        mEditor = mPrefs.edit();
        mSound = mPrefs.getBoolean("sound", true);

        CheckBox checkBoxSound = (CheckBox) findViewById(R.id.checkBoxSound);

        if(mSound){

            checkBoxSound.setChecked(true);
        }
        else{
            checkBoxSound.setChecked(false);
        }

        checkBoxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                Log.i("Info", "Sound " + mSound);
                Log.i("Info", "isChecked " + isChecked);

                mSound = !mSound;

                Log.i("Info", "Sound " + mSound);

                mEditor.putBoolean("sound", mSound);
            }
        });//End of anonymous class.

        mAnimOption = mPrefs.getInt("Anim option", mFast);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        switch(mAnimOption){

            case mFast:

                radioGroup.check(R.id.radioFast);
                break;

            case mSlow:
                radioGroup.check(R.id.radioSlow);
                break;

            case mNone:
                radioGroup.check(R.id.radioNone);
                break;
        }//End of switch

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                RadioButton radioButton = group.findViewById(checkedId);

                if(radioButton != null && checkedId > -1){

                    switch(radioButton.getId()){

                        case R.id.radioFast:

                            mAnimOption = mFast;
                            break;

                        case R.id.radioSlow:

                            mAnimOption = mSlow;
                            break;

                        case R.id.radioNone:

                            mAnimOption = mNone;
                            break;
                    }//End of switch.

                    mEditor.putInt("Anim option", mAnimOption);
                }
            }
        });//End of anonymous class.
    }//End of onCreate() method.

    //Calling commit() when the used exit the setting activity or the app.
    @Override
    protected void onPause(){
        super.onPause();

        //Saving the settings here.
        mEditor.commit();
    }
}
