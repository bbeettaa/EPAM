package ua.advanced.Practical6.startegy.cards.Interfaces;

import java.util.List;

public interface Deck{
    Card dealCard();
    List<Card> restCards();
    int size();
}
