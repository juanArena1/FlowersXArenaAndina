/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Pago;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import facade.EstadoPedidoFacade;
import facade.PedidoFacade;
import facade.ProductoFacade;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.persistence.EntityManager;

/**
 *
 * @author Aprendiz
 */
@Named(value = "pedidoControlador")
@SessionScoped
public class PedidoControlador implements Serializable {

    /**
     * Creates a new instance of PedidoControlador
     */
    public PedidoControlador() {
    }
    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    List<Pedido> listaPedidos;
    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();
    List<Usuario> listaUsuarios;
    @EJB
    ProductoFacade productoFacade;
    Producto producto = new Producto();
    List<Producto> listaProductos;
    @EJB
    EstadoPedidoFacade estadoPedidoFacade;
    private double impuestos = 0;
    PagoControlador pago = new PagoControlador();

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public PedidoFacade getPedidoFacade() {
        return pedidoFacade;
    }

    public void setPedidoFacade(PedidoFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public PagoControlador getPago() {
        return pago;
    }

    public void setPago(PagoControlador pago) {
        this.pago = pago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

    public void setProductoFacade(ProductoFacade productoFacade) {
        this.productoFacade = productoFacade;
    }

    public EstadoPedidoFacade getEstadoPedidoFacade() {
        return estadoPedidoFacade;
    }

    public void setEstadoPedidoFacade(EstadoPedidoFacade estadoPedidoFacade) {
        this.estadoPedidoFacade = estadoPedidoFacade;
    }
    
    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Pedido> listar() {
        return pedidoFacade.findAll();
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public String crearPedido(List<Producto> carrito) {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        pedido.setUsuarioid(usuarioFacade.find(usuario.getId()));
        pedido.setIdPedido(1);
        double subTotal = 0;
        double[] tiempos = new double[carrito.size()];
        for (int i = 0; i < tiempos.length; i++) {
            for (Producto producto1 : carrito) {
                tiempos[i] = producto1.getTiempoDeCultivo();
            }
        }
        int max = 0;
        for (double tiempo : tiempos) {
            if (max < tiempo) {
                max = (int) tiempo;
            }
        }
        for (Producto producto3 : carrito) {
            producto.setExistencias(producto.getExistencias() - 100);
        }
        Date fechaEnvio = pedido.getFechaDeCreacion();
        pedido.setFechaDeCreacion(fechaEnvio);
        fechaEnvio.setMonth((fechaEnvio.getMonth() - 1 + max) % 12 + 1);
        pedido.setFechaDeEntrega(fechaEnvio);
        pedido.setEstadoPedido(estadoPedidoFacade.find(1));
        for (Producto producto2 : carrito) {
            subTotal = subTotal + producto2.getPrecio();
        }
        double total = subTotal + (subTotal * 0.18);
        pedido.setSubTotal(subTotal);
        pedido.setTotal(total);
        pedidoFacade.create(pedido);
        pedidoFacade.agregarProductosAlPedido(carrito, pedido);
        pago.setPedido(pedido);
        pedido = new Pedido();
        return "crear-pago";
    }

    public String preEditarPedido(Pedido pedido) {
        this.pedido = pedido;
        return "editar-pedido";
    }

    public String editarPedido() {
        pedido.setUsuarioid(usuarioFacade.find(usuario.getId()));
        pedidoFacade.edit(pedido);
        pedido = new Pedido();
        return "gestionar-pedido";
    }

    public void eliminarPedido(Pedido pedido) {
        pedidoFacade.remove(pedido);
        //return "Lista";
    }
    
    public List<Pedido> listarPedidoCliente(Usuario usuario) {
        return pedidoFacade.listarPedidosCliente(usuario);
    }

} 
