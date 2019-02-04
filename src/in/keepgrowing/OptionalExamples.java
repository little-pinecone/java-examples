package in.keepgrowing;

import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;
import in.keepgrowing.printers.MealsPrinter;
import in.keepgrowing.printers.Printer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class OptionalExamples {

    public static void main(String[] args) {
        changeNullsToEmptyMealsInForeach();
        changeMealsNamesInFor();
        changeMealsNamesWhenIteratingWithIterator();
        changeMealsNamesDuringIteration();
        doNotCrashWhenListIsNull(null);
        getMealByIndex();
    }

    private static void changeNullsToEmptyMealsInForeach() {
        List<Meal> mealsWithNulls = MealProvider.provide();
        List<Optional<Meal>> mealsWithOptionals = new ArrayList<>();

        mealsWithNulls.forEach(m -> {
            Optional<Meal> optionalMeal = Optional.ofNullable(m);
            mealsWithOptionals.add(optionalMeal);
        });

        mealsWithOptionals.get(0).ifPresent(m -> m.setName("modified in copy"));
        System.out.println("[optional meals] " + mealsWithOptionals);
        System.out.println("[meals with Nulls] " + mealsWithNulls);
    }

    private static void changeMealsNamesInFor() {
        List<Meal> meals = MealProvider.provide();
        for (Meal meal : meals) {
            Optional.ofNullable(meal).ifPresent(m -> m.setName("change name for not null meals"));
        }
        Printer<List<Meal>> printer = new MealsPrinter();
        printer.print("names changed in for loop", meals);
    }

    private static void changeMealsNamesWhenIteratingWithIterator() {
        List<Meal> meals = MealProvider.provide();
        Iterator<Meal> iterator = meals.iterator();
        while (iterator.hasNext()) {
            Optional.ofNullable(iterator.next()).ifPresent(m -> m.setName("name changed during iterating with iterator"));
        }
        Printer<List<Meal>> printer = new MealsPrinter();
        printer.print("names changed during iterating with iterator", meals);
    }

    private static void changeMealsNamesDuringIteration() {
        List<Meal> meals = MealProvider.provide();
        for (int i = 0; i < meals.size(); i++) {
            Optional.ofNullable(meals.get(i)).
                    ifPresent(meal -> {
                        if (meal.getKilocalories() > 200) {
                            meal.setName("high calorie value");
                        }
                    });
        }
        Printer<List<Meal>> printer = new MealsPrinter();
        printer.print("names changed in high calorie meals", meals);
    }

    private static void doNotCrashWhenListIsNull(List<Meal> meals) {
        System.out.println("[foreach save for nullable list]");
        Optional.ofNullable(meals).ifPresent(m -> m.forEach((System.out::println)));
        System.out.println("\n-------------------------------------------------\n");
    }

    private static void getMealByIndex() {
        List<Meal> meals = MealProvider.provide();
        System.out.println("[meal fetched by index - cautious ways]");
        Optional.ofNullable(meals.get(10)).ifPresent(System.out::println);
        System.out.println(Optional.ofNullable(meals.get(10))
                .orElse(MealProvider.provideEmptyHealthy()));
        System.out.println("\n-------------------------------------------------\n");
    }
}
