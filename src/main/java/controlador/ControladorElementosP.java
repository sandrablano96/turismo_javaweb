/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import DAO.CrudElementoPatrimonial;
import DAO.CrudFavoritos;
import DAO.CrudTipos;
import DAO.ICrudElementoPatrimonial;
import DAO.ICrudFavoritos;
import DAO.ICrudTipos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ElementoPatrimonial;
import modelo.Favorito;
import modelo.UsuarioSesion;

/**
 *
 * @author Sandra
 */
@WebServlet(name = "ControladorElementosP", urlPatterns = {"/ControladorElementosP"})
public class ControladorElementosP extends HttpServlet {
    ICrudElementoPatrimonial daoele;
    ICrudTipos daotipos;
    ICrudFavoritos daofav;
    private RequestDispatcher rd;
    ArrayList listadoElementos;
     private HttpSession sesion;
     
    
    
    @Override
    public void init() {
        daoele = new CrudElementoPatrimonial();
        daotipos = new CrudTipos();
        daofav = new CrudFavoritos();
        
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        if(accion == null){
            mostrarTodos(request,response);
        }
        String msg = "";
        List tipos;
        switch(accion){
            case "mostrarOrdenados":
                mostrarTodosOrdenados(request, response);
                break;
            case "buscarFiltros":
                String tipo = request.getParameter("tipo");
                String nombre = request.getParameter("nombre");
                List listadoElementos = daoele.consultarElementosPatrimonialesFiltros(tipo, nombre);
                if(listadoElementos.size() > 0){
                    request.setAttribute("listadoElementos", listadoElementos);
                } else{
                    msg = "No ha habido resultados que coincidan con su búsqueda";
                    request.setAttribute("msg", msg);
                }
                
                request.getRequestDispatcher("busquedaFiltros.jsp").forward(request, response);
                break;
            case "cargarElemento":
                int idSeleccionado = Integer.parseInt(request.getParameter("id"));              
                ElementoPatrimonial el = daoele.consultarElementoPatrimonial(idSeleccionado);
                sesion = request.getSession();
                UsuarioSesion us = (UsuarioSesion) sesion.getAttribute("usuario");
                if(us != null){
                    Favorito fav = (Favorito) daofav.cargarFavoritoUsuario(us.getId(), idSeleccionado);
                    if(fav != null){
                        request.setAttribute("favorito", fav.getFavoritoPK().getIdElemento());
                    }
                }
                request.setAttribute("elemento", el);
                request.getRequestDispatcher("elemento.jsp").forward(request, response);
                break;
            
                    
        }
    }
    
    public void mostrarTodos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            List listadoElementos = daoele.consultarElementosPatrimoniales();
            sesion = request.getSession();
            UsuarioSesion us = (UsuarioSesion) sesion.getAttribute("usuario");
            if(us != null){
                List listadoFavoritos = daofav.cargarFavoritosUsuario(us.getId());
                request.setAttribute("favoritos", listadoFavoritos);
            }
            
            request.setAttribute("listadoElementos", listadoElementos);
            request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    public void mostrarTodosOrdenados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            String orden = request.getParameter("ordenar");
            List listadoElementos = daoele.consultarElementosPatrimonialesOrdenados(orden);
            sesion = request.getSession();
            UsuarioSesion us = (UsuarioSesion) sesion.getAttribute("usuario");
            if(us != null){
                List listadoFavoritos = daofav.cargarFavoritosUsuario(us.getId());
                request.setAttribute("favoritos", listadoFavoritos);
            }
            request.setAttribute("select", orden);
            request.setAttribute("listadoElementos", listadoElementos);
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
