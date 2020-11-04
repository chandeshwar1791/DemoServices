package com.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.bean.Movie;
import com.test.bean.MovieNotFoundException;
import com.test.repository.MovieRepository;

@RestController
@RequestMapping("/MovieServices")
public class HomeController {

	@Autowired
	MovieRepository movieRepo;
	
	@RequestMapping(method= RequestMethod.POST,value="api/createMovie")
	public ResponseEntity<String> createMovie(@RequestBody Movie movie)
	{
	    try{
	        movieRepo.save(movie);
	        return new ResponseEntity<String>("Successfully added movie "+movie.getTitle(), HttpStatus.OK);
	    }
	    catch(Exception e){
	        return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = {"api/getMoviesName","api/getMoviesName/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getAll(@PathVariable(name = "id", required = false) final Integer id)
	{
		Optional requestedID =Optional.ofNullable(id);
		if(!requestedID.isPresent())
		{
			List<Movie> movies= movieRepo.findAll();
			if(movies.size()>0)
	        {
	            return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
	        }
			else
	        {
	            return new ResponseEntity("No movies found",HttpStatus.NOT_FOUND);
	        }
		}
		else
        {
			List<Movie> movies = movieRepo.findIdById(Long.valueOf(id));
			if(movies.size()>0)
	        {
	            return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
	        }
			else
	        {
	            return new ResponseEntity("No movies found",HttpStatus.NOT_FOUND);
	        }
        }
        
    }

    @RequestMapping(value = "api/deleteMovieUsingId/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMovie(@PathVariable long id) throws MovieNotFoundException {
    	try{
            movieRepo.deleteById(id);
            return new ResponseEntity<String>("Successfully deleted movie with id "+id,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/movieDataUpdate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateMovie(@PathVariable long id, @RequestBody Movie movie) {
    	Optional<Movie> movieOptional =movieRepo.findById(id);
    	if(movieOptional.isPresent())
    	{
    		Movie update=movieOptional.get();
	        update.setTitle(movie.getTitle());
	        update.setStar(movie.getStar());
	        update.setCategory(movie.getCategory());
	        movieRepo.save(update);
	        return new ResponseEntity<String>("Updated Movie with id "+id,HttpStatus.OK);
    	}
    	else
        {
            return new ResponseEntity<String>("No Movie with id "+id+" found",HttpStatus.NOT_FOUND);
        }
    }
}
