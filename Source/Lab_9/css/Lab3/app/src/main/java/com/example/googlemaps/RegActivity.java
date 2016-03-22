package com.example.googlemaps;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by ibraincpu6 on 09-02-2016.
 */
public class

        RegActivity extends AppCompatActivity implements View.OnClickListener {
    TextView firstName, lastName, email, password;
    Button register;
    ImageView profile;
    String fName, lName, mEmail, mPassword;
    String PROFILE_PICTURE_NAME = "PROFILE_PICTURE", CAMERA_PROFILE_PICTURE = "CAMERA_PICTURE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstName = (TextView) findViewById(R.id.first_name);
        lastName = (TextView) findViewById(R.id.last_name);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        profile = (ImageView) findViewById(R.id.profile);
        register.setOnClickListener(this);
        profile.setOnClickListener(this);
        loadImageFromStorage(PROFILE_PICTURE_NAME);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.profile) {
            showPopUp(profile);
        } else if (id == R.id.register) {
            if (isValidate()) {
                SharedPreferences sharedPreferences=getSharedPreferences("REGISTRATION",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Name", fName + " " + lName);
                editor.commit();
                Intent intent=new Intent(RegActivity.this,MapsActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    private void showPopUp(ImageView imageButton) {
        PopupMenu popup = new PopupMenu(this, imageButton);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.profile_picture_options, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                //Toast.makeText(mContext, "You Clicked : " + item.getTitle() + ":" + position, Toast.LENGTH_SHORT).show();
                String title = item.getTitle().toString();
                if (title.equals("Gallery")) {
                    Crop.pickImage(RegActivity.this);
                } else if (title.equals("Camera")) {
                    btnTakePhoto();
                }
                return true;
            }
        });
        popup.show();//showing popup menu
    }

    private boolean isValidate() {
        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        mEmail = email.getText().toString();
        mPassword = password.getText().toString();
        if(fName.equals("")){
            firstName.setError("Enter first name");
            firstName.requestFocus();
            return false;
        }else if(lName.equals("")){
            lastName.setError("Enter lastt name");
            lastName.requestFocus();
            return false;
        }else if(mPassword.equals("")){
            password.setError("Enter password");
            password.requestFocus();
            return false;
        }else if(mEmail.equals("")){
            email.setError("Enter email");
            email.requestFocus();
            return false;
        }else{
            File file=loadImageFromStorage(PROFILE_PICTURE_NAME);
            if(file==null){
                Toast.makeText(this, "Take a profile picture", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private File loadImageFromStorage(String imageName) {
        File file = new File(getCacheDir(), imageName);
        if (file.exists()) {
            try {
                Uri destination = Uri.fromFile(file);
                profile.setImageURI(destination);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            file = null;
            profile.setImageResource(R.drawable.ic_camera);
        }
        return file;
    }

    private static final int CAMERA_REQUEST = 1888, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1122;

    public void btnTakePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Yes", "Camera Intent result");
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Bitmap bmp = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    bmp = Bitmap.createScaledBitmap(bmp, 600, 600, false);
                    byte[] byteArray = stream.toByteArray();
                    // convert byte array to Bitmap

                    Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    saveToInternalStorage(bitmap, CAMERA_PROFILE_PICTURE);
                    Uri source = Uri.fromFile(new File(getCacheDir(), CAMERA_PROFILE_PICTURE));
                    beginCrop(source);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to fetch image", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (requestCode == CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE) {
            Log.d("Yes", "I am here");
            //Get our saved file into a bitmap object:
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
            Uri source = Uri.fromFile(file);
            beginCrop(source);
        }
        if (requestCode == 9162 && resultCode == Activity.RESULT_OK) {
            beginCrop(data.getData());
        }
        if (requestCode == 6709 && resultCode == Activity.RESULT_OK) {
            handleCrop(resultCode, data);
        }

    }

    private void saveToInternalStorage(Bitmap bitmapImage, String imageName) {

        OutputStream outStream = null;
        File file = new File(getCacheDir(), imageName);
        try {
            outStream = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void beginCrop(Uri source) {
        Log.d("StatusB", "I am here");
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    public void handleCrop(int resultCode, Intent result) {
        Log.d("StatusH", "I am here");
        if (resultCode == Activity.RESULT_OK) {
            profile.setImageURI(Crop.getOutput(result));
            profile.setVisibility(View.VISIBLE);
            Bitmap bitmap = ((BitmapDrawable) profile.getDrawable()).getBitmap();
            bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, false);
            saveToInternalStorage(bitmap, PROFILE_PICTURE_NAME);
            profile.setImageBitmap(bitmap);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
