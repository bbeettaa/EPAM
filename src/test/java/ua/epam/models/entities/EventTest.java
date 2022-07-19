package ua.epam.models.entities;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void DateBegin() {
        Date date = new Date(2202L);
        Event ev = new Event();
        ev.setDateBegin(date);

        Date act = ev.getDateBegin();

        assertEquals(date.toString(),act.toString());
    }

    @Test
    void TimeBegin() {
        Time time = new Time(2202L);
        Event ev = new Event();
        ev.setTimeBegin(time);

        Time act = ev.getTimeBegin();

        assertEquals(time.toString(),act.toString());
    }

}