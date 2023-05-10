/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.dao;

import com.alura.storedina.models.Category;
import javax.persistence.EntityManager;

/**
 *
 * @author PC
 */
public class CategoryDao {
    private EntityManager em;
    
    public CategoryDao(EntityManager em) {
        this.em = em;
    }
    
    public void save(Category category) {
        this.em.persist(category);
    }
    
    public void remove(Category category) {
        this.em.remove(category);
    }     
}
