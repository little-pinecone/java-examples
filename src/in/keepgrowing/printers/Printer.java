package in.keepgrowing.printers;

import java.util.function.Consumer;

public class Printer<SUBJECT> {

    private Consumer<SUBJECT> consumer;

    public Printer(Consumer<SUBJECT> consumer) {
        this.consumer = consumer;
    }

    public void print(String title, SUBJECT subject) {
        System.out.println("[" + title + "]:");
        consumer.accept(subject);
        System.out.println("\n-------------------------------------------------\n");
    }
}
