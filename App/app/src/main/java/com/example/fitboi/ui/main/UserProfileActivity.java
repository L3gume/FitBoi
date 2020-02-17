package com.example.fitboi.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fitboi.R;
import com.example.fitboi.ui.meal.AddMealActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UserProfileActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainPageActivity.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        uiSetup();
    }

    private void uiSetup() {
        getSupportActionBar().setTitle("My Profile");


    }

    private void launchMainPageActivity() {
        Intent intent = new Intent(this, MainPageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
