/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class PacienteDTO {
    
    //Atributos
    
    private int altura;
    private int edad;
    private int cedulaCiudadania;
    private String nombre;
    private ArrayList<Reporte> reportes;
    private String id;
    
    public PacienteDTO()
    {
        
    }
    
    public void setAltura(int altura)
    {
        this.altura=altura;
    }
    
    public int getAltura()
    {
        return this.altura;
    }
    
    public void setEdad(int edad)
    {
        this.edad=edad;
    }
    
    public int getEdad()
    {
        return this.edad;
    }
    
    public void setCedulaCiudadania(int cedulaCiudadania)
    {
        this.cedulaCiudadania=cedulaCiudadania;
    }
    
    public int getCedulaCiudadania()
    {
        return this.cedulaCiudadania;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
     public void setId(String id) {
        this.id = id;
    }
     
      public String getId() {
        return id;
    }
    
    public void setReportes(ArrayList<Reporte> reportes)
    {
        if(this.reportes==null)
        {
            reportes= new ArrayList();
        }
        for(int i=0;i<reportes.size();i++)
        {
            this.reportes.add(reportes.get(i));
        }
    }
    
    public ArrayList<Reporte> getReportes()
    {
        return reportes;
    }
}
