package org.mindswap.exceptions;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(){
        super("Client not found");
    }
}
