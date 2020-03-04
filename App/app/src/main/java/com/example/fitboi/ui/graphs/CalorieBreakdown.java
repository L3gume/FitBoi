package com.example.fitboi.ui.graphs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView.OnItemSelectedListener;
import com.anychart.APIlib;
import com.anychart.enums.ScaleStackMode;
import com.anychart.scales.Linear;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.Orientation;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.fitboi.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/*
This Class is responsible for displaying to the user
his/her calorie breakdown over a certain period of time:
- TODAY: a pie chart split in 3, showing the proportion of
 the split between protein, carbs, and fat
 - THIS WEEK: a barchart, with each column representing
 a day of the week (Mon, Tue,..., Sun), each column split
 in three, representing the proportions between protein, carbs, and fat
 - THIS MONTH: same as THIS WEEK, but for 31 days

The Data Structure used in this class (to communicate with the API)

        List<double[]> dto_output = new ArrayList<>();

is a list of arrays, each array containing 3 elements (protein, carbs, fat respectively)
each element of this list thus represents a day of consumption in terms of macros
Can make more consistent (because we can make mistakes with order of macros in array) by
making a list of macro objects, each containing attributes protein, carbs, fat.

Have a bug when switching pages between today, week, and month, although the pages
work on their own as expected. In this switch statement at the end,
just swap the 0 with whichever chart you want to observe i.e. swap 0 and 1 to observe
this week, and swap 0 and 2 to observe this month, as the page will start by default with
the case 0.
 */


public class CalorieBreakdown extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] options = {"Today", "This Week", "This Month"};

    Pie pie;
    Cartesian cartesian;
    AnyChartView anyChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_calorie_breakdown);

        pie = AnyChart.pie();
        cartesian = AnyChart.cartesian();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(CalorieBreakdown.this, event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });


        /*
        Spinner
         */
        spinner = (Spinner)findViewById(R.id.graph_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CalorieBreakdown.this,
                android.R.layout.simple_spinner_item,options);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    protected void showPie(double[] macros) {

        anyChartView = findViewById(R.id.any_chart_view1);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);


        List<DataEntry> data = new ArrayList<>();

        data.add(new ValueDataEntry("Protein", macros[0]));
        data.add(new ValueDataEntry("Carbohydrates", macros[1]));
        data.add(new ValueDataEntry("Fat", macros[2]));
        pie.data(data);

        String title = "Your Calorie Breakdown ";
        pie.title(title);

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Macro")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);

//        List<double[]> dto_output = new ArrayList<>();
//        for (int i = 0; i < 100; i++) dto_output.add(new double[]{i, i, i});
//        showBarChart(dto_output.subList(dto_output.size()-8, dto_output.size()-1), 7);

    }

    void showBarChart(List<double[]> macros, int period) {

        anyChartView = findViewById(R.id.any_chart_view1);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);


        cartesian.legend().enabled(true);
        cartesian.animation(true);

        cartesian.title("Your Calorie Breakdown");

        cartesian.yScale().stackMode(ScaleStackMode.VALUE);
        cartesian.crosshair(true);

        List<DataEntry> data = new ArrayList<>();
        String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        if (period == 31) {
            days = new String[31];
            for (int i = 0; i < 31; i++) {
                days[i]= String.valueOf(i+1);
            }
        }

        for (int i = 0; i < period; i++)
            data.add(new CustomDataEntry(days[i],
                    (macros.get(i)[0] * 4 + macros.get(i)[1] * 4 + macros.get(i)[2] * 9),
                    macros.get(i)[0], macros.get(i)[1], macros.get(i)[2]));

        Set set = Set.instantiate();
        set.data(data);
        Mapping column1Data = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping column2Data = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping column3Data = set.mapAs("{ x: 'x', value: 'value4' }");


        Column protein_data = cartesian.column(column1Data);
        protein_data.name("Protein");

        Column carbs_data = cartesian.column(column2Data);
        carbs_data.name("Carbs");

        Column fat_data = cartesian.column(column3Data);
        fat_data.name("Fat");

        Linear scalesLinear = Linear.instantiate();
        scalesLinear.ticks("{ interval: 25 }");

        com.anychart.core.axes.Linear extraYAxis = cartesian.yAxis(1d);
        extraYAxis.orientation(Orientation.RIGHT)
                .scale(scalesLinear);
        extraYAxis.labels()
                .padding(0d, 0d, 0d, 5d)
                .format("{%Value} kcal");
        Mapping lineData = set.mapAs("{ x: 'x', value: 'value' }");
        Line line = cartesian.line(lineData);
        line.name("Calories");
        line.yScale(scalesLinear);

        cartesian.yScale().ticks("{ interval: 40 }");
        cartesian.xAxis(0).stroke("1 #a18b7e");
        cartesian.xAxis(0).labels().fontSize("#a18b7e");
        cartesian.yAxis(0).stroke("1 #a18b7e");
        cartesian.yAxis(0).labels().fontColor("#a18b7e");
        cartesian.yAxis(0).labels().format("{%Value}");
        cartesian.yAxis(0).title().enabled(true);
        cartesian.yAxis(0).title().text("Macro Split");

        anyChartView.setChart(cartesian);
    }
/*
This method combines n successive elements into one element.
e.g. [1, 2, 3, 4, 5, 6] with n =2 => [3, 7, 11]
Might be useful eventually for graphing
 */
    List<double[]> filterMacros(List<double[]> macros, int period) {
        List<double[]> result = new ArrayList<>();
        double[] temp = new double[3];

        for (int i = 0; i < macros.size(); i++) {
            if ((i % period) == 0) {
                result.add(temp);
                temp = new double[3];
            }
            else {
                temp[0] += macros.get(i)[0];
                temp[1] += macros.get(i)[1];
                temp[2] += macros.get(i)[2];
            }
        }
        // showBarChart(result);
        return result;
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        //TODO: MacroDto getMacros = new MacroDto(email, from, to);
        // get email from the page that opens this one as follow:
        // String email = getIntent().getStringExtra("EXTRA_EMAIL");
        // and from previous page, do as follow (before doing startActivity(intent))
        // intent.putExtra("EXTRA_EMAIL", email.toString());
        // output will be list of macro objects (each macro object is double protein, double carbs, double fat)
        List<double[]> dto_output = new ArrayList<>();
        for (int i = 0; i < 100; i++) dto_output.add(new double[]{i, i, i});

        switch (position) {
            case 0: // today
                showPie(dto_output.get(dto_output.size()-1));
                break;
            case 1: // this week (week is 7 days)
                showBarChart(dto_output.subList(dto_output.size()-8, dto_output.size()-1), 7);
                break;
            case 2:  // this month (assuming month is 31 days)
                showBarChart(dto_output.subList(dto_output.size()-32, dto_output.size()-1), 31);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
        }
    }
}

