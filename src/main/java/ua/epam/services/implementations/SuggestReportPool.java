package ua.epam.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.dao.sevices.EventService;
import ua.epam.dao.sevices.ReportService;
import ua.epam.models.entities.Report;
import ua.epam.services.IMailService;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class SuggestReportPool {
    static SuggestReportPool entity;
    private final Hashtable<Integer, List<Report>> eventPool;

    @Autowired
    ReportService reportService;
    @Autowired
    EventService eventService;
    @Autowired
    IMailService mailService;

    private SuggestReportPool() {
        eventPool = new Hashtable<>();
    }

    public static SuggestReportPool getEntity() {
        if (isNull(entity)) entity = new SuggestReportPool();
        return entity;
    }

    public boolean addSuggestion(Report report) {
        if (isNameExist(report.getName()))
            return false;

        if (isNull(eventPool.get(report.getSpeakerId()))) {
            List<Report> reports = new ArrayList<>();
            reports.add(report);
            eventPool.put(report.getSpeakerId(), reports);
            return true;
        } else {
            List<Report> reports = eventPool.get(report.getSpeakerId());
            reports.add(report);
            eventPool.put(report.getSpeakerId(), reports);
            return true;
        }
    }

    public List<Report> getSuggestions(int speakerId) {
        return eventPool.get(speakerId);
    }

    public List<Report> getSuggestions() {
        List<Report> reports = new ArrayList<>();
        for (Map.Entry<Integer, List<Report>> map : eventPool.entrySet())
            reports.addAll(map.getValue());
        return reports;
    }

    public void declineByName(String reportName, int speakerId) {
        List<Report> reports = eventPool.get(speakerId);
        if (nonNull(reports)) {
            Report report = reports.stream()
                    .filter(e -> e.getName().equals(reportName))
                    .findFirst().orElse(null);
            if (report != null) {
                reports.remove(report);
                new Thread(() -> mailService
                        .sendDeclineSuggestion(report.getSpeaker().getId(), eventService.get(report.getEventId()).getName(), reportName)).start();
            }
        }
    }

    public void declineByName(String reportName) {
        for (Map.Entry<Integer, List<Report>> map : eventPool.entrySet()) {
            Report report = map.getValue().stream().filter(e -> e.getName().equals(reportName)).findFirst().orElse(null);
            if (nonNull(report)) {
                map.getValue().remove(report);
                new Thread(() -> mailService
                        .sendDeclineSuggestion(
                                report.getSpeaker().getId(),
                                eventService.get(report.getEventId()).getName(), reportName)
                ).start();
                return;
            }
        }
    }

    public void acceptByName(String reportName) {
        for (Map.Entry<Integer, List<Report>> map : eventPool.entrySet()) {
            Report report = map.getValue().stream().filter(e -> e.getName().equals(reportName)).findFirst().orElse(null);
            if (nonNull(report)) {
                new Thread(() -> mailService
                        .sendAcceptSuggestion(
                                report.getSpeaker().getId(),
                                eventService.get(report.getEventId()).getName(), reportName)
                ).start();
                reportService.save(report);
                map.getValue().remove(report);
                return;
            }
        }
    }

    public boolean isNameExist(String eventName) {
        for (Map.Entry<Integer, List<Report>> map : eventPool.entrySet()) {
            Report report = map.getValue().stream().filter(e -> e.getName().equals(eventName)).findFirst().orElse(null);
            if (nonNull(report))
                return true;
        }
        return false;
    }

    public void clear(){
        eventPool.clear();
    }
}
