package io.blacketron.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private int mTotalScore = 100;

    private Score mScore = new Score();

    private boolean mIsAnswered;

    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*If savedInstaceState is not empty get value stored in KEY_INDEX Key String and assigned
        to mCurrentIndex.*/
        if (savedInstanceState != null){

            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        viewInitializer();

        updateQuestion();

    }

    /*using onSavedInstaceState to save mCurrentIndex
    value for restoration on configuration changes.*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Storing mCurrentIndex value into KEY_INDEX Key String.
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    //Helper method for initializing views.
    private void viewInitializer(){

        mTrueButton = findViewById(R.id.btn_true);
        mFalseButton = findViewById(R.id.btn_false);
        mNextButton = findViewById(R.id.btn_next);
        mPrevButton = findViewById(R.id.btn_previous);
        mCheatButton = findViewById(R.id.btn_cheat);
        mQuestionTextView = findViewById(R.id.txt_question);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPrevButton.setOnClickListener(this);
        mCheatButton.setOnClickListener(this);
        mQuestionTextView.setOnClickListener(this);

    }

    public int getCurrentIndex(){

        return this.mCurrentIndex;
    }

    /*Sets the mCurrentIndex value and enables the buttons to be clickable again when ever
    * mCurrentIndex value changes.*/
    public void setCurrentIndex(int currentIndex){

        if(currentIndex != mCurrentIndex) {

            mTrueButton.setClickable(true);
            mFalseButton.setClickable(true);

            this.mCurrentIndex = currentIndex;
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_true:

                //Put code when True button is clicked.

                checkAnswer(true);
                clickedBefore();

                /*Toast.makeText(this,"True button pressed!",Toast.LENGTH_SHORT).show();*/
                break;

            case R.id.btn_false:

                //Put code when False button is clicked.

                checkAnswer(false);
                clickedBefore();

                /*Toast.makeText(this,"False button pressed!",Toast.LENGTH_SHORT).show();*/
                break;

            case R.id.btn_next:

//                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                setCurrentIndex((mCurrentIndex + 1) % mQuestionBank.length);

                mIsCheater = false;

                updateQuestion();
                quizComplete();

                /*Toast.makeText(this,"Next button pressed!",Toast.LENGTH_SHORT).show();*/
                break;

            case R.id.btn_previous:

                //Handling ArrayIndexOutOfBoundsException Error, making mCurrentIndex never get
                // less than 0.
                try {

//                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;

                    setCurrentIndex((mCurrentIndex - 1) % mQuestionBank.length);

                    updateQuestion();

                } catch (ArrayIndexOutOfBoundsException outOfIndexError){

                    mCurrentIndex = 0;

                    /*Log.i("Error", "" + outOfIndexError);
                    Log.i("Current Index", "" + mCurrentIndex);*/
                }

            case R.id.btn_cheat:

                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);

                startActivityForResult(intent, REQUEST_CODE_CHEAT);

                break;


                /*Toast.makeText(this,"Next button pressed!",Toast.LENGTH_SHORT).show();*/
            /*case R.id.txt_question:

                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                updateQuestion();

                break;*/
        }

    }

    /*Updates mQuestionTextView*/
    private void updateQuestion(){

        int question = mQuestionBank[mCurrentIndex].getTextResId();

        mQuestionTextView.setText(question);

//        Log.i("Current Index: ","" + mCurrentIndex);
//        Log.i("Question bank length: ","" + mQuestionBank.length);

    }

    private  void checkAnswer (boolean userAnswer){

        boolean isTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messegeResID = 0;

        if(mIsCheater){

            messegeResID = R.string.judgment_toast;

        } else {

            if (userAnswer == isTrue) {

                messegeResID = R.string.correct_toast;
                mScore.setTotalScore(mTotalScore);

            } else {

                if (!mIsAnswered) {

                    mScore.setTotalScore(mTotalScore -= 5);

                    messegeResID = R.string.incorrect_toast;
                }
            }
        }

        Toast.makeText(this, messegeResID, Toast.LENGTH_LONG).show();
    }

    private void quizComplete(){

        if (mCurrentIndex == 0){

            int score = mScore.getTotalScore();

            Toast.makeText(this, "Your score is " + score + "%", Toast.LENGTH_SHORT).show();
        }
    }

    /*Disables mTrue or mFalse buttons once clicked per question.*/
    private void clickedBefore(){

        switch (mCurrentIndex){

            case 0:

                if (mTrueButton.isClickable() && mFalseButton.isClickable()) {

                    if (mTrueButton.isPressed()) {

                        mTrueButton.setClickable(false);

                        mIsAnswered = true;


                    } else if (mFalseButton.isPressed()) {

                        mFalseButton.setClickable(false);

                        mIsAnswered = true;

                    }
                }else{

                    Toast.makeText(this, "Already answered Thank you!", Toast.LENGTH_SHORT).show();
                }

                break;

            case 1:

                if (mTrueButton.isClickable() && mFalseButton.isClickable()) {

                    if (mTrueButton.isPressed()) {

                        mTrueButton.setClickable(false);

                        mIsAnswered = true;

                    } else if (mFalseButton.isPressed()) {

                        mFalseButton.setClickable(false);

                        mIsAnswered = true;

                    }
                }else{

                    Toast.makeText(this, "Already answered Thank you!", Toast.LENGTH_SHORT).show();
                }

                break;

            case 2:

                if (mTrueButton.isClickable() && mFalseButton.isClickable()) {

                    if (mTrueButton.isPressed()) {

                        mTrueButton.setClickable(false);

                        mIsAnswered = true;

                    } else if (mFalseButton.isPressed()) {

                        mFalseButton.setClickable(false);

                        mIsAnswered = true;

                    }
                }else{

                    Toast.makeText(this, "Already answered Thank you!", Toast.LENGTH_SHORT).show();
                }

                break;

            case 3:

                if (mTrueButton.isClickable() && mFalseButton.isClickable()) {

                    if (mTrueButton.isPressed()) {

                        mTrueButton.setClickable(false);

                        mIsAnswered = true;

                    } else if (mFalseButton.isPressed()) {

                        mFalseButton.setClickable(false);

                        mIsAnswered = true;

                    }
                }else{

                    Toast.makeText(this, "Already answered Thank you!", Toast.LENGTH_SHORT).show();
                }

                break;

            case 4:

                if (mTrueButton.isClickable() && mFalseButton.isClickable()) {

                    if (mTrueButton.isPressed()) {

                        mTrueButton.setClickable(false);

                        mIsAnswered = true;

                    } else if (mFalseButton.isPressed()) {

                        mFalseButton.setClickable(false);

                        mIsAnswered = true;

                    }
                }else{

                    Toast.makeText(this, "Already answered Thank you!", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_CODE_CHEAT){

            if(data == null){
                return;
            }

            mIsCheater = CheatActivity.WasAnswerShown(data);
        }
    }
}
