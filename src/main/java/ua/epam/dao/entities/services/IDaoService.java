package ua.epam.dao.entities.services;

import ua.epam.models.entities.Entity;

import java.util.List;

public interface IDaoService<T extends Entity> {
    List<T> findAll();

    T findById(int id);

    boolean isExist(T entity);

    T findByArg(String... args);

    boolean deleteById(int id);

    boolean add(T entity);

    boolean update(T entity);
}
