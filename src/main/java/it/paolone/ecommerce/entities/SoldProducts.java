package it.paolone.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sold_products")
@Data
public class SoldProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sold_product_id")
    private Long soldProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_barcode", referencedColumnName = "product_barcode")
    private Product joinedProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order joinedOrder;
}
