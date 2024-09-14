/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.ReporteFecha;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author ACER
 */
@Named(value = "controladorFechas")
@SessionScoped
public class ControladorFechas implements Serializable {

    /**
     * Creates a new instance of ControladorFechas
     */
    public ControladorFechas() {
    }
    
    
    private Date fechaI;
    private Date fechaF;
    private String  RolC;

    public Date getFechaI() {
        return fechaI;
    }

    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }
    
    public String getRolC() {
        return RolC;
    }

    public void setRolC(String RolC) {
        this.RolC = RolC;
    }
    
    

    public void descargarPedidos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        ReporteFecha reporteR = new ReporteFecha();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("//Admin//reportes//ReportePedido.jasper");
        reporteR.DescargarPedido(ruta,this.RolC);
        FacesContext.getCurrentInstance().getResponseComplete();
    }

    public void descargarPedidosFechas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        ReporteFecha reporteR = new ReporteFecha();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("//Admin//reportes//Reporte-PedidoFecha.jasper");
        reporteR.DescargarPedidoFecha(ruta,this.fechaI,this.fechaF);
        FacesContext.getCurrentInstance().getResponseComplete();
    }
    public String reporteProductos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        ReporteFecha reporteR = new ReporteFecha();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("//Admin//reportes//Reporte-Producto.jasper");
        reporteR.DescargarPedido(ruta,this.getRolC());
        FacesContext.getCurrentInstance().getResponseComplete();
        return "";
    }
    
  public void descargarNovedad() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        ReporteFecha reporteR = new ReporteFecha();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("//Admin//reportes//Reporte-Novedad.jasper");
        reporteR.DescargarPedidoFecha(ruta,this.fechaI,this.fechaF);
        FacesContext.getCurrentInstance().getResponseComplete();
    }

    
}
