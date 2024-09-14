/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import facade.RolFacade;
import entidades.Rol;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "rolControlador")
@SessionScoped
public class RolControlador implements Serializable {

    /**
     * Creates a new instance of RolControlador
     */
    @EJB
    RolFacade rolFacade;
    Rol rol = new Rol();
    public RolControlador() {
    }
    
    public List<Rol> listar() {
        return rolFacade.findAll();
    }
    
}
