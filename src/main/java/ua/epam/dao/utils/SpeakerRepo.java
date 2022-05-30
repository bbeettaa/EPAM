package ua.epam.dao.utils;

import ua.epam.dao.entities.entity.EventDao;
import ua.epam.dao.entities.entity.ReportDao;
import ua.epam.dao.entities.entity.UserDao;
import ua.epam.models.Role;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.user.IUser;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class SpeakerRepo {
    public List<IEvent> get(int id) {
        EventDao eventDao = new EventDao();
        IUser speaker = new UserDao().get(id);

        List<IEvent> events =  new ArrayList<>();

        if (nonNull(speaker) && speaker.getRole() == Role.SPEAKER)
           for(IReport report : new ReportDao().get())
               if(report.getSpeakerId() == id)
                   events.add(eventDao.get(report.getEventId()));

        return events;
    }
}
