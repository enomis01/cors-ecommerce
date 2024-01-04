package it.paolone.ecommerce.repositories;

import it.paolone.ecommerce.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
