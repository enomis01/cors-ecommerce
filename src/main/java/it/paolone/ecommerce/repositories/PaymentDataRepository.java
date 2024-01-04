package it.paolone.ecommerce.repositories;

import it.paolone.ecommerce.entities.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDataRepository extends JpaRepository<PaymentData, Long> {
}
