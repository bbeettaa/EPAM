package ua.epam.dao.sevices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.subscribes.ISubscribesDao;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Subscriptions;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SubscriptionsServiceTest {

    @Autowired
    SubscriptionsService subscriptionsService;
    @MockBean
    ISubscribesDao subscribesDao;
    @MockBean
    EventService eventService;

    Subscriptions subscriptions;

    @BeforeEach
    public void setUp() {
        subscriptions = new Subscriptions(1, 10, 11);
    }

    @Test
    void create_true() {
        when(subscribesDao.read(anyInt(), anyInt())).thenReturn(null);
        when(subscribesDao.create(any())).thenReturn(true);

        boolean actual = subscriptionsService.create(subscriptions);

        assertTrue(actual);
    }

    @Test
    void create_false() {
        when(subscribesDao.read(anyInt(), anyInt())).thenReturn(subscriptions);
        boolean actual = subscriptionsService.create(subscriptions);
        assertFalse(actual);
    }

    @Test
    void delete() {
        subscriptionsService.delete(1,4);
        verify(subscribesDao).delete(1,4);
    }

    @Test
    void isExist_true() {
        when(subscribesDao.read(anyInt(), anyInt())).thenReturn(subscriptions);
        boolean actual = subscriptionsService.isExist(1, 1);
        assertTrue(actual);
    }

    @Test
    void isExist_false() {
        when(subscribesDao.read(anyInt(), anyInt())).thenReturn(null);
        boolean actual = subscriptionsService.isExist(1, 1);
        assertFalse(actual);
    }

    @Test
    void subscribersCount() {
        when(subscribesDao.subscribersCount(anyInt())).thenReturn(1);
        int actual = subscriptionsService.subscribersCount(1);
        assertEquals(1, actual);
    }

    @Test
    void subscriptionsCount() {
        when(subscribesDao.subscriptionsCount(anyInt())).thenReturn(1);
        int actual = subscriptionsService.subscriptionsCount(1);
        assertEquals(1, actual);
    }

    @Test
    void get() {
        Event event1 = new Event(1, "event1", new Date(1L), new Time(1L), 1, 1, false, false);
        Event event2 = new Event(2, "event2", new Date(2L), new Time(2L), 2, 2, false, false);
        Event event3 = new Event(3, "event3", new Date(3L), new Time(3L), 3, 3, false, false);
        Event event4 = new Event(4, "event4", new Date(4L), new Time(4L), 4, 4, false, false);
        List<Event> expected = new ArrayList<Event>() {{
            add(event1);
            add(event2);
            add(event3);
            add(event4);
        }};
        when(subscribesDao.read(anyInt(), anyInt(), anyInt(), anyString(), any(), any())).thenReturn(expected);

        List<Event> actual = subscriptionsService.get(0,10,2,"hello", ISubscribesDao.SortOrder.ID, IEntityDao.SortType.ASC);

        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void getSubscribers() {
        List<Integer> expected = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        when(subscribesDao.getSubscribers(anyInt())).thenReturn(expected);

        List<Integer> actual = subscriptionsService.getSubscribers(1);

        assertEquals(expected, actual);
    }
}