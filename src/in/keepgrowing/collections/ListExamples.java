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
        howToFetchElement();
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
        removeItem(mealsPrinter);
    }

    private static void addItemsOneByOne(Printer<List<Meal>> printer) {
        ArrayList<Meal> meals = new ArrayList<>();
        meals.add(Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200));
        meals.add(Meal.healthy("scrambled eggs", GlutenPresence.GLUTEN_FREE, 150));
        meals.add(Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200));
        printer.print("meals added one by one", meals);
    }

    private static void appendNewItemsWhileIterating(Printer<List<Meal>> printer) {
        List<Meal> meals = new LinkedList<>(MealProvider.provideBalanced());
        for (Meal meal: new LinkedList<>(meals))
        {
            meals.add(Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400));
        }
        printer.print("chocolate cookies added while iterating over the LinkedList", meals);

    }

    private static void removeItem(Printer<List<Meal>> printer) {
        Meal cookie = Meal.tasty("almond cookie", GlutenPresence.CONTAINS_GLUTEN, 350);
        List<Meal> linkedList = new LinkedList<>(MealProvider.provideBalanced());
        linkedList.remove(cookie);
        printer.print("almond cookie from the LinkedList", linkedList);
    }

    private static void howToFetchElement() {
        Printer<List<Meal>> mealsPrinter = new MealsPrinter();
        List<Meal> meals = new ArrayList<>(MealProvider.provide());
        mealsPrinter.print("meal fetched by (9) index from an ArrayList with null values",
                Collections.singletonList(meals.get(9)));
    }

    private static void howToIterate() {

    }
}
