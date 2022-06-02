package ua.epam.models.entities;

import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.isNull;

public class ActiveEventsPool {
    List<IEvent> events = new ArrayList<>();
    HashMap<Integer, Integer> connectionStatistic = new HashMap<>();
    static ActiveEventsPool entity;

    private ActiveEventsPool() {
    }

    public static ActiveEventsPool getEntity() {
        if (isNull(entity)) entity = new ActiveEventsPool();
        return entity;
    }

    public void add(IEvent report) {
        events.add(report);
    }

    public List<IEvent> getEvents() {
        return events;
    }

    public boolean isActive(int id) {
        return events.stream().filter(e -> e.getId() == id).findFirst().orElse(null) != null;
    }

    public void delete(int id) {
        events.removeIf(e -> e.getId() == id);
        connectionStatistic.remove(id);
    }

    public void connect(int eventId) {
        if (connectionStatistic.containsKey(eventId))
            connectionStatistic.put(eventId, connectionStatistic.get(eventId) + 1);
        else
            connectionStatistic.put(eventId, 1);
    }

    public int getStatistic(int eventId) {
        if (connectionStatistic.containsKey(eventId))
            return connectionStatistic.get(eventId);
        return 0;
    }
}
