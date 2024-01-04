package it.paolone.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long customerId;
    @Column(name = "nominative")
    private String nominative;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email", unique = true)
    private User user;
    @Column(name = "phone_number")
    private String phoneNumber;
}
