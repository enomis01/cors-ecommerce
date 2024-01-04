package it.paolone.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import it.paolone.ecommerce.dto.PaymentDataDTO;
import it.paolone.ecommerce.dto.TransactionDTO;
import it.paolone.ecommerce.dto.TransactionDetailsDTO;
import it.paolone.ecommerce.entities.PaymentData;
import it.paolone.ecommerce.entities.Transaction;

@Service
@AllArgsConstructor
public class TransactionDetailsService {

    private final PaymentDataService paymentDataService;
    private final ModelMapper modelMapper;
    private final TransactionService transactionService;

    public List<TransactionDetailsDTO> getAll() {
        List<Transaction> transactionData = transactionService.getAllTransaction();
        List<TransactionDetailsDTO> transactionDetailsDTOList = new ArrayList<>();

        for (Transaction transaction : transactionData) {
            PaymentDataDTO paymentDataDataDTO = paymentDataService
                    .convertToPaymentDataDTO(paymentDataService.getPaymentDataById(transaction.getId()));
            TransactionDetailsDTO returnData = modelMapper.map(transaction, TransactionDetailsDTO.class);
            returnData.setPaymentDataData(paymentDataDataDTO);
        }
        return transactionDetailsDTOList;
    }

    public TransactionDetailsDTO saveNewTransaction(TransactionDetailsDTO dataInput) {
        if (dataInput != null) {
            PaymentData finalFormPayment = modelMapper.map(dataInput.getPaymentDataData(), PaymentData.class);

            PaymentData returningPaymentDataRawData = paymentDataService.savePaymentData(finalFormPayment);

            TransactionDetailsDTO returningCheckingTransaction = new TransactionDetailsDTO();
            returningCheckingTransaction
                    .setPaymentDataData(modelMapper.map(returningPaymentDataRawData, PaymentDataDTO.class));
            returningCheckingTransaction
                    .setTransactionData(modelMapper.map(returningCheckingTransaction, TransactionDTO.class));

            return returningCheckingTransaction;

        } else {
            throw new IllegalArgumentException("!!! saveNewOrder in TransactionDetailsService cannot be null !!!");
        }
    }

    public TransactionDetailsDTO retrieveTransactionDetailsDTOById(Long id) {

        Transaction transaction = transactionService.getTransactionById(id);
        TransactionDetailsDTO finalTransaction = modelMapper.map(transaction, TransactionDetailsDTO.class);
        return finalTransaction;
    }
}
