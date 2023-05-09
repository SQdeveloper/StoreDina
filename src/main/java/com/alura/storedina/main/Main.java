/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.storedina.main;

import com.alura.storedina.utils.JPAUtil;
import com.alura.storedina.view.LoginUser;
import javax.persistence.EntityManager;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        LoginUser loginUser = new LoginUser();
        loginUser.setLocationRelativeTo(null);
        loginUser.setVisible(true);        
    }             
}
