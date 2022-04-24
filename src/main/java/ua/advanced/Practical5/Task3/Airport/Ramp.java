package ua.advanced.Practical5.Task3.Airport;

import java.util.concurrent.TimeUnit;

public class Ramp extends RampPoint {
    public Ramp(int id) {
        super(id);
    }

    @Override
    void using() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);//new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Ramp{id=%d}", id);
    }
}
