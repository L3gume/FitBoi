package com.example.fitboi.ui.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitboi.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class Signup_Step1 extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    public  EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button signUp;
    private TextView errorPrompt;

    public EditText getFirstName() {
        return this.firstName;
    }

    public EditText getLastName() {
        return this.lastName;
    }

    public EditText getEmail() {
        return this.email;
    }

    public EditText getPassword() {
        return this.password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_one);

        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        confirmPassword = (EditText) findViewById(R.id.etRetypePassword);
        signUp = (Button) findViewById(R.id.btnSignup_3);
        errorPrompt = (TextView) findViewById(R.id.etSignupError);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstName.getText().toString().isEmpty()
                        || lastName.getText().toString().isEmpty()
                        || email.getText().toString().isEmpty()
                        || password.getText().toString().isEmpty()
                        || confirmPassword.getText().toString().isEmpty()) {
                    errorPrompt.setText("At least a field is empty");
                } else
                    validatePassword(password.getText().toString(), confirmPassword.getText().toString());
            }
        });
    }

    private void validatePassword(String password, String confirmPassword) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        if (password.length() < 8) {
            errorPrompt.setText("Password length should be greater than 8 characters");
        } else if (!matcher.matches()) {
            errorPrompt.setText("Password should contain an uppercase, a lowercase, a number, and a special character");

        } else if (!password.equals(confirmPassword)) {
            errorPrompt.setText("Passwords do not match");
        } else {
            Intent intent = new Intent(Signup_Step1.this, Signup_Step2.class);
            intent.putExtra("EXTRA_EMAIL", email.toString());
            startActivity(intent);
        }
    }
}