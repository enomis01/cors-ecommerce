package it.paolone.ecommerce.dto;

import lombok.Data;

@Data
public class ShippingDTO {
    private String shippingDate;
    private String shippingAddress;
    private String shippingCompany;
    private String trackingCode;

}
