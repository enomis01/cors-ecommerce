package it.paolone.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDataDTO {

    // @Pattern(regexp = "\\d{16}", message = "Il numero della carta deve essere composto da 16 cifre.")
    private String cardNumber;

    // @Pattern(regexp = "\\d{3,4}", message = "Il CVC deve essere composto da 3 o 4 cifre.")
    private String cvc;

    // @Pattern(regexp = "(0[1-9]|1[0-2])", message = "Il mese di scadenza deve essere compreso tra 01 e 12.")
    private String expiresMonth;

    // @Pattern(regexp = "\\d{4}", message = "L'anno di scadenza deve essere composto da 4 cifre.")
    private String expiresYear;

    // Getter, setter e altri metodi

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

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
}

