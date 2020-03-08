package com.example.fitboi.ui.profile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Context;
import android.app.Dialog;


import androidx.appcompat.app.AppCompatActivity;

import com.example.fitboi.R;
import com.example.fitboi.ui.login.Signup_Step3;

public class ViewGoals extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //
    //
    // Need GoalDTO to set field values to the current ones
    //
    //

    private ImageButton[] btn_gain_lose_weight = new ImageButton[2];
    private ImageButton btn_unfocus_gain_lose_weight;
    private int[] btn_id_gain_lose_weight = {R.id.btn_GainWeight, R.id.btn_LoseWeight};

    private Button calendar;
    private Button saveGoalButton;
    private Button deleteGoalButton;
    private boolean calendar_selected = false;
    private SeekBar seekBar;
    private TextView seekbarTV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view_goals);
        getSupportActionBar().setTitle("My Goals");

        calendar = (Button) findViewById(R.id.date_picker_updated_goal);
        saveGoalButton = (Button) findViewById(R.id.save_updated_goal_button);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        deleteGoalButton = (Button) findViewById(R.id.delete_goal_button);

        findViewById(R.id.btn_GainWeight_updated_goal).setBackgroundResource(R.drawable.border_white);
        findViewById(R.id.btn_LoseWeight_updated_goal).setBackgroundResource(R.drawable.border_white);


        //
        //
        // Need to set fields to current values ; get values from GoalDTO
        //
        //

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
                    Signup_Step3.setFocus2(btn_unfocus_gain_lose_weight, (ImageButton) findViewById(v.getId()));
                    findViewById(v.getId()).setSelected(true);
                }
            });
        }
        btn_unfocus_gain_lose_weight = btn_gain_lose_weight[0];

        saveGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                boolean saveOK = true;

                while(true) {

                    if (!findViewById(R.id.btn_GainWeight).isSelected() &&
                            !findViewById(R.id.btn_LoseWeight).isSelected()) {
                        findViewById(R.id.btn_GainWeight).setBackgroundResource(R.drawable.border_red);
                        findViewById(R.id.btn_LoseWeight).setBackgroundResource(R.drawable.border_red);
                        saveOK = false;
                    } // can delete else if statements
                    else if(findViewById(R.id.btn_GainWeight).isSelected()){
                        if(findViewById(R.id.btn_LoseWeight).isSelected()){
                            saveOK = true;
                        }
                    }else if (findViewById(R.id.btn_LoseWeight).isSelected()){
                        if(findViewById(R.id.btn_GainWeight).isSelected()){
                            saveOK = true;
                        }
                    }

                    if (!calendar_selected) {
                        calendar.setBackgroundResource(R.drawable.border_red);
                        saveOK = false;
                    } else calendar.setBackgroundResource(R.drawable.border_transparent);


                    if (saveOK) {

                        saveOK = showSaveConfirmationDialog(this)[0];

                        if(saveOK){
                            //
                            //
                            // Call on Goal DTO to update
                            //
                            //

                        }



                    }

                }
            }});

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        calendar.setText(date);
        calendar_selected = true;


    }

    private boolean[] showSaveConfirmationDialog(View.OnClickListener context) {
        final boolean[] choice = {false};
        final Dialog dialog = new Dialog((Context) context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.profile_view_goals);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        TextView textView = (TextView) dialog.findViewById(R.id.save_confirmation_edit_foal);
        Button confirmSaveButton = (Button) dialog.findViewById(R.id.btn_goal_confirm_save);
        Button cancelSaveButton = (Button) dialog.findViewById(R.id.btn_goal_cancel_save);

        DisplayMetrics displayMetrics = ((Context) context).getResources().getDisplayMetrics();
        int dialogWidth = (int)(displayMetrics.widthPixels * 0.65);
        int dialogHeight = (int)(displayMetrics.heightPixels * 0.65);
        dialog.getWindow().setLayout(dialogWidth, dialogHeight);

        dialog.show();

        cancelSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                choice[0] = false;
            }
        });

        confirmSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice[0] = false;
                dialog.dismiss();
            }
        });

        return choice;
    }

}
