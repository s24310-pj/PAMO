package com.example.bmicalculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.widget.SeekBar;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Espresso test sprawdzający, że po wpisaniu danych i ustawieniu
 * poziomu aktywności (index 2 → factor 1.55)
 * pojawia się oczekiwana wartość kalorii i przycisk jest włączony.
 */
@RunWith(AndroidJUnit4.class)
public class CalorieCalculatorActivityTest {

    @Rule
    public ActivityScenarioRule<CalorieCalculatorActivity> rule =
            new ActivityScenarioRule<>(CalorieCalculatorActivity.class);

    /**
     * Pomocnicza akcja Espresso, która ustawia progress na SeekBar.
     */
    private static ViewAction setSeekBarProgress(final int progress) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(SeekBar.class);
            }
            @Override
            public String getDescription() {
                return "Ustawia SeekBar na wartość " + progress;
            }
            @Override
            public void perform(UiController uiController, View view) {
                ((SeekBar) view).setProgress(progress);
            }
        };
    }

    @Test
    public void enteringDataCalculatesCaloriesAndEnablesButton() {
        onView(withId(R.id.weightEditText))
                .perform(typeText("70"), closeSoftKeyboard());

        onView(withId(R.id.heightEditText))
                .perform(typeText("170"), closeSoftKeyboard());

        onView(withId(R.id.ageEditText))
                .perform(typeText("30"), closeSoftKeyboard());

        onView(withId(R.id.activitySeekBar))
                .perform(setSeekBarProgress(2));

        double bmr = 66.47 + 13.75 * 70 + 5.003 * 170 - 6.755 * 30;
        int expected = (int) Math.round(bmr * 1.55);

        onView(withId(R.id.caloriesTextView))
                .check(matches(withText(String.valueOf(expected))));

        onView(withId(R.id.recipeButton))
                .check(matches(isEnabled()));
    }

}
