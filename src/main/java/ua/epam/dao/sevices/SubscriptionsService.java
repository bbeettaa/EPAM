package ua.epam.dao.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.subscribes.ISubscribesDao;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Subscriptions;

import java.util.List;

import static java.util.Objects.nonNull;

public class SubscriptionsService {
    @Autowired
    ISubscribesDao subscribesDao;
    @Autowired
    EventService eventService;

    public boolean create(Subscriptions subscriptions) {
        if (!isExist(subscriptions.getEventId(), subscriptions.getUserId()))
            return subscribesDao.create(subscriptions);
        return false;
    }

    public void delete(int eventId, int userId) {
        subscribesDao.delete(eventId, userId);
    }

    public boolean isExist(int eventId, int userId) {
        return nonNull(get(eventId, userId));
    }

    private Subscriptions get(int eventId, int userId) {
        return subscribesDao.read(eventId, userId);
    }

    public int subscribersCount(int eventId) {
        return subscribesDao.subscribersCount(eventId);
    }

    public int subscriptionsCount(int userId) {
        return subscribesDao.subscriptionsCount(userId);
    }


    public List<Event> get(int page, int count, int userId, String pattern, ISubscribesDao.SortOrder sortOrder, IEntityDao.SortType sortType) {
        List<Event> events = subscribesDao.read(userId, page, count, pattern, sortOrder, sortType);
        eventService.setReportsAndSubs(events);
        eventService.setSubscriptions(events,userId);
        eventService.setActiveEvents(events);
        return events;
    }

    public List<Integer> getSubscribers(int eventId){
        return subscribesDao.getSubscribers(eventId);
    }

}

