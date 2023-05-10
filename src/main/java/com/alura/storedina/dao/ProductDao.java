/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.dao;

import com.alura.storedina.models.Product;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author PC
 */
public class ProductDao {
    
    private EntityManager em;
    
    public ProductDao(EntityManager em) {
        this.em = em;
    }
    
    public void save(Product product) {
        this.em.persist(product);
    }
    
    public void remove(Product product) {
        this.em.remove(product);
    }    
    
    public List<Product> getAllProducts() {
        String jpql = "SELECT p FROM Product as p";
        return this.em.createQuery(jpql, Product.class).getResultList();
    }
}
