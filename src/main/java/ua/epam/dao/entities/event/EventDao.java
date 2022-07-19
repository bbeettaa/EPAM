package ua.epam.dao.entities.event;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.models.entities.Event;
import ua.epam.models.entities.Report;
import ua.epam.models.entities.Subscriptions;


import java.util.ArrayList;
import java.util.List;

public class EventDao implements IEventDao  {

    private static final Logger logger
            = LoggerFactory.getLogger(EventDao.class);

    @Override
    public boolean create(Event entity) {
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
    public Event read(int id) {
        Event person = null;
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            person = session.get(Event.class, id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return person;
    }

    @Override
    public List<Event> read(int page, int count, String pattern, IEventDao.SortOrder sortOrder, IEntityDao.SortType sortType) {
        String sortByReportCount = "SELECT e, COUNT(r.eventId) as report_count " +
                "FROM Event e LEFT JOIN Report r " +
                "ON e.id = r.eventId " +
                "WHERE e.name LIKE :name " +
                "GROUP BY e " +
                "ORDER BY report_count ";

        String sortBySubsCount = "SELECT e, COUNT(s.userId) as subs_count " +
                "FROM Event e LEFT JOIN Subscriptions s " +
                "ON e.id = s.eventId " +
                "WHERE e.name LIKE :name " +
                "GROUP BY e " +
                "order by subs_count ";

        switch (sortOrder) {
            default:
            case ID:
                return read("From Event p WHERE p.name LIKE :name ORDER BY p.id " + sortType.toString(), page, count, pattern);
            case NAME:
                return read("From Event p WHERE p.name LIKE :name ORDER BY p.name " + sortType.toString(), page, count, pattern);
            case DATE:
                return read("From Event p WHERE p.name LIKE :name ORDER BY p.dateBegin " + sortType.toString(), page, count, pattern);
            case TIME:
                return read("From Event p WHERE p.name LIKE :name ORDER BY p.timeBegin " + sortType.toString(), page, count, pattern);
            case SUBS:
                return readWithTwoTables(sortBySubsCount + sortType.toString(), page, count, pattern, Event.class, Subscriptions.class);
            case REPORT:
                return readWithTwoTables(sortByReportCount + sortType.toString(), page, count, pattern, Event.class, Report.class);
        }
    }

    private List<Event> read(String query, int page, int count, String pattern) {
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        List<Event> personList = new ArrayList<>();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Object per : session.createQuery(query)
                    .setParameter("name", '%' + pattern + '%')
                    .setFirstResult(page * count)
                    .setMaxResults(count)
                    .stream().toArray())
                personList.add((Event) per);

            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return personList;
    }


    private List<Event> readWithTwoTables(String query, int page, int count, String pattern,Class first, Class second) {
        Configuration configuration = new Configuration().addAnnotatedClass(first).addAnnotatedClass(second);
        List<Event> personList = new ArrayList<>();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Object[] objs = session.createQuery(query)
                    .setParameter("name", '%' + pattern + '%')
                    .setFirstResult(page * count)
                    .setMaxResults(count)
                    .stream().toArray();

            for (Object per : objs)
                for (Object obj : (Object[]) per)
                    if(obj instanceof Event)
                        personList.add((Event) obj);

            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return personList;
    }

    @Override
    public Event getByName(String name) {
        Event event;
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            event = (Event) session.createQuery("From Event p WHERE p.name LIKE :name")
                    .setParameter("name", name).uniqueResult();

            session.getTransaction().commit();
        } catch (NonUniqueResultException ex) {
            logger.error(ex.getMessage());
            event = null;
        }
        return event;
    }

    @Override
    public boolean subscribe(int eventId) {
        Event event = read(eventId);
        event.setSubscribers(event.getSubscribers()+1);

        boolean statement=false;
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
            statement=true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return statement;
    }

    @Override
    public boolean unsubscribe(int eventId) {
        Event event = read(eventId);
        event.setSubscribers(event.getSubscribers()-1);

        boolean statement=false;
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
            statement=true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return statement;
    }

    @Override
    public boolean addReport(int eventId) {
        Event event = read(eventId);
        event.setReports(event.getReports()+1);

        boolean statement=false;
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
            statement=true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return statement;
    }

    @Override
    public boolean dellReport(int eventId) {
        Event event = read(eventId);
        event.setReports(event.getReports()-1);

        boolean statement=false;
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
            statement=true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return statement;
    }

    @Override
    public boolean update(int id, Event entity) {
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
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
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Event p WHERE p.id = :entity_id")
                    .setParameter("entity_id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public int count(String pattern) {
        Long count = -1L;
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            count = (Long) session.createQuery("SELECT COUNT(*) FROM Event u WHERE u.name LIKE :name")
                    .setParameter("name", '%' + pattern + '%')
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return Integer.parseInt(count + "");
    }
}