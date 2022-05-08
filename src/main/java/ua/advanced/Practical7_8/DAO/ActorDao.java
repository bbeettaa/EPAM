package ua.advanced.Practical7_8.DAO;

import ua.advanced.Practical7_8.Entity.Actor;
import ua.advanced.Practical7_8.ConnectionCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActorDao extends AbstractDao<Actor> {

    public static final String ALL_FILM = """
            SELECT *
            FROM VideoLibrary.actors;""";
    public static final String FILM_COUNT = """
            SELECT VideoLibrary.actors.*, count(*)
            FROM VideoLibrary.films,VideoLibrary.actors
            WHERE VideoLibrary.films.actorId = VideoLibrary.actors.id
            Group by actors.name;""";
    public static final String ACTORS_THAT_ARE_DIRECTORS = """
            SELECT videoLibrary.directors.*
            FROM VideoLibrary.films, VideoLibrary.actors, VideoLibrary.directors
            WHERE VideoLibrary.films.actorId = VideoLibrary.actors.id AND VideoLibrary.Films.name = VideoLibrary.Directors.CreatedFilmname
            Group by Directors.DirectorName;""";


    public List<Actor> findAll() {
        List<Actor> actors = new ArrayList<>();
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ALL_FILM);
            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt("Id"));
                actor.setName(resultSet.getString("Name"));
                actors.add(actor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
        return actors;
    }

    public List<Actor> findActorsStaredInMovies(int filmCount) {
        List<Actor> actors = new ArrayList<>();
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FILM_COUNT);
            while (resultSet.next()) {
                if (resultSet.getInt("count(*)") >= filmCount) {
                    Actor actor = new Actor();
                    actor.setId(resultSet.getInt("Id"));
                    actor.setName(resultSet.getString("Name"));
                    actor.setDate(resultSet.getDate("Birth").toString());
                    actors.add(actor);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
        return actors;
    }

    public List<Actor> findActorsThatAreDirectors() {
        List<Actor> actors = new ArrayList<>();
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ACTORS_THAT_ARE_DIRECTORS);
            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt("DirectorId"));
                actor.setName(resultSet.getString("DirectorName"));
                actor.setDate(resultSet.getDate("DirectorBirth").toString());
                actors.add(actor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
        return actors;
    }

    @Override
    public Actor findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Actor entity) {
        return false;
    }

    @Override
    public boolean create(Actor entity) {
        return false;
    }

    @Override
    public Actor update(Actor entity) {
        return null;
    }
}
