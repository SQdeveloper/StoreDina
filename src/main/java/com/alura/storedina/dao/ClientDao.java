/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.dao;

import com.alura.storedina.models.Client;
import javax.persistence.EntityManager;

/**
 *
 * @author PC
 */
public class ClientDao {
    
    private EntityManager em;
    
    public ClientDao(EntityManager em) {
        this.em = em;
    }
    
    public void save(Client client) {
        this.em.persist(client);
    }
    
    public void remove(Client client) {
        this.em.remove(client);
    }        
}
