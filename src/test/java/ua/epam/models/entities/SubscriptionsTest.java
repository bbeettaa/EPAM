package ua.epam.models.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionsTest {

    @Test
    void Id() {
        Subscriptions sub = new Subscriptions();
        sub.setId(11);

        int act = sub.getId();

        assertEquals(11,act);
    }
}