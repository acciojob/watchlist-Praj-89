package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }

    void addDirector(Director director){
        movieRepository.addDirector(director);
    }

    void mapmd(String movie,String direct)
    {
        movieRepository.mapMovieDirect(movie,direct);
    }

    public Movie getMovieByName(String name) {

        return movieRepository.getMovie(name);
    }

    public Director getDirectorByName(String name) {

        return movieRepository.getDirector(name);
    }

    public List<String> getMoviesByDirector(String directorName) {

        return movieRepository.getMoviesByDirector(directorName);
    }
    List<Movie> getAll(){

        return movieRepository.getAllMovies();
    }

    public void deleteDirectorByName(String directorName) {

        movieRepository.deleteDirectorByName(directorName);
    }


    public void deleteAllDirector() {

        movieRepository.deleteAllDirectors();
    }

}
