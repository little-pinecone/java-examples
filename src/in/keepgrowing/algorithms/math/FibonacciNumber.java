package in.keepgrowing.algorithms.math;

import in.keepgrowing.printers.Printer;

import java.util.LinkedList;
import java.util.List;

public class FibonacciNumber {

    public static void main(String[] args) {
        var printer = new Printer<>(System.out::println);
        printer.print("Fibonacci sequence", getSequence(11));
    }

    /**
     * @see <a href="https://github.com/little-pinecone/javascript-algorithms/blob/master/src/algorithms/math/fibonacci/fibonacci.js">Example</a>
     */
    private static List<Integer> getSequence(Integer length) {
        var sequence = new LinkedList<Integer>();
        if (length <= 1) {
            return sequence;
        }
        var currentValue = 1;
        var previousValue = 0;
        int iterationsCounter = length;
        while (iterationsCounter > 0) {
            currentValue += previousValue;
            sequence.add(currentValue);
            previousValue = currentValue - previousValue;

            iterationsCounter -= 1;
        }

        return sequence;
    }
}
