package org.mindswap.exceptions;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(){
        super("Invoice not found");
    }
}
