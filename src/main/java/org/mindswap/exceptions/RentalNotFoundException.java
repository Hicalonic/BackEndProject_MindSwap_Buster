package org.mindswap.exceptions;

public class RentalNotFoundException extends RuntimeException{

    public RentalNotFoundException() {
        super("Rental not found");
    }
}
