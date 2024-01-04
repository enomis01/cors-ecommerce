package it.paolone.ecommerce.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name= "transaction")
@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "id")
  private Long id;

    @Column (name = "transaction_date")
    private String transactionDate;

    @Column(name = "transaction_amount")
    private int transactionAmount;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "payment_data", referencedColumnName = "id")
    private PaymentData paymentData;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "payment_type", referencedColumnName = "type_name")
    // private PaymentType paymentType;

    public Long getId() {
        return id;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

  public PaymentData getPaymentData() {
    return paymentData;
  }

  // public PaymentType getPaymentType() {
  //   return paymentType;
  // }

  public void setPaymentData(PaymentData paymentData) {
    this.paymentData = paymentData;
  }

  // public void setPaymentType(PaymentType paymentType) {
  //   this.paymentType = paymentType;
  // }
}
