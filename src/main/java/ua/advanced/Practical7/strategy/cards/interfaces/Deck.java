package ua.advanced.Practical7.strategy.cards.interfaces;

import java.util.List;

public interface Deck{
    Card dealCard();
    List<Card> restCards();
    int size();
}
