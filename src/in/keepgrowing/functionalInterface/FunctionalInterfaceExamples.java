package in.keepgrowing.functionalInterface;

public class FunctionalInterfaceExamples {

    public static void main(String[] args) {
        CookieJar cookieJar = new CookieJar();
        cookieJar.giveCookie(() -> "Macaroons",
                cookie -> System.out.println(cookie.toUpperCase())
        );
    }
}
