package it.paolone.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class TransactionDetailsDTO {
    private PaymentDataDTO paymentDataData;
    private PaymentTypeDTO paymentTypeData;
    private TransactionDTO transactionData;

    public PaymentDataDTO getPaymentDataData() {
        return paymentDataData;
    }

    public PaymentTypeDTO getPaymentTypeData() {
        return paymentTypeData;
    }

    public TransactionDTO getTransactionData() {
        return transactionData;
    }

    public void setPaymentDataData(PaymentDataDTO paymentDataData) {
        this.paymentDataData = paymentDataData;
    }

    public void setPaymentTypeData(PaymentTypeDTO paymentTypeData) {
        this.paymentTypeData = paymentTypeData;
    }

    public void setTransactionData(TransactionDTO transactionData) {
        this.transactionData = transactionData;
    }
}
