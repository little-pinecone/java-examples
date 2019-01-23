package in.keepgrowing.printers;

import in.keepgrowing.meal.Meal;

import java.util.List;
import java.util.Map;

public class MealsByGroupPrinter<GROUP> extends Printer<Map<GROUP, List<Meal>>> {

    public MealsByGroupPrinter() {
        super(MealsByGroupPrinter::printMealsByGroup);
    }

    private static <GROUP> void printMealsByGroup(Map<GROUP, List<Meal>> mealGroups) {
        mealGroups.forEach((group, mealList) -> {
            System.out.println("- " + group);
            mealList.forEach(m -> System.out.println("-- " + m.getName()));
            System.out.print("\n");
        });
    }
}