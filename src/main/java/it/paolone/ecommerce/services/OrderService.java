package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.OrderDTO;
import it.paolone.ecommerce.entities.Order;
import it.paolone.ecommerce.repositories.OrderRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;


    public Order getOrderById(Long id) {
        Optional<Order> fetchedOrder = orderRepository.findById(id);
        return fetchedOrder.orElse(null);
    }

    public List<Order> getAllOrders() {
        List<Order> fetchedOrders = orderRepository.findAll();
        return fetchedOrders;
    }

    public Order saveOrder(Order data) {
        return orderRepository.save(data);
    }

    public OrderDTO convertToOrderDTO(Order data) {
        return modelMapper.map(data, OrderDTO.class);
    }

    public Order convertToOrder(OrderDTO source) {
        if (source != null) {
            return modelMapper.map(source, Order.class);
        } else {
            throw new IllegalArgumentException("!!! convertToOrder input cannot be null !!!");
        }

    }
}
