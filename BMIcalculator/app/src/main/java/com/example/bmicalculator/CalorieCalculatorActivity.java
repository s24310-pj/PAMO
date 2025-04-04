package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 * CalorieCalculatorActivity - Aktywność umożliwiająca obliczenie dziennego zapotrzebowania kalorycznego użytkownika.
 * <p>
 * Użytkownik wprowadza swoją wagę, wzrost, wiek, płeć oraz poziom aktywności fizycznej.
 * Na podstawie tych danych wyliczana jest dzienna ilość kalorii potrzebna do utrzymania wagi.
 * Użytkownik może również przejść do ekranu z sugerowanymi przepisami na podstawie obliczonego wyniku.
 */
public class CalorieCalculatorActivity extends AppCompatActivity {

    private EditText weightEditText, heightEditText, ageEditText;
    private TextView caloriesTextView, activityLevelText;
    private RadioButton maleRadioButton;
    private SeekBar activitySeekBar;
    private Button recipeButton;

    private final double[] activityFactors = {1.2, 1.375, 1.55, 1.725, 1.9};

    private int dailyCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        activityLevelText = findViewById(R.id.activityLevelText);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        ageEditText = findViewById(R.id.ageEditText);
        caloriesTextView = findViewById(R.id.caloriesTextView);
        activityLevelText = findViewById(R.id.activityLevelText);
        activitySeekBar = findViewById(R.id.activitySeekBar);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        recipeButton = findViewById(R.id.recipeButton);

        final String[] activityLevels = getResources().getStringArray(R.array.activity_levels);
        activityLevelText.setText(activityLevels[activitySeekBar.getProgress()]);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Przycisk przejścia do aktywności z przepisami
        recipeButton.setOnClickListener(v -> {
            Intent intent = new Intent(CalorieCalculatorActivity.this, RecipeActivity.class);
            intent.putExtra("CALORIES", dailyCalories);
            startActivity(intent);
        });

        // Zmiana wartości suwaka aktywności fizycznej
        activitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                activityLevelText.setText(activityLevels[progress]);
                calculateCalorieNeeds();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /**
     * Oblicza dzienne zapotrzebowanie kaloryczne użytkownika na podstawie podanych danych.
     * <p>
     * Wzory BMR (podstawowa przemiana materii):
     * Dla mężczyzn: BMR = 66.47 + (13.75 * masa) + (5.003 * wzrost) - (6.755 * wiek)
     * Dla kobiet: BMR = 655.1 + (9.563 * masa) + (1.850 * wzrost) - (4.676 * wiek)
     * Wynik BMR jest mnożony przez współczynnik aktywności fizycznej uzyskany z suwaka.
     * Obliczony wynik jest wyświetlany w polu `caloriesTextView`, a przycisk przejścia do przepisów zostaje aktywowany.
     */
    private void calculateCalorieNeeds() {
        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            double height = Double.parseDouble(heightEditText.getText().toString());
            int age = Integer.parseInt(ageEditText.getText().toString());

            int activityIndex = activitySeekBar.getProgress();
            double activityFactor = activityFactors[activityIndex];

            double bmr;
            if (maleRadioButton.isChecked()) {
                bmr = 66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
            } else {
                bmr = 655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age);
            }

            dailyCalories = (int) (bmr * activityFactor);

            caloriesTextView.setText(String.format(Locale.getDefault(), "%d", dailyCalories));
            recipeButton.setEnabled(true);
        } catch (NumberFormatException e) {
            caloriesTextView.setText(getString(R.string.error));
            recipeButton.setEnabled(false);
        }
    }
}
