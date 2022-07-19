package ua.epam.dao.entities;

public interface IDao<T> {

    boolean create(T entity);

}
