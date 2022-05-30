package ua.epam.models.entities;

import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class SuggestReportPool {
    static SuggestReportPool entity;
    List<IReport> reports = new ArrayList<>();
    private SuggestReportPool(){
    }

    public static SuggestReportPool getEntity(){
        if(isNull(entity)) entity=new SuggestReportPool();
        return entity;
    }
    public void addSuggestion(IReport report){
        reports.add(report);
    }
    public List<IReport> getSuggestion(){
        return reports;
    }

    public List<IReport> getSuggestion(int speakerId){
        List<IReport> returnList = new ArrayList<>();
        for (IReport report: reports)
            if(report.getSpeakerId()==speakerId)
                returnList.add(report);

    return returnList;
    }
}
