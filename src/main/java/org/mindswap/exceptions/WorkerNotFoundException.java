package org.mindswap.exceptions;

public class WorkerNotFoundException extends RuntimeException{
    public WorkerNotFoundException(){
        super("Worker not found");
    }
}
