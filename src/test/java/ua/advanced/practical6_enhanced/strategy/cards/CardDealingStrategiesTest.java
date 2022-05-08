package ua.advanced.practical6_enhanced.strategy.cards;

import org.junit.Test;
import ua.advanced.Practical6_enhanced.strategy.cards.CardDealingStrategies;
import ua.advanced.Practical6_enhanced.strategy.cards.implementation.DeckImpl;
import ua.advanced.Practical6_enhanced.strategy.cards.interfaces.CardDealingStrategy;
import ua.advanced.Practical6_enhanced.strategy.cards.interfaces.Deck;

import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CardDealingStrategiesTest {


    @Test
    public void texasHoldemCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.texasHoldemCardDealingStrategy();

        testCase(strategy, 52, 3,
                "{Community=[45, 44, 43, 42, 41], " +
                        "Player 1=[51, 48], " +
                        "Player 2=[50, 47], " +
                        "Player 3=[49, 46], " +
                        "Remaining=[40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");


    }

    @Test
    public void classicPokerCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.classicPokerCardDealingStrategy();

        testCase(strategy, 36, 5,
                "{Player 1=[35, 30, 25, 20, 15], " +
                        "Player 2=[34, 29, 24, 19, 14], " +
                        "Player 3=[33, 28, 23, 18, 13], " +
                        "Player 4=[32, 27, 22, 17, 12], " +
                        "Player 5=[31, 26, 21, 16, 11], " +
                        "Remaining=[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
    }

    @Test
    public void bridgeCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.bridgeCardDealingStrategy();

        testCase(strategy, 52, 4,
                "{Player 1=[51, 47, 43, 39, 35, 31, 27, 23, 19, 15, 11, 7, 3], " +
                        "Player 2=[50, 46, 42, 38, 34, 30, 26, 22, 18, 14, 10, 6, 2], " +
                        "Player 3=[49, 45, 41, 37, 33, 29, 25, 21, 17, 13, 9, 5, 1], " +
                        "Player 4=[48, 44, 40, 36, 32, 28, 24, 20, 16, 12, 8, 4, 0]}");
    }

    @Test
    public void foolCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.foolCardDealingStrategy();

        testCase(strategy, 36, 5,
                "{Player 1=[35, 30, 25, 20, 15, 10], " +
                        "Player 2=[34, 29, 24, 19, 14, 9], " +
                        "Player 3=[33, 28, 23, 18, 13, 8], " +
                        "Player 4=[32, 27, 22, 17, 12, 7], " +
                        "Player 5=[31, 26, 21, 16, 11, 6], " +
                        "Remaining=[4, 3, 2, 1, 0], " +
                        "Trump card=[5]}");
    }

    private void testCase(final CardDealingStrategy strategy, final int deckSize, final int players, final String expected) {
        final Deck deck = new DeckImpl(deckSize);
        assertEquals(deckSize, deck.size());
        assertEquals(
                expected,
                new TreeMap(strategy.dealStacks(deck, players)).toString()
        );
        assertEquals(0, deck.size());
    }

    //<------------------------------------------------------------------------------------------------
    //<------------------------------------------------------------------------------------------------
    //<------------------------------------------------------------------------------------------------

    @Test
    public void texasHoldemCardDealingStrategy_validateException_onePlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.texasHoldemCardDealingStrategy();

        testCase_validateException(strategy, 36, 1, "");
    }

    @Test
    public void classicPokerCardDealingStrategy_validateException_onePlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.classicPokerCardDealingStrategy();
        testCase_validateException(strategy, 36, 1, "");
    }

    @Test
    public void bridgeCardDealingStrategy_validateException_onePlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.bridgeCardDealingStrategy();
        testCase_validateException(strategy, 52, 1, "");
    }

    @Test
    public void foolCardDealingStrategy_validateException_onePlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.foolCardDealingStrategy();
        testCase_validateException(strategy, 52, 1, "");
    }

    //<------------------------------------------------------------------------------------------------

    @Test
    public void texasHoldemCardDealingStrategy_validateException_elevenPlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.texasHoldemCardDealingStrategy();

        testCase_validateException(strategy, 36, 11, "");
    }

    @Test
    public void classicPokerCardDealingStrategy_validateException_elevenPlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.classicPokerCardDealingStrategy();
        testCase_validateException(strategy, 36, 11, "");
    }

    @Test
    public void bridgeCardDealingStrategy_validateException_elevenPlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.bridgeCardDealingStrategy();
        testCase_validateException(strategy, 52, 11, "");
    }

    @Test
    public void foolCardDealingStrategy_validateException_elevenPlayer() {
        final CardDealingStrategy strategy = CardDealingStrategies.foolCardDealingStrategy();
        testCase_validateException(strategy, 52, 11, "");
    }

    private void testCase_validateException(final CardDealingStrategy strategy, final int deckSize, final int players, final String expected) {
        final Deck deck = new DeckImpl(deckSize);
        assertThrows(IllegalArgumentException.class, () -> strategy.dealStacks(deck, players));
    }

}




