/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import modelo.Detalle;

/**
 *
 * @author DAW-A
 */
public interface ICrudDetalles {
    public boolean insertar(Detalle d);
    public boolean modificar(Detalle d);
    public boolean borrar(int id);
    public boolean consultar(int id);
}
