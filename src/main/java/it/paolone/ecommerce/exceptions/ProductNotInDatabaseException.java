package it.paolone.ecommerce.exceptions;

public class ProductNotInDatabaseException extends Exception {

    public ProductNotInDatabaseException(){
        super();
    }

    public ProductNotInDatabaseException(String message){
        super(message);
    }
}
