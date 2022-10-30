package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

   @Autowired
   MovieService movieService;


    // 1. Add a movie
    //http://localhost:7080/movies/add-movie
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody()Movie movie){
       try {
          movieService.addMovie(movie);
           return new ResponseEntity<>("success", HttpStatus.CREATED);
       }catch(Exception e)
       {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
       }
    }

    // 2. Add a director: POST /movies/add-director
    //http://localhost:7080/movies/add-director
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody()Director director){
        try {
            movieService.addDirector(director);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

//  3.  Pair an existing movie and director
    @PutMapping("add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("moviename")String mname,
                                     @RequestParam("dirname")String dname){
        try {
            movieService.mapmd(mname,dname);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    // 4. Get Movie by movie name
  //   https://localhost:8080/get-movie-by-name?name="ppp"
    @GetMapping("/get-movie-by-name")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        return new ResponseEntity<>(movieService.getMovieByName(name), HttpStatus.OK);
    }

    //5. Get Director by director name:
    // http://localhost:7080/movies/get-director-by-name?name="Prajakta"
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        return new ResponseEntity<>(movieService.getDirectorByName(name), HttpStatus.OK);
    }


//  6.  Get List of movies name for a given director name
     @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<Director> getMoviesByDirectorName(@PathVariable("director") String director){
         return new ResponseEntity<>(movieService.getDirectorByName(director), HttpStatus.OK);
}

    //7. Get List of all movies added
    // http://localhost:7080/get-all-movies
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>>  findAllMovies(){
        return new ResponseEntity<>(movieService.getAll(),HttpStatus.FOUND);
    }

    //8.   Delete a director and its movies from the records
    // http://localhost:7080/delete-director-by-name?name="Prajakta"
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity <String>deleteDirectorByName(@RequestParam("directorName") String directorName){
        try {
            movieService.deleteDirectorByName(directorName);
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

// 9. Delete all directors and all movies by them from the records
   // http://localhost:7080/delete-all-directors
      @DeleteMapping ("/delete-all-directors")
        public ResponseEntity<String> deleteAllDirectors() {
          try {
              movieService.deleteAllDirector();
              return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
          } catch (Exception e) {
              return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
          }
      }


}
