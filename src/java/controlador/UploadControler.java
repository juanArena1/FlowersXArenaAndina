package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.Pais;
import facade.PaisFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@ManagedBean(name = "uplCtrl")
public class UploadControler {
    
    @EJB
    PaisFacade paisFacade;
    Pais pais = new Pais(); 

    private Part file;
    private String nombre;
    private String pathReal;

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

    public String upload(String tabla) {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = UploadControler.randomAlphaNumeric(15);
            pathReal = "/UploadFile/Archivos/" + nombre;
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
        return "Cargado";
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
