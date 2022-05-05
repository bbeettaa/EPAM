package ua.advanced.Practical6_enhanced.strategy.cards;

import ua.advanced.Practical6_enhanced.strategy.cards.interfaces.CardDealingStrategy;
import ua.advanced.Practical6_enhanced.strategy.cards.strategies.ClassicPokerCatDealingStrategy;
import ua.advanced.Practical6_enhanced.strategy.cards.strategies.FoolCardDealingStrategy;
import ua.advanced.Practical6_enhanced.strategy.cards.strategies.BridgeCartDealingStrategy;
import ua.advanced.Practical6_enhanced.strategy.cards.strategies.TexasHoldemCartStrategy;

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
