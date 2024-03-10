package com.in2.ahmed.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

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

    public Spaceship(String id, String name) {
        this(id, name, new ArrayList<>(), new ArrayList<>());
    }

    public Spaceship(String id, String name, List<String> series, List<String> movies) {
        this.id = id;
        this.name = name;
        this.series = new ArrayList<>(series);
        this.movies = new ArrayList<>(movies);
    }

    public Spaceship() {
        this(null, null, new ArrayList<>(), new ArrayList<>());
    }

    public Spaceship(Spaceship spaceship) {
        this(spaceship.getId(), spaceship.getName(), spaceship.getSeries(), spaceship.getMovies());
    }

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
