package io.blacketron.javameetui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.file.Files;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  int value = 0;

    private Button btnAdd;
    private Button btnTake;
    private Button btnGrow;
    private Button btnShrink;
    private Button btnHide;
    private Button btnReset;

    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnTake = (Button) findViewById(R.id.btnTake);
        btnGrow = (Button) findViewById(R.id.btnGrow);
        btnShrink = (Button) findViewById(R.id.btnShrink);
        btnHide = (Button) findViewById(R.id.btnHide);
        btnReset = (Button) findViewById(R.id.btnReset);

        txtValue = (TextView) findViewById(R.id.txtValue);

        btnAdd.setOnClickListener(this);
        btnTake.setOnClickListener(this);
        btnGrow.setOnClickListener(this);
        btnShrink.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        float size;

        switch(view.getId()){

            case R.id.btnAdd:

                value ++;
                txtValue.setText("" + value);

                break;

            case R.id.btnTake:

                value --;
                txtValue.setText("" + value);

                break;

            case R.id.btnReset:

                value = 0;
                txtValue.setText("" + value);

                break;

            case R.id.btnGrow:

                size = txtValue.getTextScaleX();
                txtValue.setTextScaleX(size + 1);

                break;

            case R.id.btnShrink:

                size = txtValue.getTextScaleX();
                txtValue.setTextScaleX(size - 1);

                break;

            case R.id.btnHide:

                if(txtValue.getVisibility() == View.VISIBLE){

                    txtValue.setVisibility(View.INVISIBLE);
                    btnHide.setText("show");
                }else{

                    txtValue.setVisibility(View.VISIBLE);
                    btnHide.setText("hide");
                }

                break;
        }

    }
}
