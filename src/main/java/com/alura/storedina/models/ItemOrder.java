/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.models;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name="itemsOrder")
public class ItemOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    private Order order;    
    private BigDecimal unitaryPrice;
    
    @ManyToOne
    private Product product;
    private int amount;
    
    public ItemOrder(){        
    }
    
    public ItemOrder(Product product, int amount){        
        this.product = product;
        this.amount = amount;
        this.unitaryPrice = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public Order setOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }        

    public BigDecimal getTotalPrice() {
        return this.unitaryPrice.multiply(new BigDecimal(this.amount));
    }
}
