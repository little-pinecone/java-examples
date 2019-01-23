package in.keepgrowing;

import in.keepgrowing.meal.GlutenPresence;
import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;
import in.keepgrowing.meal.MealType;
import in.keepgrowing.printers.MealGroupPrinter;
import in.keepgrowing.printers.MealPrinter;
import in.keepgrowing.printers.Printer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {
        List<Meal> meals = MealProvider.provide();
        Printer<List<Meal>> mealPrinter = new MealPrinter();

        List<Meal> notEmptyMeals = filterOutEmpty(meals);
        mealPrinter.print("notEmptyMeals", notEmptyMeals);

        List<Meal> sanitizedMeals = unifyCharSize(notEmptyMeals);
        mealPrinter.print("sanitizedMeals", sanitizedMeals);

        List<Meal> decentMeals = filterOutCookies(sanitizedMeals);
        mealPrinter.print("decentMeals", decentMeals);

        List<Meal> uniqueMeals = eraseDuplicates(decentMeals);
        mealPrinter.print("uniqueMeals", uniqueMeals);

        Printer<Long> numberPrinter = new Printer<>(System.out::println);

        numberPrinter.print("tasty", countByType(uniqueMeals, MealType.TASTY));
        numberPrinter.print("healthy", countByType(uniqueMeals, MealType.HEALTHY));

        List<Meal> junkFood = Arrays.asList(
                Meal.tasty("french fries", GlutenPresence.GLUTEN_FREE),
                Meal.tasty("chicken wings", GlutenPresence.CONTAINS_GLUTEN));
        List<Meal> upgradedMeals = upgradeMeals(uniqueMeals, junkFood);
        mealPrinter.print("upgradeMeals", upgradedMeals);

        List<Meal> sortedMeals = sortMeals(upgradedMeals);
        mealPrinter.print("sortedMeals", sortedMeals);

        Printer<List<String>> mealNamesPrinter = new Printer<>(System.out::println);

        List<String> healthyMealsNames = getHealthyMealsNames(sortedMeals);
        mealNamesPrinter.print("healthyMealsNames", healthyMealsNames);

        Map<MealType, List<Meal>> mealsPerCategory = groupByCategory(sortedMeals);

        Printer<Map<MealType, List<Meal>>> mealGroupPrinter = new MealGroupPrinter();
        mealGroupPrinter.print("mealsPerCategory", mealsPerCategory);
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

    private static List<Meal> filterOutCookies(List<Meal> meals) {
        return meals.stream()
                .filter(meal -> !meal.getName().contains("cookie"))
                .collect(Collectors.toList());
    }

    private static List<Meal> eraseDuplicates(List<Meal> meals) {
        return meals.stream()
                .distinct()
                .collect(Collectors.toList());

    }

    private static Long countByType(List<Meal> meals, MealType type) {
        return meals.stream()
                .filter(meal -> meal.getType() == type)
                .count();
    }

    private static List<Meal> upgradeMeals(List<Meal> uniqueMeals, List<Meal> junkFood) {
        return Stream.concat(uniqueMeals.stream(), junkFood.stream())
                .collect(Collectors.toList());
    }

    private static List<Meal> sortMeals(List<Meal> meals) {
        return meals.stream()
                .sorted(Comparator.comparing(Meal::getName))
                .collect(Collectors.toList());
    }

    private static List<String> getHealthyMealsNames(List<Meal> meals) {
        return meals.stream()
                .filter(meal -> meal.getType() == MealType.HEALTHY)
                .map(Meal::getName)
                .collect(Collectors.toList());
    }

    private static Map<MealType, List<Meal>> groupByCategory(List<Meal> meals) {
        return meals.stream()
                .collect(Collectors.groupingBy(Meal::getType));
    }
}
