package ua.epam.utils.builder;

import ua.epam.models.entities.Entity;
import ua.epam.models.entities.user.IUser;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

public interface IBuilder<T extends Entity> {
    T buildFromHttpReq(HttpServletRequest req);

    T buildFromSqlReq(ResultSet resultSet);
}
