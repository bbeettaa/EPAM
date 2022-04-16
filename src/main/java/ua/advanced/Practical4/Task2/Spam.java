package ua.advanced.Practical4.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spam {
    private Thread[] threads;

    public Spam(String message[], int times[]) {
        threads = new Thread[message.length];
        for (int i =0;i<message.length;i++)
            threads[i] = new Worker(message[i],times[i]);
    }

    public static void main(String[] args) throws InterruptedException {
        Spam spam = new Spam(new String[]{"@@@","bbbbbb"}, new int[]{333,222});
        spam.start();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isEnd = true;
            String str;
            while (isEnd) {
                str = bf.readLine();
                if (str.isEmpty()) {
                    spam.stop();
                    isEnd = false;
                }
                Thread.sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        for (var th:threads) {
            th.start();
        }
    }

    public void stop() {
        for (var th:threads) {
            th.interrupt();
        }
    }

    private static class Worker extends Thread {
        String message;
        int delay;

        public Worker(String message, int delay) {
            this.message = message;
            this.delay = delay;
        }

        @Override
        public void run() {
            while(!Thread.interrupted()){
                System.out.println(message);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
