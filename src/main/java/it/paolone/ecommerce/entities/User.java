package it.paolone.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
public class User {

    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;

}