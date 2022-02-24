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
import javax.persistence.Query;
import modelo.ElementoPatrimonial;


/**
 *
 * @author DAW-A
 */
public class CrudElementoPatrimonial implements ICrudElementoPatrimonial{

    @Override
    public boolean insertar(ElementoPatrimonial ep) {
        boolean insertado= false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        ElementoPatrimonial el = manager.find(ElementoPatrimonial.class, ep.getId());
        if(el == null){            
            manager.persist(ep);
            manager.getTransaction().commit();
            
            manager.close();
            factory.close();
            insertado = true;
        }
        return insertado;
    }

    @Override
    public boolean modificar(ElementoPatrimonial ep) {
        boolean modificado = false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        ElementoPatrimonial el = manager.find(ElementoPatrimonial.class, ep.getId());
        if(el != null){
            try{ 
            manager.merge(ep);
            manager.getTransaction().commit();
            modificado = true;
            
            }catch (Exception e){
                System.out.println(e.getMessage());
            } finally{
                manager.close();
                factory.close();
            }
        }
        return modificado;
    }

    @Override
    public boolean borrar(int id) {
        boolean eliminado = false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        ElementoPatrimonial el = manager.find(ElementoPatrimonial.class, id);
        if (el != null) {
            try {
                manager.remove(el);
                manager.getTransaction().commit();
                eliminado = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                manager.close();
            }
        }
        return eliminado;
    }

    @Override
    public ElementoPatrimonial consultarElementoPatrimonial(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        ElementoPatrimonial ep = manager.find(ElementoPatrimonial.class, id);
        manager.close();
        factory.close();
        return ep;
    }
    @Override
    public List consultarElementosPatrimoniales() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();

        List elementos = new ArrayList();
        
        elementos = manager.createNamedQuery("ElementoPatrimonial.findAll").getResultList();
        
        manager.close();
        
        factory.close();
            
        return elementos;
    }

    @Override
    public List<ElementoPatrimonial> consultarElementosPatrimonialesFiltros(String tipo, String nombre) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        List elementos = new ArrayList();
        Query querysql = null;

        if(tipo.length() > 0 && nombre.length() > 0){
            querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e WHERE e.nombre = :nombre and e.tipo.nombre = :tipo");
            querysql.setParameter("nombre",nombre);
            querysql.setParameter("tipo",tipo);
        } else if(nombre.length() > 0){
            querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e WHERE e.nombre = :nombre");
            querysql.setParameter("nombre",nombre);
        } else{
            querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e WHERE e.tipo.nombre = :tipo");
            querysql.setParameter("tipo",tipo);
        }
        elementos = querysql.getResultList();
        return elementos;
    }
    
}
