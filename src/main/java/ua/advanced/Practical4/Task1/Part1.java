package ua.advanced.Practical4.Task1;

public class Part1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(2000, "MyThread");
        myThread.run();

        Thread myRunnable = new Thread(
                () -> {
                    for (int i = 0; i < 2000; i += 334)
                        System.out.println(Thread.currentThread().getName());
                }, "myRunnable");
        myRunnable.start();
    }


}
