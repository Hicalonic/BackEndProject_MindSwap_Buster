package org.mindswap.exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(){
        super("Movie not found");
    }
}
