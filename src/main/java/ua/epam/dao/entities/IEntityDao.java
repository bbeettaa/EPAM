package ua.epam.dao.entities;

public interface IEntityDao<T> extends IDao<T> {
    T read(int id);

    void delete(int id);

    boolean update(int id, T person);

    int count(String pattern);

    enum SortType {
        ASC, DESC
    }
}
