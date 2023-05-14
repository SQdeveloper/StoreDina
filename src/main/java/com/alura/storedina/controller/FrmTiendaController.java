/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.controller;

import com.alura.storedina.dao.ClientDao;
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
        
    public void addItemToDataBase(JTable tbProducts, String clientName, String clientDni) {                                         
        Client client = new Client(clientName, clientDni);
        
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        ClientDao clientDao = new ClientDao(em);
        List<Client> clients = clientDao.queryForDni(clientDni);
        
        if(clients.size() > 0) {
            client = clients.get(0);
        }
        else {
            em.persist(client);
        }
        
        Order order = new Order(client);      
        em.persist(order);

        for(int i=0; i < tbProducts.getRowCount(); i++) {
            Product product = (Product) tbProducts.getValueAt(i, 0);
            
            String name = (String) product.getName();            
            int  amount = (int) tbProducts.getValueAt(i, 1);            
                                                                                
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
        });
        
        this.em.getTransaction().commit();
        this.em.close();
    }        
    
    public void loadComboAmount(JComboBox cbAmount, Product product) {                
        int amountProduct = product.getAmount();
        cbAmount.removeAllItems();        
        
        for(int i=1; i < amountProduct + 1; i++) {
            cbAmount.addItem(i);
        }                
    }

    public void updateTxtPrice(JTextField txtPrice, Product product, int amount) {            
        BigDecimal price = product.getPrice();
        
        txtPrice.setText(price.multiply(new BigDecimal(amount)).toString());
    }

    public void removeProductAtTable(JTable tbProducts) {
        int indexRow = tbProducts.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbProducts.getModel();
        
        model.removeRow(indexRow);
    }        
}
