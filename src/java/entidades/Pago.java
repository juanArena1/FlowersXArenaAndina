/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")
    , @NamedQuery(name = "Pago.findByIdPago", query = "SELECT p FROM Pago p WHERE p.idPago = :idPago")
    , @NamedQuery(name = "Pago.findByNumero", query = "SELECT p FROM Pago p WHERE p.numero = :numero")
    , @NamedQuery(name = "Pago.findByCodigoDeSeguridad", query = "SELECT p FROM Pago p WHERE p.codigoDeSeguridad = :codigoDeSeguridad")
    , @NamedQuery(name = "Pago.findByFechaDeVencimiento", query = "SELECT p FROM Pago p WHERE p.fechaDeVencimiento = :fechaDeVencimiento")
    , @NamedQuery(name = "Pago.findByNombre", query = "SELECT p FROM Pago p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pago.findByDireccion", query = "SELECT p FROM Pago p WHERE p.direccion = :direccion")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPago")
    private Integer idPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigoDeSeguridad")
    private String codigoDeSeguridad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "fechaDeVencimiento")
    private String fechaDeVencimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion")
    private String direccion;
    @JoinColumn(name = "Pedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private Pedido pedido;

    public Pago() {
    }

    public Pago(Integer idPago) {
        this.idPago = idPago;
    }

    public Pago(Integer idPago, String numero, String codigoDeSeguridad, String fechaDeVencimiento, String nombre, String direccion) {
        this.idPago = idPago;
        this.numero = numero;
        this.codigoDeSeguridad = codigoDeSeguridad;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoDeSeguridad() {
        return codigoDeSeguridad;
    }

    public void setCodigoDeSeguridad(String codigoDeSeguridad) {
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(String fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pago[ idPago=" + idPago + " ]";
    }
    
}
