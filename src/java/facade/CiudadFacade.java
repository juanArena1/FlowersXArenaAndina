/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Ciudad;
import entidades.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceContext(unitName = "FlowersXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }

    public List<Ciudad> consultarCiudades(Pais pais) {
        List<Ciudad> list = new ArrayList();
        try {
            Query query = em.createQuery("SELECT c FROM Ciudad c WHERE c.idPais=:idPais");
            query.setParameter("idPais", pais.getIdpais());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

}
