package ua.epam.dao;

import ua.epam.dao.entities.services.DaoUserService;
import ua.epam.models.entities.user.IUser;
import ua.epam.models.entities.user.User;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * Facade to mysql connection pool
 * */

public class UserRepo {

    DaoUserService dao = new DaoUserService();

    public boolean userIsExist(String login, String password) {
        return dao.isExist(new User(login,password));
    }

    public IUser get(String login, String password) {
        return dao.findByArg(login,password);
    }

    public boolean delete(int id) {
        return dao.deleteById(id);
    }

    public List<IUser> get() {
        return dao.findAll();
    }

    public IUser get(String login){
        return dao.findAll().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
    }

    public IUser getByEmail(String email){
        return dao.findAll().stream().filter(u -> nonNull(u.getEmail()) && u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public boolean update(IUser user) {
        return dao.update(user);
    }

    public boolean add(IUser user) {
        return dao.add(user);
    }

    public IUser get(int id) {
        return dao.findById(id);
    }
}
