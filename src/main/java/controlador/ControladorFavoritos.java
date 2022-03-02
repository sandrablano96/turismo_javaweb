/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import DAO.CrudFavoritos;
import DAO.ICrudFavoritos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Favorito;
import DAO.ICrudUsuario;
import DAO.ICrudElementoPatrimonial;
import DAO.CrudUsuario;
import DAO.CrudElementoPatrimonial;

/**
 *
 * @author DAW-A
 */
@WebServlet(name = "ControladorFavoritos", urlPatterns = {"/ControladorFavoritos"})
public class ControladorFavoritos extends HttpServlet {
    ICrudFavoritos daofav;
    ICrudUsuario daousu;
    ICrudElementoPatrimonial daoele;
    @Override
    public void init() {
        daofav = new CrudFavoritos();
        daousu = new CrudUsuario();
        daoele = new CrudElementoPatrimonial();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        int id_usuario;
        int id_elemento;
        Favorito fav;
        switch(accion){
            //ajax?
            case "like":
                id_usuario = Integer.parseInt(request.getParameter("usuario"));
                id_elemento = Integer.parseInt(request.getParameter("elemento"));
                fav = new Favorito(id_usuario,id_elemento);
                fav.setUsuario(daousu.consultarPorId(id_usuario));
                fav.setElementoPatrimonial(daoele.consultarElementoPatrimonial(id_elemento));
                if( daofav.insertar(fav)){
                    response.getWriter().write("correcto");
                }else{
                    response.getWriter().write("error");
                }
               
                break;

            case "dislike":
                id_usuario = Integer.parseInt(request.getParameter("usuario"));
                id_elemento = Integer.parseInt(request.getParameter("elemento"));
                fav = new Favorito(id_usuario,id_elemento);
                if(daofav.eliminar(fav)){
                    response.getWriter().write("correcto");
                }else{
                     response.getWriter().write("error");
                }
                break;
            case "comentar":
            case "borrarComentario":
                id_usuario = Integer.parseInt(request.getParameter("usuario"));
                id_elemento = Integer.parseInt(request.getParameter("elemento"));
                Favorito favorito = daofav.cargarFavoritoUsuario(id_usuario, id_elemento);
                String comentario = request.getParameter("comentario");
                favorito.setComentarios(comentario);
                if(daofav.comentar(favorito)){
                    response.getWriter().write("correcto");
                }else{
                    response.getWriter().write("error");
                }
                break;
        }
        
    }


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
