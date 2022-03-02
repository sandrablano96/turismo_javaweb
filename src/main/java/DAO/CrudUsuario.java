/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * @author DAW-A
 */
public class CrudUsuario implements ICrudUsuario {

    @Override
    public boolean insertar(Usuario u) {
        boolean isInsertado = false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        try {
            
            manager.getTransaction().begin();
   
            Query querysql = manager.createNamedQuery("Usuario.findByEmail");

            querysql.setParameter("email",u.getEmail());

            List usuarios = (List) querysql.getResultList();
 
            if (usuarios.isEmpty()) {
                manager.persist(u);
                isInsertado = true;
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            manager.close();
        }

        return isInsertado;
    }


    
    @Override
    public Usuario consultar(String email) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query querysql = manager.createNamedQuery("Usuario.findByEmail");
        querysql.setParameter("email", email);
        List results = querysql.getResultList();
        Usuario usuario= null;
        if (!results.isEmpty()) {
            usuario = (Usuario) results.get(0);
}
        
        manager.getTransaction().commit();
        manager.close();
        return usuario;
    }

    @Override
    public Usuario consultarPorId(int id) {
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Usuario usu = manager.find(Usuario.class, id);
        manager.getTransaction().commit();
        manager.close();
        return usu;
    }
    
}
