package ua.epam.utils.builder;

import ua.epam.AppContext;
import ua.epam.models.Role;
import ua.epam.models.entities.user.IUser;
import ua.epam.models.entities.user.User;
import ua.epam.utils.builder.IBuilder;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Build user from SQL request or HTTP request
 */

public class UserBuilder implements IBuilder<IUser> {


    @Override
    public IUser buildFromHttpReq(HttpServletRequest req) {
        IUser user = new User();
        try {
            user.setLogin(req.getParameter("loginToSet"));
            user.setPassword(req.getParameter("passwordToSet"));
            String roleStr = req.getParameter("roleToSet").toUpperCase();
            user.setRole(Enum.valueOf(Role.UNREGISTERED.getDeclaringClass(), roleStr));
            user.setEmail(req.getParameter("emailToSet"));
            user.setName(req.getParameter("nameToSet"));
            user.setSurname(req.getParameter("surnameToSet"));
        } catch (NullPointerException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return user;
    }

    @Override
    public IUser buildFromSqlReq(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("user_login"));
            user.setPassword(resultSet.getString("user_pass"));
            user.setEmail(resultSet.getString("user_email"));
            user.setName(resultSet.getString("user_name"));
            user.setSurname(resultSet.getString("user_surname"));

            String roleStr = resultSet.getString("user_role");
            user.setRole(Enum.valueOf(Role.UNREGISTERED.getDeclaringClass(), roleStr));

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return user;
    }
}
