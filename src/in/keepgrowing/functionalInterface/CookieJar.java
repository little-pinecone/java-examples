package in.keepgrowing.functionalInterface;

import java.util.function.Consumer;

public class CookieJar {

    public void giveCookie(CookieSupplier cookieSupplier, Consumer<String> cookieConsumer) {
        cookieConsumer.accept("Have this cookie: " + cookieSupplier.giveCookie());
    }
}
