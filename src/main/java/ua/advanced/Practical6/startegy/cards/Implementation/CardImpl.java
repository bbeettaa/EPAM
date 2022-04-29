package ua.advanced.Practical6.startegy.cards.Implementation;

import ua.advanced.Practical6.startegy.cards.Card;

class CardImpl implements Card {

    private String name;

    CardImpl(final String name) {
        this.name = name;
    }

    CardImpl(final int name) {
        this(Integer.toString(name));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
