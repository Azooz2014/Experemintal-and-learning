package io.blacketron.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener , View.OnClickListener{

    Animation mAnimFadeIn;
    Animation mAnimFadeOut;
    Animation mAnimFadeInOut;
    Animation mAnimZoomIn;
    Animation mAnimZoomOut;
    Animation mAnimLeftRight;
    Animation mAnimRightLeft;
    Animation mAnimTopBottom;
    Animation mAnimBounce;
    Animation mAnimFlash;
    Animation mAnimRotateLeft;
    Animation mAnimRotateRight;

    ImageView mImageView;
    TextView mTextStatus;

    Button mBtnFadeIn;
    Button mBtnFadeOut;
    Button mBtnFadeInOut;
    Button mBtnZoomIn;
    Button mBtnZoomOut;
    Button mBtnLeftRight;
    Button mBtnRightLeft;
    Button mBtnTopBottom;
    Button mBtnBounce;
    Button mBtnFlash;
    Button mBtnRotateLeft;
    Button mBtnRotateRight;

    SeekBar mSeekBarSpeed;
    TextView mTextSeekerSpeed;

    int mSeekSpeedProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadAnimations();
        loadUI();
    }

    private void loadAnimations(){

        mAnimFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mAnimFadeIn.setAnimationListener(this);
        mAnimFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        mAnimFadeInOut = AnimationUtils.loadAnimation(this, R.anim.fade_in_out);
        mAnimZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        mAnimZoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        mAnimLeftRight = AnimationUtils.loadAnimation(this, R.anim.left_right);
        mAnimRightLeft = AnimationUtils.loadAnimation(this, R.anim.right_left);
        mAnimTopBottom = AnimationUtils.loadAnimation(this, R.anim.top_bot);
        mAnimFlash = AnimationUtils.loadAnimation(this, R.anim.flash);
        mAnimBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        mAnimRotateLeft = AnimationUtils.loadAnimation(this, R.anim.rotate_left);
        mAnimRotateRight = AnimationUtils.loadAnimation(this, R.anim.rotate_right);
    }

    private void loadUI(){

        mImageView = (ImageView) findViewById(R.id.imageView);
        mTextStatus = (TextView) findViewById(R.id.textStatus);
        mBtnFadeIn = (Button) findViewById(R.id.btnFadeIn);
        mBtnFadeOut = (Button) findViewById(R.id.btnFadeOut);
        mBtnFadeInOut = (Button) findViewById(R.id.btnFadeInOut);
        mBtnZoomIn = (Button) findViewById(R.id.btnZoomIn);
        mBtnZoomOut = (Button) findViewById(R.id.btnZoomOut);
        mBtnLeftRight = (Button) findViewById(R.id.btnLeftRight);
        mBtnRightLeft = (Button) findViewById(R.id.btnRightLeft);
        mBtnTopBottom = (Button) findViewById(R.id.btnTopBot);
        mBtnBounce = (Button) findViewById(R.id.btnBounce);
        mBtnFlash = (Button) findViewById(R.id.btnFlash);
        mBtnRotateLeft = (Button) findViewById(R.id.btnRotateLeft);
        mBtnRotateRight = (Button) findViewById(R.id.btnRotateRight);

        mBtnFadeIn.setOnClickListener(this);
        mBtnFadeOut.setOnClickListener(this);
        mBtnFadeInOut.setOnClickListener(this);
        mBtnZoomIn.setOnClickListener(this);
        mBtnZoomOut.setOnClickListener(this);
        mBtnLeftRight.setOnClickListener(this);
        mBtnRightLeft.setOnClickListener(this);
        mBtnTopBottom.setOnClickListener(this);
        mBtnBounce.setOnClickListener(this);
        mBtnFlash.setOnClickListener(this);
        mBtnRotateLeft.setOnClickListener(this);
        mBtnRotateRight.setOnClickListener(this);

        mSeekBarSpeed = (SeekBar) findViewById(R.id.seekBarSpeed);
        mTextSeekerSpeed = (TextView) findViewById(R.id.textSeekerSpeed);

        mSeekBarSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                mSeekSpeedProgress = i;
                mTextSeekerSpeed.setText("" + mSeekSpeedProgress + " of " + mSeekBarSpeed.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

        mTextStatus.setText("Running");
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        mTextStatus.setText("Stopped");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnFadeOut:
                mAnimFadeOut.setDuration(mSeekSpeedProgress);
                mAnimFadeOut.setAnimationListener(this);
                mImageView.startAnimation(mAnimFadeOut);
                break;
            case R.id.btnFadeIn:
                mAnimFadeIn.setDuration(mSeekSpeedProgress);
                mAnimFadeIn.setAnimationListener(this);
                mImageView.startAnimation(mAnimFadeIn);
                break;
            case R.id.btnFadeInOut:
                mAnimFadeInOut.setDuration(mSeekSpeedProgress);
                mAnimFadeInOut.setAnimationListener(this);
                mImageView.startAnimation(mAnimFadeInOut);
                break;
            case R.id.btnZoomIn:
                mAnimZoomIn.setDuration(mSeekSpeedProgress);
                mAnimZoomIn.setAnimationListener(this);
                mImageView.startAnimation(mAnimZoomIn);
                break;
            case R.id.btnZoomOut:
                mAnimZoomOut.setDuration(mSeekSpeedProgress);
                mAnimZoomOut.setAnimationListener(this);
                mImageView.startAnimation(mAnimZoomOut);
                break;
            case R.id.btnLeftRight:
                mAnimLeftRight.setDuration(mSeekSpeedProgress);
                mAnimLeftRight.setAnimationListener(this);
                mImageView.startAnimation(mAnimLeftRight);
                break;
            case R.id.btnRightLeft:
                mAnimRightLeft.setDuration(mSeekSpeedProgress);
                mAnimRightLeft.setAnimationListener(this);
                mImageView.startAnimation(mAnimRightLeft);
                break;
            case R.id.btnTopBot:
                mAnimTopBottom.setDuration(mSeekSpeedProgress);
                mAnimTopBottom.setAnimationListener(this);
                mImageView.startAnimation(mAnimTopBottom);
                break;
            case R.id.btnBounce:
                mAnimBounce.setDuration(mSeekSpeedProgress / 10);
                mAnimBounce.setAnimationListener(this);
                mImageView.startAnimation(mAnimBounce);
                break;
            case R.id.btnFlash:
                mAnimFlash.setDuration(mSeekSpeedProgress / 10);
                mAnimFlash.setAnimationListener(this);
                mImageView.startAnimation(mAnimFlash);
                break;
            case R.id.btnRotateLeft:
                mAnimRotateLeft.setDuration(mSeekSpeedProgress);
                mAnimRotateLeft.setAnimationListener(this);
                mImageView.startAnimation(mAnimRotateLeft);
                break;
            case R.id.btnRotateRight:
                mAnimRotateRight.setDuration(mSeekSpeedProgress);
                mAnimRotateRight.setAnimationListener(this);
                mImageView.startAnimation(mAnimRotateRight);
                break;
        }
    }
}
