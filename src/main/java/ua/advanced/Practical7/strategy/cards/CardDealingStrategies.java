package ua.advanced.Practical7.strategy.cards;

import ua.advanced.Practical7.strategy.cards.interfaces.CardDealingStrategy;
import ua.advanced.Practical7.strategy.cards.strategies.BridgeCartDealingStrategy;
import ua.advanced.Practical7.strategy.cards.strategies.ClassicPokerCatDealingStrategy;
import ua.advanced.Practical7.strategy.cards.strategies.FoolCardDealingStrategy;
import ua.advanced.Practical7.strategy.cards.strategies.TexasHoldemCartStrategy;

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
