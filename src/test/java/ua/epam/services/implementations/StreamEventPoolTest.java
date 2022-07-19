package ua.epam.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.epam.dao.entities.event.IEventDao;
import ua.epam.models.entities.Event;
import ua.epam.services.IStreamEventPool;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamEventPoolTest {

    private IStreamEventPool streamEventPool;
    @MockBean
    private IEventDao eventDao;

    private Event event1;
    private Event event2;
    private Event event3;
    private Event event4;

    @BeforeEach
    void setUp() {
        streamEventPool = new StreamEventPool();

        event1 = new Event(1,"event1",new Date(1L),new Time(1L),1,1,false,false);
        event2 = new Event(2,"event2",new Date(2L),new Time(2L),2,2,false,false);
        event3 = new Event(3,"event3",new Date(3L),new Time(3L),3,3,false,false);
        event4 = new Event(4,"event4",new Date(4L),new Time(4L),4,4,false,false);
    }

    @Test
    void start() {
        List<Integer> expectedEvents = new ArrayList<>();
        expectedEvents.add(event1.getId());
        expectedEvents.add(event2.getId());
        expectedEvents.add(event3.getId());

        streamEventPool.start(event1.getId());
        streamEventPool.start(event2.getId());
        streamEventPool.start(event3.getId());

        assertEquals(expectedEvents.toString(),streamEventPool.getActiveEvents().toString());
    }

    @Test
    void setActive() {
        streamEventPool.start(event1.getId());
        streamEventPool.start(event2.getId());
        streamEventPool.start(event3.getId());
        streamEventPool.setActive(event1);
        streamEventPool.setActive(event2);
        streamEventPool.setActive(event3);

        assertTrue(event1.isActive());
        assertTrue(event2.isActive());
        assertTrue(event2.isActive());
    }

    @Test
    void stop_oneStop() {
        List<Integer> expectedEvents = new ArrayList<>();
        expectedEvents.add(event2.getId());
        expectedEvents.add(event3.getId());
        streamEventPool.start(event1.getId());
        streamEventPool.start(event2.getId());
        streamEventPool.start(event3.getId());

        streamEventPool.stop(event1.getId());

        assertEquals(expectedEvents.toString(),streamEventPool.getActiveEvents().toString());
    }

    @Test
    void stop_twoStop() {
        List<Integer> expectedEvents = new ArrayList<>();
        expectedEvents.add(event3.getId());
        streamEventPool.start(event1.getId());
        streamEventPool.start(event2.getId());
        streamEventPool.start(event3.getId());

        streamEventPool.stop(event1.getId());
        streamEventPool.stop(event2.getId());

        assertEquals(expectedEvents.toString(),streamEventPool.getActiveEvents().toString());
    }

    @Test
    void connect() {
        streamEventPool.connect(event1.getId());
        streamEventPool.connect(event1.getId());
        streamEventPool.connect(event1.getId());


        streamEventPool.connect(event2.getId());
        streamEventPool.connect(event2.getId());

        assertEquals(3, streamEventPool.getStatistic(event1.getId()));
        assertEquals(2, streamEventPool.getStatistic(event2.getId()));
    }

}