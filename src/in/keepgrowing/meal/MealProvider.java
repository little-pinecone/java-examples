package in.keepgrowing.meal;

import java.util.Arrays;
import java.util.List;

public class MealProvider {

    public static List<Meal> provide() {
        return Arrays.asList(
                Meal.healthy("oatmeal"), Meal.healthy("scrambled eggs"),
                Meal.healthy("bruschetta"), Meal.healthy("shake"), Meal.healthy("rice pudding"),
                Meal.tasty("ice cream"), Meal.tasty("cinnamon bun"),
                Meal.tasty("chocolate cookie"), Meal.tasty("almond cookie"),
                Meal.tasty("CINNAMON BUN"), null, null
        );
    }
}