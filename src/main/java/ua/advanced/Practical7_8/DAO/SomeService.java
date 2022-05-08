package ua.advanced.Practical7_8.DAO;

import ua.advanced.Practical7_8.Entity.Actor;
import ua.advanced.Practical7_8.Entity.Film;

import java.sql.SQLException;
import java.util.List;

public class SomeService {
    public void doService() {
// 1. init DAO
        FilmDao filmDao = new FilmDao();
        ActorDao actorDao = new ActorDao();
// 2. transaction initialization for DAO objects
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(filmDao, actorDao);
// 3. query execution
        try {
            //filmDao.create(parameters_1);
            //actorDao.update(parameters_2);
            //actorDao.delete(parameters_3);
            transaction.commit();
        } finally {
// 4. transaction closing
            transaction.endTransaction();
        }
    }

    public List<Film> findAllFilms() throws SQLException {
        List<Film> films;
        FilmDao filmDao = new FilmDao();
        ActorDao actorDao = new ActorDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(filmDao, actorDao);
        try {
            films = filmDao.findAll();
            transaction.commit();
        } finally {
            transaction.endTransaction();
        }
        return films;
    }

    public List<Film> findAllFilmsInCurrentAndPreviousYear(int year) throws SQLException {
        List<Film> films;
        FilmDao filmDao = new FilmDao();
        ActorDao actorDao = new ActorDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(filmDao, actorDao);
        try {
            films = filmDao.findFilmsInCurrentPreviousYear(year);
            transaction.commit();
        } finally {
            transaction.endTransaction();
        }
        return films;
    }

    public List<Actor> findActorsStarredInAtLeastFilms(int filmCount) throws SQLException {
        List<Actor> actors;
        FilmDao filmDao = new FilmDao();
        ActorDao actorDao = new ActorDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(filmDao, actorDao);
        try {
            actors = actorDao.findActorsStaredInMovies(filmCount);
            transaction.commit();
        } finally {
            transaction.endTransaction();
        }
        return actors;
    }

    public List<Actor> findActorThatIsDirector() throws SQLException {
        List<Actor> actors;
        FilmDao filmDao = new FilmDao();
        ActorDao actorDao = new ActorDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(filmDao, actorDao);
        try {
            actors = actorDao.findActorsThatAreDirectors();
            transaction.commit();
        } finally {
            transaction.endTransaction();
        }
        return actors;
    }

    public void deleteFilmsFromPreviousYears(int year) throws SQLException {
        FilmDao filmDao = new FilmDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(filmDao);
        try {
            filmDao.deleteFilmsAboveYear(year);
            transaction.commit();
        } finally {
            transaction.endTransaction();
        }
    }
}