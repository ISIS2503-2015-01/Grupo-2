/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;
 
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


/**
 *
 * @author estudiante
 */
@NoSql(dataFormat=DataFormatType.MAPPED)
@Embeddable
@XmlRootElement
public class Reporte implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    private String id;
    
    private String actividadFisica;
    private String alimentacion;
    private String gravedad;
    private String fechaCreacion;
    private String localizacionDolor;
    private String patronSuenio;
    private int numeroIdentificacion;
    private String medicamentosRecientes;
    
    public Reporte(String id, String actividadFisica, String alimentacion, String gravedad, String fechaCreacion, String localizacionDolor, String patronSuenio, int numeroIdentificacion,String medicamentosRecientes){
        this.id = id;
        this.actividadFisica = actividadFisica;
        this.alimentacion = alimentacion;
        this.gravedad = gravedad;
        this.fechaCreacion  = fechaCreacion;
        this.localizacionDolor = localizacionDolor;
        this.patronSuenio = patronSuenio;
        this.numeroIdentificacion = numeroIdentificacion;
        this.medicamentosRecientes = medicamentosRecientes;
    }

   
    
    public void setMedicamentosRecientes(String medicamentosRecientes) {
        this.medicamentosRecientes = medicamentosRecientes;
    }

    public String getMedicamentosRecientes() {
        return medicamentosRecientes;
    }

    public void setNumeroIdentificacion(int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public int getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocalizacionDolor(String localizacionDolor) {
        this.localizacionDolor = localizacionDolor;
    }

    public void setPatronSuenio(String patronSuenio) {
        this.patronSuenio = patronSuenio;
    }

    public String getActividadFisica() {
        return actividadFisica;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public String getGravedad() {
        return gravedad;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getId() {
        return id;
    }

    public String getLocalizacionDolor() {
        return localizacionDolor;
    }

    public String getPatronSuenio() {
        return patronSuenio;
    }
    
}
