package in.keepgrowing.printers;

import in.keepgrowing.meal.Meal;

import java.util.Queue;

public class MealsQueuePrinter extends Printer<Queue<Meal>> {

    public MealsQueuePrinter() {
        super(MealsQueuePrinter::printMealQueue);
    }

    private static void printMealQueue(Queue<Meal> queue) {
        while (queue.size() != 0) {
            Meal currentMeal = queue.poll();
            System.out.println(currentMeal.getName() + " [kcal: " + currentMeal.getKilocalories() + "]");
        }
    }
}
