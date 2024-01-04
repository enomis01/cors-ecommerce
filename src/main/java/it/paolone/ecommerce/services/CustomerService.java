package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.CustomerDTO;
import it.paolone.ecommerce.entities.Customer;
import it.paolone.ecommerce.repositories.CustomerRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public Customer getCustomerById(Long query) {
        Optional<Customer> fetchedCustomer = customerRepository.findById(query);
        return fetchedCustomer.orElse(null);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> fetchedCustomers = customerRepository.findAll();
        return fetchedCustomers;
    }

    public Customer saveCustomer(Customer data) {
        return customerRepository.save(data);
    }

    public Customer getCustomerByEmail(String customerEmail) {
        Optional<Customer> fetchedCustomer = customerRepository.getCustomerByEmail(customerEmail);
        return fetchedCustomer.orElse(null);
    }

    public CustomerDTO convertToCustomerDTO(Customer data) {
        return modelMapper.map(data, CustomerDTO.class);
    }

    public Customer convertToCustomer(CustomerDTO data) {
        return modelMapper.map(data, Customer.class);
    }

    public Boolean isCustomerAlreadyRegistered(String customerEmail) {
        return customerRepository.countByEmail(customerEmail) > 0;
    }

}
