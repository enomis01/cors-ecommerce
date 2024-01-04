package it.paolone.ecommerce.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDTO {

    private Long orderId;
    private Long customerId;
    private LocalDate orderDate;
    private Long shippingId;
}
