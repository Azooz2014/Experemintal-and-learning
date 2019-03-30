package io.blacketron.whereitssnap2;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;

import static android.os.Build.VERSION_CODES.O;

/**
 * Created by Blacketron on 10/5/2017.
 */

public class CaptureFragment extends Fragment {

    private static final int CAMERA_REQUEST_CODE = 1;
    private ImageView mImageView;

    //The file path for the photo.
    private String mCurrentPhotoPath;

    //Where the capture image is stored.
    private Uri mImageUri = Uri.EMPTY;

    //A reference to the database.
    private DataManager mDataManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataManager = new DataManager(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_capture, container, false);

        Button btnCapture = view.findViewById(R.id.btnCapture);
        Button btnSave = view.findViewById(R.id.btnSave);
        mImageView = view.findViewById(R.id.imageView);
        final EditText editTitle = view.findViewById(R.id.editTextTitle);
        final EditText editTag1 = view.findViewById(R.id.editTextTag1);
        final EditText editTag2 = view.findViewById(R.id.editTextTag2);
        final EditText editTag3 = view.findViewById(R.id.editTextTag3);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File photoFile = null;

                try{

                    photoFile = createImageFile();
                }catch (IOException e){

                    Log.e("Error","Couldn't create file: " + e);
                }

                if(photoFile != null){

                    mImageUri = Uri.fromFile(photoFile);

                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT , Uri.fromFile(photoFile));

                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
            }
        }); //End of capture button.

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(mImageUri != null){

                    if(!mImageUri.equals(Uri.EMPTY)){

                        //We have a photo to save.
                        Photo photo = new Photo();
                        photo.setTitle(editTitle.getText().toString());

                        //Set storage location to the image uri variable.
                        photo.setStorageLocation(mImageUri);

                        //get the tags from the user entery.
                        String tag1 = editTag1.getText().toString();
                        String tag2 = editTag2.getText().toString();
                        String tag3 = editTag3.getText().toString();

                        //Assign the tags string to the photo object.

                        photo.setTag1(tag1);
                        photo.setTag2(tag2);
                        photo.setTag3(tag3);

                        //Send the new object to the DataManager.
                        mDataManager.addPhotos(photo);

                        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(getActivity(), "No image to save", Toast.LENGTH_LONG).show();
                    }
                }else{

                    //Uri is not initialized.
                    Log.e("Error", "uri is null");
                }
            }
        });
        return view;
    } // End of onCreateView.

    private File createImageFile() throws IOException{

        String timeStamp;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }else {

            timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }

        //Create an image file name with a time stamp.
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName /*File name */, ".jpg" /* Extension*/, storageDir /* Folder*/);

        //Save for use with ACTION_VIEW Intent.
        mCurrentPhotoPath = "file: " + image.getAbsolutePath();

        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            try {

                mImageView.setImageURI(Uri.parse(mImageUri.toString()));
            }catch (Exception e){

                Log.e("Error", "Uri is not set" + e);
            }

        }else{

            mImageUri = Uri.EMPTY;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Make sure we don't run out of memory.

        BitmapDrawable bd = (BitmapDrawable) mImageView.getDrawable();

        bd.getBitmap().recycle();

        mImageView.setImageBitmap(null);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static int getCameraRequestCode() {
        return CAMERA_REQUEST_CODE;
    }
}
