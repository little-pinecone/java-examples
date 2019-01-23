package in.keepgrowing.meal;

import java.util.Objects;

public class Meal {

    private String name;
    private MealType type;
    private GlutenPresence glutenPresence;

    private Meal(MealType type, String name, GlutenPresence glutenPresence) {
        this.type = type;
        this.name = name;
        this.glutenPresence = glutenPresence;
    }

    public static Meal healthy(String name, GlutenPresence glutenPresence) {
        return new Meal(MealType.HEALTHY, name, glutenPresence);
    }

    public static Meal tasty(String name, GlutenPresence glutenPresence) {
        return new Meal(MealType.TASTY, name, glutenPresence);
    }

    public String getName() {
        return name;
    }

    public MealType getType() {
        return type;
    }

    public GlutenPresence getGlutenPresence() {
        return glutenPresence;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(name, meal.name) &&
                type == meal.type &&
                glutenPresence == meal.glutenPresence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, glutenPresence);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", glutenPresence=" + glutenPresence +
                '}';
    }
}
