package com.example.fitboi.ui.profile;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;

import com.example.fitboi.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UserProfileActivity extends AppCompatActivity {
    private static final String LOG_TAG = UserProfileActivity.class.getSimpleName();

    public static ImageView profileImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        uiSetup();
    }

    private void uiSetup() {
        getSupportActionBar().setTitle("My Profile");
        profileImage = (ImageView) findViewById(R.id.profile_pic);

        final Button editProfileInfoButton = findViewById((R.id.profile_info_btn));
        editProfileInfoButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchEditProfileInfoActivity();
            }
        }));

        final Button viewGoalsButton = findViewById((R.id.view_goals_btn));
        viewGoalsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchViewGoalsActivity();
            }
        }));


    }

    private void launchEditProfileInfoActivity() {
        Intent intent = new Intent(this, EditProfileInfo.class);
        startActivity(intent);
    }

    private void launchViewGoalsActivity(){
        Intent intent = new Intent(this, ViewGoals.class);
        startActivity(intent);
    }

}
