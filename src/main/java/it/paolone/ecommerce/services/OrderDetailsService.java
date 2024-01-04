package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.*;
import it.paolone.ecommerce.entities.*;
import it.paolone.ecommerce.exceptions.CustomerNotRegisteredException;
import it.paolone.ecommerce.exceptions.ProductNotInDatabaseException;
import it.paolone.ecommerce.exceptions.ProductQuantityException;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailsService {

    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final ShippingService shippingService;
    private final OrderService orderService;
    private final ProductService productService;
    private final SoldProductService soldProductService;

    public OrderDetailsDTO convertToOrderDetailsDto(Order order) {
        ShippingDTO shippingDtoData = shippingService.convertToShippingDTO(order.getJoinedShipping());

        OrderDetailsDTO returnData = modelMapper.map(order, OrderDetailsDTO.class);
        returnData.setCustomerEmail(order.getJoinedCustomer().getUser().getEmail());
        returnData.setShippingData(shippingDtoData);

        return returnData;

    }

    public List<OrderDetailsDTO> getAllOrdersDetails() {
        List<Order> ordersData = orderService.getAllOrders();
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();

        for (Order order : ordersData) {
            ShippingDTO shippingDtoData = shippingService.convertToShippingDTO(order.getJoinedShipping());

            OrderDetailsDTO returnData = modelMapper.map(order, OrderDetailsDTO.class);
            returnData.setCustomerEmail(order.getJoinedCustomer().getUser().getEmail());
            returnData.setShippingData(shippingDtoData);
        }

        return orderDetailsDTOList;
    }

    public OrderDetailsDTO saveNewOrder(OrderDetailsDTO dataInput)
            throws ProductQuantityException, CustomerNotRegisteredException, ProductNotInDatabaseException {
        if (dataInput != null) {

            OrderDetailsDTO returningCheckObj = new OrderDetailsDTO();

            if (customerService.isCustomerAlreadyRegistered(dataInput.getCustomerEmail())) {
                Customer fetchedCustomerData = customerService.getCustomerByEmail(dataInput.getCustomerEmail());
                returningCheckObj.setCustomerEmail(fetchedCustomerData.getUser().getEmail());
            } else {
                throw new CustomerNotRegisteredException();
            }

            // generazione lista prodotti da lista barcode
            List<Product> orderProducts = new ArrayList<>();
            for (String productBarcode : dataInput.getProductsBarcode()) {
                Product fetchedProduct = productService.getProductByBarcode(productBarcode);
                if (fetchedProduct == null)
                    throw new ProductNotInDatabaseException();
                orderProducts.add(fetchedProduct);
            }

            Shipping finalFormShipping = modelMapper.map(dataInput.getShippingData(), Shipping.class);
            Shipping returningShippingRawData = shippingService.saveShippping(finalFormShipping);

            returningCheckObj.setShippingData(modelMapper.map(returningShippingRawData, ShippingDTO.class));

            // generating Order obj

            OrderDTO buildingOrderDto = new OrderDTO();
            buildingOrderDto
                    .setCustomerId(customerService.getCustomerByEmail(dataInput.getCustomerEmail()).getCustomerId());
            buildingOrderDto.setOrderDate(LocalDate.now());
            buildingOrderDto.setShippingId(returningShippingRawData.getId());

            Order savingOrderData = modelMapper.map(buildingOrderDto, Order.class);
            Order savedOrderRaw = orderService.saveOrder(savingOrderData);

            for (Product productData : orderProducts) {
                int actualQuantity = productService.getProductByBarcode(productData.getProductBarcode())
                        .getProductQuantity();
                int orderedQuantity = dataInput.getProductQuantity();

                int updatedQuantity = actualQuantity - orderedQuantity;

                if (updatedQuantity < 0) {
                    throw new ProductQuantityException();
                } else if (updatedQuantity == 0) {
                    productService.removeProductByBarcode(productData.getProductBarcode());
                } else {
                    productService.updateProductQuantity(productData.getProductBarcode(), updatedQuantity);
                }

                for (short quantity = 0; quantity < orderedQuantity; quantity++) {
                    SoldProductDTO soldProductData = new SoldProductDTO();
                    soldProductData.setOrderId(savedOrderRaw.getOrderId());
                    soldProductData.setProductId(productData.getProductId());

                    SoldProducts convertedSoldProductData = new SoldProducts();
                    convertedSoldProductData.setJoinedOrder(savedOrderRaw);
                    convertedSoldProductData.setJoinedProduct(productData);

                    soldProductService.saveSoldProduct(convertedSoldProductData);
                }
            }

            return returningCheckObj;

        } else {
            throw new IllegalArgumentException("!!! saveNewOrder in OrderDetailsService cannot be null !!!");
        }
    }

}
