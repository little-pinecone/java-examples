package in.keepgrowing.collections;

import in.keepgrowing.meal.GlutenPresence;
import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;
import in.keepgrowing.printers.MealsPrinter;
import in.keepgrowing.printers.Printer;

import java.util.*;

public class ListExamples {

    public static void main(String[] args) {
        howToCreate();
        howToModify();
    }

    private static void howToCreate() {
        ArrayList<Meal> fromSpecifiedCollection = new ArrayList<>(MealProvider.provide());
        ArrayList<Meal> emptyWithInitiCalapacityOfTen = new ArrayList<>();
        List<Meal> synchronizedList = Collections.synchronizedList(MealProvider.provide());
        List<Meal> fixedSizeBackedBySpecifiedArray = Arrays.asList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200),
                Meal.healthy("scrambled eggs", GlutenPresence.GLUTEN_FREE, 150));
        List<Meal> immutableContainingOneSpecifiedObject = Collections.singletonList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200)
        );
        Set<Meal> set = new HashSet<>(MealProvider.provide());
        ArrayList<Meal> fromSet = new ArrayList<>(set);
    }

    private static void howToModify() {
        Printer<List<Meal>> mealsPrinter = new MealsPrinter();
        addItemsOneByOne(mealsPrinter);
        appendNewItemsWhileIterating(mealsPrinter);
    }

    private static void addItemsOneByOne(Printer<List<Meal>> printer) {
        ArrayList<Meal> meals = new ArrayList<>();
        meals.add(Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200));
        meals.add(Meal.healthy("scrambled eggs", GlutenPresence.GLUTEN_FREE, 150));
        meals.add(Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200));
        printer.print("meals added one by one", meals);
    }

    private static void appendNewItemsWhileIterating(Printer<List<Meal>> printer) {
        ArrayList<Meal> meals = new ArrayList<>(MealProvider.provideBalanced());
        for (Meal meal: new ArrayList<>(meals))
        {
            meals.add(Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400));
        }
        printer.print("cookies added while iterating over the list", meals);
    }

    private static void howToFetchelement() {

    }

    private static void howToIterate() {

    }
}
