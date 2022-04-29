package ua.advanced.Practical6.startegy.cards;

import ua.advanced.Practical6.startegy.cards.Strategies.BridgeCartDealingStrategy;
import ua.advanced.Practical6.startegy.cards.Strategies.ClassicPokerCatDealingStrategy;
import ua.advanced.Practical6.startegy.cards.Strategies.FoolCardDealingStrategy;
import ua.advanced.Practical6.startegy.cards.Strategies.TexasHoldemCartStrategy;

public class CardDealingStrategies {

    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new TexasHoldemCartStrategy();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new ClassicPokerCatDealingStrategy();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return new BridgeCartDealingStrategy();
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return new FoolCardDealingStrategy();
    }

}
