package in.keepgrowing;

import in.keepgrowing.meal.GlutenPresence;
import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;
import in.keepgrowing.meal.MealType;
import in.keepgrowing.printers.MealsByGroupPrinter;
import in.keepgrowing.printers.MealsByTwoGroupsPrinter;
import in.keepgrowing.printers.MealsPrinter;
import in.keepgrowing.printers.Printer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {
        List<Meal> meals = MealProvider.provide();
        Printer<List<Meal>> mealPrinter = new MealsPrinter();

        List<Meal> notEmptyMeals = filterOutEmpty(meals);
        mealPrinter.print("not empty meals", notEmptyMeals);

        List<Meal> sanitizedMeals = unifyCharSize(notEmptyMeals);
        mealPrinter.print("sanitized meals", sanitizedMeals);

        List<Meal> uniqueMeals = eraseDuplicates(sanitizedMeals);
        mealPrinter.print("unique meals", uniqueMeals);

        List<Meal> decentMeals = filterOutCookies(uniqueMeals);
        mealPrinter.print("decent meals", decentMeals);

        List<Meal> upgradedMeals = concatenate(decentMeals);
        mealPrinter.print("upgraded meals", upgradedMeals);

        List<Meal> sortedMeals = sort(upgradedMeals);
        mealPrinter.print("sorted meals", sortedMeals);

        group(sortedMeals);
        count(sortedMeals);
        extractNames(sortedMeals);
    }

    private static List<Meal> filterOutEmpty(List<Meal> meals) {
        return meals.stream()
                .filter(Objects::nonNull)
                .filter(meal -> !meal.getName().equals(""))
                .collect(Collectors.toList());
    }

    private static List<Meal> unifyCharSize(List<Meal> meals) {
        return meals.stream()
                .map(m -> {
                    m.setName(m.getName().toLowerCase());
                    return m;
                })
                .collect(Collectors.toList());
    }

    private static List<Meal> eraseDuplicates(List<Meal> meals) {
        return meals.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private static List<Meal> filterOutCookies(List<Meal> meals) {
        return meals.stream()
                .filter(meal -> !meal.getName().contains("cookie"))
                .collect(Collectors.toList());
    }

    private static List<Meal> concatenate(List<Meal> meals) {
        List<Meal> junkFood = createJunkFood();
        return Stream.concat(meals.stream(), junkFood.stream())
                .collect(Collectors.toList());
    }

    private static List<Meal> createJunkFood() {
        return Arrays.asList(
                Meal.tasty("french fries", GlutenPresence.GLUTEN_FREE),
                Meal.tasty("chicken wings", GlutenPresence.CONTAINS_GLUTEN));
    }

    private static List<Meal> sort(List<Meal> meals) {
        return meals.stream()
                .sorted(Comparator.comparing(Meal::getName))
                .collect(Collectors.toList());
    }

    private static void group(List<Meal> meals) {
        Printer<Map<MealType, List<Meal>>> mealsByTypePrinter = new MealsByGroupPrinter<>();
        mealsByTypePrinter.print("meals by type", groupByType(meals));

        Printer<Map<GlutenPresence, Map<MealType, List<Meal>>>> mealsByGlutenPresenceAndTypePrinter =
                new MealsByTwoGroupsPrinter<>();
        mealsByGlutenPresenceAndTypePrinter.print("meals by gluten presence and type",
                groupByGlutenPresenceAndType(meals));
    }

    private static Map<MealType, List<Meal>> groupByType(List<Meal> meals) {
        return meals.stream()
                .collect(Collectors.groupingBy(Meal::getType));
    }

    private static Map<GlutenPresence, Map<MealType, List<Meal>>> groupByGlutenPresenceAndType(List<Meal> sortedMeals) {
        return sortedMeals.stream()
                .collect(Collectors.groupingBy(Meal::getGlutenPresence, Collectors.groupingBy(Meal::getType)));
    }

    private static void count(List<Meal> meals) {
        Printer<Long> numberPrinter = new Printer<>(System.out::println);
        numberPrinter.print("tasty meals amount", countByType(meals, MealType.TASTY));
        numberPrinter.print("healthy meals amount", countByType(meals, MealType.HEALTHY));
    }

    private static Long countByType(List<Meal> meals, MealType type) {
        return meals.stream()
                .filter(meal -> meal.getType() == type)
                .count();
    }

    private static void extractNames(List<Meal> meals) {
        Printer<List<String>> mealNamesPrinter = new Printer<>(System.out::println);
        mealNamesPrinter.print("healthy meals names", getHealthyMealsNames(meals));
    }

    private static List<String> getHealthyMealsNames(List<Meal> meals) {
        return meals.stream()
                .filter(meal -> meal.getType() == MealType.HEALTHY)
                .map(Meal::getName)
                .collect(Collectors.toList());
    }
}
