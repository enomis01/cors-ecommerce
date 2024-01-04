package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.ProductDTO;
import it.paolone.ecommerce.entities.Product;
import it.paolone.ecommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public Product getProductById(Long id) {
        Optional<Product> fetchedProduct = productRepository.findById(id);
        return fetchedProduct.orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product data) {
        Optional<Product> eventuallyExistingProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(data.getProductBarcode()));

        if (eventuallyExistingProduct.isPresent()) {
            Product existingData = eventuallyExistingProduct.get();
            int newQuantity = data.getProductQuantity() + existingData.getProductQuantity();
            productRepository.updateProductQuantityByBarcode(data.getProductBarcode(), newQuantity);
            return productRepository.searchProductByBarcode(data.getProductBarcode());
        } else {
            return productRepository.save(data);
        }
    }

    public Product rePriceProduct(String productBarcode, float newPrice) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            productRepository.updateProductPriceByBarcode(productBarcode, newPrice);
            return productRepository.searchProductByBarcode(productBarcode);
        }

        return null;

    }

    public Product getProductByBarcode(String productBarcode) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            return fetchedProduct.get();
        }

        return null;

    }

    public Boolean isAvaiable(Product data) {
        int quantity = productRepository.countProductsByBarcode(data.getProductBarcode());
        return quantity > 0 && quantity < data.getProductQuantity();
    }

    public Product updateProductQuantity(String productBarcode, int newQuantity) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            productRepository.updateProductQuantityByBarcode(productBarcode, newQuantity);
            return productRepository.searchProductByBarcode(productBarcode);
        }

        return null;
    }

    public Product removeProductByBarcode(String productBarcode) {
        Optional<Product> fetchedProduct = Optional
                .ofNullable(productRepository.searchProductByBarcode(productBarcode));

        if (fetchedProduct.isPresent()) {
            productRepository.deleteProductByBarcode(productBarcode);
            return fetchedProduct.get();
        }

        return null;
    }

    public ProductDTO convertToProductDto(Product data) {
        return modelMapper.map(data, ProductDTO.class);
    }

    public Product convertToProduct(ProductDTO data) {
        return modelMapper.map(data, Product.class);
    }

}
