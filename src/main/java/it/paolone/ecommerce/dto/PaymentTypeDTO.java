package it.paolone.ecommerce.dto;

public class PaymentTypeDTO {
    private String typeName;
    private String registrationDate;

    public String getTypeName() {
        return typeName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
