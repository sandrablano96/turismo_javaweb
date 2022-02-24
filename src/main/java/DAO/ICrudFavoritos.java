/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.ElementoPatrimonial;
import modelo.Favorito;

/**
 *
 * @author Sandra
 */
public interface ICrudFavoritos {
    public List<ElementoPatrimonial> cargarFavoritosUsuario(int id);
    public boolean insertar(Favorito f);
    public boolean eliminar(Favorito f);
}
