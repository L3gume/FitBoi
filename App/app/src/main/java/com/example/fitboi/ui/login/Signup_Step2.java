package com.example.fitboi.ui.login;

import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;

import android.app.DatePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.fitboi.R;

import java.util.Calendar;
import java.util.function.Consumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Signup_Step2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText height;
    private EditText weight;
    private Button dateOfBirth;

    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private boolean sex;
    public EditText getHeight() {
        return this.height;
    }
    public EditText getWeight() {
        return this.weight;
    }
    public Button getDateOfBirth() {
        return dateOfBirth;
    }

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    private Button signup;
    private TextView signup_er;

    private ImageButton[] btn = new ImageButton[3];
    private ImageButton btn_unfocus;
    private int[] btn_id = {R.id.btn_LowAF, R.id.btn_MidAF, R.id.btn_HighAF};
    private boolean calendar_selected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);

        dateOfBirth = (Button) findViewById(R.id.show_dialog2);

        signup = (Button) findViewById(R.id.btnSignup_2);
        signup_er = (TextView) findViewById(R.id.tv_step2_error);
        height = (EditText) findViewById(R.id.etHeight);
        weight = (EditText) findViewById(R.id.etWeight);

        for(int i = 0; i < btn.length; i++){
            btn[i] = (ImageButton) findViewById(btn_id[i]);
            btn[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFocus(btn_unfocus, (ImageButton) findViewById(v.getId()));
                    findViewById(v.getId()).setSelected(true);
                }
            });
        }

        btn_unfocus = btn[0];

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

        radioSexGroup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);
                if (selectedId == R.id.rbMale)
                    sex = true;
                else
                    sex = false;
                radioSexButton.setBackgroundResource(R.drawable.border_green);
            }
        });

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                boolean signupOk = true;
                if (radioSexGroup.getCheckedRadioButtonId() == -1) {
                    radioSexGroup.setBackgroundResource(R.drawable.border_red);
                    signupOk = false;
                } else radioSexGroup.setBackgroundResource(R.drawable.border_transparent);

                if (!calendar_selected) {
                    dateOfBirth.setBackgroundResource(R.drawable.border_red);
                    signupOk = false;
                } else dateOfBirth.setBackgroundResource(R.drawable.border_transparent);

                if (!findViewById(R.id.btn_LowAF).isSelected()
                        & !findViewById(R.id.btn_MidAF).isSelected()
                        & !findViewById(R.id.btn_HighAF).isSelected()) {
                    signup_er.setText("Please Determine Your Activity Level");
                    signupOk = false;
                } else {
                    signup_er.setText("");
                }

                if (height.getText().toString().matches("")) {
                    height.setBackgroundResource(R.drawable.border_red);
                    signupOk = false;
                } else if (!height.getText().toString().matches("^[0-9]*$")) {
                    signup_er.setText("Height should have the format #.## and only contain numbers");
                    signupOk = false;
                } else height.setBackgroundResource(R.drawable.border_transparent);

                if (weight.getText().toString().matches("")) {
                    weight.setBackgroundResource(R.drawable.border_red);
                    signupOk = false;
                } else if (!weight.getText().toString().matches("^[0-9]*$")) {
                    signup_er.setText("Weight should have the format ### and only contain numbers");
                    signupOk = false;
                } else weight.setBackgroundResource(R.drawable.border_transparent);

                if (signupOk) {
                    Intent intent = new Intent(Signup_Step2.this, Signup_Step3.class);
                    startActivity(intent);
                    int age = Calendar.getInstance().get(Calendar.YEAR) - birthYear;
                    String email = getIntent().getStringExtra("EXTRA_EMAIL");
                    String firstName = getIntent().getStringExtra("EXTRA_FIRST_NAME");
                    String lastName = getIntent().getStringExtra("EXTRA_LAST_NAME");
                    String password = getIntent().getStringExtra("EXTRA_PASSWORD");


                    String username = firstName.charAt(0) + lastName;
                            UserDto userToAdd = new UserDto(email, firstName, username,
                                    password, age,
                                    Integer.parseInt(height.getText().toString()), sex);
                }

            }
        });

    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        this.birthYear = year;
        dateOfBirth.setText(date);
        calendar_selected = true;
    }

    public static void setFocus(ImageButton btn_unfocus, ImageButton btn_focus){
        btn_unfocus.setBackgroundResource(R.drawable.border_white);
        btn_focus.setBackgroundResource(R.drawable.border_green);
        // this.btn_unfocus = btn_focus;
    }
}