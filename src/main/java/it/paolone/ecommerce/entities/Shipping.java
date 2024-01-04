package it.paolone.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "shipping")
@Entity
@Data
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "shipping_date")
    private String shippingDate;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_company")
    private String shippingCompany;

    @Column(name = "tracking_code", unique = true)
    private String trackingCode;
  
    public long getId() {
        return id;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
}
