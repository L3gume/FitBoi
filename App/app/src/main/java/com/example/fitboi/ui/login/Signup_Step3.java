package com.example.fitboi.ui.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.fitboi.ui.login.Signup_Step2;
import com.example.fitboi.ui.login.Signup_Step1;

import com.example.fitboi.R;
import com.example.fitboi.ui.main.MainPageActivity;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class Signup_Step3 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button calendar;

    private ImageButton[] btn_gain_lose_weight = new ImageButton[2];
    private ImageButton btn_unfocus_gain_lose_weight;
    private int[] btn_id_gain_lose_weight = {R.id.btn_GainWeight, R.id.btn_LoseWeight};

    private Button[] btn_maintain = new Button[2];
    private Button btn_unfocus_maintain;
    private int[] btn_id_maintain = {R.id.btn_maintain_No, R.id.btn_maintain_Yes};

    private TextView seekbarTV;
    private Button signup3;

    private SeekBar seekBar;
    private boolean calendar_selected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_three);

       calendar = (Button) findViewById(R.id.show_dialog);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekbarTV = (TextView) findViewById(R.id.textViewSB);

        signup3 = (Button) findViewById(R.id.btnSignup_3);
        findViewById(R.id.btn_maintain_Yes).setBackgroundResource(R.drawable.border_white);
        findViewById(R.id.btn_maintain_No).setBackgroundResource(R.drawable.border_white);
        findViewById(R.id.btn_GainWeight).setBackgroundResource(R.drawable.border_white);
        findViewById(R.id.btn_LoseWeight).setBackgroundResource(R.drawable.border_white);


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onStartTrackingTouch(SeekBar seekbar) {}
            public void onStopTrackingTouch(SeekBar seekbar) {}

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                seekbarTV.setText("" + progress);
                seekbarTV.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                //textView.setY(100); just added a value set this properly using screen with height aspect ratio , if you do not set it by default it will be there below seek bar

            }
        });


        for(int i = 0; i < btn_gain_lose_weight.length; i++){
            btn_gain_lose_weight[i] = (ImageButton) findViewById(btn_id_gain_lose_weight[i]);
            btn_gain_lose_weight[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFocus2(btn_unfocus_gain_lose_weight, (ImageButton) findViewById(v.getId()));
                    findViewById(v.getId()).setSelected(true);
                }
            });
        }
        btn_unfocus_gain_lose_weight = btn_gain_lose_weight[0];


        for(int i = 0; i < btn_maintain.length; i++){
            btn_maintain[i] = (Button) findViewById(btn_id_maintain[i]);
            btn_maintain[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFocus(btn_unfocus_maintain, (Button) findViewById(v.getId()));
                    findViewById(v.getId()).setSelected(true);
                }
            });
        }

        btn_unfocus_maintain = btn_maintain[0];

        signup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                boolean signupOk = true;
                if (!findViewById(R.id.btn_GainWeight).isSelected() &&
                        !findViewById(R.id.btn_LoseWeight).isSelected()) {
                    findViewById(R.id.btn_GainWeight).setBackgroundResource(R.drawable.border_red);
                    findViewById(R.id.btn_LoseWeight).setBackgroundResource(R.drawable.border_red);
                    signupOk = false;
                } else {

                }
                if (!findViewById(R.id.btn_maintain_No).isSelected() &&
                        !findViewById(R.id.btn_maintain_Yes).isSelected()) {
                    findViewById(R.id.btn_maintain_No).setBackgroundResource(R.drawable.border_red);
                    findViewById(R.id.btn_maintain_Yes).setBackgroundResource(R.drawable.border_red);
                    signupOk = false;
                } else {
                }
                if (!calendar_selected) {
                    calendar.setBackgroundResource(R.drawable.border_red);
                    signupOk = false;
                } else calendar.setBackgroundResource(R.drawable.border_transparent);

                if (signupOk) {
                    Intent intent = new Intent(Signup_Step3.this, MainPageActivity.class);
                    startActivity(intent);

                }
            }});
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
        calendar.setText(date);
        calendar_selected = true;
    }

    private void setFocus(Button btn_unfocus, Button btn_focus){

        btn_unfocus.setBackgroundResource(R.drawable.border_white);
        btn_focus.setBackgroundResource(R.drawable.border_green);
        this.btn_unfocus_maintain = btn_focus;
    }

    private void setFocus2(ImageButton btn_unfocus, ImageButton btn_focus){

        btn_unfocus.setBackgroundResource(R.drawable.border_white);
        btn_focus.setBackgroundResource(R.drawable.border_green);
        this.btn_unfocus_gain_lose_weight = btn_focus;
    }
}