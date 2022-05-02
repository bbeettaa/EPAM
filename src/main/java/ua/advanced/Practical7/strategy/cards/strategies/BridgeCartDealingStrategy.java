package ua.advanced.Practical7.strategy.cards.strategies;

import ua.advanced.Practical7.strategy.cards.Range;
import ua.advanced.Practical7.strategy.cards.StrategyValidator;
import ua.advanced.Practical7.strategy.cards.interfaces.Card;
import ua.advanced.Practical7.strategy.cards.interfaces.CardDealingStrategy;
import ua.advanced.Practical7.strategy.cards.interfaces.Deck;
import ua.advanced.Practical7.strategy.cards.logger.LoggerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class BridgeCartDealingStrategy  implements CardDealingStrategy {
    public static final String PLAYER_NAME = "Player ";
    private int cardPerPlayer;

    @Range(max = 5)
    private int players;
    public BridgeCartDealingStrategy() {
        LoggerManager.logger.log(Level.INFO,BridgeCartDealingStrategy.class.getSimpleName()+" created");
    }

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        cardPerPlayer = deck.size()/players;
        this.players = players;
        try {
            StrategyValidator.validate(this);
        } catch (Exception e) {
            LoggerManager.logger.log(Level.WARNING,e.getMessage()+"\n{0}",e.getStackTrace() );
            throw new IllegalArgumentException();
        }
        LoggerManager.logger.log(Level.INFO,"{0}: start dealing, deck size: {1}", new Object[]{this.getClass().getSimpleName(),deck.size()} );
        Map<String, List<Card>> map = new HashMap<>();
        dealCardToPlayers(deck, map);
        LoggerManager.logger.log(Level.INFO,"{0}: end dealing\n", this.getClass().getSimpleName() );
        return map;
    }

    private void dealCardToPlayers(Deck deck, Map<String, List<Card>> map) {
        for (int i=0;i<players;i++) map.put(PLAYER_NAME + (i+1), new ArrayList<>());

        for (int iteration = 0; iteration < cardPerPlayer; iteration++)
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


}