package org.mindswap.exceptions;


public class ImdbMovieModelNotFoundException extends RuntimeException{

    public ImdbMovieModelNotFoundException() {
        super("Imdb Movie not found");
    }
}
