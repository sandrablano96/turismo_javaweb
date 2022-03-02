/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.ElementoPatrimonial;
import modelo.Favorito;
import modelo.Usuario;
/**
 *
 * @author Sandra
 */
public interface ICrudElementoPatrimonial {
    public boolean insertar(ElementoPatrimonial ep);
    public ElementoPatrimonial modificar(ElementoPatrimonial ep);
    public boolean borrar(int id);
    public ElementoPatrimonial consultarElementoPatrimonial(int id);
    public List<ElementoPatrimonial> consultarElementosPatrimoniales();
    public List consultarElementosPatrimonialesOrdenados(String orden);
    public List<ElementoPatrimonial> consultarElementosPatrimonialesFiltros(String tipo, String nombre);
    
    
}
