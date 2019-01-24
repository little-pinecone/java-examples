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
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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
                .collect(toList());
    }

    private static List<Meal> unifyCharSize(List<Meal> meals) {
        return meals.stream()
                .map(m -> {
                    m.setName(m.getName().toLowerCase());
                    return m;
                })
                .collect(toList());
    }

    private static List<Meal> eraseDuplicates(List<Meal> meals) {
        return meals.stream()
                .distinct()
                .collect(toList());
    }

    private static List<Meal> filterOutCookies(List<Meal> meals) {
        return meals.stream()
                .filter(meal -> !meal.getName().contains("cookie"))
                .collect(toList());
    }

    private static List<Meal> concatenate(List<Meal> meals) {
        List<Meal> junkFood = createJunkFood();
        return Stream.concat(meals.stream(), junkFood.stream())
                .collect(toList());
    }

    private static List<Meal> createJunkFood() {
        return Arrays.asList(
                Meal.tasty("french fries", GlutenPresence.GLUTEN_FREE, 500),
                Meal.tasty("chicken wings", GlutenPresence.CONTAINS_GLUTEN, 600));
    }

    private static List<Meal> sort(List<Meal> meals) {
        return meals.stream()
                .sorted(Comparator.comparing(Meal::getName))
                .collect(toList());
    }

    private static void group(List<Meal> meals) {
        byType(meals);
        byGlutenPresenceAndType(meals);
        Printer<String> stringPrinter = new Printer<>(System.out::println);
        justNamesByType(meals, stringPrinter);
        averageKilocalorieByType(meals, stringPrinter);
        summaryStatisticsForKilocalorieByType(meals, stringPrinter);
        mostCaloricMealByType(meals, stringPrinter);
    }

    private static void byType(List<Meal> meals) {
        Map<MealType, List<Meal>> mealsByType = meals.stream()
                .collect(groupingBy(Meal::getType));

        Printer<Map<MealType, List<Meal>>> mealsByTypePrinter = new MealsByGroupPrinter<>();
        mealsByTypePrinter.print("meals by type", mealsByType);
    }

    private static void byGlutenPresenceAndType(List<Meal> meals) {
        Map<GlutenPresence, Map<MealType, List<Meal>>> mealsByGlutenPresenceAndType = meals.stream()
                .collect(groupingBy(
                        Meal::getGlutenPresence,
                        groupingBy(Meal::getType)));

        Printer<Map<GlutenPresence, Map<MealType, List<Meal>>>> mealsByGlutenPresenceAndTypePrinter =
                new MealsByTwoGroupsPrinter<>();
        mealsByGlutenPresenceAndTypePrinter.print("meals by gluten presence and type",
                mealsByGlutenPresenceAndType);
    }

    private static void justNamesByType(List<Meal> meals, Printer<String> printer) {
        Map<MealType, String> mealsNamesByType = meals.stream()
                .collect(groupingBy(
                        Meal::getType,
                        mapping(Meal::getName, joining(", ", "Names: ", "."))));

        mealsNamesByType.forEach((mealType, names) -> printer.print(mealType.toString(), names));
    }

    private static void averageKilocalorieByType(List<Meal> meals, Printer<String> printer) {
        Map<MealType, Double> averageKilocalorieByType = meals.stream()
                .collect(groupingBy(
                        Meal::getType,
                        averagingInt(Meal::getKilocalories)
                ));

        averageKilocalorieByType.forEach(
                (mealType, kilocalorie) -> {
                    String title = mealType.toString() + " average kilocalorie";
                    printer.print(title, kilocalorie.toString());
                });
    }

    private static void summaryStatisticsForKilocalorieByType(List<Meal> meals, Printer<String> printer) {
        Map<MealType, IntSummaryStatistics> statistics = meals.stream()
                .collect(groupingBy(
                        Meal::getType,
                        summarizingInt(Meal::getKilocalories)));

        statistics.forEach(
                (mealType, values) -> {
                    String title = mealType.toString() + " kilocalorie statistics";
                    printer.print(title, values.toString());
                });
    }

    private static void mostCaloricMealByType(List<Meal> meals, Printer<String> printer) {
        Map<MealType, Meal> mostCaloricMeals = meals.stream()
                .collect(toMap(Meal::getType, m -> m, mostCaloric()));

        mostCaloricMeals.forEach(((mealType, meal) -> {
            String title = mealType.toString() + " most caloric meal";
            printer.print(title, meal.toString());
        }));
    }

    private static BinaryOperator<Meal> mostCaloric() {
        return BinaryOperator.maxBy(Comparator.comparing(Meal::getKilocalories));
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
                .collect(toList());
    }
}
