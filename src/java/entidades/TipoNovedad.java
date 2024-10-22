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
@Table(name = "tipo_novedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNovedad.findAll", query = "SELECT t FROM TipoNovedad t")
    , @NamedQuery(name = "TipoNovedad.findByIdtiponovedad", query = "SELECT t FROM TipoNovedad t WHERE t.idtiponovedad = :idtiponovedad")
    , @NamedQuery(name = "TipoNovedad.findByNombre", query = "SELECT t FROM TipoNovedad t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoNovedad.findByNombreEn", query = "SELECT t FROM TipoNovedad t WHERE t.nombreEn = :nombreEn")})
public class TipoNovedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtiponovedad")
    private Integer idtiponovedad;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoNovedad")
    private List<Novedad> novedadList;

    public TipoNovedad() {
    }

    public TipoNovedad(Integer idtiponovedad) {
        this.idtiponovedad = idtiponovedad;
    }

    public TipoNovedad(Integer idtiponovedad, String nombre, String nombreEn) {
        this.idtiponovedad = idtiponovedad;
        this.nombre = nombre;
        this.nombreEn = nombreEn;
    }

    public Integer getIdtiponovedad() {
        return idtiponovedad;
    }

    public void setIdtiponovedad(Integer idtiponovedad) {
        this.idtiponovedad = idtiponovedad;
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
    public List<Novedad> getNovedadList() {
        return novedadList;
    }

    public void setNovedadList(List<Novedad> novedadList) {
        this.novedadList = novedadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiponovedad != null ? idtiponovedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNovedad)) {
            return false;
        }
        TipoNovedad other = (TipoNovedad) object;
        if ((this.idtiponovedad == null && other.idtiponovedad != null) || (this.idtiponovedad != null && !this.idtiponovedad.equals(other.idtiponovedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoNovedad[ idtiponovedad=" + idtiponovedad + " ]";
    }
    
}
