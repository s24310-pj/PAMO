package com.example.bmicalculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


/**
 * Ekran prezentujący wykres liniowy zmian wartości BMI w czasie.
 * Wykorzystuje bibliotekę MPAndroidChart i zamockowane dane pomiarów.
 */
public class BmiHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_history);

        LineChart chart = findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 22.5f));
        entries.add(new Entry(7, 22.8f));
        entries.add(new Entry(14, 23.0f));
        entries.add(new Entry(21, 22.7f));
        entries.add(new Entry(28, 22.4f));


        LineDataSet set = new LineDataSet(entries, "BMI w czasie");
        set.setLineWidth(4f);
        set.setCircleRadius(8f);

        chart.setData(new LineData(set));
        chart.getDescription().setText("Dni od startu");
        chart.invalidate();

    }
}