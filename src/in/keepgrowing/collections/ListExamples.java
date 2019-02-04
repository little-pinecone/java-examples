package in.keepgrowing.collections;

import in.keepgrowing.meal.GlutenPresence;
import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;
import in.keepgrowing.printers.MealsPrinter;
import in.keepgrowing.printers.Printer;

import java.util.*;
import java.util.stream.Collectors;

public class ListExamples {

    public static void main(String[] args) {
        howToCreate();
        howToModify();
        howToFetchElement();
        howToIterate();
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
        List<Meal> meals = MealProvider.provideTasty();
        List<Meal> referencedMeals = new ArrayList<>(meals);

        meals.get(0).setName("name modified in meals");
        referencedMeals.add(MealProvider.provideCookie());
        referencedMeals.get(1).setName("name modified in referencedMeals");

        printer.print("meals", meals);
        printer.print("referencedMeals", referencedMeals);

        unmodifiableCopyOfList(printer);
        unmodifiableListOfElements(printer);
        unmodifiableArrayAsList();
    }

    private static void unmodifiableCopyOfList(Printer<List<Meal>> printer) {
        List<Meal> newMeals = MealProvider.provideTasty();
        List<Meal> copiedMeals = List.copyOf(newMeals);
        copiedMeals.get(1).setName("name modified in copiedMeals");
        try {
            copiedMeals.add(MealProvider.provideCookie());
        } catch (UnsupportedOperationException e) {
            System.out.println("Adding elements to an unmodifiable list is not allowed.");
            System.out.println("\n-------------------------------------------------\n");
        }
        printer.print("meals", newMeals);
        printer.print("copiedMeals", copiedMeals);
    }

    private static void unmodifiableListOfElements(Printer<List<Meal>> printer) {
        List<Meal> cookies = List.of(MealProvider.provideCookie(), MealProvider.provideCookie());
        cookies.get(1).setName("name modified in cookies");
        try {
            cookies.add(MealProvider.provideCookie());
        } catch (UnsupportedOperationException e) {
            System.out.println("Adding elements to an unmodifiable list is not allowed.");
            System.out.println("\n-------------------------------------------------\n");
        }
        printer.print("cookies", cookies);
    }

    private static void unmodifiableArrayAsList() {
        List<Meal> unmodifiableMeals = MealProvider.provide();
        try {
            unmodifiableMeals.add(MealProvider.provideCookie());
        } catch (UnsupportedOperationException e) {
            System.out.println("Adding elements to an unmodifiable list is not allowed.");
            System.out.println("\n-------------------------------------------------\n");
        }
    }

    private static void useCollectionsUtil(Printer<List<Meal>> printer) {
        List<Meal> meals = new ArrayList<>();

        Meal cookie = MealProvider.provideCookie();
        Collections.addAll(meals, cookie);
        printer.print("Collections.addAll with one element", meals);

        Meal oatmeal = Meal.healthy("oatmeal", GlutenPresence.CONTAINS_GLUTEN, 200);
        Meal[] healthyMeals = new Meal[]{oatmeal};
        Collections.addAll(meals, healthyMeals);
        printer.print("Collections.addAll with an array", meals);
    }

    private static void howToModify() {
        Printer<List<Meal>> mealsPrinter = new MealsPrinter();

        addItemsOneByOne(mealsPrinter);
        appendNewItemsWhileIterating(mealsPrinter);
        addItemAtSpecificIndex(mealsPrinter);
        filterInStream(mealsPrinter);
        filterInForEach(mealsPrinter);
        removeItem(mealsPrinter);
        createIntersectionOfTwoLists(mealsPrinter);
        createSublist(mealsPrinter);
        sort(mealsPrinter);
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
            meals.add(MealProvider.provideCookie());
        }
        printer.print("chocolate cookies added while iterating", meals);
    }

    private static void addItemAtSpecificIndex(Printer<List<Meal>> printer) {
        List<Meal> meals = new LinkedList<>(MealProvider.provideBalanced());
        meals.add(1, MealProvider.provideCookie());
        printer.print("chocolate cookie added at index (1)", meals);

        try {
            meals.add(200, MealProvider.provideCookie());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());
            System.out.println("\n-------------------------------------------------\n");
        }
    }

    private static void filterInStream(Printer<List<Meal>> printer) {
        List<Meal> meals = new ArrayList<>(MealProvider.provideBalanced());
        List<Meal> lowCalorieMeals = new ArrayList<>();

        meals.stream()
                .filter(Meal::isLowCalorie)
                .forEachOrdered((lowCalorieMeals::add));
        printer.print("filtered low-calorie meals", lowCalorieMeals);

        List<Meal> lowCalorieMeals2 = meals.stream()
                .filter(Meal::isLowCalorie)
                .collect(Collectors.toList());
        printer.print("filtered low-calorie meals", lowCalorieMeals2);
    }

    private static void filterInForEach(Printer<List<Meal>> printer) {
        List<Meal> meals = MealProvider.provideBalanced();
        List<Meal> copy = new ArrayList<>();
        meals.forEach(m -> {
            if (m.isLowCalorie()) {
                copy.add(m);
            }
        });
        printer.print("low-calorie meals filtered in forEach", copy);
    }

    private static void removeItem(Printer<List<Meal>> printer) {
        Meal cinnamonBun = Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN, 450);
        List<Meal> meals = new LinkedList<>(MealProvider.provide());
        meals.remove(cinnamonBun);
        printer.print("first cinnamon bun removed", meals);
        try {
            meals.remove(1);
            printer.print("first element removed", meals);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());
            System.out.println("\n-------------------------------------------------\n");
        }
        meals.clear();
        printer.print("all elements removed", meals);
    }

    private static void createIntersectionOfTwoLists(Printer<List<Meal>> printer) {
        List<Meal> meals = new ArrayList<>(MealProvider.provideBalanced());
        List<Meal> tasty = new ArrayList<>(MealProvider.provideTasty());
        meals.retainAll(tasty);
        printer.print("intersection of meals and tasty meals", meals);
    }

    private static void createSublist(Printer<List<Meal>> printer) {
        List<Meal> meals = new ArrayList<>(MealProvider.provideBalanced());
        List<Meal> sublist = meals.subList(0, 4);
        printer.print("First 4 elements of a list", sublist);
    }

    private static void sort(Printer<List<Meal>> mealsPrinter) {
        List<Meal> meals = new ArrayList<>(MealProvider.provideBalanced());
        meals.sort(Comparator.comparing(Meal::getKilocalories));
        mealsPrinter.print("meals sorted by their caloric value", meals);
        meals.sort(Comparator.comparing(Meal::getName));
        mealsPrinter.print("meals sorted by their name", meals);
    }

    private static void howToFetchElement() {
        Printer<List<Meal>> printer = new MealsPrinter();
        List<Meal> meals = new ArrayList<>(MealProvider.provide());

        getMealByIndex(printer, meals);
        getIndexOfMeal(meals);
        checkIfListContainsMeal();
    }

    private static void getMealByIndex(Printer<List<Meal>> mealsPrinter, List<Meal> meals) {
        mealsPrinter.print("[meal carelessly fetched by index]", Collections.singletonList(meals.get(9)));
    }

    private static void getIndexOfMeal(List<Meal> meals) {
        System.out.println("[indexes of requested meals]");
        System.out.println(meals.indexOf(MealProvider.provideCookie()));
        System.out.println(meals.indexOf(null));
        System.out.println(meals.lastIndexOf(null));
        System.out.println("\n-------------------------------------------------\n");
    }

    private static void checkIfListContainsMeal() {
        List<Meal> meals = new LinkedList<>(MealProvider.provide());
        System.out.println("[check if meals contains requested dishes]");
        System.out.println(meals.contains(MealProvider.provideCookie()));
        System.out.println(meals.contains(null));
        System.out.println("\n-------------------------------------------------\n");
    }

    private static void howToIterate() {
        Printer<List<Meal>> printer = new MealsPrinter();

        changeElementsWhenIteratingWithIterator(MealProvider.provideBalanced(), printer);
        changeNamesInFor(MealProvider.provide(), printer);
        modifyElementsWithSpecifiedIndexDuringIteration(MealProvider.provide(), printer);
        modifyElementsDuringIteration(MealProvider.provideBalanced(), printer);
        filterOutNullsInStream(MealProvider.provide(), printer);
    }

    private static void changeElementsWhenIteratingWithIterator(List<Meal> meals, Printer<List<Meal>> printer) {
        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            iterator.next().setName("name changed during iterating with iterator");
        }
        printer.print("names changed during iterating with iterator", meals);
    }

    private static void failToModifyListWhenIterating() {
        List<Meal> balanced = new ArrayList<>(MealProvider.provideBalanced());
        Iterator<Meal> balancedIterator = balanced.iterator();
        while (balancedIterator.hasNext()) {
            if (balancedIterator.next().equals(Meal.tasty("cinnamon bun", GlutenPresence.CONTAINS_GLUTEN, 450))) {
                balanced.add(MealProvider.provideEmptyHealthy());
            }
        }
    }

    private static void changeNamesInFor(List<Meal> meals, Printer<List<Meal>> printer) {
        List<Meal> balancedMeals = new ArrayList<>(MealProvider.provideBalanced());
        for (Meal meal : balancedMeals) {
            meal.setName("changed in for loop");
        }
        printer.print("names changed in for loop", meals);
    }

    private static void modifyElementsWithSpecifiedIndexDuringIteration(List<Meal> meals, Printer<List<Meal>> printer) {
        for (int i = 0; i < meals.size(); i++) {
            if (i % 2 != 0) {
                if (meals.get(i) != null) {
                    meals.get(i).setName("even meal");
                }
            }
        }
        printer.print("names changed in even meals", meals);
    }

    private static void modifyElementsDuringIteration(List<Meal> meals, Printer<List<Meal>> printer) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getKilocalories() > 200) {
                meals.get(i).setName("high calorie value");
            }
        }
        printer.print("names changed in high calorie meals", meals);
    }

    private static void filterOutNullsInStream(List<Meal> meals, Printer<List<Meal>> printer) {
        List<Meal> mealsFromStream = meals.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        printer.print("meals without nulls from stream", mealsFromStream);
    }
}
