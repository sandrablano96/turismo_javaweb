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
import modelo.Detalle;
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
        ElementoPatrimonial el = null;
        manager.getTransaction().begin();
            Query querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e WHERE e.nombre = :nombre and e.tipo.id = :tipo and e.localidad = :localidad");
            querysql.setParameter("nombre",ep.getNombre());
            querysql.setParameter("tipo",ep.getTipo().getId());
            querysql.setParameter("localidad",ep.getLocalidad());
            List resultado = querysql.getResultList();
            if(resultado.size() == 0){
                manager.persist(ep);
                
                manager.flush();
                ep.getId();
                el = manager.find(ElementoPatrimonial.class, ep.getId());
                insertado = true;
            }

            manager.getTransaction().commit();
            
            manager.close();
            factory.close();

        return insertado;
    }

    @Override
    public ElementoPatrimonial modificar(ElementoPatrimonial ep) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        ElementoPatrimonial el = manager.find(ElementoPatrimonial.class, ep.getId());
        
        if(el != null){
            try{ 
            manager.merge(ep);
            manager.getTransaction().commit();
            
            }catch (Exception e){
                System.out.println(e.getMessage());
            } finally{
                manager.close();
                factory.close();
            }
        }
        return el;
    }

    @Override
    public boolean borrar(int id) {
        boolean eliminado = false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        ElementoPatrimonial el = manager.find(ElementoPatrimonial.class, id);
        Detalle det = manager.find(Detalle.class, el.getDetalle().getId());
        if (el != null) {
            try {
                manager.remove(det);
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
        Query querysql = null;
        querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e order by e.nombre asc");
        elementos = querysql.getResultList();
        manager.close();
        
        factory.close();
            
        return elementos;
    }
    @Override
    public List consultarElementosPatrimonialesOrdenados(String orden) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        List elementos = new ArrayList();
        Query querysql = null;
        querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e order by e.nombre " + orden);
        elementos = querysql.getResultList();
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
            querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e WHERE e.nombre LIKE :nombre and e.tipo.nombre = :tipo");
            querysql.setParameter("nombre","%" +nombre +"%");
            querysql.setParameter("tipo",tipo);
        } else if(nombre.length() > 0){
            querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e WHERE e.nombre LIKE :nombre");
            querysql.setParameter("nombre","%" +nombre +"%");
        } else{
            querysql = manager.createQuery("SELECT e FROM ElementoPatrimonial e WHERE e.tipo.nombre = :tipo");
            querysql.setParameter("tipo",tipo);
        }
        elementos = querysql.getResultList();
        return elementos;
    }
    
}
