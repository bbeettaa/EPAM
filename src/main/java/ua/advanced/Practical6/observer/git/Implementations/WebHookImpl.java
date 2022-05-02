package ua.advanced.Practical6.observer.git.Implementations;

import ua.advanced.Practical6.observer.git.Implementations.Event;
import ua.advanced.Practical6.observer.git.Interfaces.WebHook;

import java.util.ArrayList;
import java.util.List;

public class WebHookImpl implements WebHook {
    Event.Type type;
    List<Event> events;
    String branch;

    public WebHookImpl(String branch, Event.Type type) {
        this.events = new ArrayList<>();
        this.branch = branch;
        this.type = type;
    }

    @Override
    public String branch() {
        return branch;
    }

    @Override
    public Event.Type type() {
        return type;
    }

    @Override
    public List<Event> caughtEvents() {
        return events;
    }

    @Override
    public void onEvent(Event event) {
        this.type = event.type();
        events.add(event);
    }
}
