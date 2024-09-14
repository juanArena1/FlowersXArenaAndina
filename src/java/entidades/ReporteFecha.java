/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author ACER
 */
public class ReporteFecha {

    public void DescargarPedido(String ruta, String RolC) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conexion;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/flowersx", "root", "");
        Map parametro = new HashMap();
        parametro.put("RolC", RolC);
        
        try {
            File file = new File(ruta);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-Type", "application/pdf");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, conexion);

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.addHeader("content-disposition", "attachment; filename=Reporte.pdf");
            ServletOutputStream descarga = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, descarga);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void DescargarPedidoFecha(String ruta,Date fechaInicio,Date fechaF) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conexion;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/flowersx", "root", "");
        Map parametro = new HashMap();
       
        parametro.put("fechaI", fechaInicio);
        parametro.put("fechaF", fechaF);
        try {
            File file = new File(ruta);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-Type", "application/pdf");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, conexion);

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.addHeader("content-disposition", "attachment; filename=Reporte.pdf");
            ServletOutputStream descarga = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, descarga);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}