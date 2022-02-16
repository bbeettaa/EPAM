package ua.advanced.Practical1.Task1.house;

import java.util.ArrayList;
import java.util.List;

public class House<T> {

    private final ArrayList residents = new ArrayList();

    public void enter(T resident) {
        residents.add(resident);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("There are following residents in the house:\n");
        for (Object resident : residents) {
            builder.append(resident.toString()).append("\n");
        }
        return builder.toString();
    }
}
