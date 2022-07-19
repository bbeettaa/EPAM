package ua.epam.dao.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.user.IUserDao;
import ua.epam.models.Role;
import ua.epam.models.entities.User;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class UserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private MessageSource messageSource;

    public List<User> get(int page, int count, String pattern, IUserDao.SortOrder sortOrder, IEntityDao.SortType sortType) {
        return userDao.read(page, count, pattern, sortOrder, sortType);
    }

    public User get(int id) {
        return userDao.read(id);
    }

    public boolean save(User user) {
        return userDao.create(user);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public boolean update(int id, User user) {
        return userDao.update(id, user);
    }

    public int count(String pattern) {
        return userDao.count(pattern);
    }

    public String getRoleByLoginPassword(String login, String password) {
        User user = userDao.getByLoginPassword(login, password);
        if (nonNull(user))
            return user.getRole();
        return Role.UNREGISTERED.toString();
    }

    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public boolean isError(User person, BindingResult bindingResult) {
        boolean error = bindingResult.hasErrors();

        if (this.isEmailExisted(person.getEmail(), person.getId())) {
            bindingResult.addError(new FieldError("person", "email",
                    person.getEmail(), false, null, null,
                    messageSource.getMessage("error.email.not.exist", null, LocaleContextHolder.getLocale())));
            error = true;
        }

        if (this.isLoginExisted(person.getLogin(), person.getId())) {
            bindingResult.addError(new FieldError("person", "login",
                    person.getLogin(), false, null, null,
                    messageSource.getMessage("error.login.not.exist", null, LocaleContextHolder.getLocale())));
            error = true;
        }
        return error;
    }

    public boolean isEmailExisted(String email, int id) {
        User user = getByEmail(email);
        return nonNull(user) && user.getId() != id;
    }

    public boolean isLoginExisted(String login, int id) {
        User user = getByLogin(login);
        return nonNull(user) && user.getId() != id;
    }
}
