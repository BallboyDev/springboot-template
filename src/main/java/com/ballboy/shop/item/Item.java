package com.ballboy.shop.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return this.price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament
    private Integer id;

    @Column(nullable = false, unique = true)
    private String title;

    private Integer price;

    @Override
    public String toString() {
        return "Item(" +
                "id='" + this.id + "', " +
                "title='" + this.title + "', " +
                "price='" + this.price + "'" +
                ")";
    }
}
