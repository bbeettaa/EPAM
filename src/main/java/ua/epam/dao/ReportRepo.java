package ua.epam.dao;

import ua.epam.dao.entities.services.DaoEventService;
import ua.epam.dao.entities.services.DaoReportService;
import ua.epam.dao.entities.services.IDaoService;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;

import java.util.List;

public class ReportRepo {
    DaoReportService dao = new DaoReportService();

    public boolean isExist(IReport event){
        return dao.isExist(event);
    }

    public IReport get(String... args){
        return dao.findByArg(args);
    }

    public boolean delete(int id){
        return dao.deleteById(id);
    }

    public List<IReport> get(){
        return dao.findAll();
    }

    public boolean update(IReport event){
        return dao.update(event);
    }

    public boolean add(IReport event){
        return dao.add(event);
    }

    public IReport get(int id){
        return dao.findById(id);
    }





}