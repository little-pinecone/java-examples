package in.keepgrowing.printers;

import in.keepgrowing.meal.Meal;

import java.util.List;

public class MealPrinter extends Printer<List<Meal>> {

    public MealPrinter() {
        super(MealPrinter::printMealList);
    }

    private static void printMealList(List<Meal> list) {
        list.forEach(m -> System.out.println( m.getName() + " - " + m.getType()));
    }
}