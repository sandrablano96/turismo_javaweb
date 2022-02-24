/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sandra
 */
@Entity
@Table(name = "elementos_patrimoniales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementoPatrimonial.findAll", query = "SELECT e FROM ElementoPatrimonial e"),
    @NamedQuery(name = "ElementoPatrimonial.findById", query = "SELECT e FROM ElementoPatrimonial e WHERE e.id = :id"),
    @NamedQuery(name = "ElementoPatrimonial.findByNombre", query = "SELECT e FROM ElementoPatrimonial e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "ElementoPatrimonial.findByDireccion", query = "SELECT e FROM ElementoPatrimonial e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "ElementoPatrimonial.findByHorario", query = "SELECT e FROM ElementoPatrimonial e WHERE e.horario = :horario"),
    @NamedQuery(name = "ElementoPatrimonial.findByLocalidad", query = "SELECT e FROM ElementoPatrimonial e WHERE e.localidad = :localidad")})
public class ElementoPatrimonial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "horario")
    private String horario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "localidad")
    private String localidad;
    @JoinColumn(name = "tipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipo tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElemento")
    private List<Detalle> detalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elementoPatrimonial")
    private List<Favorito> favoritoList;

    public ElementoPatrimonial() {
    }

    public ElementoPatrimonial(Integer id) {
        this.id = id;
    }

    public ElementoPatrimonial(Integer id, String nombre, String direccion, String horario, String localidad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horario = horario;
        this.localidad = localidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    @XmlTransient
    public List<Favorito> getFavoritoList() {
        return favoritoList;
    }

    public void setFavoritoList(List<Favorito> favoritoList) {
        this.favoritoList = favoritoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementoPatrimonial)) {
            return false;
        }
        ElementoPatrimonial other = (ElementoPatrimonial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ElementoPatrimonial[ id=" + id + " ]";
    }
    
}
