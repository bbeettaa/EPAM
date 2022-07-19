package ua.epam.services;

import ua.epam.models.entities.Event;

import java.util.List;

public interface IStreamEventPool {

    void start(int id);
    void setActive(Event event);
    void stop(int id);
    void connect(int eventId);
    int getStatistic(int eventId);
    List<Integer> getActiveEvents();
}
