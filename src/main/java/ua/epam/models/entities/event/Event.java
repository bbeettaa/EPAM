package ua.epam.models.entities.event;

import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.user.IUser;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event implements IEvent {
    private int id;
    private String name;
    private LocalDate dateBegin;
    private Time timeBegin;
    private final List<IUser> subscribers = new ArrayList<>();
    private final List<IReport> reports = new ArrayList<>();

    @Override
    public int getSubCount() {
        return subscribers.size();
    }

    @Override
    public int getReportsCount() {
        return reports.size();
    }

    public List<IReport> getReports() {
        return reports;
    }

    public void addReports(IReport entity) {
        this.reports.add(entity);
    }

    public List<IUser> getSubscribers() {
        return subscribers;
    }

    public void addSubscriber(IUser subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public String getDateBegin() {
        return dateBegin.toString();
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Time getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Time timeBegin) {
        this.timeBegin = timeBegin;
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


}
