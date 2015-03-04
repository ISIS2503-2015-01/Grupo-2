/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import java.util.ArrayList;


 
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
 
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
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
 * @author jssalamanca1967
 */

@NoSql(dataFormat=DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class Paciente implements Serializable{
     private static final long serialVersionUID = 1L;
    
    //--------------------------------
    // Atributos
    //--------------------------------
    @Id
    @GeneratedValue
    @Field(name="_idPaciente")
    private String id;
    
    
    private int altura;
    private int edad;
    private int cedulaCiudadania;
    private String nombre;
    
    @ElementCollection
    private ArrayList<Reporte> reportes;
    
     public Paciente(){
        
    }
    
    public Paciente(String id, String nombre, int edad, int cedulaCiudadania, int altura, ArrayList<Reporte> reportesN){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.cedulaCiudadania = cedulaCiudadania;
        this.altura = altura;
        reportes = reportesN;
    }

   
    public void setReportes(ArrayList<Reporte> reportes) {
        this.reportes = reportes;
    }

    public ArrayList<Reporte> getReportes() {
        return reportes;
    }

    public void setCedulaCiudadania(int cedulaCiudadania) {
        this.cedulaCiudadania = cedulaCiudadania;
    }

    public int getCedulaCiudadania() {
        return cedulaCiudadania;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAltura() {
        return altura;
    }

    public int getEdad() {
        return edad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void agregarReporte(Reporte reporte)
    {
        reportes.add(reporte);
    }
    
    
    
}
