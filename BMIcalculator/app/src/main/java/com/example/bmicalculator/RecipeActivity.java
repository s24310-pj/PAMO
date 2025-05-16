package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * RecipeActivity - Aktywność wyświetlająca sugerowane przepisy na podstawie dziennego zapotrzebowania kalorycznego.
 * <p>
 * Użytkownik otrzymuje listę potraw dostosowanych do jego zapotrzebowania kalorycznego.
 * Możliwy jest powrót do ekranu kalkulatora kalorii.
 */
public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        TextView calorieInfo = findViewById(R.id.calorieInfo);
        ListView recipeListView = findViewById(R.id.recipeListView);

        // Pobranie danych o zapotrzebowaniu kalorycznym
        Intent intent = getIntent();
        int calorieRequirement = intent.getIntExtra("CALORIES", 2000);

        calorieInfo.setText(getString(R.string.daily_calorie_needs, calorieRequirement));

        // Pobranie listy przepisów na podstawie kalorii
        List<String> recipes = getRecipesForCalories(calorieRequirement);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Konfiguracja adaptera listy i ustawienie jej w widoku
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipes);
        recipeListView.setAdapter(adapter);

        Button shoppingListButton = findViewById(R.id.shoppingListButton);
        shoppingListButton.setOnClickListener(v -> {
            Intent i = new Intent(RecipeActivity.this, ShoppingActivity.class);
            startActivity(i);
        });
    }


    /**
     * Metoda zwracająca listę przykładowych przepisów dostosowanych do zapotrzebowania kalorycznego użytkownika.
     *
     * @param calories Ilość kalorii obliczona dla użytkownika.
     * @return Lista sugerowanych potraw z ich wartością kaloryczną.
     */
    private List<String> getRecipesForCalories(int calories) {
        List<String> recipes = new ArrayList<>();

        if (calories < 1800) {
            recipes.add(getString(R.string.recipe_salad_chicken));
            recipes.add(getString(R.string.recipe_oatmeal_fruit));
            recipes.add(getString(R.string.recipe_sandwich_salmon));
        } else if (calories < 2500) {
            recipes.add(getString(R.string.recipe_chicken_rice));
            recipes.add(getString(R.string.recipe_pasta_pesto));
            recipes.add(getString(R.string.recipe_greek_salad));
        } else {
            recipes.add(getString(R.string.recipe_beef_burger));
            recipes.add(getString(R.string.recipe_steak_fries));
            recipes.add(getString(R.string.recipe_homemade_pizza));
        }
        return recipes;
    }
}
