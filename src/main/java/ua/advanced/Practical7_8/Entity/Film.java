package ua.advanced.Practical7_8.Entity;

import java.util.*;

public class Film extends Entity {
    String name;
    List<Actor> actors = new ArrayList<>();
    Date releaseDate;
    String country;
    int actorId;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Actor> getActorsName() {
        return actors;
    }

    public void setActorsName(Actor actor) {
        this.actors.add(actor);
    }

    @Override
    public String toString() {
        StringJoiner actorsString = new StringJoiner(", ","Actors {","}");
        for(var actor:actors)
            actorsString.add(actor.toString());

        return new StringJoiner(", ")
                .add(name)
                .add(releaseDate.toString())
                .add(country)
                .add(actors.toString())
                .toString();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
