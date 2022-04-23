package ua.advanced.Practical5.Task2.Exchange.TradePalce;

import ua.advanced.Practical5.Task2.Exchange.Offer.SellShare;

import java.util.LinkedList;
import java.util.List;

public class Market {
    List<SellShare> toSell;

    public Market() {
        this.toSell=new LinkedList<>();
    }


    public Market putSell(SellShare sellShare) {
        toSell.add(sellShare);
        return this;
    }

    public List<SellShare> getToSell() {
        return toSell;
    }

    public void addSellOffer(SellShare sellShare){
        toSell.add(sellShare);
    }

}
