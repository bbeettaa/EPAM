package ua.epam.dao.entities.user;

import ua.epam.dao.entities.IEntityDao;
import ua.epam.models.entities.User;

import java.util.List;

public interface IUserDao extends IEntityDao<User> {
    List<User> read(int page, int count, String pattern, SortOrder sortOrder, SortType sortType);

    User getByLogin(String login);

    User getByEmail(String email);

    User getByLoginPassword(String login, String password);

    enum SortOrder {
        ID, PASS, LOGIN, EMAIL, NAME, SURNAME, ROLE
    }
}
