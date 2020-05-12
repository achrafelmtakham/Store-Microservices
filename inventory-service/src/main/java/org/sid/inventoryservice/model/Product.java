package org.sid.inventoryservice.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data@ToString
public class Product {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;

    public Product(Long id, String name,double price) {
        this.id = id;
        this.name = name;
        this.price= price;
    }

    public Product() {
    }
}
