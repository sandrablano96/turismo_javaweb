/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sandra
 */
@Embeddable
public class FavoritoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_elemento")
    private int idElemento;

    public FavoritoPK() {
    }

    public FavoritoPK(int idUsuario, int idElemento) {
        this.idUsuario = idUsuario;
        this.idElemento = idElemento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idElemento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoritoPK)) {
            return false;
        }
        FavoritoPK other = (FavoritoPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idElemento != other.idElemento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FavoritoPK[ idUsuario=" + idUsuario + ", idElemento=" + idElemento + " ]";
    }
    
}
