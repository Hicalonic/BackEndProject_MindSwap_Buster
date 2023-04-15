package org.mindswap.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
            super("Email not found");
    }
}
