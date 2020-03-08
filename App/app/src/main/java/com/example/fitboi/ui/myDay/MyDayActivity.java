package com.example.fitboi.ui.myDay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitboi.R;
import com.example.fitboi.api.MetricsAPI;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.FoodDto;
import com.example.fitboi.dto.MetricDto;
import com.example.fitboi.dto.MetricsDto;
import com.example.fitboi.dto.UserDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class MyDayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.my_day_activity);
        recyclerView = findViewById(R.id.my_day_activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Day");
        recyclerView.setHasFixedSize(true);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today= formatter.format(date);
        Log.d("date", today);

        // This is temporary

//        UserDto user = new UserDto("hello1@gmail.com", "Bob1", "SpongeBob1", "abc123", "1999-07-23", "Male", 150);
//        MetricDto metricDto1 = new MetricDto(0, today, 300);
//        UserDto successAddUser = UserAPI.addUser(user, null);
////        Log.d("User", successAddUser.toString());


//        Consumer
//        List<UserDto> userDtoList = UserAPI.getUsers(null);

        final ArrayList<MetricDto> addedMetric = new ArrayList<>();
        Consumer<List<MetricDto>> consumer = new Consumer<List<MetricDto>>() {
            @Override
            public void accept(List<MetricDto> metricDtos) {
                addedMetric.addAll(metricDtos);
            }
        };

        MetricsAPI.getAllUserMetrics("hello1@gmail.com", consumer);
//        List<MetricDto> metricDtoList = MetricsAPI.getAllUserMetrics("hello@gmail.com", null);
        Log.d("Metric", addedMetric.toString());

        FoodDto foodItemDto1 = new FoodDto(1, "Chicken", 800, 2, 200, 200, 200);
        FoodDto foodItemDto2 = new FoodDto(2, "Apple", 100, 3, 100, 1000, 2);
        FoodDto foodItemDto3 = new FoodDto(3, "Bacon", 500, 3, 245, 5666,5);
        FoodDto foodItemDto4 = new FoodDto(4, "Eggs", 200, 3, 556,678 ,7 );
        FoodDto foodItemDto5 = new FoodDto(5, "Chocolate Waffles", 1200, 3, 678, 906, 432);

//        Log.d("Metric", hello.toString());

        ArrayList<FoodDto> foodItemDtos = new ArrayList<FoodDto>();
        foodItemDtos.add(foodItemDto1);
        foodItemDtos.add(foodItemDto2);
        foodItemDtos.add(foodItemDto3);
        foodItemDtos.add(foodItemDto4);
        foodItemDtos.add(foodItemDto5);


//        Log.d("myDay", foodItemDtos.toString());

        this.mAdapter = new MyDayCardAdapter(foodItemDtos);
        recyclerView.setAdapter(this.mAdapter);
        this.layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_day_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.add_activity_button:
                createActivityDialogue();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createActivityDialogue() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Activity")
                .setMessage("Which Type of Activity do you want to Create?")
                .setPositiveButton("Meal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, MyDayActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                })
                .setNeutralButton("Exercise", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, MyDayActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                })
                .show();
    }
}
