package ua.advanced.Practical5.Task2.Exchange.Offer;

import ua.advanced.Practical5.Task2.Exchange.Broker;

public class Trade {
    private Broker fromBroker;
    private Broker toBroker;
    private SellShare share;

    public Trade(Broker from, Broker to, SellShare share) {
        this.fromBroker = from;
        this.toBroker = to;
        this.share=share;
    }

    public Broker getFromBroker() {
        return fromBroker;
    }

    public Broker getToBroker() {
        return toBroker;
    }

    public SellShare getShare() {
        return share;
    }
}
