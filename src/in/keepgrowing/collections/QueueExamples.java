package in.keepgrowing.collections;

import in.keepgrowing.meal.Meal;
import in.keepgrowing.meal.MealProvider;
import in.keepgrowing.printers.MealsQueuePrinter;

import java.util.*;
import java.util.stream.Collectors;

public class QueueExamples {

    public static void main(String[] args) {

        List<Meal> meals = MealProvider.provide().stream().filter(Objects::nonNull).collect(Collectors.toList());
        MealsQueuePrinter printer = new MealsQueuePrinter();

        priorityQueue(meals, printer);

        Queue<Meal> linkedList = new LinkedList<>(meals);
        printer.print("linkedList", linkedList);

        Deque<Meal> linkedListDeque = new LinkedList<>(meals);
        printer.print("linkedListDeque", linkedListDeque);

        Deque<Meal> arrayDeque = new ArrayDeque<>(meals);
        printer.print("arrayDeque", arrayDeque);
    }

    private static void priorityQueue(List<Meal> meals, MealsQueuePrinter printer) {
        Comparator<Meal> comparator = Comparator.comparing(Meal::getKilocalories);
        Queue<Meal> priorityQueue = new PriorityQueue<>(12, comparator);
        priorityQueue.addAll(meals);
        printer.print("priorityQueue", priorityQueue);
    }
}
