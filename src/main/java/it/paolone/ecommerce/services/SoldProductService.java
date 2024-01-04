package it.paolone.ecommerce.services;

import java.util.List;
import java.util.Optional;
import it.paolone.ecommerce.entities.SoldProducts;
import it.paolone.ecommerce.repositories.SoldProductsRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SoldProductService {

        private final SoldProductsRepository soldProductsRepository;

        public SoldProducts getSoldProductById(Long id) {
                Optional<SoldProducts> fetchedSoldProduct = soldProductsRepository.findById(id);
                return fetchedSoldProduct.orElse(null);
        }

        public List<SoldProducts> getAllSoldProducts() {
                return soldProductsRepository.findAll();
        }

        public SoldProducts saveSoldProduct(SoldProducts data) {
                return soldProductsRepository.save(data);
        }

        public List<SoldProducts> getSoldProductsByOrderDate(String orderDate) {
                return soldProductsRepository.findSoldProductsByOrderDate(orderDate);
        }

        public List<SoldProducts> getSoldProductsByProductId(Long productId) {
                return soldProductsRepository.findSoldProductsByProductId(productId);
        }

        public List<SoldProducts> getSoldProductsByOrderId(Long orderId) {
                return soldProductsRepository.findSoldProductsByOrderId(orderId);
        }
}
