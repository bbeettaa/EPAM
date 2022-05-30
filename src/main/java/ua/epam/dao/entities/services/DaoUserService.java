package ua.epam.dao.entities.services;

import ua.epam.AppContext;
import ua.epam.dao.entities.entity.UserDao;
import ua.epam.dao.utils.EntityTransaction;
import ua.epam.models.entities.user.IUser;

import java.util.List;
import java.util.Objects;

public class DaoUserService implements IDaoService<IUser> {

    UserDao userDao = new UserDao();

    @Override
    public boolean isExist(IUser entity) {
        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(userDao);
        try {
            if(Objects.nonNull(this.findByArg(entity.getLogin(), entity.getPassword()))){
                transaction.commit();
                statement=true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;
    }

    @Override
    public IUser findByArg(String... args) {
        IUser user = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(userDao);
        try {
            user = userDao.get(args);
            transaction.commit();
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return user;
    }

    public List<IUser> findAll() {
        List<IUser> users = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(userDao);
        try {
            users = userDao.get();
            transaction.commit();
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return users;
    }

    public IUser findById(int id) {
        IUser user = null;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(userDao);
        try {
            user = userDao.get(id);
            transaction.commit();
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return user;
    }

    public boolean deleteById(int id)  {
        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(userDao);
        try {
            if(userDao.delete(id)) {
                transaction.commit();
                statement=true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;
    }

    public boolean add(IUser user){
        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(userDao);
        try {
            if(this.isExist(user))
                throw new IllegalArgumentException("cannot create new user because user with current login is exist");
            else if(userDao.create(user)) {
                transaction.commit();
                statement=true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;
    }

    public boolean update(IUser user){
        boolean statement = false;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(userDao);
        try {
            if(Objects.nonNull(userDao.update(user))) {
                transaction.commit();
                statement=true;
            }
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
        } finally {
            transaction.endTransaction();
        }
        return statement;
    }
}