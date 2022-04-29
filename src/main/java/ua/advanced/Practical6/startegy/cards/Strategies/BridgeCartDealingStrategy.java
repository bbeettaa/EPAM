package ua.advanced.Practical6.startegy.cards.Strategies;

import ua.advanced.Practical6.startegy.cards.Card;
import ua.advanced.Practical6.startegy.cards.CardDealingStrategy;
import ua.advanced.Practical6.startegy.cards.Deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BridgeCartDealingStrategy  implements CardDealingStrategy {
    public static final String PLAYER_NAME = "Player ";
    private int cardPerPlayer;

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        cardPerPlayer = deck.size()/players;
        Map<String, List<Card>> map = new HashMap<>();
        dealCardToPlayers(deck, players, map);
        return map;
    }

    private void dealCardToPlayers(Deck deck, int players, Map<String, List<Card>> map) {
        for (int i=0;i<players;i++) map.put(PLAYER_NAME + (i+1), new ArrayList<>());

        for (int iteration = 0; iteration < cardPerPlayer; iteration++)
            for (int p = 0; p < players; p++)
                if (map.containsKey(PLAYER_NAME + (p+1))) {
                    List<Card> cards = map.get(PLAYER_NAME + (p+1));
                    cards.add(deck.dealCard());
                    map.replace(PLAYER_NAME + (p+1), cards);
                }
    }


}