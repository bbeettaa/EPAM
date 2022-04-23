package ua.advanced.Practical5.Task2;


/*class Payment {
    private int amount;
    public synchronized void doPayment() {
        try {
            System.out.println("Start payment");
            while (amount <= 0) {
                this.wait();
            }
// payment code
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Payment is closed");
    }
    public synchronized void init() {
        System.out.println("Init amount:");
        amount = new Scanner(System.in).nextInt();
        this.notify();
    }
}

    public static void main(String[] args) {
        Payment payment = new Payment();
        new Thread(() -> payment.doPayment()).start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        payment.init();
    }*/

import ua.advanced.Practical5.Task2.Exchange.Broker;
import ua.advanced.Practical5.Task2.Exchange.Share;
import ua.advanced.Practical5.Task2.Exchange.TradePalce.Exchange;
import ua.advanced.Practical5.Task2.Exchange.TradePalce.Market;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Exchange exchange = new Exchange(90, new Market());
        List<Broker> list = new LinkedList<>();
        List<Share> shareList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++)
                shareList.add(new Share(new Random().nextInt(10),
                        new Random().nextInt(25,75),
                        "Company " + j));
            list.add(new Broker(i,shareList, exchange));
            shareList.clear();
        }
        exchange.registryBrokers(list);
    }
}