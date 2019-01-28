package in.keepgrowing.collections;

import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;
import in.keepgrowing.printers.MealsQueuePrinter;
import in.keepgrowing.printers.Printer;

import java.util.*;
import java.util.stream.Collectors;

public class QueueExamples {

    public static void main(String[] args) {

        List<Meal> meals = provideNonNullMeals();
        MealsQueuePrinter printer = new MealsQueuePrinter();

        priorityQueue(meals, printer);
        fifoQueue(meals, printer);
        lifoQueue(meals);
    }

    private static List<Meal> provideNonNullMeals() {
        return MealProvider.provide().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static void priorityQueue(List<Meal> meals, MealsQueuePrinter printer) {
        Comparator<Meal> comparator = Comparator.comparing(Meal::getKilocalories);
        Queue<Meal> priorityQueue = new PriorityQueue<>(12, comparator);
        priorityQueue.addAll(meals);
        printer.print("meals removed from the queue according to their calorific value", priorityQueue);
    }

    private static void fifoQueue(List<Meal> meals, MealsQueuePrinter printer) {
        Queue<Meal> linkedList = new LinkedList<>(meals);
        printer.print("FIFO queue", linkedList);
    }

    private static void lifoQueue(List<Meal> meals) {
        Printer<Boolean> printer = new Printer<>(System.out::println);
        printer.print("meal list is balanced", isBalanced(meals));
        printer.print("meal list is balanced", isBalanced(MealProvider.provideBalanced()));
    }

    private static boolean isBalanced(List<Meal> meals) {
        Deque<Meal> deque = new LinkedList<>();
//        use ArrayDeque if you need a resizable-array implementation of the Deque interface that has no capacity restrictions
//        Deque<Meal> deque = new ArrayDeque<>();

        for (Meal meal : meals) {
            if (listStartsWithHighCalorieMeal(deque, meal)) {
                return false;
            }
            if (meal.isLowCalorie()) {
                deque.add(meal);
            } else {
                removeLatestFromDequeIfMatchingMealFond(deque, meal);
            }
        }
        return deque.isEmpty();
    }

    private static boolean listStartsWithHighCalorieMeal(Deque<Meal> queue, Meal meal) {
        return !meal.isLowCalorie() && queue.isEmpty();
    }

    private static boolean highCalorieMealMatchLatestLowCalorieMeal(Deque<Meal> queue, Meal meal) {
        return queue.peekLast().isLowCalorie() && queue.peekLast().getType().equals(meal.getType());
    }

    private static void removeLatestFromDequeIfMatchingMealFond(Deque<Meal> deque, Meal meal) {
        if (deque.peekLast() != null) {
            if (highCalorieMealMatchLatestLowCalorieMeal(deque, meal)) {
                deque.pollLast();
            }
        }
    }
}
