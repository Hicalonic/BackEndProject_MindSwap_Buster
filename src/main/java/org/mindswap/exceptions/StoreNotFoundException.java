package org.mindswap.exceptions;

public class StoreNotFoundException extends RuntimeException{
    public StoreNotFoundException(){
        super("Store not found");
    }
}
