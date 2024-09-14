/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Pago;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class PagoFacade extends AbstractFacade<Pago> {

    @PersistenceContext(unitName = "FlowersXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }
    
    public void crearPago (Pago pago) {
        Query q = em.createNativeQuery("INSERT INTO pago (idPago, numero, codigoDeSeguridad, fechaDeVencimiento, nombre, direccion, Pedido) VALUES (NULL,?1,?2,?3,?4,?5,?6);");
        q.setParameter(1, pago.getNumero());
        q.setParameter(2, pago.getFechaDeVencimiento());
        q.setParameter(3, pago.getCodigoDeSeguridad());
        q.setParameter(4, pago.getNombre());
        q.setParameter(5, pago.getDireccion());
        q.setParameter(6, pago.getPedido().getIdPedido());
        q.executeUpdate();
    }
    
}
