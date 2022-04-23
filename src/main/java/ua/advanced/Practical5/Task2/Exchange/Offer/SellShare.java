package ua.advanced.Practical5.Task2.Exchange.Offer;

import ua.advanced.Practical5.Task2.Exchange.Broker;
import ua.advanced.Practical5.Task2.Exchange.Share;

public class SellShare implements Comparable<SellShare> {
    private Share share;
    private Broker broker;

    public SellShare(Share share, Broker broker) {
        this.share = share;
        this.broker = broker;
    }

    public Share getShare() {
        return share;
    }

    public Broker getBroker() {
        return broker;
    }

    @Override
    public int compareTo(SellShare o) {
        return Integer.compare(share.getPrice(),o.getShare().getPrice());
    }
}
