package com.example.bmicalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalorieCalculatorUtilTest {

    @Test
    public void maleActivity2_returnsExpectedValue() {
        double weight = 70;
        double height = 170;
        int age = 30;
        boolean isMale = true;
        int activityIdx = 2;

        double bmr = 66.47 + 13.75 * weight + 5.003 * height - 6.755 * age;
        double expected = Math.round(bmr * 1.55);

        int actual = CalorieCalculatorUtil.calculateDailyCalories(
                weight, height, age, isMale, activityIdx);

        assertEquals((int) expected, actual);
    }

    @Test
    public void femaleActivity1_returnsExpectedValue() {
        double weight = 50;
        double height = 160;
        int age = 20;
        boolean isMale = false;
        int activityIdx = 1;

        double bmr = 655.1 + 9.563 * weight + 1.850 * height - 4.676 * age;
        double expected = Math.round(bmr * 1.375);

        int actual = CalorieCalculatorUtil.calculateDailyCalories(
                weight, height, age, isMale, activityIdx);

        assertEquals((int) expected, actual);
    }

}
