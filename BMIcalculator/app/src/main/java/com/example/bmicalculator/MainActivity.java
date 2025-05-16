package com.example.bmicalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


/**
 * Autor: Michał Kaczmarek s24310
 * <p>
 * Główna aktywność aplikacji Kalkulatora BMI.
 * Pozwala użytkownikowi na wpisanie masy ciała i wzrostu,
 * a następnie oblicza BMI i klasyfikuje kategorię BMI.
 */
public class MainActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView bmiTextView;
    private TextView bmiCategoryTextView;


    /**
     * Metoda wywoływana przy tworzeniu aktywności.
     * Inicjalizuje elementy interfejsu użytkownika i dodaje nasłuchiwacze zdarzeń.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        bmiTextView = findViewById(R.id.bmiTextView);
        bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);
        Button historyButton = findViewById(R.id.historyButton);

        // Przycisk cofnięcia do wyboru funkcji
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Przycisk przejścia do wykresu BMI
        historyButton.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, BmiHistoryActivity.class);
            startActivity(i);
        });

        // Watcher który przy zmianie tekstu kalkuluje BMI
        TextWatcher bmiTextWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateBMI();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        weightEditText.addTextChangedListener(bmiTextWatcher);
        heightEditText.addTextChangedListener(bmiTextWatcher);
    }

    /**
     * Oblicza wartość BMI na podstawie masy ciała i wzrostu wprowadzonego przez użytkownika.
     * Wyświetla wynik oraz kategorię BMI, zmieniając również kolor tła dla odpowiedniej klasyfikacji.
     */
    private void calculateBMI() {
        String weightInput = weightEditText.getText().toString();
        String heightInput = heightEditText.getText().toString();

        if (weightInput.isEmpty() || heightInput.isEmpty()) {
            bmiTextView.setText("");
            bmiCategoryTextView.setText("");
            bmiCategoryTextView.setBackgroundColor(Color.rgb(221, 221, 221));
            return;
        }

        try {
            double weight = Double.parseDouble(weightInput);
            double height = Double.parseDouble(heightInput);

            if (height <= 0) {
                bmiTextView.setText(R.string.invalid_height);
                return;
            }

            double heightMeters = height / 100.0;
            double bmi = weight / (heightMeters * heightMeters);
            bmiTextView.setText(String.format(Locale.getDefault(), "%.2f", bmi));

            String bmiCategory;
            int color;
            if (bmi < 18.5) {
                bmiCategory = getString(R.string.underweight);
                color = ContextCompat.getColor(this, R.color.yellow);
            } else if (bmi < 25) {
                bmiCategory = getString(R.string.optimal);
                color = ContextCompat.getColor(this, R.color.green);
            } else if (bmi < 30) {
                bmiCategory = getString(R.string.overweight);
                color = ContextCompat.getColor(this, R.color.orange);
            } else {
                bmiCategory = getString(R.string.obesity);
                color = ContextCompat.getColor(this, R.color.red);
            }
            bmiCategoryTextView.setText(bmiCategory);
            bmiCategoryTextView.setBackgroundColor(color);

        } catch (NumberFormatException e) {
            bmiTextView.setText(R.string.invalid_mass);
            bmiCategoryTextView.setText("");
            bmiCategoryTextView.setBackgroundColor(Color.rgb(221, 221, 221));
        }
    }
}