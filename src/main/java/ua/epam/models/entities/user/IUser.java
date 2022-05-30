package ua.epam.models.entities.user;

import ua.epam.models.Role;
import ua.epam.models.entities.Entity;

import java.io.Serializable;

public interface IUser extends Entity, Serializable {
     int getId();

     void setId(int id);

     String getLogin();

     void setLogin(String login);

     void setPassword(String password);

     String getPassword();

     Role getRole();

     void setRole(Role role);

     String getName();

     void setName(String name);

     String getSurname();

     void setSurname(String surname);

     String getEmail();

     void setEmail(String email);
}
