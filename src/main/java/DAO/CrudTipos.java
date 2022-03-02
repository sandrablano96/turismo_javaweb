/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tipo;

/**
 *
 * @author DAW-A
 */
public class CrudTipos implements ICrudTipos {

    @Override
    public Tipo consultar(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Tipo tipo = manager.find(Tipo.class, id);
        manager.close();
        factory.close();
        return tipo;
    }

    @Override
    public List<Tipo> consultarTodos() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();

        List tipos = new ArrayList();
        
        tipos = manager.createNamedQuery("Tipo.findAll").getResultList();
        
        manager.close();
        
        factory.close();
            
        return tipos;
    }
    
}
