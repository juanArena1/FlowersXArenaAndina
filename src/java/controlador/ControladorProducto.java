/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Producto;
import facade.ProductoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "controladorProducto")
@SessionScoped
public class ControladorProducto implements Serializable {

    /**
     * Creates a new instance of ControladorProducto
     */
    public ControladorProducto() {
    }
    
    @EJB
    ProductoFacade productoFacade;
    Producto producto = new Producto();
    
    public void prueba () {
        System.out.println("Prueba");
    }
    
}
