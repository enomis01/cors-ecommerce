package it.paolone.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_barcode", unique = true)
    private String productBarcode;
    @Column(name = "image_filename", unique = true)
    private String imageFilename;
    @Column(name = "quantity")
    private int productQuantity;
    @Column(name = "price")
    private float productPrice;

}
