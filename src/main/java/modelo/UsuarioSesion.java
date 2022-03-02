/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Sandra
 */
public class UsuarioSesion {
    private int id;
    private String nombre;
    private String apellidos;
    private String rol;
    private String imagen;
    private String email;

    public UsuarioSesion(int id, String nombre, String apellidos, String rol, String imagen, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.imagen = imagen;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getRol() {
        return rol;
    }


    public String getImagen() {
        return imagen;
    }

    public String getEmail() {
        return email;
    }


}
