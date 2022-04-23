package ua.advanced.Practical5.Task1;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
    INotifier notifier;
    private Integer participantId;
    private int currentLotPrice;
    private int cash;
    private boolean lost;
    private CountDownLatch latchEndBid;
    private CountDownLatch latchAuctionBegin;
    public CountDownLatch blockedCountDown;
    private LotStatus lotStatus;

    public Participant(int id, int cash, INotifier notifier) {
        this.participantId = id;
        this.cash = cash;
        this.notifier = notifier;
        this.blockedCountDown = new CountDownLatch(0);
    }

    public void setLot(Lot lot) {
        this.currentLotPrice = lot.getStartLotPrice();
        this.latchEndBid = lot.getLatchEndAuction();
        this.latchAuctionBegin = lot.getLatchAuctionBegin();
        this.lotStatus = lotStatus;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public int getCurrentLotPrice() {
        return currentLotPrice;
    }

    public void setCurrentLotPrice(int currentLotPrice) {
        this.currentLotPrice = currentLotPrice;
    }

    public void setBlockedCountDown(int blockedLots) {
        this.blockedCountDown = new CountDownLatch(blockedLots);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isLost() {
        return lost;
    }

    @Override
    public void run() {
        lost = false;
        notify(String.format("(Participant #%d) -> specifies a cash: %d", participantId, cash));
        try {
            if (blockedCountDown.getCount() > 0) {
                notify(String.format("(Participant #%d) -> blocked for %d lots", participantId, blockedCountDown.getCount()));
                lost = true;
            } else if (cash < currentLotPrice) {
                lostBet();
            } else {
                    makeBet();
            }
            latchAuctionBegin.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lostBet() {
        lost = true;
        latchAuctionBegin.countDown();
        notify(String.format("(Participant #%d) -> lost, cause (cache '%d' < price '%d')", participantId, cash, currentLotPrice));
    }

    public void makeBet() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(3500));

        int delta = new Random().nextInt(10);
        currentLotPrice += delta;
        currentLotPrice = Math.min(currentLotPrice, cash);

        notify(String.format("(Participant #%d) -> made a bet: %d", participantId, currentLotPrice));
    }

    public boolean pay() {
        try {
            Thread.sleep(new Random().nextInt(3000));
            boolean isPay = new Random().nextBoolean();
            isPay = new Random().nextBoolean();
            if (isPay) {
                setCash(cash - currentLotPrice);
                notify(String.format("(Participant #%d) -> balance: %d", participantId, cash));
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void notify(String message) {
        notifier.notify(message, this);
    }
}