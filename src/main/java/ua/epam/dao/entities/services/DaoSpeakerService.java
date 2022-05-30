package ua.epam.dao.entities.services;

import ua.epam.AppContext;
import ua.epam.dao.entities.entity.EventDao;
import ua.epam.dao.utils.EntityTransaction;
import ua.epam.models.entities.event.IEvent;

import java.util.List;
import java.util.Objects;

public class DaoSpeakerService implements IDaoService<IEvent> {

    EventDao dao = new EventDao();


    @Override
    public boolean isExist(IEvent entity) {
/*        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            if (Objects.nonNull(dao.get(entity.getName()))) {
                transaction.commit();
                statement = true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;*/
        throw new IllegalArgumentException("not implement");
    }

    @Override
    public IEvent findByArg(String... args) {
/*        IEvent event = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            transaction.commit();
            event = dao.get(args);
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return event;*/
        throw new IllegalArgumentException("not implement");
    }

    @Override
    public List<IEvent> findAll() {
/*        List<IEvent> entities = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            entities = dao.get();
            transaction.commit();
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return entities;*/
        throw new IllegalArgumentException("not implement");
    }

    @Override
    public IEvent findById(int id) {
/*        IEvent event = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            event = dao.get(id);
            transaction.commit();
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return event;*/
        throw new IllegalArgumentException("not implement");
    }

    @Override
    public boolean deleteById(int id) {
/*        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            if (dao.delete(id)) {
                transaction.commit();
                statement = true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;*/
        throw new IllegalArgumentException("not implement");
    }

    @Override
    public boolean add(IEvent event) {
/*        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            if (dao.create(event)) {
                transaction.commit();
                statement = true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;*/
        throw new IllegalArgumentException("not implement");
    }

    @Override
    public boolean update(IEvent event) {
/*        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            if (Objects.nonNull(dao.update(event))) {
                transaction.commit();
                statement = true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;*/
        throw new IllegalArgumentException("not implement");
    }

    /*split the same events*/

}