package ua.epam.dao.entities.report;

import ua.epam.dao.entities.IEntityDao;
import ua.epam.models.entities.Report;

import java.util.List;

public interface IReportDao extends IEntityDao<Report> {
    List<Report> read(int page, int count,  int eventId);
    Report getByName(String name);
    int count(int eventId);
}
