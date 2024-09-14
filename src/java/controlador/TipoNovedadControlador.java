/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.TipoNovedad;
import facade.TipoNovedadFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author ACER
 */
@Named(value = "tipoNovedadControlador")
@SessionScoped
public class TipoNovedadControlador implements Serializable {

    /**
     * Creates a new instance of TipoNovedadControlador
     */
    public TipoNovedadControlador() {
    }
    
    @EJB
    TipoNovedadFacade tipoNovedadFacade;
    TipoNovedad tipoNovedad = new TipoNovedad(); 
    List<TipoNovedad> listaCiudades;
    

     public List<TipoNovedad> listar() {
        return tipoNovedadFacade.findAll();
    }
}
