package ua.advanced.Practical6_enhanced.strategy.cards.strategies;

import ua.advanced.Practical6_enhanced.strategy.cards.Range;
import ua.advanced.Practical6_enhanced.strategy.cards.StrategyValidator;
import ua.advanced.Practical6_enhanced.strategy.cards.interfaces.Card;
import ua.advanced.Practical6_enhanced.strategy.cards.interfaces.CardDealingStrategy;
import ua.advanced.Practical6_enhanced.strategy.cards.interfaces.Deck;
import ua.advanced.Practical6_enhanced.strategy.cards.logger.LoggerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class FoolCardDealingStrategy implements CardDealingStrategy {
    public static final String PLAYER_NAME = "Player ";
    public static final String TRUMP = "Trump card";
    public static final String REMAINING = "Remaining";
    public static final int CARD_PER_PLAYER = 6;
    public static final int CARD_PER_TRUMP = 1;

    @Range(max = 5)
    private int players;

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> map = new HashMap<>();
        this.players = players;
        try {
            StrategyValidator.validate(this);
        } catch (Exception e) {
            LoggerManager.logger.log(Level.WARNING,e.getMessage()+"\n{0}",e.getStackTrace() );
            throw new IllegalArgumentException();
        }
        LoggerManager.logger.log(Level.INFO,"{0}: start dealing, deck size: {1}", new Object[]{this.getClass().getSimpleName(),deck.size()} );
        dealCardToPlayers(deck, map);
        dealCardToTrump(deck, map);
        dealCardToRemaining(deck,map);
        LoggerManager.logger.log(Level.INFO,"{0}: end dealing\n", this.getClass().getSimpleName() );

        return map;
    }

    private void dealCardToPlayers(Deck deck, Map<String, List<Card>> map) {
        for (int i=0;i<players;i++) map.put(PLAYER_NAME + (i+1), new ArrayList<>());

        for (int iteration = 0; iteration < CARD_PER_PLAYER; iteration++)
            for (int p = 0; p < players; p++)
                if (map.containsKey(PLAYER_NAME + (p+1))) {
                    List<Card> cards = map.get(PLAYER_NAME + (p+1));
                    cards.add(deck.dealCard());
                    map.replace(PLAYER_NAME + (p+1), cards);
                }
        for (int p = 1; p < players; p++)
            LoggerManager.logger.log(Level.INFO,""+this.getClass().getSimpleName()
                    +": dealing card "+map.get(PLAYER_NAME+p)+" to "+PLAYER_NAME+" "+p);
    }

    private void dealCardToTrump(Deck deck, Map<String, List<Card>> map) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < CARD_PER_TRUMP; i++)
            cards.add(deck.dealCard());
        map.put(TRUMP, cards);

        LoggerManager.logger.log(Level.INFO,""+this.getClass().getSimpleName()
                +": dealing card "+cards+" to "+TRUMP);
    }

    private void dealCardToRemaining(Deck deck, Map<String, List<Card>> map) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i< deck.size();)
            cards.add(deck.dealCard());
        map.put(REMAINING, cards);

        LoggerManager.logger.log(Level.INFO,""+this.getClass().getSimpleName()
                +": dealing card "+cards+" to "+REMAINING);
    }
}
