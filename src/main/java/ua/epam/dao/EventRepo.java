package ua.epam.dao;

import ua.epam.dao.entities.services.DaoEventService;
import ua.epam.models.entities.event.IEvent;

import java.util.List;

public class EventRepo {
    DaoEventService dao = new DaoEventService();

    public boolean isExist(IEvent event){
        return dao.isExist(event);
    }

    public IEvent get(String... args){
        return dao.findByArg(args);
    }

    public boolean delete(int id){
        return dao.deleteById(id);
    }

    public List<IEvent> get(){
        return dao.findAll();
    }

    public boolean update(IEvent event){
        return dao.update(event);
    }

    public boolean add(IEvent event){
        return dao.add(event);
    }

    public IEvent get(int id){
        return dao.findById(id);
    }





}
