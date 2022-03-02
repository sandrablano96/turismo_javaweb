/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Favorito;

/**
 *
 * @author Sandra
 */
public class CrudFavoritos implements ICrudFavoritos{
    
    @Override
    public boolean insertar(Favorito fvrt) {
        boolean insertado = false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query querysql = manager.createQuery("SELECT f FROM Favorito f WHERE f.favoritoPK.idUsuario = :idUsuario and f.favoritoPK.idElemento = :idElemento");
        querysql.setParameter("idUsuario",fvrt.getFavoritoPK().getIdUsuario());
        querysql.setParameter("idElemento",fvrt.getFavoritoPK().getIdElemento());
        List favs = querysql.getResultList();
        if(favs.size() == 0){            
            manager.persist(fvrt);
            manager.getTransaction().commit();
            
            manager.close();
            factory.close();
            insertado = true;
        }
        return insertado;
    }

    @Override
    public boolean eliminar(Favorito fvrt) {
        boolean eliminado = false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query querysql = manager.createQuery("SELECT f FROM Favorito f WHERE f.favoritoPK.idUsuario = :idUsuario and f.favoritoPK.idElemento = :idElemento");
        querysql.setParameter("idUsuario",fvrt.getFavoritoPK().getIdUsuario());
        querysql.setParameter("idElemento",fvrt.getFavoritoPK().getIdElemento());
        List favs = querysql.getResultList();
        if(favs.size() > 0){
            try {
                Favorito fav = manager.merge(fvrt);
                manager.remove(fav);
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
    public List<Favorito> cargarFavoritosUsuario(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query querysql = manager.createQuery("SELECT f FROM Favorito f WHERE f.favoritoPK.idUsuario = :idUsuario");
        querysql.setParameter("idUsuario",id);
        List favs = querysql.getResultList();
        return favs;
    }
    @Override
    public Favorito cargarFavoritoUsuario(int idUsuario, int idElemento) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Favorito fav = null;
        Query querysql = manager.createQuery("SELECT f FROM Favorito f WHERE f.favoritoPK.idUsuario = :idUsuario and f.favoritoPK.idElemento = :idElemento");
        querysql.setParameter("idUsuario",idUsuario);
        querysql.setParameter("idElemento",idElemento);
        List resultado = querysql.getResultList();
        if(resultado.size() > 0){
            fav = (Favorito)resultado.get(0);
        }
        return fav;
    }

    @Override
    public boolean comentar(Favorito f) {
        boolean modificado = false;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad_persistencia");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query querysql = manager.createQuery("SELECT f FROM Favorito f WHERE f.favoritoPK.idUsuario = :idUsuario and f.favoritoPK.idElemento = :idElemento");
        querysql.setParameter("idUsuario",f.getFavoritoPK().getIdUsuario());
        querysql.setParameter("idElemento",f.getFavoritoPK().getIdElemento());
        List resultado = querysql.getResultList();
        if(resultado.size() > 0){
            try{
               manager.merge(f);
               modificado = true; 
               manager.getTransaction().commit();
            }catch(Exception ex){
                ex.getMessage();
            }finally{
               manager.close();
                factory.close(); 
            }
            
        }
        return modificado;
       
    }

    
}
