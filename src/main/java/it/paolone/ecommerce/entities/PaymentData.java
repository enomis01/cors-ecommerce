package it.paolone.ecommerce.entities;

import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "payment_data")
public class PaymentData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "cvc")
    private String cvc;
    @Column(name = "expires_month")
    private String expiresMonth;
    @Column(name = "expires_year")
    private String expiresYear;

    @OneToOne

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public String getExpiresMonth() {
        return expiresMonth;
    }

    public String getExpiresYear() {
        return expiresYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardnumber(String cardnumber) {
        this.cardNumber = cardnumber;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public void setExpiresMonth(String expiresMonth) {
        this.expiresMonth = expiresMonth;
    }

    public void setExpiresYear(String expiresYear) {
        this.expiresYear = expiresYear;
    }

    public List<PaymentData> getAllPaymentData() {
        return null;
    }
}
