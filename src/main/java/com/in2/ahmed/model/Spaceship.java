package com.in2.ahmed.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Spaceship entity.
 */
@Document(collection = "spaceships")
public class Spaceship {
    @Id
    private String id;

    @Field
    private String name;

    @Field
    private List<String> series;

    @Field
    private List<String> movies;

    /**
     * Constructs a Spaceship object with the given id and name.
     * Initializes series and movies as empty lists.
     *
     * @param id   the id of the spaceship
     * @param name the name of the spaceship
     */
    public Spaceship(String id, String name) {
        this(id, name, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Constructs a Spaceship object with the given id, name, series, and movies.
     *
     * @param id     the id of the spaceship
     * @param name   the name of the spaceship
     * @param series the series associated with the spaceship
     * @param movies the movies associated with the spaceship
     */
    public Spaceship(String id, String name, List<String> series, List<String> movies) {
        this.id = id;
        this.name = name;
        this.series = new ArrayList<>(series);
        this.movies = new ArrayList<>(movies);
    }

    /**
     * Default constructor. Initializes id, name, series, and movies as null or empty lists.
     */
    public Spaceship() {
        this(null, null, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Copy constructor. Creates a new Spaceship object with the same properties as the given spaceship.
     *
     * @param spaceship the spaceship to copy
     */
    public Spaceship(Spaceship spaceship) {
        this(spaceship.getId(), spaceship.getName(), spaceship.getSeries(), spaceship.getMovies());
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }
}
