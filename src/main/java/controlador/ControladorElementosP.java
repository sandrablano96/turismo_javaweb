/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import DAO.CrudDetalles;
import DAO.CrudElementoPatrimonial;
import DAO.CrudTipos;
import DAO.CrudUsuario;
import DAO.ICrudDetalles;
import DAO.ICrudElementoPatrimonial;
import DAO.ICrudTipos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Detalle;
import modelo.ElementoPatrimonial;
import modelo.Tipo;

/**
 *
 * @author Sandra
 */
@WebServlet(name = "ControladorElementosP", urlPatterns = {"/ControladorElementosP"})
public class ControladorElementosP extends HttpServlet {
    ICrudElementoPatrimonial daoele;
    ICrudDetalles daodet;
    ICrudTipos daotipos;
    ICrudDetalles daodetalle;
    private RequestDispatcher rd;
    ArrayList listadoElementos;
    
    
    @Override
    public void init() {
        daoele = new CrudElementoPatrimonial();
        daodet = new CrudDetalles();
        daotipos = new CrudTipos();
        daodetalle = new CrudDetalles();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion == null){
            mostrarTodos(request,response);
        }
        String msg = "";
        switch(accion){
            case "insertar":
                msg = insertar(request,response);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("insertar-modificar.jsp").forward(request, response);
                break;
            case "modificar":
                msg = modificar(request,response);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("insertar-modificar.jsp").forward(request, response);
                break;
            case "eliminar": 
                int id = Integer.parseInt(request.getParameter("id"));
                if(daoele.borrar(id)){
                    msg = "Borrado correctamente";
                } else{
                    msg = "Fallo al borrar";
                }
                break;
            case "buscarFiltros":
                String tipo = request.getParameter("tipo");
                String nombre = request.getParameter("nombre");
                List listadoElementos = daoele.consultarElementosPatrimonialesFiltros(tipo, nombre);
                request.setAttribute("listadoElementos", listadoElementos);
                request.getRequestDispatcher("busquedaFiltros.jsp").forward(request, response);
                break;
            case "cargarElemento":
                int idSeleccionado = Integer.parseInt(request.getParameter("id"));
                ElementoPatrimonial el = daoele.consultarElementoPatrimonial(idSeleccionado);
                request.setAttribute("elemento", el);
                request.getRequestDispatcher("elemento.jsp").forward(request, response);
                break;
            
                    
        }
    }
    
    public void mostrarTodos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            List listadoElementos = daoele.consultarElementosPatrimoniales();
            request.setAttribute("listadoElementos", listadoElementos);
            request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    
    public String insertar(HttpServletRequest request, HttpServletResponse response){
        String msg = "Fallo al insertar";
        //creamos el elemento
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String horario = request.getParameter("horario");
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        Tipo tipoSelec = daotipos.consultar(tipo);
        ElementoPatrimonial elpat = new ElementoPatrimonial();
        elpat.setNombre(nombre);
        elpat.setDireccion(direccion);
        elpat.setHorario(horario);
        elpat.setTipo(tipoSelec);
        
        //creamos el detalle del elemento
        Detalle det = new Detalle();
        String desc = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");
        det.setDescripcion(desc);
        det.setImagen(imagen);
        det.setIdElemento(elpat);
        //si se inserta el elemento, insertamos su detalle
        if(daoele.insertar(elpat)){
            ICrudDetalles daodetalle = new CrudDetalles();
            if(daodetalle.insertar(det)){
                msg = "Insertado correctamente";
            }
        }
        return msg;
    }
    
    public String modificar(HttpServletRequest request, HttpServletResponse response){
        String msg = "Fallo al modificar";
        //creamos el elemento
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String horario = request.getParameter("horario");
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        Tipo tipoSelec = daotipos.consultar(tipo);
        ElementoPatrimonial elpat = new ElementoPatrimonial();
        elpat.setNombre(nombre);
        elpat.setDireccion(direccion);
        elpat.setHorario(horario);
        elpat.setTipo(tipoSelec);
        
        //creamos el detalle del elemento
        Detalle det = new Detalle();
        String desc = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");
        det.setDescripcion(desc);
        det.setImagen(imagen);
        det.setIdElemento(elpat);
        boolean insertadoEle = false;
        //si se inserta el elemento, insertamos su detalle
        if(daoele.modificar(elpat)){
            if(daodetalle.modificar(det)){
                msg = "Modificado correctamente";
            }
        }
        return msg;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    

    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
