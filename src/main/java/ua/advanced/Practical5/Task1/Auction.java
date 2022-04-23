package ua.advanced.Practical5.Task1;

public class Auction {
    Lot[] lots;

    Auction(Lot... lots) {
        this.lots = lots;
    }

    void start() {
            for (var lot : lots) {
                lot.start();
                try {
                    lot.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
    static public void notify(int i){

    }
}