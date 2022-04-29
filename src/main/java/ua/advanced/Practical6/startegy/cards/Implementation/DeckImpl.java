package ua.advanced.Practical6.startegy.cards.Implementation;

import ua.advanced.Practical6.startegy.cards.Card;
import ua.advanced.Practical6.startegy.cards.Deck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeckImpl implements Deck {

    LinkedList<Card> cards;

    public DeckImpl(final int cardsAmount) {
        this.cards = new LinkedList<>();
        for (int i = 0; i < cardsAmount; i++) {
            cards.push(new CardImpl(i));
        }
    }

    @Override
    public Card dealCard() {
        return cards.size() == 0 ? null : cards.pop();
    }

    @Override
    public List<Card> restCards() {
        final ArrayList<Card> rest = new ArrayList<>(this.cards);
        cards.clear();
        return rest;
    }

    @Override
    public int size() {
        return cards.size();
    }


}
