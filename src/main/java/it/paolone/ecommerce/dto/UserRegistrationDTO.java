package it.paolone.ecommerce.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String nominative;
    private String phoneNumber;
    private String email;
    private String password;
    private String roles;

}
