package ua.advanced.Practical4.Task2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Part2 {
    public static void main(String[] args) throws InterruptedException {

        Spam spam = new Spam(new String[]{"@@@", "####"}, new int[]{333, 555});

        System.setIn(new InputStream() {
            boolean isRead = true;
            @Override
            public int read(){
                int i = -1;
                if(isRead)
                    try {
                        Thread.sleep(2000);
                        isRead = false;
                        i = "\n".getBytes(StandardCharsets.UTF_8)[0];
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                return i;
            }
        });

        Thread t = new Thread(() -> {
            try {
                Spam.main(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.setIn(System.in);
    }
}