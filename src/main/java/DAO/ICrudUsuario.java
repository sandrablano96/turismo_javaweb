/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author Sandra
 */
public interface ICrudUsuario {
    public boolean insertar(Usuario u);
    public Usuario consultar(String email);


    
}
