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
        addAllFromList();
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

    private static void addAllFromList() {
        Printer<List<Meal>> mealsPrinter = new MealsPrinter();
        elementsReferenceTheSameObjectFromOriginalList(mealsPrinter);
        useCollectionsUtil(mealsPrinter);
    }

    private static void elementsReferenceTheSameObjectFromOriginalList(Printer<List<Meal>> printer) {
        List<Meal> meals = Arrays.asList(
                Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200),
                Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200));
        List<Meal> copiedMeals = new ArrayList<>(meals);
        printer.print("meals", meals);
        printer.print("copiedMeals", copiedMeals);
        meals.get(0).setName("modified in meals");
        copiedMeals.add(Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400));
        copiedMeals.get(1).setName("modified in copiedMeals");
        printer.print("meals", meals);
        printer.print("copiedMeals", copiedMeals);

        List<Meal> nullableMeals = null;
        List<Meal> validMeals = new ArrayList<>();
        Optional.ofNullable(nullableMeals).ifPresent(validMeals::addAll);
        printer.print("checked for nulls", validMeals);
    }

    private static void useCollectionsUtil(Printer<List<Meal>> printer) {
        List<Meal> meals = new ArrayList<>();

        Collections.addAll(meals, Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400));
        printer.print("Collections.addAll with individual element", meals);

        Meal[] healthyMeals = new Meal[]{Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200)};
        Collections.addAll(meals, healthyMeals);
        printer.print("Collections.addAll with an array", meals);
    }

    private static void howToModify() {
        Printer<List<Meal>> mealsPrinter = new MealsPrinter();
        addItemsOneByOne(mealsPrinter);
        appendNewItemsWhileIterating(mealsPrinter);
        addFilteredInStream(mealsPrinter);
        removeItem(mealsPrinter);

    }

    private static void addItemsOneByOne(Printer<List<Meal>> printer) {
        List<Meal> meals = new ArrayList<>();
        meals.add(Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200));
        meals.add(Meal.healthy("scrambled eggs", GlutenPresence.GLUTEN_FREE, 150));
        meals.add(Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200));
        printer.print("meals added one by one", meals);
    }

    private static void appendNewItemsWhileIterating(Printer<List<Meal>> printer) {
        List<Meal> meals = new LinkedList<>(MealProvider.provideBalanced());
        for (Meal meal : new LinkedList<>(meals)) {
            meals.add(Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400));
        }
        printer.print("chocolate cookies added while iterating over the LinkedList", meals);
    }

    private static void addFilteredInStream(Printer<List<Meal>> printer) {
        List<Meal> meals = Arrays.asList(
                Meal.tasty("chocolate cookie", GlutenPresence.CONTAINS_GLUTEN, 400),
                Meal.tasty("ice cream", GlutenPresence.GLUTEN_FREE, 200),
                Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN, 450));
        printer.print("meals regardless of their calorific value", meals);
        List<Meal> lowCalorieMeals = new ArrayList<>();
        meals.stream()
                .filter(Meal::isLowCalorie)
                .forEachOrdered((lowCalorieMeals::add));
        printer.print("filtered low-calorie meals", lowCalorieMeals);
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
        mealsPrinter.print("meal fetched by index from an ArrayList with null values - careless way",
                Collections.singletonList(meals.get(9)));

        System.out.println("meal fetched by index from an ArrayList with null values - cautious ways");
        Optional.ofNullable(meals.get(10)).ifPresent(System.out::println);
        System.out.println(Optional.ofNullable(meals.get(10))
                .orElse(Meal.healthy("empty", GlutenPresence.GLUTEN_FREE, 0)));
        System.out.print("\n");
    }

    private static void howToIterate() {

    }
}
