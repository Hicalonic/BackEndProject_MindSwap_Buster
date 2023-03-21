package org.mindswap.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(){
        super("Client not found");
    }
}
