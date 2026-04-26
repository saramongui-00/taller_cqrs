package edu.uptc.swii.ordercontroller.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerid")
    private String customerid;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private int quantity;

    public Order() {
    }

    public Order(Long id, String customerid, String product, int quantity) {
        this.id = id;
        this.customerid = customerid;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", customerid=" + customerid + ", product=" + product + ", quantity=" + quantity
                + "]";
    }

}