package ua.advanced.Practical7.strategy.cards.interfaces;

import java.util.List;
import java.util.Map;

public interface CardDealingStrategy {
    Map<String, List<Card>> dealStacks(Deck deck, int players);
}
