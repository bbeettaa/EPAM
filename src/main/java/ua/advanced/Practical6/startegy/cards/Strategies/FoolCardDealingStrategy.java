package ua.advanced.Practical6.startegy.cards.Strategies;

import ua.advanced.Practical6.startegy.cards.Card;
import ua.advanced.Practical6.startegy.cards.CardDealingStrategy;
import ua.advanced.Practical6.startegy.cards.Deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoolCardDealingStrategy implements CardDealingStrategy {
    public static final String PLAYER_NAME = "Player ";
    public static final String TRUMP = "Trump card";
    public static final String REMAINING = "Remaining";
    public static final int CARD_PER_PLAYER = 6;
    public static final int CARD_PER_TRUMP = 1;

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> map = new HashMap<>();

        dealCardToPlayers(deck, players, map);
        dealCardToTrump(deck, map);
        dealCardToRemaining(deck,map);

        return map;
    }

    private void dealCardToPlayers(Deck deck, int players, Map<String, List<Card>> map) {
        for (int i=0;i<players;i++) map.put(PLAYER_NAME + (i+1), new ArrayList<>());

        for (int iteration = 0; iteration < CARD_PER_PLAYER; iteration++)
            for (int p = 0; p < players; p++)
                if (map.containsKey(PLAYER_NAME + (p+1))) {
                    List<Card> cards = map.get(PLAYER_NAME + (p+1));
                    cards.add(deck.dealCard());
                    map.replace(PLAYER_NAME + (p+1), cards);
                }
    }

    private void dealCardToTrump(Deck deck, Map<String, List<Card>> map) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < CARD_PER_TRUMP; i++)
            cards.add(deck.dealCard());
        map.put(TRUMP, cards);
    }

    private void dealCardToRemaining(Deck deck, Map<String, List<Card>> map) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i< deck.size();)
            cards.add(deck.dealCard());
        map.put(REMAINING, cards);
    }
}
