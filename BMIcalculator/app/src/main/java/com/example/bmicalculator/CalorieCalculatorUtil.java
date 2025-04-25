package com.example.bmicalculator;

/**
 * CalorieCalculatorUtil – klasa zawierająca czystą logikę obliczania dziennego zapotrzebowania kalorycznego.
 */
public class CalorieCalculatorUtil {
    private static final double[] ACTIVITY_FACTORS = {1.2, 1.375, 1.55, 1.725, 1.9};

    /**
     * Oblicza dzienne zapotrzebowanie kaloryczne.
     *
     * @param weightKg    waga w kilogramach (np. 70.0)
     * @param heightCm    wzrost w centymetrach (np. 175.0)
     * @param age         wiek w latach (np. 25)
     * @param isMale      true = mężczyzna, false = kobieta
     * @param activityLvl poziom aktywności (0–4)
     * @return zaokrąglona liczba kalorii jako int
     */
    public static int calculateDailyCalories(double weightKg, double heightCm, int age, boolean isMale, int activityLvl) {
        double bmr;
        if (isMale) {
            bmr = 66.47 + 13.75 * weightKg + 5.003 * heightCm - 6.755 * age;
        } else {
            bmr = 655.1 + 9.563 * weightKg + 1.850 * heightCm - 4.676 * age;
        }
        double factor = ACTIVITY_FACTORS[Math.min(Math.max(activityLvl, 0), ACTIVITY_FACTORS.length - 1)];
        return (int) Math.round(bmr * factor);
    }


}
