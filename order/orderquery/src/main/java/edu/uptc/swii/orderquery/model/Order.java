package edu.uptc.swii.orderquery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "orders")
public class Order {

    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("customerid")
    private String customerid;
    @JsonProperty("product")
    private String product;
    @JsonProperty("quantity")
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