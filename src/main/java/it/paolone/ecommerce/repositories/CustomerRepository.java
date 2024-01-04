package it.paolone.ecommerce.repositories;

import it.paolone.ecommerce.entities.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT COUNT(*) FROM customer WHERE email = ?1", nativeQuery = true)
    Integer countByEmail(String email);

    @Query(value = "SELECT * FROM customer WHERE email = ?1", nativeQuery = true)
    Optional<Customer> getCustomerByEmail(String customerEmail);

}
