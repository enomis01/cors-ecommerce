package it.paolone.ecommerce.repositories;

import it.paolone.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product p WHERE p.barcode = ?1", nativeQuery = true)
    public Product searchProductByBarcode(String productBarcode);

    @Modifying
    @Query(value = "UPDATE product SET quantity = ?2 WHERE barcode = ?1", nativeQuery = true)
    void updateProductQuantityByBarcode(String productBarcode, int newQuantity);

    @Modifying
    @Query(value = "UPDATE product SET price = ?2 WHERE barcode = ?1", nativeQuery = true)
    void updateProductPriceByBarcode(String productBarcode, float newPrice);

    @Modifying
    @Query(value = "DELETE FROM product WHERE barcode = ?1", nativeQuery = true)
    void deleteProductByBarcode(String productBarcode);

    @Query(value = "SELECT COUNT(*) FROM product p WHERE p.barcode =? 1", nativeQuery = true)
    Integer countProductsByBarcode(String productBarcode);

}
