/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Pais;
import facade.PaisFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Aprendiz
 */
@Named(value = "paisControlador")
@SessionScoped
public class PaisControlador implements Serializable {

    /**
     * Creates a new instance of PaisControlador
     */
    public PaisControlador() {
    }
    
    @EJB
    PaisFacade paisFacade;
    Pais pais = new Pais();
    
    public List<Pais> listar() {
        return paisFacade.findAll();
    }
    
    
}
