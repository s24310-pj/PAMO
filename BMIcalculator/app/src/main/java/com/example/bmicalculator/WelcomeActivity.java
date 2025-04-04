package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * WelcomeActivity - Aktywność powitalna wyświetlana przy uruchomieniu aplikacji.
 * <p>
 * Ta aktywność wyświetla ekran powitalny przez określony czas ,
 * a następnie automatycznie przekierowuje użytkownika do ekranu głównego aplikacji.
 */
public class WelcomeActivity extends AppCompatActivity {

    private static final int DELAY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, DELAY);
    }

}
