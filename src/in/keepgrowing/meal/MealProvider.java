package in.keepgrowing.meal;

import java.util.Arrays;
import java.util.List;

public class MealProvider {

    public static List<Meal> provide() {
        return Arrays.asList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200),
                Meal.healthy("scrambled eggs", GlutenPresence.GLUTEN_FREE, 150),
                Meal.healthy("bruschetta", GlutenPresence.CONTAINS_GLUTEN, 170),
                Meal.healthy("shake", GlutenPresence.GLUTEN_FREE, 200),
                Meal.healthy("rice pudding", GlutenPresence.GLUTEN_FREE, 100),
                Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200),
                Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN, 450),
                Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400),
                Meal.tasty("almond cookie", GlutenPresence.CONTAINS_GLUTEN, 350),
                Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN, 450),
                null,
                null
        );
    }

    public static List<Meal> provideBalanced() {
        return Arrays.asList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200),
                Meal.healthy("scrambled eggs", GlutenPresence.GLUTEN_FREE, 150),
                Meal.healthy("bruschetta", GlutenPresence.CONTAINS_GLUTEN, 170),
                Meal.healthy("shake", GlutenPresence.GLUTEN_FREE, 300),
                Meal.healthy("shake", GlutenPresence.GLUTEN_FREE, 300),
                Meal.healthy("rice pudding", GlutenPresence.GLUTEN_FREE, 300),
                Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200),
                Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN, 450),
                Meal.tasty("ice cream", GlutenPresence.CONTAINS_GLUTEN, 200),
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200),
                Meal.healthy("salad", GlutenPresence.GLUTEN_FREE, 400),
                Meal.tasty("almond cookie", GlutenPresence.CONTAINS_GLUTEN, 350)
                );
    }

    public static List<Meal> provideTasty() {
        return Arrays.asList(
                Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200),
                Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN, 450)
        );
    }

    public static Meal provideCookie() {
        return Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400);
    }

    public static Meal provideEmptyHealthy(){
        return Meal.healthy("empty", GlutenPresence.GLUTEN_FREE, 0);
    }
}