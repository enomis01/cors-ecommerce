package it.paolone.ecommerce.controllers;

import java.util.List;
import it.paolone.ecommerce.dto.ProductDTO;
import it.paolone.ecommerce.entities.Product;
import it.paolone.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import it.paolone.ecommerce.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5500")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/all_products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> fetchedProducts = productService.getAllProducts();
        List<ProductDTO> fetchedProductsDto = new ArrayList<>();

        if (!fetchedProducts.isEmpty()) {
            for (Product fetchedProduct : fetchedProducts)
                fetchedProductsDto.add(productService.convertToProductDto(fetchedProduct));

            return ResponseEntity.ok(fetchedProductsDto);

        }

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/update_price/{barcode}/{newPrice}")
    public ResponseEntity<ProductDTO> updateProductPrice(@PathVariable String barcode, @PathVariable float newPrice) {
        try {
            productService.rePriceProduct(barcode, newPrice);
            Product updatedProduct = productRepository.searchProductByBarcode(barcode);
            return ResponseEntity.ok(productService.convertToProductDto(updatedProduct));
        } catch (ResponseStatusException exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while updating price of product n." + barcode + ": " + exc);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Generic error while updating price of product n." + barcode + ": " + exc);

        }
    }

    @PostMapping("/delete_product/{productBarcode}")
    public ResponseEntity<ProductDTO> deleteProductByBarcode(@PathVariable String productBarcode) {
        try {
            Product deletedProduct = productService.removeProductByBarcode(productBarcode);
            return ResponseEntity.ok(productService.convertToProductDto(deletedProduct));
        } catch (ResponseStatusException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "An error occurred while trying to delete product n." + productBarcode + ": " + exc);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while trying to delete product n." + productBarcode + ": " + exc);
        }
    }

}
