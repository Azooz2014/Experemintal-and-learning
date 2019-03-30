package io.blacketron.autobackground;

import android.app.WallpaperManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button browseBtn = (Button) findViewById(R.id.btnBrowse);
        Button setBtn = (Button) findViewById(R.id.btnSet);
        imageView = (ImageView) findViewById(R.id.imageView);

        browseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

                try{

                    wallpaperManager.setResource();
                }catch (IOException e){

                    e.getStackTrace();
                }*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){

            Picasso.with(MainActivity.this).load(data.getData()).noPlaceholder().centerCrop().fit()
                    .into(imageView);
        }
    }

    //TODO: code setBtn to set the image as the device background from the imageView.
}
