package ua.epam.dao.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.event.IEventDao;
import ua.epam.dao.entities.report.IReportDao;
import ua.epam.dao.entities.subscribes.ISubscribesDao;
import ua.epam.models.entities.Event;
import ua.epam.services.IStreamEventPool;

import java.util.List;

import static java.util.Objects.nonNull;

public class EventService {
    @Autowired
    private IEventDao eventDao;
    @Autowired
    private IReportDao reportDao;
    @Autowired
    private ISubscribesDao subscribesDao;
    @Autowired
    IStreamEventPool eventPool;
    @Autowired
    private MessageSource messageSource;

    public List<Event> get(int page, int count, String pattern, IEventDao.SortOrder sortOrder, IEntityDao.SortType sortType) {
        return setActiveEvents(
                setReportsAndSubs(
                        eventDao.read(page, count, pattern, sortOrder, sortType)));
    }

    public List<Event> getWithSubscribes(int page, int count, int userId, String pattern, IEventDao.SortOrder sortOrder, IEntityDao.SortType sortType) {
        return setActiveEvents(
                setSubscriptions(
                        setReportsAndSubs(eventDao.read(page, count, pattern, sortOrder, sortType)), userId));
    }

    public Event get(int id) {
        return setActiveEvent(
                setReportsAndSubs(
                        eventDao.read(id)));
    }

    public boolean save(Event user) {
        return eventDao.create(user);
    }

    public void delete(int id) {
        eventDao.delete(id);
    }

    public boolean update(int id, Event event) {
        Event oldEvent = this.get(id);
        event.setSubscribers(oldEvent.getSubscribers());
        event.setReports(oldEvent.getReports());
        return eventDao.update(id, event);
    }

    public int count(String pattern) {
        return eventDao.count(pattern);
    }

    public boolean isNameExisted(String name, int id) {
        Event event = getByName(name);
        return nonNull(event) && event.getId() != id;
    }

    public Event getByName(String name) {
        return eventDao.getByName(name);
    }

    public boolean isError(Event event, BindingResult bindingResult) {
        boolean error = bindingResult.hasErrors();
        if (this.isNameExisted(event.getName(), event.getId())) {
            bindingResult.addError(new FieldError("entity", "name",
                    event.getName(), false, null, null,
                    messageSource.getMessage("error.name.exist",null, LocaleContextHolder.getLocale())));
            error = true;
        }
        return error;
    }

    public List<Event> setReportsAndSubs(List<Event> events) {
        for (Event event : events)
            setReportsAndSubs(event);
        return events;
    }

    public Event setReportsAndSubs(Event event) {
        event.setSubscribers(subscribesDao.subscribersCount(event.getId()));
        event.setReports(reportDao.count(event.getId()));
        return event;
    }

    public List<Event> setSubscriptions(List<Event> events, int userId) {
        for (Event event : events)
            setSubscriptions(event, userId);
        return events;
    }

    public Event setSubscriptions(Event event, int userId) {
        event.setSubscribe(nonNull(subscribesDao.read(event.getId(), userId)));
        return event;
    }

    public List<Event> setActiveEvents(List<Event> events) {
        for (Event event : events)
            setActiveEvent(event);
        return events;
    }

    public Event setActiveEvent(Event event) {
        eventPool.setActive(event);
        return event;
    }

}
