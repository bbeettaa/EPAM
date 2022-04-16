package ua.advanced.Practical4.Task3;

public class Part3 {
    private int counter;
    private int counter2;
    private final Object locker = new Object();
    private final int iterationTime;

class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < iterationTime; i++)
                try {
                    System.out.println(String.format("%s = %s", counter, counter2));
                    counter++;
                    Thread.sleep(100);
                    counter2++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
        }
    }

    /*private final Thread thSync = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (locker) {
                for (int i = 0; i < iterationTime; i++)
                    try {
                        System.out.println(String.format("%s = %s", counter, counter2));
                        counter++;
                        locker.wait(100);
                        counter2++;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
            }
        }
    });*/

    public Part3(int iterationTime) {
        this.iterationTime = iterationTime;
    }

    public static void main(String[] args) throws InterruptedException {
        Part3 p3 = new Part3(2);
        p3.compare();
        p3.compareSync();
    }

    public void compare() throws InterruptedException {
        MyThread th1 = new MyThread();
        th1.start();
        th1.join();
    }

    synchronized public void compareSync() throws InterruptedException {
        MyThread thSync  = new MyThread();
        thSync.start();
        thSync.join();
    }
}
