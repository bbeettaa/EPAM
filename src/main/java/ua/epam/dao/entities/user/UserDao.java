package ua.epam.dao.entities.user;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.epam.models.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    private static final Logger logger
            = LoggerFactory.getLogger(UserDao.class);

    @Override
    public boolean create(User entity) {
        Configuration configuration = new Configuration().addAnnotatedClass(entity.getClass());
        encodePass(entity);
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
    public User read(int id) {
        User person = null;
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            person = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        //encodePass(person);
        return person;
    }

    @Override
    public User getByLogin(String login) {
        User person = null;
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            person = (User) session.createQuery("From User p WHERE p.login LIKE :username")
                    .setParameter("username", login).uniqueResult();

            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        //encodePass(person);
        return person;
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("From User p WHERE p.email LIKE :username")
                    .setParameter("username", email).getResultList();
            if(!users.isEmpty()) user = users.get(0);

            session.getTransaction().commit();
        } catch (NonUniqueResultException ex) {
            logger.error(ex.getMessage());
            user = null;
        }
        return user;
    }

    @Override
    public User getByLoginPassword(String login, String password) {
        User user;
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            user = (User) session.createQuery("From User p WHERE p.login LIKE :login AND p.password LIKE :pass")
                    .setParameter("login", login)
                    .setParameter("pass", password)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (NonUniqueResultException ex) {
            logger.error(ex.getMessage());
            user = null;
        }
        return user;
    }

    @Override
    public List<User> read(int page, int count, String pattern, SortOrder sortOrder, SortType sortType) {
        switch (sortOrder) {
            default:
            case ID:
                return read("From User p WHERE p.login LIKE :username ORDER BY p.id " + sortType.toString(), page, count, pattern);
            case NAME:
                return read("From User p WHERE p.login LIKE :username ORDER BY p.name " + sortType.toString(), page, count, pattern);
            case LOGIN:
                return read("From User p WHERE p.login LIKE :username ORDER BY p.login " + sortType.toString(), page, count, pattern);
            case EMAIL:
                return read("From User p WHERE p.login LIKE :username ORDER BY p.email " + sortType.toString(), page, count, pattern);
            case PASS:
                return read("From User p WHERE p.login LIKE :username ORDER BY p.password " + sortType.toString(), page, count, pattern);
            case SURNAME:
                return read("From User p WHERE p.login LIKE :username ORDER BY p.surname " + sortType.toString(), page, count, pattern);
            case ROLE:
                return read("From User p WHERE p.login   LIKE :username ORDER BY p.role " + sortType.toString(), page, count, pattern);
        }
    }

    private List<User> read(String query, int page, int count, String pattern) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        List<User> personList = new ArrayList<>();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Object per : session.createQuery(query)
                    .setParameter("username", '%' + pattern + '%')
                    .setFirstResult(page * count)
                    .setMaxResults(count)
                    .stream().toArray()) {

                personList.add((User) per);
            }

            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return personList;
    }

    @Override
    public boolean update(int id, User person) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);

        boolean statement = false;
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            person.setId(id);

            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
            statement = true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return statement;
    }

    @Override
    public void delete(int id) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User p WHERE p.id = :person_id")
                    .setParameter("person_id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public int count(String pattern) {
        Long count = -1L;
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            count = (Long) session.createQuery("SELECT COUNT(*) FROM User u WHERE u.login LIKE :userLogin")
                    .setParameter("userLogin", '%' + pattern + '%')
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return Integer.parseInt(count + "");
    }

    private void encodePass(User person){
        if (person != null)
            person.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));
    }

}
