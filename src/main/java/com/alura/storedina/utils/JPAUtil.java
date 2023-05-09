/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC
 */
public class JPAUtil {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("storeDina");   
   
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
