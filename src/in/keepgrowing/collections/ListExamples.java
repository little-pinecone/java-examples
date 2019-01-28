package in.keepgrowing.collections;

import in.keepgrowing.meal.GlutenPresence;
import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;

import java.util.*;

public class ListExamples {

    public static void main(String[] args) {
        howToCreate();
    }

    private static void howToCreate() {
        ArrayList<Meal> fromSpecifiedCollection = new ArrayList<>(MealProvider.provide());
        ArrayList<Meal> emptyWithInitiCalapacityOfTen = new ArrayList<>();
        List<Meal> synchronizedList = Collections.synchronizedList(MealProvider.provide());
        List<Meal> fixedSizeBackedBySpecifiedArray = Arrays.asList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200),
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200));
        List<Meal> immutableContainingOnlySpecifiedObject = Collections.singletonList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200)
        );
        Set<Meal> set = new HashSet<>(MealProvider.provide());
        ArrayList<Meal> fromSet = new ArrayList<>(set);
    }
}
