package io.blacketron.widgetexploration;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextClock;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exploration_layout);

        //Get a reference to all out widgets.

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGropup);
        RadioButton rbBeijing = (RadioButton) findViewById(R.id.radioButtonBeijing);
        RadioButton rbLondon = (RadioButton) findViewById(R.id.radioButtonLondon);
        RadioButton rbNewYork = (RadioButton) findViewById(R.id.radioButtonNewYork);

        final EditText editText = (EditText) findViewById(R.id.editText);
        final Button button = (Button) findViewById(R.id.button);

        final TextClock tClock = (TextClock) findViewById(R.id.textClock);

        final CheckBox cbTransparancy = (CheckBox) findViewById(R.id.checkBoxTransparancy);
        final CheckBox cbTint = (CheckBox) findViewById(R.id.checkBoxTint);
        final CheckBox cbReSize = (CheckBox) findViewById(R.id.checkBoxReSize);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        final Switch switch1 = (Switch) findViewById(R.id.switch1);

        final WebView webView = (WebView) findViewById(R.id.webView);

        cbTransparancy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(cbTransparancy.isChecked()){

                    imageView.setAlpha(.1f);
                } else{

                    imageView.setAlpha(1f);
                }
            }
        });

        cbTint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(cbTint.isChecked()){

                    imageView.setColorFilter(Color.argb(150, 255, 0, 0));
                } else{

                    imageView.setColorFilter(Color.argb(0, 0, 0, 0));
                }
            }
        });

        cbReSize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(cbReSize.isChecked()){

                    imageView.setScaleX(2);
                    imageView.setScaleY(2);
                } else{

                    imageView.setScaleX(1);
                    imageView.setScaleY(1);
                }
            }
        });

        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                RadioButton rb = (RadioButton) findViewById(i);

                switch(rb.getId()){

                    case R.id.radioButtonLondon:

                        tClock.setTimeZone("Europe/London");
                        break;

                    case R.id.radioButtonBeijing:

                        tClock.setTimeZone("CST6CDT");
                        break;

                    case R.id.radioButtonNewYork:

                        tClock.setTimeZone("America/New_York");
                        break;
                }//End of switch.
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button.setText(editText.getText());
            }
        });

        webView.loadUrl("http://www.cs.yale.edu/homes/tap/Files/hopper-story.html");
        webView.setVisibility(View.INVISIBLE);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(switch1.isChecked()){

                    webView.setVisibility(View.VISIBLE);
                } else{

                    webView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
