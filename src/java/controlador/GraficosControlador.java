/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Producto;
import facade.ProductoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Aprendiz
 */
@Named(value = "graficosControlador")
@SessionScoped
public class GraficosControlador implements Serializable {

    /**
     * Creates a new instance of GraficosControlador
     */
    public GraficosControlador() {
    }

    @PostConstruct
    public void init() {
        graficoBarra = new BarChartModel();
        createBarModel();
    }

    BarChartModel graficoBarra;

    @EJB
    ProductoFacade productoFacade;
    Producto producto = new Producto();

    public BarChartModel getGraficoBarra() {
        return graficoBarra;
    }

    public void setGraficoBarra(BarChartModel graficoBarra) {
        this.graficoBarra = graficoBarra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BarChartModel initBarModel() {
        List<Producto> listaProductos = productoFacade.findAll();
        BarChartModel model = new BarChartModel();
        ChartSeries serieProducto = new ChartSeries();
        serieProducto.setLabel("Productos");
        for (Producto producto : listaProductos) {
            if (!(producto.getExistencias() == 0)) {
                serieProducto.set(producto.getNombreProducto(), producto.getExistencias());
            }
        }
        graficoBarra.addSeries(serieProducto);
        graficoBarra.setTitle("Existencias de productos");
        return model;
    }

    public BarChartModel createBarModel() {
        graficoBarra = initBarModel();
        graficoBarra.setAnimate(true);
        graficoBarra.setLegendPosition("ne");
        Axis ejeX = graficoBarra.getAxis(AxisType.X);
        ejeX.setLabel("Producto");
        Axis ejeY = graficoBarra.getAxis(AxisType.Y);
        ejeY.setLabel("Existencias");
        ejeY.setMin(0);
        ejeY.setMax(80000);
        return graficoBarra;
    }

}
