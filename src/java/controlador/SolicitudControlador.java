/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Pedido;
import facade.SolicitudFacade;
import entidades.Solicitud;
import entidades.Usuario;
import facade.PedidoFacade;
import facade.UsuarioFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Aprendiz
 */
@Named(value = "solicitudControlador")
@SessionScoped
public class SolicitudControlador implements Serializable {

    /**
     * Creates a new instance of SolicitudControlador
     */
    @EJB
    SolicitudFacade solicitudFacade;
    Solicitud solicitud = new Solicitud();
    private List<Solicitud> listaSolicitudes;
    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();

    private Part file;
    private Part file1;
    private Part file2;
    private String nombre;
    private String nombre1;
    private String nombre2;
    private String pathReal;
    private String pathReal1;
    private String pathReal2;
    Mailer mailer = new Mailer();


    public SolicitudControlador() {
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
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

    public List<Solicitud> consultarSolicitud() {
        return solicitudFacade.findAll();
    }

    public String crearSolicitud() throws UnsupportedEncodingException {
        solicitud.setIdSolicitud(1);
        solicitud.setPedido(pedidoFacade.find(pedido.getIdPedido()));
//        solicitud.setPedidoidPedido(pedidoFacade.find(pedido.getIdPedido()));
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        solicitud.setUsuario(usuarioFacade.find(usuario.getId()));
        String asunto = "Nueva solicitud de transporte";
        String mensaje = "Se ha generado una nueva solicitud de transporte:\nPedido correspondiente: " + solicitud.getPedido() + "\nSoporte(s) adjuntos.";
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        String path1 = "";
        String path2 = "";
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/FlowersX/Archivos/" + nombre;
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
        if (file1 != null) {
            path1 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
            path1 = path1.substring(0, path1.indexOf("\\build"));
            path1 = path1 + "\\web\\Archivos\\";
            try {
                this.nombre1 = file1.getSubmittedFileName();
                pathReal1 = "/FlowersX/Archivos/" + nombre1;
                path1 = path1 + this.nombre1;
                InputStream in = file.getInputStream();
                byte[] data = new byte[in.available()];
                in.read(data);
                FileOutputStream out = new FileOutputStream(new File(path1));
                out.write(data);
                in.close();
                out.close();
                path.replace("\\", "\\\\");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (file2 != null) {
            path2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
            path2 = path2.substring(0, path2.indexOf("\\build"));
            path2 = path2 + "\\web\\Archivos\\";
            try {
                this.nombre2 = file2.getSubmittedFileName();
                pathReal2 = "/FlowersX/Archivos/" + nombre2;
                path2 = path2 + this.nombre2;
                InputStream in = file.getInputStream();
                byte[] data = new byte[in.available()];
                in.read(data);
                FileOutputStream out = new FileOutputStream(new File(path2));
                out.write(data);
                in.close();
                out.close();
                path.replace("\\", "\\\\");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mailer.configurar();
        if (file1 == null && file2 == null) {
            mailer.enviarMensajeConUnAdjunto(solicitud.getDestinatario(), asunto, mensaje, path, nombre);
            solicitud.setSoporte1(pathReal);
            solicitud.setSoporte2(pathReal1);
            solicitud.setSoporte3(pathReal2);
            solicitudFacade.create(solicitud);
            solicitud = new Solicitud();
        } else if (file1 != null && file2 == null) {
            mailer.enviarMensajeConDosAdjuntos(solicitud.getDestinatario(), asunto, mensaje, path, nombre, path1, nombre1);
            solicitud.setSoporte1(pathReal);
            solicitud.setSoporte2(pathReal1);
            solicitud.setSoporte3(pathReal2);
            solicitudFacade.create(solicitud);
            solicitud = new Solicitud();
        } else if (file1 == null && file2 != null) {
            mailer.enviarMensajeConDosAdjuntos(solicitud.getDestinatario(), asunto, mensaje, path, nombre, path2, nombre2);
            solicitud.setSoporte1(pathReal);
            solicitud.setSoporte2(pathReal1);
            solicitud.setSoporte3(pathReal2);
            solicitudFacade.create(solicitud);
            solicitud = new Solicitud();
        } else if (file1 != null && file2 != null) {
            mailer.enviarMensajeConTresAdjuntos(solicitud.getDestinatario(), asunto, mensaje, path, nombre, path1, nombre1, path2, nombre2);
            solicitud.setSoporte1(pathReal);
            solicitud.setSoporte2(pathReal1);
            solicitud.setSoporte3(pathReal2);
            solicitudFacade.create(solicitud);
            solicitud = new Solicitud();
        }
        return "gestionar-solicitudes.xhtml";
    }

    public String preEditarSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
        return "editar-solicitud.xhtml";
    }

    public String editarSolicitud() {
        solicitudFacade.edit(solicitud);
        solicitud = new Solicitud();
        return "gestionar-solicitudes.xhtml";
    }

    public void eliminarSolicitud(Solicitud solicitud) {
        solicitudFacade.remove(solicitud);
    }

    public List<Solicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<Solicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public String getPath() {
        return pathReal;
    }

    public void setPath(String path) {
        this.pathReal = path;
    }

    public Part getFile() {
        return file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public Part getFile2() {
        return file2;
    }

    public void setFile2(Part file2) {
        this.file2 = file2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
