package ua.epam.dao.entities.entity;

import ua.epam.models.entities.user.IUser;

import java.util.List;

public class EventSubscribersDao extends AbstractDao<IUser> {
    @Override
    public IUser get(String... args) {
        return null;
    }

    @Override
    public List<IUser> get() {
        return null;
    }

    @Override
    public IUser get(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(IUser entity) {
        return false;
    }

    @Override
    public boolean create(IUser entity) {
        return false;
    }

    @Override
    public IUser update(IUser entity) {
        return null;
    }
}
