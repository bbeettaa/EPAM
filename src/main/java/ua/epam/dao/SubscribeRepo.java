package ua.epam.dao;

import ua.epam.dao.entities.entity.SubscriberDao;
import ua.epam.dao.entities.services.DaoEventService;
import ua.epam.models.entities.event.IEvent;

import java.util.ArrayList;
import java.util.List;

public class SubscribeRepo {
    public List<IEvent> getAllEvents(int userId){
        List<IEvent> events = new ArrayList<>();
        SubscriberDao subscriberDao = new SubscriberDao();
        for (int id: subscriberDao.get(userId))
            events.add(new DaoEventService().findById(id));
        return events;
    }
    public boolean subscribe(int userId, int eventId){
        return new SubscriberDao().subscribe(userId,eventId);
    }
    public boolean unsubscribe(int userId, int eventId){
        return new SubscriberDao().unsubscribe(userId,eventId);
    }
}
