/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.CrudElementoPatrimonial;
import DAO.CrudTipos;
import DAO.ICrudElementoPatrimonial;
import DAO.ICrudTipos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Detalle;
import modelo.ElementoPatrimonial;
import modelo.Tipo;

/**
 *
 * @author Sandra
 */
@WebServlet(name = "ControladorAdmin", urlPatterns = {"/ControladorAdmin"})
@MultipartConfig(
        fileSizeThreshold = 1048576*4, // 4mb
        maxFileSize = 1048576 * 4, // 4mb
        maxRequestSize = 1048576*5) // 5mb
public class ControladorAdmin extends HttpServlet {
    ICrudElementoPatrimonial daoele;
    ICrudTipos daotipos;
    private static final String UPLOAD_DIR = "imagenes";

    @Override
    public void init(){
        daoele = new CrudElementoPatrimonial();
        daotipos = new CrudTipos();
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
        String msg = "";
        List tipos = new ArrayList();
        List listadoElementos = new ArrayList();
        String accion = request.getParameter("accion");
        switch(accion){
            case "insertar":
                tipos = daotipos.consultarTodos();
                request.setAttribute("tipos", tipos);
                request.setAttribute("accion", "insertarDatos");
                request.getRequestDispatcher("insertar-modificar.jsp").forward(request, response);
                break;
            case "gestion":
                listadoElementos = daoele.consultarElementosPatrimoniales();
                request.setAttribute("listadoElementos", listadoElementos);
                request.setAttribute("accion", "modificarDatos");
                request.getRequestDispatcher("perfilAdmin.jsp").forward(request, response);
                break;
            case "elementoModificar":
                int elemento = Integer.parseInt(request.getParameter("id"));
                ElementoPatrimonial el = daoele.consultarElementoPatrimonial(elemento);
                request.setAttribute("elementoP", el);
                tipos = daotipos.consultarTodos();
                request.setAttribute("tipos", tipos);
                request.setAttribute("accion", "modificarDatos");
                request.getRequestDispatcher("insertar-modificar.jsp?elemento="+el.getId()).forward(request, response);
                break;
        case "insertarDatos":
                msg = insertar(request,response);
                request.setAttribute("msg", msg);
                request.setAttribute("accion", "insertarDatos");
                request.getRequestDispatcher("insertar-modificar.jsp").forward(request, response);
                break;
            case "modificarDatos":
                el = modificar(request,response);
                if(el != null){
                    msg = "Modificado correctamente";
                } else{
                    msg="Fallo al actualizar";
                }
                request.setAttribute("msg", msg);
                request.setAttribute("elementoP", el);
                tipos = daotipos.consultarTodos();
                request.setAttribute("tipos", tipos);
                request.setAttribute("accion", "modificarDatos");
                request.getRequestDispatcher("ControladorAdmin?accion=gestion").forward(request, response);
                break;
            case "borrar": 
                int id = Integer.parseInt(request.getParameter("id"));
                if(daoele.borrar(id)){
                    msg = "Borrado correctamente";
                } else{
                    msg = "Fallo al borrar";
                }
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("ControladorElementosP").forward(request, response);
                break;
        }
    }
    
    
    public String insertar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String msg = "Fallo al insertar";
        //creamos el elemento
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String horario = request.getParameter("horario");
        String localidad = request.getParameter("localidad");
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        Tipo tipoSelec = daotipos.consultar(tipo);
        ElementoPatrimonial elpat = new ElementoPatrimonial();
        elpat.setNombre(nombre);
        elpat.setDireccion(direccion);
        elpat.setHorario(horario);
        elpat.setTipo(tipoSelec);
        elpat.setLocalidad(localidad);
        
        //creamos el detalle del elemento
        Detalle det = new Detalle();
        String desc = request.getParameter("descripcion");
        Part imagen = request.getPart("imagen");  
        Part imagen2 = request.getPart("imagen2");  
        det.setDescripcion(desc);
        det.setImagen(imagen.getSubmittedFileName());
        det.setImagen2(imagen2.getSubmittedFileName());
        det.setIdElemento(elpat);
        elpat.setDetalle(det);
        //si se inserta el elemento, insertamos su detalle
        try{
            subirFoto(request, imagen);
            subirFoto(request, imagen2);
           
        } catch(Exception ex){
            ex.getMessage();
        } 
        if(daoele.insertar(elpat)){
                 msg = "Insertado correctamente";
            }
         
        return msg;
    }
    
    public ElementoPatrimonial modificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        ElementoPatrimonial ep = null;
        //creamos el elemento
        int id = Integer.parseInt(request.getParameter("elemento"));
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String horario = request.getParameter("horario");
        String localidad = request.getParameter("localidad");
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        Tipo tipoSelec = daotipos.consultar(tipo);
        ElementoPatrimonial elpat = daoele.consultarElementoPatrimonial(id);
        elpat.setNombre(nombre);
        elpat.setDireccion(direccion);
        elpat.setHorario(horario);
        elpat.setTipo(tipoSelec);
        elpat.setLocalidad(localidad);
        
        //creamos el detalle del elemento
        Detalle det = elpat.getDetalle();
        String desc = request.getParameter("descripcion");
        Part imagen = request.getPart("imagen");
        Part imagen2 = request.getPart("imagen2");
        det.setDescripcion(desc);
        det.setImagen(imagen.getSubmittedFileName());
        det.setImagen2(imagen2.getSubmittedFileName());
        det.setIdElemento(elpat);
        elpat.setDetalle(det);
        //si se inserta el elemento, insertamos su detalle
        try{
            subirFoto(request, imagen);
            subirFoto(request, imagen2);
           
        }catch(Exception e){
            e.getMessage();
        }
         ep = daoele.modificar(elpat);
           

        return ep;
    }
    
        
    
    public void subirFoto(HttpServletRequest request, Part archivo){
        
        //Obtiene la ruta absoluta de la aplicacion web (getServletContext)
        String applicationPath = request.getServletContext().getRealPath("");
        //Construye la ruta a donde se subirán los archivos.
        String uploadFilePath = applicationPath + UPLOAD_DIR; 
           // Crea la carpeta si no existe
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        // Se monta el archivo a subir
        String fileName = archivo.getSubmittedFileName();
        String archivoSubir = uploadFilePath + File.separator + fileName;
        try {
            //Se escribe el archivo en la ruta obtenidoa
             archivo.write(archivoSubir);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
