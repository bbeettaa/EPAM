package ua.advanced.Practical7_8.DAO;

import ua.advanced.Practical7_8.Entity.Actor;
import ua.advanced.Practical7_8.ConnectionCreator;
import ua.advanced.Practical7_8.Entity.Film;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilmDao extends AbstractDao<Film> {
    public static final String ALL_FILM = """
            SELECT  VideoLibrary.films.*, VideoLibrary.actors.Name, VideoLibrary.actors.Birth
            FROM VideoLibrary.films, VideoLibrary.actors
            WHERE VideoLibrary.films.actorId = VideoLibrary.actors.id ;""";
    public static final String FILMS_WITH_CURRENT_PREVIOUS_YEARS = """
            SELECT  VideoLibrary.films.*, VideoLibrary.actors.Name, VideoLibrary.actors.Birth
            FROM VideoLibrary.films,VideoLibrary.actors
            WHERE VideoLibrary.films.actorId = VideoLibrary.actors.id\s
            AND ( (select year (releaseDate)) = (select year(?))\s
            Or (select year (releaseDate)) = (select year(?)) );""";
    public static final String DELETE_FILMS_ABOVE_YEAR = """
            Delete FROM VideoLibrary.films
            WHERE  (select year (releaseDate)) < (select year(?));""";

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ALL_FILM);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Film film ;
                var findResultFilm = films.stream().filter(x -> x.getName().equals(name)).findAny();

                if (findResultFilm.isEmpty()) {
                    film = new Film();
                    film.setId(resultSet.getInt("Id"));
                    film.setName(resultSet.getString("Name"));
                    film.setReleaseDate(resultSet.getDate("releaseDate"));
                    film.setCountry(resultSet.getString("Country"));
                    film.setActorId(resultSet.getInt("actorId"));
                    film.setActorsName(new Actor(resultSet.getString(6),resultSet.getString(7)));
                    films.add(film);
                } else {
                    film = findResultFilm.get();
                    film.setActorsName(new Actor(resultSet.getString(6),resultSet.getString(7)));
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
        return films;
    }

    public List<Film> findFilmsInCurrentPreviousYear(int year) {
        List<Film> films = new ArrayList<>();
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(FILMS_WITH_CURRENT_PREVIOUS_YEARS);
            prepareStatement.setString(1,year+"-0-0");
            prepareStatement.setString(2,year-1+"-0-0");

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Film film ;
                var findResultFilm = films.stream().filter(x -> x.getName().equals(name)).findAny();

                if (findResultFilm.isEmpty()) {
                    film = new Film();
                    film.setId(resultSet.getInt("Id"));
                    film.setName(resultSet.getString("Name"));
                    film.setReleaseDate(resultSet.getDate("releaseDate"));
                    film.setCountry(resultSet.getString("Country"));
                    film.setActorId(resultSet.getInt("actorId"));
                    film.setActorsName(new Actor(resultSet.getString(6),resultSet.getString(7)));
                    films.add(film);
                } else {
                    film = findResultFilm.get();
                    film.setActorsName(new Actor(resultSet.getString(6),resultSet.getString(7)));
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
        return films;
    }

    public void deleteFilmsAboveYear(int year) {
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(DELETE_FILMS_ABOVE_YEAR);
            prepareStatement.setString(1,year+"-0-0");


            prepareStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
    }


    @Override
    public Film findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Film entity) {
        return false;
    }

    @Override
    public boolean create(Film entity) {
        return false;
    }

    @Override
    public Film update(Film entity) {
        return null;
    }
// implementation
}

/*

public class AbonentDaoImpl implements AbonentDao {
    private static final String SQL_SELECT_ALL_ABONENTS =
            "SELECT idphonebook, lastname,phone FROM phonebook";
    private static final String SQL_SELECT_ABONENT_BY_LASTNAME =
            "SELECT idphonebook, phone FROM phonebook WHERE lastname=?";

    @Override
    public List<Abonent> findAbonentByLastname(String namePattern)
            throws DaoException {
        List<Abonent> abonents = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;9
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ABONENT_BY_LASTNAME);
            statement.setString(1, namePattern);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Abonent abonent = new Abonent();
                abonent.setId(resultSet.getInt("idphonebook"));
                abonent.setPhone(resultSet.getInt("phone"));
                abonent.setName(resultSet.getString("lastname"));
                abonents.add(abonent);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return abonents;
    }



    @Override
    public Abonent findEntityById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Abonent abonent) throws DaoException {
        JAVA FROM EPAM
        448
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Abonent abonent) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Abonent update(Abonent abonent) throws DaoException {
        throw new UnsupportedOperationException();
    }
}*/
