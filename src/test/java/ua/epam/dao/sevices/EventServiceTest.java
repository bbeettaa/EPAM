package ua.epam.dao.sevices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.event.IEventDao;
import ua.epam.dao.entities.report.IReportDao;
import ua.epam.dao.entities.subscribes.ISubscribesDao;
import ua.epam.models.Role;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.Subscriptions;
import ua.epam.models.entities.User;
import ua.epam.services.IStreamEventPool;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventService eventService;
    @MockBean
    private IEventDao eventDao;
    @MockBean
    private IReportDao reportDao;
    @MockBean
    private ISubscribesDao subscribesDao;
    @MockBean
    IStreamEventPool eventPool;

    Event event1;
    Event event2;

    @BeforeEach
    public void setUp() {
        doNothing().when(eventPool).setActive(any());
        when(subscribesDao.read(anyInt(), anyInt())).thenReturn(null);
        when(subscribesDao.subscribersCount(anyInt())).thenReturn(0);
        when(reportDao.count(any())).thenReturn(0);
        event1 = new Event(1, "nameEv", new Date(1L), new Time(1L), 0, 0, false, false);
        event2 = new Event(2, "nameEv2", new Date(2L), new Time(2L), 0, 0, false, false);
    }

    @Test
    void get_list() {
        when(eventDao.read(anyInt(), anyInt(), anyString(), any(), any())).thenReturn(new ArrayList<Event>() {{
            add(event2);
            add(event1);
        }});

        List<Event> actual = eventService.get(1, 2, "hello", IEventDao.SortOrder.ID, IEntityDao.SortType.ASC);

        assertEquals(new ArrayList<Event>() {{
            add(event2);
            add(event1);
        }}, actual);
    }

    @Test
    void getWithSubscribes() {
        when(eventDao.read(anyInt(), anyInt(), anyString(), any(), any())).thenReturn(new ArrayList<Event>() {{
            add(event2);
            add(event1);
        }});

        List<Event> actual = eventService.getWithSubscribes(1, 2, 1, "hello", IEventDao.SortOrder.ID, IEntityDao.SortType.ASC);

        assertEquals(new ArrayList<Event>() {{
            add(event2);
            add(event1);
        }}, actual);
    }

    @Test
    void test() {
        when(eventDao.read(anyInt())).thenReturn(event1);

        Event actual = eventService.get(1);

        assertEquals(event1.toString(), actual.toString());
    }

    @Test
    void save_true() {
        when(eventDao.create(event1)).thenReturn(true);
        assertTrue(eventService.save(event1));
    }

    @Test
    void save_false() {
        when(eventDao.create(event1)).thenReturn(false);
        assertFalse(eventService.save(event1));
    }

    @Test
    void delete() {
        eventService.delete(event1.getId());
        verify(eventDao).delete(event1.getId());
    }

    @Test
    void update_true() {
        when(eventDao.read(anyInt())).thenReturn(event1);
        when(eventDao.update(2, event1)).thenReturn(true);
        assertTrue(eventService.update(2, event1));
    }

    @Test
    void update_false() {
        when(eventDao.read(anyInt())).thenReturn(event1);
        when(eventDao.update(2, event1)).thenReturn(false);
        assertFalse(eventService.update(2, event1));
    }

    @Test
    void count() {
        when(eventDao.count(anyString())).thenReturn(1);
        int actual = eventService.count("hello");
        assertEquals(1, actual);
    }

    @Test
    void isNameExisted_true() {
        when(eventDao.getByName(anyString())).thenReturn(event1);
        boolean actual = eventService.isNameExisted("123", 123);
        assertTrue(actual);
    }

    @Test
    void isNameExisted_false() {
        when(eventDao.getByName(anyString())).thenReturn(null);
        boolean actual = eventService.isNameExisted("123", 123);
        assertFalse(actual);
    }

    @Test
    void isError_bindingResultError_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        bindingResult.addError(new FieldError("User", "login", "msg"));

        boolean actual = eventService.isError(event1, bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError_nameExist_true() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        when(eventDao.getByName(anyString())).thenReturn(event2);

        boolean actual = eventService.isError(event1, bindingResult);

        assertTrue(actual);
    }

    @Test
    void isError() {
        BindingResult bindingResult = new BeanPropertyBindingResult("User", "User");
        when(eventDao.getByName(anyString())).thenReturn(event1);

        boolean actual = eventService.isError(event1, bindingResult);

        assertFalse(actual);
    }

    @Test
    void setSubscriptions() {
        when(subscribesDao.read(anyInt(), anyInt())).thenReturn(new Subscriptions());
        eventService.setSubscriptions(event1, 1);
        assertTrue(event1.isSubscribe());
    }


}