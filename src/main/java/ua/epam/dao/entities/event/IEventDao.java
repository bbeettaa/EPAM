package ua.epam.dao.entities.event;

import ua.epam.dao.entities.IEntityDao;
import ua.epam.models.entities.Event;

import java.util.List;

public interface IEventDao extends IEntityDao<Event> {
    List<Event> read(int page, int count, String pattern, SortOrder sortOrder, SortType sortType);

    Event getByName(String name);

    boolean subscribe(int eventId);

    boolean unsubscribe(int eventId);

    boolean addReport(int eventId);

    boolean dellReport(int eventId);

    enum SortOrder {
        ID, NAME, DATE, TIME, SUBS, REPORT
    }
}