package controlador;

import entidades.Producto;
import facade.ProductoFacade;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ACER
 */
@Named(value = "chartBean")
@ViewScoped
public class ChartBean implements Serializable {

    @EJB
    private ProductoFacade productoFacade;
    private List<Producto> listaProducto;
    private List<Producto> listaProductos;
    private BarChartModel barra;
    private PieChartModel pastel;

    public ChartBean() {
    }

    public void listar() {
        listaProducto = productoFacade.findAll();
        /*Poder hacer el grafico*/
        graficar();
    }

    public void graficar() {

        barra = new BarChartModel();
        List<Producto> listaMasvendidos = productoFacade.listar();
        ChartSeries serie = new BarChartSeries();
        for (int i = 0; i < listaMasvendidos.size(); i++) {

            if (listaMasvendidos.get(i).getExistencias() != 0) {
                //serie.setLabel(listaMasvendidos.get(i).getNombreProducto());
                serie.set(listaMasvendidos.get(i).getNombreProducto(), listaMasvendidos.get(i).getExistencias());

            }
        }
        barra.addSeries(serie);

        /*
        for (Producto producto : listaProducto) {
            ChartSeries serie = new ChartSeries();
            if (producto.getExistencias() > 0) {
                serie.setLabel(producto.getNombreProducto());
                serie.set(producto.getNombreProducto(), producto.getExistencias());
                barra.addSeries(serie);
            }
        }
         */
        barra.setTitle("Existencias de producto");
        //barra.setLegendPosition("ne");
        barra.setShowPointLabels(false);
        barra.setAnimate(true);


        /*Eje x se nombres de los ejes*/
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Productos");

        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad de existencias");
        yAxis.setMin(0);
        yAxis.setMax(100000);

    }

   

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public PieChartModel getPastel() {
        return pastel;
    }

    public void setPastel(PieChartModel pastel) {
        this.pastel = pastel;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public BarChartModel getBarra() {
        return barra;
    }

    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }

}
