package com.test.bean;

public class MovieNotFoundException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 4118114053713548824L;
    private long movie_id;
	public MovieNotFoundException(long movie_id) {
	        super(String.format("movie is not found with id : '%s'", movie_id));
	        }
}