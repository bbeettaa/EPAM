package ua.epam.dao.entities.services;

import ua.epam.AppContext;
import ua.epam.dao.entities.entity.AbstractDao;
import ua.epam.dao.entities.entity.EventDao;
import ua.epam.dao.entities.entity.ReportDao;
import ua.epam.dao.entities.entity.UserDao;
import ua.epam.dao.utils.ConnectionCreator;
import ua.epam.dao.utils.EntityTransaction;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class DaoReportService implements IDaoService<IReport> {

    AbstractDao dao = new ReportDao();


    @Override
    public boolean isExist(IReport entity) {
        boolean statement = false;
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
        return statement;
    }

    @Override
    public IReport findByArg(String... args) {
        IReport event = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            transaction.commit();
            event = (IReport) dao.get(args);
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return event;
    }

    @Override
    public List<IReport> findAll() {
        List<IReport> entities = null;
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
        return entities;
    }

    @Override
    public IReport findById(int id) {
        IReport event = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            event = (IReport) dao.get(id);
            transaction.commit();
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return event;
    }

    @Override
    public boolean deleteById(int id) {
        boolean statement = false;
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
        return statement;
    }

    @Override
    public boolean add(IReport event) {
        boolean statement = false;
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
        return statement;
    }

    @Override
    public boolean update(IReport event) {
        boolean statement = false;
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
        return statement;
    }

    /*split the same events*/

}