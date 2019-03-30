package io.blacketron.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String EXTRA_ANSWER_IS_TRUE = "io.blacketron.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "io.blacketron.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    private TextView mTextView;

    private TextView mApiLvlTextView;

    private Button mShowAnswerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        viewInitializer();

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        getDeviceApiLevel();
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue){

        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);

        return intent;
    }

    public static boolean WasAnswerShown (Intent result){

        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private void viewInitializer(){

        mTextView = findViewById(R.id.answer_txt);
        mApiLvlTextView = findViewById(R.id.apiLvl_txt);
        mShowAnswerBtn = findViewById(R.id.btn_show_answer);

        mShowAnswerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(mAnswerIsTrue){

            mTextView.setText(R.string.true_button);
        }else{

            mTextView.setText(R.string.false_button);
        }

        showAnswerBtnAnimation();

        setExtraAnswerShownResult(true);
        Log.i("mAnswerITure Value is: ","" + mAnswerIsTrue);
    }

    /*Used to flag if the shown answer button is clicked by the user*/
    private void setExtraAnswerShownResult(boolean isAnswerShown){

        Intent data = new Intent();

        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);

        setResult(RESULT_OK, data);
    }

    private void showAnswerBtnAnimation(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int cx = mShowAnswerBtn.getWidth() / 2;
            int cy = mShowAnswerBtn.getHeight() / 2;
            float radius = mShowAnswerBtn.getWidth();
            Animator anim = ViewAnimationUtils
                    .createCircularReveal(mShowAnswerBtn, cx, cy, radius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    mShowAnswerBtn.setVisibility(View.INVISIBLE);
                }
            });

            anim.start();
        } else {

            mShowAnswerBtn.setVisibility(View.INVISIBLE);
        }
    }

    private void getDeviceApiLevel(){

        mApiLvlTextView.setText("API Level " + Build.VERSION.SDK_INT);
    }
}
