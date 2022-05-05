package ua.advanced.Practical6_enhanced.strategy.cards.interfaces;

import java.util.List;

public interface Deck{
    Card dealCard();
    List<Card> restCards();
    int size();
}
