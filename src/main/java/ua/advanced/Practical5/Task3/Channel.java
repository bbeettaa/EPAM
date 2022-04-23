package ua.advanced.Practical5.Task3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class Channel {
    private volatile boolean busy;

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public abstract void using();
}

