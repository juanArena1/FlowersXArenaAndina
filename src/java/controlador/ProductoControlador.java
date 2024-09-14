/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import facade.PedidoFacade;
import facade.ProductoFacade;
import facade.UsuarioFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Aprendiz
 */
@Named(value = "productoControlador")
@SessionScoped
public class ProductoControlador implements Serializable {

    /**
     * Creates a new instance of ProductoControlador
     */
    public ProductoControlador() {
    }

    @EJB
    ProductoFacade productoFacade;
    Producto producto = new Producto();
    List<Producto> listaProductos;
    List<Producto> carrito = new ArrayList();

    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    List<Pedido> listaPedidos;

    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();
    List<Usuario> listaUsuarios;

    Mailer mailer = new Mailer();
    private Part file;
    private String nombre;
    private String pathReal;

    public Mailer getMailer() {
        return mailer;
    }

    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }

    public List<Producto> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Producto> carrito) {
        this.carrito = carrito;
    }
    
    

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

    public void setProductoFacade(ProductoFacade productoFacade) {
        this.productoFacade = productoFacade;
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

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
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
    
    public void agregarAlCarrito (Producto p) {
        carrito.add(p);
        System.out.println(carrito);
    }
    
    public void removerDelCarrito (Producto p) {
        carrito.remove(p);
        System.out.println(carrito);
    }
    
    public void prueba() {
        System.out.println("Hola");
    }

    public String crearProducto() throws UnsupportedEncodingException {
        producto.setIdProducto(1);
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/FlowersXProyecto/Archivos/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();
            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
            path.replace("\\", "\\\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
        producto.setFoto(pathReal);
        productoFacade.create(producto);
        String mensaje = "Hemos añadido " + producto.getNombreProducto() + " a nuestro catálogo.\n"
                + "Para conocer más, visita nuestro sitio web.";
        mailer.configurar();
        listaUsuarios = usuarioFacade.findAll();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getRol().getIdRol() == 4) {
                mailer.enviarMensajeConUnAdjunto(usuario.getEmail(), "¡Hemos añadido nuevos productos a nuestro catálogo!", mensaje, path, nombre);
            }
        }
        producto = new Producto();
        return "gestionar-catalogo";
    }

    public String editarProducto() {
        producto.setIdProducto(1);
        producto.setFoto(producto.getFoto());
        productoFacade.edit(producto);
        producto = new Producto();
        return "gestionar-catalogo";
    }

    public String preEditarProducto(Producto producto) {
        this.producto = producto;
        return "editar-catalogo";
    }

    public void eliminarProducto(Producto producto) {
        productoFacade.remove(producto);
        //return "Lista";
    }

    public List<Producto> listar() {
        return productoFacade.findAll();
    }
}
