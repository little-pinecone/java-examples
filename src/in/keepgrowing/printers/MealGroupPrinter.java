package in.keepgrowing.printers;

import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealType;

import java.util.List;
import java.util.Map;

public class MealGroupPrinter extends Printer<Map<MealType, List<Meal>>> {

    public MealGroupPrinter() {
        super(MealGroupPrinter::printMealGroups);
    }

    private static void printMealGroups(Map<MealType, List<Meal>> mealGroups) {
        mealGroups.forEach((groupName, mealList) -> {
            System.out.println("-" + groupName);
            mealList.forEach(m -> System.out.println("--" + m.getName()));
            System.out.print("\n");
        });
    }
}