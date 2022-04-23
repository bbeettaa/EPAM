package ua.advanced.Practical5.Task1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Participant> participants = new LinkedList<>();
        for (int num = 0; num < 5; num++) {
            int cash = 100 + new Random().nextInt(50);
            participants.add(new Participant(num, cash, new ConsoleNotification()));
        }

        Lot lot0 = new Lot(0, 50, new ConsoleNotification(), participants);
        Lot lot1 = new Lot(1, 50, new ConsoleNotification(), participants);
        Lot lot2 = new Lot(2, 75, new ConsoleNotification(), participants);
        lot2.add(new Participant(6,105,new ConsoleNotification()));
        lot2.add(new Participant(7,95,new ConsoleNotification()));
        lot2.add(new Participant(8,87,new ConsoleNotification()));
        Lot lot3 = new Lot(3, 95, new ConsoleNotification(), participants);

        Auction auction = new Auction(lot0, lot1,lot2,lot3);
        auction.start();
    }
}
//счетчик для нескольких проб в одном лоте