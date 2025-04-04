package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

/**
 * HomeActivity - Główna strona wyboru aplikacji.
 * <p>
 * Pozwala użytkownikowi wybrać między kalkulatorem BMI a kalkulatorem kalorii.
 * Kliknięcie odpowiedniego przycisku przenosi użytkownika do wybranej funkcji.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button bmiButton = findViewById(R.id.bmiButton);
        Button calorieButton = findViewById(R.id.calorieButton);

        // Przejście do kalkulatora BMI
        bmiButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Przejście do kalkulatora kalorii
        calorieButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CalorieCalculatorActivity.class);
            startActivity(intent);
        });
    }

}
