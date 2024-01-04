package it.paolone.ecommerce.repositories;

import it.paolone.ecommerce.entities.SoldProducts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SoldProductsRepository extends JpaRepository<SoldProducts, Long> {

    @Query(value = "SELECT * FROM sold_products sp " +
            "INNER JOIN orders o ON sp.order_id = o.id " +
            "INNER JOIN order_product op ON sp.product_id = op.product_id " +
            "WHERE o.order_date = :date", nativeQuery = true)
    List<SoldProducts> findSoldProductsByOrderDate(@Param("date") String date);

    @Query(value = "SELECT * FROM sold_products WHERE product_id = :product_id", nativeQuery = true)
    List<SoldProducts> findSoldProductsByProductId(@Param("product_id") Long productId);

    @Query(value = "SELECT * FROM sold_products WHERE order_id = :order_id", nativeQuery = true)
    List<SoldProducts> findSoldProductsByOrderId(@Param("order_id") Long orderId);
}
