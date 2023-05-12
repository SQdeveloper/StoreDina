/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.controller;

import com.alura.storedina.dao.ProductDao;
import com.alura.storedina.models.Client;
import com.alura.storedina.models.ItemOrder;
import com.alura.storedina.models.Order;
import com.alura.storedina.models.Product;
import com.alura.storedina.utils.JPAUtil;
import com.alura.storedina.view.LoginUser;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class FrmTiendaController {
   
    private ProductDao productDao;
    private EntityManager em;

    public FrmTiendaController() {
    }
    
    public void addItemInTable(JTable tbProducts, Product product, int amount, int index, BigDecimal totalPrice) {                           
        
        BigDecimal unitaryPrice = product.getPrice();
        
        //We show the product add in the table(interfaz)
        DefaultTableModel model = (DefaultTableModel) tbProducts.getModel();
        model.addRow(new Object[] {product, amount, unitaryPrice, totalPrice});                        
    }
    
    //para el button COMPRARRRRRRRRR (BUYYYY)
    public void addItemToDataBase(JTable tbProducts, Client client) {                                         
        
        Order order = new Order(client);
        
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(client);
        em.persist(order);

        for(int i=0; i < tbProducts.getRowCount(); i++) {
            Product product = (Product) tbProducts.getValueAt(i, 0);
            
            String name = (String) product.getName();            
            int  amount = (int) tbProducts.getValueAt(i, 1);
            BigDecimal unitaryPrice = (BigDecimal) tbProducts.getValueAt(i, 2);
            BigDecimal totalPrice = (BigDecimal) tbProducts.getValueAt(i, 3);
                                                                                
            ItemOrder itemOrder = new ItemOrder(product, amount);                                                          
            order.addItems(itemOrder);               
                                    
            //We persist(save) the data in the dataBase            
            em.persist(itemOrder);            
        }
        
        em.getTransaction().commit();
        em.close();        
    }
    
    public void loadComboProduct(JComboBox cbProduct) {        
        
        this.em = JPAUtil.getEntityManager();               
        this.productDao = new ProductDao(em);
        this.em.getTransaction().begin();
                
        List<Product> products = productDao.getAllProducts();                        
        
        products.forEach(product -> {
            cbProduct.addItem(product);
            Product.addAllProducts(product);
        });
        
        this.em.getTransaction().commit();
        this.em.close();
    }        
    
    public void loadComboAmount(JComboBox cbAmount, int index) {
        List<Product> products = Product.getAllProducts();
        
        int amountProduct = products.get(index - 1).getAmount();
        cbAmount.removeAllItems();        
        
        for(int i=1; i < amountProduct + 1; i++) {
            cbAmount.addItem(i);
        }                
    }

    public void updateTxtPrice(JTextField txtPrice, int index, int amount) {
        List<Product> products = Product.getAllProducts();        
        BigDecimal price = products.get(index - 1).getPrice();
        
        txtPrice.setText(price.multiply(new BigDecimal(amount)).toString());
    }
}
