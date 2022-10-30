package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MovieRepository {

    HashMap<String, Movie> movies = new HashMap<>();

    HashMap<String, Director> directors = new HashMap<>();

    HashMap<String, List<String>> mdpair = new HashMap<>();

    void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    void mapMovieDirect(String movie, String direct) {
        List<String> movielist;
        if (mdpair.containsKey(direct)) {
            movielist = mdpair.get(direct);
        } else {
            movielist = new ArrayList<>();
        }
        movielist.add(movie);
        mdpair.put(direct, movielist);
    }

    public Movie getMovie(String name) {
        try {
            Movie newm = movies.get(name);
            return newm;
        } catch (Exception e) {
            return null;
        }
    }

    public Director getDirector(String name) {
        try {
            Director newd = directors.get(name);
            return newd;
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getAllMovies () {
        List<String> list = new ArrayList<>();
        for (String movie : movies.keySet()) {
            list.add(movie);

        }
        return list;
    }

    public void deleteDirector(String directorName) {
        directors.remove(directorName);
    }

    public void deleteMovie(String name){
        movies.remove(name);
    }


    public void deleteDirectorByName(String directorName) {
        if (mdpair.containsKey(directorName)) {
            List<String> movieList = mdpair.get(directorName);
            for (String name : movieList
            ) {
                deleteMovie(name);
            }
            deleteDirector(directorName);
            mdpair.remove(directorName);
        }
    }

    public void deleteAllDirectors() {
        if (!mdpair.isEmpty()) {
            for (String name : mdpair.keySet()) {

                deleteDirectorByName(name);

            }
            mdpair.clear();
        }
    }

    public List<String> getMoviesByDirector(String directorName) {
        if(mdpair.containsKey(directorName)) {
            for (String curr : mdpair.keySet()) {
                if (curr.equals(directorName)) {
                    return mdpair.get(curr);
                }
            }
        }
        return null;
    }
}
