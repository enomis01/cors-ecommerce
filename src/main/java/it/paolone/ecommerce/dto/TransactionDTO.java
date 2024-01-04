package it.paolone.ecommerce.dto;

public class TransactionDTO {

    private Long transactionId;
    private String transactionDate;
    private int transactionAmount;
    private Long paymentTypeId;
    private Long paymentDataId;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public Long getPaymentTypeId() {
        return this.paymentTypeId;
    }

    public Long getPaymentDataId() {
        return this.paymentDataId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public void setPaymentDataId(Long paymentDataId) {
        this.paymentDataId = paymentDataId;
    }
}
