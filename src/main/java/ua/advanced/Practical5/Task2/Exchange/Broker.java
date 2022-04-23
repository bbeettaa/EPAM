package ua.advanced.Practical5.Task2.Exchange;

import ua.advanced.Practical5.Task2.Exchange.Offer.SellShare;
import ua.advanced.Practical5.Task2.Exchange.Offer.Trade;
import ua.advanced.Practical5.Task2.Exchange.TradePalce.Exchange;

import java.util.*;
import java.util.stream.Collectors;

public class Broker {//implements Runnable{//, Comparable<Broker> {
    private final int brokerId;
    public List<Share> shares;
    Exchange exchange;
    private boolean canSell;
    private boolean canBought;

    public Broker(int brokerId, List<Share> shares, Exchange exchange) {
        this.brokerId = brokerId;
        this.shares = new ArrayList<>();
        this.shares.addAll(shares);
        this.exchange = exchange;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public boolean isCanSell() {
        return canSell;
    }

    public boolean isCanBought() {
        return canBought;
    }

    synchronized public void buy(Share share) {
        share.setPrice(share.getPrice() + new Random().nextInt(10));
        shares.add(share);
        this.notify();
    }

    public void sell(Share share) {
        shares.remove(share);
    }

    @Override
    public String toString() {
        return "brokerId=" + brokerId;
    }


    synchronized public void doTrade() {
        while (!exchange.isClosed()) {
            try {
                Thread.sleep(new Random().nextInt(1500, 3500));
                //int rand = new Random().nextInt(6);
                if (shares.size() > 2 && exchange.market.getToSell().size() != 0) {//&& this.shares.size() / 3 <= lotsToSellCount) {

                        SellShare toBuy = Collections.min(exchange.market.getToSell()
                                .stream()
                                .filter(o -> o.getBroker().brokerId != this.brokerId)
                                .collect(Collectors.toList()), SellShare::compareTo);
                        exchange.buy(new Trade(toBuy.getBroker(), this, toBuy));
                } else {
                    Share toSell = Collections.max(shares.stream().collect(Collectors.toList()), Comparator.comparingInt(Share::getPrice));
                    exchange.putSell(new SellShare(toSell, this));
                    ConsoleContext.notify(String.format("?(%s) is wait for someone buy Share",this));
                    this.wait(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchElementException ex) {
            } catch (ConcurrentModificationException ex) {
            }
        }
    }
}
