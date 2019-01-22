package in.keepgrowing.meal;

import java.util.Objects;

public class Meal {

    public static final String TYPE_HEALTHY ="healthy";
    public static final String TYPE_TASTY="tasty";

    private String type;
    private String name;

    private Meal(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public static Meal healthy(String name) {
        return new Meal(TYPE_HEALTHY, name);
    }

    public static Meal tasty(String name) {
        return new Meal(TYPE_TASTY, name);
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(type, meal.type) &&
                Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
