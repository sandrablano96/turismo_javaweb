/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import DAO.CrudFavoritos;
import DAO.CrudUsuario;
import DAO.ICrudFavoritos;
import DAO.ICrudUsuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Usuario;

/**
 *
 * @author Sandra
 */
@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
@MultipartConfig(
        fileSizeThreshold = 1048576*4, // 4mb
        maxFileSize = 1048576 * 4, // 4mb
        maxRequestSize = 1048576*5) // 5mb
public class ControladorUsuarios extends HttpServlet {
    private ICrudUsuario usudao;
    private String msg;
    private RequestDispatcher rd;
    private HttpSession sesion;
    private ICrudFavoritos daofav;
    private static final String UPLOAD_DIR = "imagenes";
    
    @Override
    public void init() {
        usudao = new CrudUsuario();
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch(accion){
            case "registrar":
                registrar(request,response);
                break;
            case "login":
                login(request,response);
                break;
            case "logout":
                cerrarSesion(request, response);
                break;
            case "mi_perfil":
                Usuario u = (Usuario) sesion.getAttribute("usuario");
                daofav = new CrudFavoritos();
                ArrayList favoritos = (ArrayList) daofav.cargarFavoritosUsuario(u.getId());
                request.getRequestDispatcher("perfilUsuario").forward(request, response);
                
        }
        
    }
    public void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        Part archivo = request.getPart("imagen");   
        boolean insertado;

        try {
            Usuario u = new Usuario(nombre, apellidos, email,password, "usuario", archivo.getSubmittedFileName());
            subirFoto(request, archivo);
            insertado = usudao.insertar(u); 
            if (insertado) { 
                request.setAttribute("msg", "Insertado correctamente");
                rd = request.getRequestDispatcher("registro.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("msg", "Error al insertar");
                rd = request.getRequestDispatcher("registro.jsp");
                rd.forward(request, response);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usudao.consultar(email);
        RequestDispatcher rd;
            if (email.equals(usuario.getEmail()) && password.equals(usuario.getPassword())) {
                //iniciamos sesion
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);
                sesion.setMaxInactiveInterval(300);
                response.sendRedirect("ControladorElementosP");
            } else {
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }
    public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        sesion.invalidate();
        request.setAttribute("msg", "Sesión finalizada.");
        response.sendRedirect("index.html");
    }
    
    public void subirFoto(HttpServletRequest request, Part archivo){
        
        //Obtiene la ruta absoluta de la aplicacion web (getServletContext)
        String applicationPath = request.getServletContext().getRealPath("");
        //Construye la ruta a donde se subirán los archivos.
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
           // Crea la carpeta si no existe
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        // Se monta el archivo a subir
        String fileName = archivo.getSubmittedFileName();
            String archivoSubir = uploadFilePath + fileName;
        try {
            //Se escribe el archivo en la ruta obtenidoa
            archivo.write(uploadFilePath);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
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
