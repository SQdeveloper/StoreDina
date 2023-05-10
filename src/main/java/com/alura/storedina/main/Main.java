/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.main;

import com.alura.storedina.dao.CategoryDao;
import com.alura.storedina.dao.ProductDao;
import com.alura.storedina.models.Category;
import com.alura.storedina.models.Product;
import com.alura.storedina.utils.JPAUtil;
import com.alura.storedina.view.LoginUser;
import javax.persistence.EntityManager;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args) {        
        
        //We adding product and categories
        addingProductsAndCategories();
        
        //We call to FrmLoginUser
        LoginUser loginUser = new LoginUser();
        loginUser.setLocationRelativeTo(null);
        loginUser.setVisible(true);        
    }             
    
    public static void addingProductsAndCategories() {
        //We We instantiate the EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        
        //We instantiate the daos
        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);

        //We instantiate the models
        Category tecnology = new Category("tecnology");                                       
        Category soda = new Category("soda");
        Category cookie = new Category("cookie");
        Category cleannigServices = new Category("cleannigServices");

        
        Product product1 = new Product("PlayStation", "console", 15, tecnology);
        Product product2 = new Product("Coca Cola", "gaseosa", 39, soda);
        Product product3 = new Product("Galleta Ricks", "galleta", 34, cookie);
        Product product4 = new Product("Papel Higienico", "servicio", 53, cleannigServices);        
        
        //We begin the transaction
        em.getTransaction().begin();
        
        //We persist the product and category
        categoryDao.save(tecnology);                
        categoryDao.save(soda);
        categoryDao.save(cookie);
        categoryDao.save(cleannigServices);

        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);
        productDao.save(product4);

        
        //We commit the transaction
        em.getTransaction().commit();
        
        //We close the transaction
        em.close();
    }
}
