/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PC
 */

@Entity
@Table(name="products")
public class Product {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int amount;    
    @ManyToOne(fetch=FetchType.LAZY)
    private Category category;
    
    public Product() {        
    }
    
    public Product(String name, String description, int amount, BigDecimal price, Category category) {        
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.category = category;        
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }        

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }           

    public BigDecimal getPrice() {
        return price;
    }        
    
    @Override
    public String toString() {
        return this.name;
    }
}
