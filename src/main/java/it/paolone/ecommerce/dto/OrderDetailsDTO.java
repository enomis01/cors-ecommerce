package it.paolone.ecommerce.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderDetailsDTO {
    private String customerEmail;
    private ShippingDTO shippingData;
    private List<String> productsBarcode;
    private int productQuantity;
}
