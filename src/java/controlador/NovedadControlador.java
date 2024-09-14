package controlador;

import entidades.Novedad;
import entidades.Pedido;
import entidades.TipoNovedad;
import entidades.Usuario;
import facade.NovedadFacade;
import facade.PedidoFacade;
import facade.TipoNovedadFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "novedadControlador")
@SessionScoped
public class NovedadControlador implements Serializable {

    public NovedadControlador() {
    }
    @EJB
    NovedadFacade novedadFacade;
    Novedad novedad = new Novedad();
    private List<Novedad> listaNovedades;
    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();
    @EJB
    TipoNovedadFacade tipoNovedadFacade;
    TipoNovedad tipoNovedad = new TipoNovedad();
    
    public Novedad getNovedad() {
        return novedad;
    }

    public void setNovedad(Novedad novedad) {
        this.novedad = novedad;
    }

    public TipoNovedad getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(TipoNovedad tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public TipoNovedadFacade getTipoNovedadFacade() {
        return tipoNovedadFacade;
    }

    public void setTipoNovedadFacade(TipoNovedadFacade tipoNovedadFacade) {
        this.tipoNovedadFacade = tipoNovedadFacade;
    }
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Novedad> consultarNovedad() {
        return novedadFacade.findAll();
    }

    public String crearNovedadProduccion() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        novedad.setIdNovedad(null);
        novedad.setPedido(pedidoFacade.find(pedido.getIdPedido()));
        novedad.setUsuarioid(usuarioFacade.find(usuario.getId()));
        novedad.setTipoNovedad(tipoNovedadFacade.find(1));
        novedadFacade.create(novedad);
        novedad = new Novedad();
        return "gestionar-novedades";
    }

    public String preEditarNovedad(Novedad novedad) {
        this.novedad = novedad;
        return "editar-novedad.xhtml";
    }

    public void editarNovedad() {
        novedadFacade.edit(novedad);
        novedad = new Novedad();
    }

    public void eliminarNovedad(Novedad novedad) {
        novedadFacade.remove(novedad);
    }

    public List<Novedad> getListaNovedades() {
        return listaNovedades;
    }

    public void setListaNovedades(List<Novedad> listaNovedades) {
        this.listaNovedades = listaNovedades;
    }

}
