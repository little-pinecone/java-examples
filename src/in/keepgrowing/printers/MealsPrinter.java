package in.keepgrowing.printers;

import in.keepgrowing.meal.Meal;

import java.util.List;
import java.util.Optional;

public class MealsPrinter extends Printer<List<Meal>> {

    public MealsPrinter() {
        super(MealsPrinter::printMealList);
    }

    private static void printMealList(List<Meal> list) {
        list.forEach(m -> Optional.ofNullable(m)
                .ifPresent(meal -> System.out.println(m.getName())));
    }
}