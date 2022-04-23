package ua.advanced.Practical5.Task1;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class Lot extends Thread {
    public INotifier notifier;
    private CountDownLatch latchEndAuction;
    private CountDownLatch latchAuctionBegin;
    private List<Participant> participants;
    //private LotStatus lotStatus;
    private int lotId;
    private int startLotPrice;

    public Lot(int lotId, int startLotPrice, INotifier notifier, List<Participant> participants) {
        this.participants = new LinkedList<>();
        this.participants.addAll(participants);
        this.notifier = notifier;

        latchAuctionBegin = new CountDownLatch(participants.size());
        latchEndAuction = new CountDownLatch(1);

        this.startLotPrice = startLotPrice;
        this.lotId = lotId;

        changeStatusAndNotify(LotStatus.Created);
    }

    public CountDownLatch getLatchEndAuction() {
        return latchEndAuction;
    }

    public CountDownLatch getLatchAuctionBegin() {
        return latchAuctionBegin;
    }


    public int getStartLotPrice() {
        return startLotPrice;
    }

    public boolean add(Participant e) {
        return participants.add(e);
    }

    @Override
    public void run() {
        try {
            notify("-".repeat(30));
            notify(String.format("(Lot ID: %d) -> lot price: %s", lotId, startLotPrice));

            playLot(new Random().nextInt(6));
            latchEndAuction.countDown();
            Thread.sleep(1500);

            changeStatusAndNotify(LotStatus.Closed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startBet() throws InterruptedException {
        //Thread.sleep(5);
        for (var participant : participants) {
            participant.setLot(this);
            participant.setCurrentLotPrice(startLotPrice);
            new Thread(participant).start();
        }
    }

    private void playLot(int playLotTime) throws InterruptedException {
        try {
            Participant winner= null;
            for (int i =0 ;i<playLotTime;i++){
                notify(String.format("(Lot ID: %d) -> play lot, %d", lotId, i+1));
                changeStatusAndNotify(LotStatus.WaitingForBets);
                latchAuctionBegin = new CountDownLatch(participants.size());
                startBet();
                latchAuctionBegin.await();

                winner = Collections.max(participants
                                .stream()
                                .filter(o -> !o.isLost())
                                .collect(Collectors.toList()),
                        Comparator.comparingInt(Participant::getCurrentLotPrice));
                startLotPrice = winner.getCurrentLotPrice();
            }
            for (var participant:participants) {
                participant.blockedCountDown.countDown();
            }
            defineWinnerAndNotify(winner);
        }
        catch (NoSuchElementException ex){
            changeStatusAndNotify(LotStatus.WinnerAbsent);
        }

    }

    private void defineWinnerAndNotify(Participant winner) {

        try {
            changeStatusAndNotify(LotStatus.Win);
            notifier.notify(String.format("(Participant #%d) -> price: %d, win!",
                    winner.getParticipantId(), winner.getCurrentLotPrice()), this);
            changeStatusAndNotify(LotStatus.WaitingForPayment);
            payForLot(winner);
        } catch (NoSuchElementException ex) {
            changeStatusAndNotify(LotStatus.WinnerAbsent);
        }

    }

    private void payForLot(Participant winner) {
        if (!winner.pay()) {
            int blocked = 2;
            winner.setBlockedCountDown(blocked);
            changeStatusAndNotify(LotStatus.PaymentFailed);
            notifier.notify(String.format("(Participant #%d) -> blocked, for %d lots, because non payment",
                    winner.getParticipantId(), blocked), this);
        } else
            changeStatusAndNotify(LotStatus.PaymentSuccessful);
    }

    private void changeStatusAndNotify(LotStatus lotStatus) {
        //this.lotStatus = lotStatus;
        notify(String.format("(Lot ID: %d) -> lot status: %s", lotId, lotStatus.name()));
    }

    private void notify(String message) {
        notifier.notify(message, this);
    }
}
