package it.paolone.ecommerce.exceptions;

public class UserAndCustomerEmailMismatchException extends Exception {

    public UserAndCustomerEmailMismatchException() {
        super();
    }

    public UserAndCustomerEmailMismatchException(String message) {
        super(message);
    }

}
