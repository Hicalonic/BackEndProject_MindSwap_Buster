package org.mindswap.exceptions;

public class WorkerNotFoundException extends Exception{
    public WorkerNotFoundException(){
        super("Worker not found");
    }
}
