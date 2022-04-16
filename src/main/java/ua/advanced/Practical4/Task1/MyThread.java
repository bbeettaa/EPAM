package ua.advanced.Practical4.Task1;

import javax.sound.midi.Soundbank;

class MyThread extends Thread {
    long milliseconds;

    public MyThread() {
        this(2000,"MyThread");
    }

    public MyThread(long milliseconds, String name) {
        setName(name);
        this.milliseconds = milliseconds;
    }

    @Override
    public void run() {
        for(int i=0;i<milliseconds;i+=350)
            System.out.println(getName());
    }
}
