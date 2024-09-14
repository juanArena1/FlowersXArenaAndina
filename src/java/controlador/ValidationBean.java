/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

/**
 *
 * @author Aprendiz
 */
@Named(value = "validationBean")
@SessionScoped
public class ValidationBean implements Serializable {

    /**
     * Creates a new instance of ValidationBean
     */
    public ValidationBean() {
    }

    @Past
    private Date pastDate;

    @Future
    private Date futureDate;

    public Date getPastDate() {
        return pastDate;
    }

    public void setPastDate(Date pastDate) {
        this.pastDate = pastDate;
    }

    public Date getFutureDate() {
        return futureDate;
    }

    public void setFutureDate(Date futureDate) {
        this.futureDate = futureDate;
    }

}
