package com.example.fitboi.ui.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitboi.R;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Signup extends AppCompatActivity {

    private Button Login;
    private Button Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        Login = (Button)findViewById(R.id.btnLogin_1);
        Signup = (Button)findViewById(R.id.btnSignup_1);

        // Go to Login Activity if user presses on "Login"
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (Login_Signup.this, Login.class);
                startActivity(intent);
            }
        });

        // Go to Signup Activity if user presses on "Signup"
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (Login_Signup.this, Signup_Step1.class);
                startActivity(intent);
            }
        });
    }
}
