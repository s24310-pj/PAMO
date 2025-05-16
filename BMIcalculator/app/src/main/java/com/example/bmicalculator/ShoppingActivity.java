package com.example.bmicalculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

/**
 * Ekran wyświetlający listę zakupów potrzebnych do przygotowania
 * jednego z przepisów. Używa RecyclerView i ShoppingAdapter, aby umożliwić odznaczanie
 * zakupionych pozycji.
 */
public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);


        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<ShoppingItem> list = Arrays.asList(
                new ShoppingItem("filet z kurczaka"),
                new ShoppingItem("sałata"),
                new ShoppingItem("pomidor"),
                new ShoppingItem("ogórek"),
                new ShoppingItem("oliwa z oliwek"),
                new ShoppingItem("sól"),
                new ShoppingItem("pieprz")
        );

        rv.setAdapter(new ShoppingAdapter(list));

    }
}