package ua.advanced.Practical6.observer.git.Interfaces;

import ua.advanced.Practical6.observer.git.Implementations.Event;

import java.util.List;

public interface WebHook {
    String branch();
    Event.Type type();
    List<Event> caughtEvents();
    void onEvent(Event event);
}
