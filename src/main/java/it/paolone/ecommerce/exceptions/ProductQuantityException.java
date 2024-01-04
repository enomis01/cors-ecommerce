package it.paolone.ecommerce.exceptions;

public class ProductQuantityException extends Exception{

    public ProductQuantityException(){
        super();
    }

    public ProductQuantityException(String message){
        super(message);
    }

}
