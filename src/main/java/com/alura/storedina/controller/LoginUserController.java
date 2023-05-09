/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.controller;

import com.alura.storedina.dao.ClientDao;
import com.alura.storedina.models.Client;
import com.alura.storedina.utils.JPAUtil;
import javax.persistence.EntityManager;

/**
 *
 * @author PC
 */
public class LoginUserController {
    
    private ClientDao clientDao;
    private EntityManager em;
    
    public LoginUserController() {
        this.em = JPAUtil.getEntityManager();
        this.clientDao = new ClientDao(em);
    }
    
    public void saveData(String name, String dni) {
        this.em.getTransaction().begin();
        Client client = new Client(name, dni);
        clientDao.save(client);
        this.em.getTransaction().commit();
        this.em.close();
    }
}
