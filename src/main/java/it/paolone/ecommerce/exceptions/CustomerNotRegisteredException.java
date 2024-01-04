package it.paolone.ecommerce.exceptions;

public class CustomerNotRegisteredException extends Exception {

    public CustomerNotRegisteredException() {
        super();
    }

    public CustomerNotRegisteredException(String message) {
        super(message);
    }
}
