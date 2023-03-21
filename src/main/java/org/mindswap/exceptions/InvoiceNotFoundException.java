package org.mindswap.exceptions;

public class InvoiceNotFoundException extends Exception{
    public InvoiceNotFoundException(){
        super("Invoice not found");
    }
}
