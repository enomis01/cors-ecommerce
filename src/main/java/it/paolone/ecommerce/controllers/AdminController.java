package it.paolone.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import it.paolone.ecommerce.dto.OrderDetailsDTO;
import it.paolone.ecommerce.dto.ProductDTO;
import it.paolone.ecommerce.dto.SoldProductDTO;
import it.paolone.ecommerce.entities.Product;
import it.paolone.ecommerce.entities.SoldProducts;
import it.paolone.ecommerce.services.OrderDetailsService;
import it.paolone.ecommerce.services.ProductService;
import it.paolone.ecommerce.services.SoldProductService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth/admin")
@CrossOrigin(origins = "http://localhost:5500")
@AllArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final SoldProductService soldProductService;
    private final ModelMapper modelMapper;
    private final OrderDetailsService orderDetailsService;

    @PostMapping("/products/new_product")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO data) {

        if (data != null) {
            try {// try catch serve per la gestione delle eccezioni

                // converte il dato in DTO che sta in input in un'entità normale
                Product convertedDataDto = productService.convertToProduct(data);

                // ricreo un oggetto dto che corrisponde al ritorno convertito di
                // productService.saveproduct(convertedDataDto)
                ProductDTO returningProductData = productService
                        .convertToProductDto(productService.saveProduct(convertedDataDto));
                return ResponseEntity.ok(returningProductData);
            } catch (ResponseStatusException exc) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred while processing the request: " + exc);
            } catch (Exception exc) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred while processing the request : " + exc);
            }
        } else
            return ResponseEntity.badRequest().build();

    }

    @GetMapping("sold_products/list_all")
    @PreAuthorize("hasAuthority('ROLE ADMIN')")
    public ResponseEntity<List<SoldProductDTO>> getAllSoldProducts() {
        List<SoldProducts> fetchedSoldProductsRaw = soldProductService.getAllSoldProducts();

        if (!fetchedSoldProductsRaw.isEmpty()) {

            List<SoldProductDTO> fetchedSoldProducts = new ArrayList<>();

            for (SoldProducts soldProduct : fetchedSoldProductsRaw)
                fetchedSoldProducts.add(modelMapper.map(soldProduct, SoldProductDTO.class));

            return ResponseEntity.ok(fetchedSoldProducts);
        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping("sold_product/list_date/{date}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<SoldProductDTO>> getAllSoldProductsByOrderDate(@PathVariable String orderDate) {
        List<SoldProducts> fetchedDataRaw = soldProductService.getSoldProductsByOrderDate(orderDate);
        if (!fetchedDataRaw.isEmpty()) {
            List<SoldProductDTO> fetchedData = new ArrayList<>();

            for (SoldProducts soldProduct : fetchedDataRaw)
                fetchedData.add(modelMapper.map(soldProduct, SoldProductDTO.class));

            return ResponseEntity.ok(fetchedData);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("sold_product/by_product_id")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<SoldProductDTO>> getAllSoldProductsByProductId(@PathVariable Long productId) {
        List<SoldProducts> fetchedDataRaw = soldProductService.getSoldProductsByProductId(productId);
        if (!fetchedDataRaw.isEmpty()) {
            List<SoldProductDTO> fetchedData = new ArrayList<>();

            for (SoldProducts soldProduct : fetchedDataRaw)
                fetchedData.add(modelMapper.map(soldProduct, SoldProductDTO.class));

            return ResponseEntity.ok(fetchedData);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("sold_product/by_order_id")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<SoldProductDTO>> getAllSoldProductsByOrderId(@PathVariable Long orderId) {
        List<SoldProducts> fetchedDataRaw = soldProductService.getSoldProductsByOrderId(orderId);
        if (!fetchedDataRaw.isEmpty()) {
            List<SoldProductDTO> fetchedData = new ArrayList<>();

            for (SoldProducts soldProduct : fetchedDataRaw)
                fetchedData.add(modelMapper.map(soldProduct, SoldProductDTO.class));

            return ResponseEntity.ok(fetchedData);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/get_all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails(){
        List<OrderDetailsDTO> fetchedData = orderDetailsService.getAllOrdersDetails();
        if(!fetchedData.isEmpty()){
            return ResponseEntity.ok(fetchedData);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

}
