/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name="orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    private Client client;
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private List<ItemOrder> items;
    private LocalDate orderDate;
    private BigDecimal totalPrice;
    
    public Order() {        
    }
    
    public Order(Client client) {        
        this.client = client;
        this.orderDate = LocalDate.now();        
        this.items = new ArrayList();
        this.totalPrice = new BigDecimal(0);
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ItemOrder> getItems() {
        return items;
    }

    public void addItems(ItemOrder item) {
        item.setOrder(this);
        this.items.add(item);
        
        //We add total price
        this.totalPrice.add(item.getTotalPrice());
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal TotalPrice) {
        this.totalPrice = TotalPrice;
    }        
}
