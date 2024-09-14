/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
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
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "FlowersXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    public void agregarProductosAlPedido (List<Producto> listaProductos, Pedido pedido) {
        for (Producto producto : listaProductos) {
            Query q = em.createNativeQuery("INSERT INTO producto_has_pedido (producto_idProducto, pedido_idPedido) VALUES (?1, ?2)");
            q.setParameter(1, producto.getIdProducto());
            q.setParameter(2, pedido.getIdPedido());
            q.executeUpdate();
        }
    }
    
    public List<Pedido> listarPedidosCliente (Usuario usuario) {
        Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.usuarioid = ?1");
        q.setParameter(1, usuario);
        List<Pedido> listaPedidos = q.getResultList();
        return listaPedidos;
    }
    
}
