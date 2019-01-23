package in.keepgrowing.printers;

import in.keepgrowing.meal.Meal;

import java.util.List;
import java.util.Map;

public class MealsByTwoGroupsPrinter<GROUP1, GROUP2> extends Printer<Map<GROUP1, Map<GROUP2, List<Meal>>>> {

    public MealsByTwoGroupsPrinter() {
        super(MealsByTwoGroupsPrinter::printMealsByTwoGroups);
    }

    private static <GROUP1, GROUP2> void printMealsByTwoGroups(Map<GROUP1, Map<GROUP2, List<Meal>>> meals) {
        meals.forEach((group1, mealTypeListMap) -> {
            System.out.println("- " + group1);
            mealTypeListMap.forEach((group2, mealList) -> {
                System.out.println("-- " + group2);
                mealList.forEach(meal -> System.out.println("--- " + meal.getName()));
            });
            System.out.print("\n");
        });
    }
}