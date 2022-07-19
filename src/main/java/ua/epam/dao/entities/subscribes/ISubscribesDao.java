package ua.epam.dao.entities.subscribes;

import ua.epam.dao.entities.IDao;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Subscriptions;

import java.util.List;

public interface ISubscribesDao extends IDao<Subscriptions> {
    int subscribersCount(int eventId);

    int subscriptionsCount(int eventId);

    Subscriptions read(int eventId, int userId);

    List<Event> read(int userId, int page, int count, String pattern, SortOrder sortOrder, IEntityDao.SortType sortType);

    List<Integer> getSubscribers(int eventId);

    void delete(int eventId, int userId);

    enum SortOrder {
        ID, NAME, DATE, TIME, SUBS, REPORT
    }
}