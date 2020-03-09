package com.example.fitboi.ui.profile;


import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitboi.ui.login.Signup_Step2;

import com.example.fitboi.R;
import com.example.fitboi.ui.login.Signup_Step3;
import com.example.fitboi.ui.profile.UserProfileActivity;
import com.example.fitboi.ui.meal.AddMealActivity;
import com.example.fitboi.ui.profile.UserProfileActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.Serializable;

public class EditProfileInfo extends AppCompatActivity{

    ImageView profileImage;
    ImageButton profileImageButton;
    Button saveButton;
    Button updateProfilePicButton;
    private TextView save_profile_changer_er;

    // height and weight fields
    private EditText height;
    private EditText weight;

    // get methods for user input
    public EditText getHeight() {
        return this.height;
    }

    public EditText getWeight() {
        return this.weight;
    }

    private final static int PICK_IMAGE = 100;
    private final static int OTHER_IMAGE = 80;

    private ImageButton[] btn = new ImageButton[3];
    private ImageButton btn_unfocus;
    private int[] btn_id = {R.id.btn_LowAF_edit_profile, R.id.btn_MidAF_edit_profile, R.id.btn_HighAF_edit_profile};

    Uri imageURI;
    Drawable setImage;



    //*
    //
    // NEED API Call for UserDto
    //
    //

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_info);

        getSupportActionBar().setTitle("Edit Profile");

        final LayoutInflater factory = getLayoutInflater();

        final View mainProfile = factory.inflate(R.layout.user_profile, null);

        profileImage = (ImageView) mainProfile.findViewById(R.id.profile_pic);

        saveButton = (Button) findViewById(R.id.save_btn_profile_info);
        profileImageButton = (ImageButton) findViewById(R.id.profile_pic_editable);
        updateProfilePicButton = (Button) findViewById(R.id.upload_new_pp_button);
        save_profile_changer_er = (TextView) findViewById(R.id.edit_profile_error);

        setImage = profileImageButton.getBackground();

        height = (EditText) findViewById(R.id.profile_height_edit);
        weight = (EditText) findViewById(R.id.profile_weight_edit);

        for(int i = 0; i < btn.length; i++){
            btn[i] = (ImageButton) findViewById(btn_id[i]);
            btn[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Signup_Step2.setFocus(btn_unfocus, (ImageButton) findViewById(v.getId()));
                    findViewById(v.getId()).setSelected(true);
                }
            });
        }

        btn_unfocus = btn[0];

        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPhotoFromGallery();

            }
        });

        profileImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent profileActivity = new Intent(getParentActivityIntent());
                profileActivity.putExtra("Current Image", (Serializable) setImage);
            }

        });

        updateProfilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileImage.setBackground(setImage);

            }
        });



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                boolean saveOK = true;

                if (!findViewById(R.id.btn_LowAF).isSelected()
                        & !findViewById(R.id.btn_MidAF).isSelected()
                        & !findViewById(R.id.btn_HighAF).isSelected()) {
                    save_profile_changer_er.setText("Please Determine Your Activity Level");
                    saveOK = false;
                } else {
                    save_profile_changer_er.setText("");
                }

                if (height.getText().toString().matches("")) {
                    height.setBackgroundResource(R.drawable.border_red);
                    saveOK = false;
                } else if (!height.getText().toString().matches("^[0-9]*$")) {
                    save_profile_changer_er.setText("Height should have the format #.## and only contain numbers");
                    saveOK = false;
                } else height.setBackgroundResource(R.drawable.border_transparent);

                if (weight.getText().toString().matches("")) {
                    weight.setBackgroundResource(R.drawable.border_red);
                    saveOK = false;
                } else if (!weight.getText().toString().matches("^[0-9]*$")) {
                    save_profile_changer_er.setText("Weight should have the format ### and only contain numbers");
                    saveOK = false;
                } else weight.setBackgroundResource(R.drawable.border_transparent);

                if (saveOK) {
                    //
                    //
                    // Get specific user and update with new height, weight and Activity Factor
                    //
                    //
                }

            }
        });
    }

    private void getPhotoFromGallery(){
        Intent photoGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photoGallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageURI = data.getData();
            profileImageButton.setImageURI(imageURI);
        }
    }

}
