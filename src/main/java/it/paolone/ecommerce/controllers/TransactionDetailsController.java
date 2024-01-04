package it.paolone.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;

import it.paolone.ecommerce.services.TransactionDetailsService;
import lombok.AllArgsConstructor;

import it.paolone.ecommerce.dto.TransactionDetailsDTO;

@RestController
@RequestMapping("/api/transaction_details")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5500")
public class TransactionDetailsController {

    public final TransactionDetailsService transactionDetailsService;

    @GetMapping("/{id}")
    public TransactionDetailsDTO getTransactionData(@PathVariable Long id) {
        return transactionDetailsService.retrieveTransactionDetailsDTOById(id);

    }

    @PostMapping("/save_new_transaction")
    public TransactionDetailsDTO crateTransaction(@RequestBody TransactionDetailsDTO transactionDetailsDTO) {

        return this.transactionDetailsService.saveNewTransaction(transactionDetailsDTO);
    }

}
