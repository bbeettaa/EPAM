package ua.advanced.Practical5.Task3.Airport;

import java.util.concurrent.TimeUnit;

public class Terminal extends RampPoint {
    public Terminal(int id) {
        super(id);
    }

    @Override
    void using() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Terminal{id=%d}", id);
    }
}