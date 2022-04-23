package ua.advanced.Practical5.Task2.Exchange.TradePalce;

import ua.advanced.Practical5.Task2.Exchange.Broker;
import ua.advanced.Practical5.Task2.Exchange.ConsoleContext;
import ua.advanced.Practical5.Task2.Exchange.Offer.SellShare;
import ua.advanced.Practical5.Task2.Exchange.Offer.Trade;
import ua.advanced.Practical5.Task2.Exchange.Share;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Exchange {
    public Market market;
    List<Broker> registryBrokers;
    AtomicInteger index;
    List<Integer> indexHistory;
    AtomicBoolean isClosed;

    public Exchange(int index, Market market) {
        this.market = market;
        this.index = new AtomicInteger(index);
        this.indexHistory = new ArrayList<>();
        this.indexHistory.add(index);
        this.isClosed = new AtomicBoolean(false);
    }

    public void registryBrokers(List<Broker> brokers) {
        this.registryBrokers = brokers;
        for (var broker : brokers) {
            //Thread.sleep(new Random().nextInt(1500));
            ConsoleContext.notify(broker + " -> registered to exchange");
            new Thread(()->broker.doTrade()).start();
        }
    }

    public synchronized void buy(Trade trade) {
        if (isClosed())
            return;
        this.market.toSell.remove(trade.getShare());

        Share share = trade.getShare().getShare();
        trade.getFromBroker().sell(share);
        trade.getToBroker().buy(share);

        ConsoleContext.notify(String.format("+(Exchange operation 'buy') -> '%s' buy {%s} from '%s'.",
                trade.getToBroker(), share, trade.getFromBroker()));

        index.set(index.get() + (int) (share.getPrice() * 0.1));
        setIndex();
        checkIndex();
    }

    public synchronized void putSell(SellShare sellShare) {
        if (isClosed())
            return;
        this.market.putSell(sellShare);
        ConsoleContext.notify(String.format("-(Exchange operation 'put to sell') -> '%s' sell {%s}.",
                sellShare.getBroker(), sellShare.getShare()));

        index.set(index.get() - (int) (sellShare.getShare().getPrice() * 0.10));
        setIndex();
        checkIndex();
    }

    private synchronized void setIndex() {
        indexHistory.add(index.get());
        ConsoleContext.notify(String.format("\t\t(Exchange index change) -> from %d to %d",
                indexHistory.get(indexHistory.size() - 2), indexHistory.get(indexHistory.size() - 1)));
    }

    private synchronized void checkIndex() {
        if (indexHistory.size() >= 10)
            indexHistory.remove(0);
        if (indexHistory.get(indexHistory.size() / 2) - index.get() > 20 || index.get()<=20) {
            ConsoleContext.notify(String.format("Exchange close all trading because the index plummeted. (from %d to %d).",
                    indexHistory.get(indexHistory.size() / 2),index.get()));
            close();
        }
    }

    public boolean isClosed() {
        return isClosed.get();
    }

    private void close() {
        isClosed.set(true);
    }
}
