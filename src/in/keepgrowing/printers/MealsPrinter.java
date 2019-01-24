package in.keepgrowing.printers;

import in.keepgrowing.meal.Meal;

import java.util.List;

public class MealsPrinter extends Printer<List<Meal>> {

    public MealsPrinter() {
        super(MealsPrinter::printMealList);
    }

    private static void printMealList(List<Meal> list) {
        list.forEach(m -> System.out.println( m.getName()));
    }
}