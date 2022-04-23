package ua.advanced.Practical5.Task3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AudioChannel extends Channel {
    private int сhannellId;

    public AudioChannel(int id) {
        this.сhannellId = id;
    }

    public int getСhannellId() {
        return сhannellId;
    }

    public void setСhannellId(int id) {
        this.сhannellId = id;
    }

    @Override
    public void using() {
        try {
// using channel
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AudioChannel{");
        sb.append("id=").append(сhannellId);
        sb.append(", busy=").append(isBusy()).append('}');
        return sb.toString();
    }
}