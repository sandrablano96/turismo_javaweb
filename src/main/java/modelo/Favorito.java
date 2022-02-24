/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sandra
 */
@Entity
@Table(name = "favoritos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favorito.findAll", query = "SELECT f FROM Favorito f"),
    @NamedQuery(name = "Favorito.findByIdUsuario", query = "SELECT f FROM Favorito f WHERE f.favoritoPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Favorito.findByIdElemento", query = "SELECT f FROM Favorito f WHERE f.favoritoPK.idElemento = :idElemento"),
    @NamedQuery(name = "Favorito.findByComentarios", query = "SELECT f FROM Favorito f WHERE f.comentarios = :comentarios")})
public class Favorito implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FavoritoPK favoritoPK;
    @Size(max = 255)
    @Column(name = "comentarios")
    private String comentarios;
    @JoinColumn(name = "id_elemento", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ElementoPatrimonial elementoPatrimonial;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Favorito() {
    }

    public Favorito(FavoritoPK favoritoPK) {
        this.favoritoPK = favoritoPK;
    }

    public Favorito(int idUsuario, int idElemento) {
        this.favoritoPK = new FavoritoPK(idUsuario, idElemento);
    }

    public FavoritoPK getFavoritoPK() {
        return favoritoPK;
    }

    public void setFavoritoPK(FavoritoPK favoritoPK) {
        this.favoritoPK = favoritoPK;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public ElementoPatrimonial getElementoPatrimonial() {
        return elementoPatrimonial;
    }

    public void setElementoPatrimonial(ElementoPatrimonial elementoPatrimonial) {
        this.elementoPatrimonial = elementoPatrimonial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoritoPK != null ? favoritoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorito)) {
            return false;
        }
        Favorito other = (Favorito) object;
        if ((this.favoritoPK == null && other.favoritoPK != null) || (this.favoritoPK != null && !this.favoritoPK.equals(other.favoritoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Favorito[ favoritoPK=" + favoritoPK + " ]";
    }
    
}
