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
@Table(name = "estado_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPedido.findAll", query = "SELECT e FROM EstadoPedido e")
    , @NamedQuery(name = "EstadoPedido.findByIdestadoPedido", query = "SELECT e FROM EstadoPedido e WHERE e.idestadoPedido = :idestadoPedido")
    , @NamedQuery(name = "EstadoPedido.findByNombre", query = "SELECT e FROM EstadoPedido e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EstadoPedido.findByNombreEn", query = "SELECT e FROM EstadoPedido e WHERE e.nombreEn = :nombreEn")})
public class EstadoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idestado_pedido")
    private Integer idestadoPedido;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoPedido")
    private List<Pedido> pedidoList;

    public EstadoPedido() {
    }

    public EstadoPedido(Integer idestadoPedido) {
        this.idestadoPedido = idestadoPedido;
    }

    public EstadoPedido(Integer idestadoPedido, String nombre, String nombreEn) {
        this.idestadoPedido = idestadoPedido;
        this.nombre = nombre;
        this.nombreEn = nombreEn;
    }

    public Integer getIdestadoPedido() {
        return idestadoPedido;
    }

    public void setIdestadoPedido(Integer idestadoPedido) {
        this.idestadoPedido = idestadoPedido;
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
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoPedido != null ? idestadoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPedido)) {
            return false;
        }
        EstadoPedido other = (EstadoPedido) object;
        if ((this.idestadoPedido == null && other.idestadoPedido != null) || (this.idestadoPedido != null && !this.idestadoPedido.equals(other.idestadoPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoPedido[ idestadoPedido=" + idestadoPedido + " ]";
    }
    
}
