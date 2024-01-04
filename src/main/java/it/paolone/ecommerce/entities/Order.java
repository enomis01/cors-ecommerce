package it.paolone.ecommerce.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer joinedCustomer;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private Shipping joinedShipping;

    // getters and setters

    public Long getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getJoinedCustomer() {
        return joinedCustomer;
    }

    public Shipping getJoinedShipping() {
        return joinedShipping;
    }

    public void setJoinedCustomer(Customer joinedCustomer) {
        this.joinedCustomer = joinedCustomer;
    }

    public void setJoinedShipping(Shipping joinedShipping) {
        this.joinedShipping = joinedShipping;
    }

}
