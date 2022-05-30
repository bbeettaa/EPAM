package ua.epam.models.entities.event;

import ua.epam.models.entities.Entity;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.user.IUser;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IEvent extends Entity, Serializable {

    int getSubCount();

    int getReportsCount();

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    List<IReport> getReports();

     void addReports(IReport entity);

    List<IUser> getSubscribers();

    void addSubscriber(IUser subscriber) ;



    String getDateBegin() ;

    void setDateBegin(LocalDate dateBegin) ;

    Time getTimeBegin();

    void setTimeBegin(Time timeBegin);
}
