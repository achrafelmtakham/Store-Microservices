package org.sid.billingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Entity
public class Bill {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date billingDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long CustomerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;

    public Bill(Long id, Date billingDate, Long customerId,Customer customer, Collection<ProductItem> productItems) {
        this.id = id;
        this.billingDate = billingDate;
        this.CustomerId = customerId;
        this.customer= customer;
        this.productItems = productItems;
    }

    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }

    public Collection<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(Collection<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billingDate=" + billingDate +
                ", CustomerId=" + CustomerId +
                ", productItems=" + productItems +
                '}';
    }
}
