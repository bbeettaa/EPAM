package ua.epam.dao.entities.report;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ReportDao implements IReportDao {
    private static final Logger logger
            = LoggerFactory.getLogger(ReportDao.class);

    @Override
    public boolean create(Report entity) {
        Configuration configuration = new Configuration().addAnnotatedClass(entity.getClass());
        boolean statement = false;
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            statement = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return statement;
    }

    @Override
    public Report read(int id) {
        Report entity = null;
        Configuration configuration = new Configuration().addAnnotatedClass(Report.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            entity = session.get(Report.class, id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return entity;
    }

    @Override
    public boolean update(int id, Report entity) {
        Configuration configuration = new Configuration().addAnnotatedClass(entity.getClass());
        boolean statement = false;
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            entity.setId(id);

            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            statement = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return statement;
    }

    @Override
    public void delete(int id) {
        Configuration configuration = new Configuration().addAnnotatedClass(Report.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Report p WHERE p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public int count(String pattern) {
        return 0;
    }

    @Override
    public List<Report> read(int page, int count, int eventId) {
        Configuration configuration = new Configuration().addAnnotatedClass(Report.class);
        List<Report> personList = new ArrayList<>();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Object per : session.createQuery("From Report p WHERE cast(p.eventId AS string) LIKE :id")
                    .setParameter("id", eventId+"" )
                    .setFirstResult(page * count)
                    .setMaxResults(count)
                    .stream().toArray())
                personList.add((Report) per);

            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return personList;
    }

    @Override
    public Report getByName(String name) {
        Report report;
        Configuration configuration = new Configuration().addAnnotatedClass(Report.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            report = (Report) session.createQuery("From Report p WHERE p.name LIKE :name")
                    .setParameter("name", name).uniqueResult();

            session.getTransaction().commit();
        } catch (NonUniqueResultException ex) {
            logger.error(ex.getMessage());
            report  = null;
        }
        return report;
    }

    @Override
    public int count(int eventId) {
        Long count = -1L;
        Configuration configuration = new Configuration().addAnnotatedClass(Report.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            count = (Long) session.createQuery("SELECT COUNT(*) FROM Report p WHERE cast(p.eventId as string) LIKE :eventId")
                    .setParameter("eventId", eventId+"")
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return Integer.parseInt(count + "");
    }
}
