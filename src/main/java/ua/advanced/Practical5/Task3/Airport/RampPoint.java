package ua.advanced.Practical5.Task3.Airport;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class RampPoint {
    AtomicBoolean busy;
    int id;

    public RampPoint(int id) {
        this.id = id;
        busy = new AtomicBoolean(false);
    }

    abstract void using();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBusy() {
        return busy.get();
    }

    public void setBusy(boolean busy) {
        this.busy.set(busy);
    }
}
