/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.controller;

import com.alura.storedina.dao.ProductDao;
import com.alura.storedina.models.Product;
import com.alura.storedina.utils.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JComboBox;

/**
 *
 * @author PC
 */
public class FrmTiendaController {
   
    private ProductDao productDao;
    private EntityManager em;

    public FrmTiendaController() {
    }
    
    public void addItemList() {        
    }
    
    public void loadComboProduct(JComboBox cbProduct) {        
        
        this.em = JPAUtil.getEntityManager();               
        this.productDao = new ProductDao(em);
        this.em.getTransaction().begin();
                
        List<Product> products = productDao.getAllProducts();                        
        
        products.forEach(product -> {
            cbProduct.addItem(product.getName());
            Product.addAllProducts(product);
        });
        
        this.em.getTransaction().commit();
        this.em.close();
    }        
    
    public void loadComboAmount(JComboBox cbAmount, int index) {
        List<Product> products = Product.getAllProducts();
        
        int amountProduct = products.get(index - 1).getAmount();
        cbAmount.removeAllItems();
        cbAmount.addItem("--cantidad--");
        
        for(int i=1; i < amountProduct + 1; i++) {
            cbAmount.addItem(i);
        }
    }
}
