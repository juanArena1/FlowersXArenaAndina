/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "estado_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoUsuario.findAll", query = "SELECT e FROM EstadoUsuario e")
    , @NamedQuery(name = "EstadoUsuario.findByIdestadoUsuario", query = "SELECT e FROM EstadoUsuario e WHERE e.idestadoUsuario = :idestadoUsuario")
    , @NamedQuery(name = "EstadoUsuario.findByNombre", query = "SELECT e FROM EstadoUsuario e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EstadoUsuario.findByNombreEn", query = "SELECT e FROM EstadoUsuario e WHERE e.nombreEn = :nombreEn")})
public class EstadoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_usuario")
    private Integer idestadoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_en")
    private String nombreEn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoUsuario")
    private List<Usuario> usuarioList;

    public EstadoUsuario() {
    }

    public EstadoUsuario(Integer idestadoUsuario) {
        this.idestadoUsuario = idestadoUsuario;
    }

    public EstadoUsuario(Integer idestadoUsuario, String nombre, String nombreEn) {
        this.idestadoUsuario = idestadoUsuario;
        this.nombre = nombre;
        this.nombreEn = nombreEn;
    }

    public Integer getIdestadoUsuario() {
        return idestadoUsuario;
    }

    public void setIdestadoUsuario(Integer idestadoUsuario) {
        this.idestadoUsuario = idestadoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEn() {
        return nombreEn;
    }

    public void setNombreEn(String nombreEn) {
        this.nombreEn = nombreEn;
    }

    @XmlTransient
    @JsonIgnore
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoUsuario != null ? idestadoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoUsuario)) {
            return false;
        }
        EstadoUsuario other = (EstadoUsuario) object;
        if ((this.idestadoUsuario == null && other.idestadoUsuario != null) || (this.idestadoUsuario != null && !this.idestadoUsuario.equals(other.idestadoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoUsuario[ idestadoUsuario=" + idestadoUsuario + " ]";
    }
    
}
