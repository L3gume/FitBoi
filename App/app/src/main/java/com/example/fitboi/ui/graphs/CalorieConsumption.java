package com.example.fitboi.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.fitboi.R;
import java.util.ArrayList;
import java.util.List;

/*
This Class is responsible for displaying to the user
his/her calorie breakdown over a certain period of time:
 - THIS WEEK: a barchart, with each column representing
 a day of the week (Mon, Tue,..., Sun)
 - THIS MONTH: same as THIS WEEK, but for 31 days

The Data Structure used in this class (to communicate with the API)

        List<Integer> dto_output = new ArrayList<>();

Have a bug when switching pages between today, week, and month, although the pages
work on their own as expected. In this switch statement at the end,
just swap the 0 with whichever chart you want to observe i.e. swap 0 and 1 to observe
this week, and swap 0 and 2 to observe this month, as the page will start by default with
the case 0.
 */


public class CalorieConsumption extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] options = {"This Week", "This Month"};

    Cartesian cartesian;
    AnyChartView anyChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_calorie_consumption);

        anyChartView = findViewById(R.id.any_chart_view);

        cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        /*
        Spinner
         */
        spinner = (Spinner)findViewById(R.id.graph_spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CalorieConsumption.this,
                android.R.layout.simple_spinner_item,options);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    void showBarChart(List<Integer> calories, int period) {
        List<DataEntry> data = new ArrayList<>();
        String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        if (period == 31) {
            days = new String[31];
            for (int i = 0; i < 31; i++) {
                days[i]= String.valueOf(i+1);
            }
        }
        for (int i = 0; i < period; i++)
            data.add(new ValueDataEntry(days[i], calories.get(i))); // 1 will be calories[i]

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        String title = "Your Calorie Consumption";

        cartesian.title(title);

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value} kcal");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Day");
        cartesian.yAxis(0).title("Calories");

        anyChartView.setChart(cartesian);
    }

        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        //TODO: CalorieDto getCalories = new CalorieDto(username, from, to);
        // get email from the page that opens this one as follow:
        // String email = getIntent().getStringExtra("EXTRA_EMAIL");
        // and from previous page, do as follow (before doing startActivity(intent))
        // intent.putExtra("EXTRA_EMAIL", email.toString());
        List<Integer> dto_output = new ArrayList<>();
        for (int i = 1000; i < 3000; i = i + 50) dto_output.add(i);
        switch (position) {
            case 0: // this week (week is 7 days)
                showBarChart(dto_output.subList(dto_output.size()-8, dto_output.size()-1), 7);
                break;
            case 1: // this month (month is 31 days)
                showBarChart(dto_output.subList(dto_output.size()-8, dto_output.size()-1), 31);
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> parent) {}

}
