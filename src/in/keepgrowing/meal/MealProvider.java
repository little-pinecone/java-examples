package in.keepgrowing.meal;

import java.util.Arrays;
import java.util.List;

public class MealProvider {

    public static List<Meal> provide() {
        return Arrays.asList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN),
                Meal.healthy("scrambled eggs", GlutenPresence.GLUTEN_FREE),
                Meal.healthy("bruschetta", GlutenPresence.CONTAINS_GLUTEN),
                Meal.healthy("shake", GlutenPresence.GLUTEN_FREE),
                Meal.healthy("rice pudding", GlutenPresence.GLUTEN_FREE),
                Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE),
                Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN),
                Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN),
                Meal.tasty("almond cookie", GlutenPresence.CONTAINS_GLUTEN),
                Meal.tasty("CINNAMON BUN", GlutenPresence.CONTAINS_GLUTEN),
                null,
                null
        );
    }
}