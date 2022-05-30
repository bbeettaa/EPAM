package ua.epam.models.entities.report;

import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;

import java.util.List;

public class Report implements IReport {
    private int id;
    private String name;
    private int speakerId;
    private IUser speaker;
    private int eventId;

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IUser getSpeaker() {
        return speaker;
    }

    public void setSpeaker(IUser speaker) {
        this.speaker = speaker;
    }
}
