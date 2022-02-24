/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;
import modelo.Tipo;

/**
 *
 * @author DAW-A
 */
public interface ICrudTipos {
    public Tipo consultar(int id);
    public List<Tipo> consultarTodos();
}
