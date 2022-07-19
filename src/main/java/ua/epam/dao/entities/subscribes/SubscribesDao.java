package ua.epam.dao.entities.subscribes;

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

public class SubscribesDao implements ISubscribesDao {

    private static final Logger logger
            = LoggerFactory.getLogger(SubscribesDao.class);

    @Override
    public int subscribersCount(int eventId) {
        Long count = -1L;
        Configuration configuration = new Configuration().addAnnotatedClass(Subscriptions.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            count = (Long) session.createQuery("SELECT COUNT(*) FROM Subscriptions p WHERE cast(p.eventId as string) LIKE :eventId")
                    .setParameter("eventId", eventId + "")
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return Integer.parseInt(count + "");
    }

    @Override
    public int subscriptionsCount(int userId) {
        Long count = -1L;
        Configuration configuration = new Configuration().addAnnotatedClass(Subscriptions.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            count = (Long) session.createQuery("SELECT COUNT(*) FROM Subscriptions p WHERE cast(p.userId as string) LIKE :userId")
                    .setParameter("userId", userId + "")
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return Integer.parseInt(count + "");
    }

    @Override
    public Subscriptions read(int eventId, int userId) {
        Subscriptions subscriptions = null;
        Configuration configuration = new Configuration().addAnnotatedClass(Subscriptions.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            subscriptions = (Subscriptions) session.createQuery("FROM Subscriptions p WHERE p.eventId = :eventId AND p.userId = :userId")
                    .setParameter("eventId", eventId)
                    .setParameter("userId", userId)
                    .getSingleResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return subscriptions;
    }

    @Override
    public List<Event> read(int userId, int page, int count, String pattern, SortOrder sortOrder, IEntityDao.SortType sortType) {
        String staticQuery = "From Event p ,Subscriptions s " +
                "WHERE p.name LIKE :name AND cast(p.id as string) LIKE cast(s.eventId as string) " +
                "AND cast(s.userId as string) LIKE :id ORDER BY ";
        switch (sortOrder) {
            default:
            case ID:
                return read(staticQuery + "p.id " + sortType.toString(), page, count, pattern, userId);
            case NAME:
                return read(staticQuery + "p.name " + sortType.toString(), page, count, pattern, userId);
            case DATE:
                return read(staticQuery + "p.dateBegin " + sortType.toString(), page, count, pattern, userId);
            case TIME:
                return read(staticQuery + "p.timeBegin " + sortType.toString(), page, count, pattern, userId);
            case SUBS:
                return read(staticQuery + "p.name " + sortType.toString(), page, count, pattern, userId);
            case REPORT:
                return read(staticQuery + "p.reports " + sortType.toString(), page, count, pattern, userId);
        }
    }

    private List<Event> read(String query, int page, int count, String pattern, int userId) {
        Configuration configuration = new Configuration().addAnnotatedClass(Event.class).addAnnotatedClass(Subscriptions.class);
        List<Event> personList = new ArrayList<>();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Object[] objs = session.createQuery(query)
                    .setParameter("id", userId + "")
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
    public List<Integer> getSubscribers(int eventId) {
        Configuration configuration = new Configuration().addAnnotatedClass(Subscriptions.class);
        List<Integer> idList = new ArrayList<>();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            for (Object per : session.createQuery("Select sub From Subscriptions sub WHERE cast(sub.eventId as string ) LIKE :eventId")
                    .setParameter("eventId", "%" + eventId + "%")
                    .stream().toArray())
                idList.add(((Subscriptions) per).getUserId());

            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return idList;
    }

    @Override
    public boolean create(Subscriptions entity) {
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
    public void delete(int eventId, int userId) {
        Configuration configuration = new Configuration().addAnnotatedClass(Subscriptions.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Subscriptions p WHERE p.eventId = :eventId AND p.userId = :userId")
                    .setParameter("eventId", eventId)
                    .setParameter("userId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

}
