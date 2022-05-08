package ua.advanced.Practical7_8.Entity;

public class Actor extends Entity {
    String name;
    String date;

    public Actor() {
    }

    public Actor(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name='" + name +", date='" + date + '}';
    }

    public String getDate() {
        return date;
    }
}
