package ua.epam.services.implementations;

import ua.epam.models.entities.Event;
import ua.epam.services.IStreamEventPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.nonNull;

public class StreamEventPool implements IStreamEventPool {
    private final List<Integer> activeEvents;
    private final HashMap<Integer, Integer> connectionStatistic = new HashMap<>();

    public StreamEventPool() {
        activeEvents = new ArrayList<>();
    }

    public void start(int id) {
        if (!checkExistActiveEvent(id))
            activeEvents.add(id);
    }

    public void setActive(Event event) {
        event.setActive(checkExistActiveEvent(event.getId()));
    }

    public void stop(int id) {
        if (checkExistActiveEvent(id)) {
            activeEvents.remove(Integer.valueOf(id));
            connectionStatistic.remove(id);
        }
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

    public List<Integer> getActiveEvents() {
        return activeEvents;
    }

    private boolean checkExistActiveEvent(int eventId) {
        return nonNull(activeEvents.stream().filter(evId -> evId == eventId).findFirst().orElse(null));
    }
}
