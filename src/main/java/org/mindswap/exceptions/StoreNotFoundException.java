package org.mindswap.exceptions;

public class StoreNotFoundException extends Exception{
    public StoreNotFoundException(){
        super("Store not found");
    }
}
