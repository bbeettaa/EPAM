package ua.epam.models.entities.report;

import ua.epam.models.entities.Entity;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;

import java.io.Serializable;
import java.util.List;

public interface IReport extends Entity, Serializable {
    int getSpeakerId() ;

    void setSpeakerId(int speakerId);

    int getEventId() ;

    void setEventId(int eventId);

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    IUser getSpeaker();

    void setSpeaker(IUser speaker);
}
