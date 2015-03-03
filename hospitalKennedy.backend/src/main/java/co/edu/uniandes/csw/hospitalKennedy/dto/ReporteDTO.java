/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

/**
 *
 * @author estudiante
 */
public class ReporteDTO {
    
    private String actividadFisica;
    private String alimentacion;
    private String gravedad;
    private String fechaCreacion;
    private String localizacionDolor;
    private String patronSuenio;
    private int numeroIdentificacion;
    private String medicamentosRecientes;
    
    public ReporteDTO()
    {
        
    }
    
    public void setActividadFisica(String actividadFisica)
    {
        this.actividadFisica=actividadFisica;
    }
    
    public String getActividadFisica()
    {
        return this.actividadFisica;
    }
    
    public void setAlimentacion(String alimentacion)
    {
        this.alimentacion=alimentacion;
    }
    
    public String getAlimentacion()
    {
        return this.alimentacion;
    }
    
    public void setGravedad(String gravedad)
    {
        this.gravedad=gravedad;
    }
    
    public String getGravedad()
    {
        return this.gravedad;
    }
    
    public void setFechaCreacion(String fechaCreacion)
    {
        this.fechaCreacion=fechaCreacion;
    }
    
    public String getFechaCreacion()
    {
        return this.fechaCreacion;
    }
    
    public void setLocalizacionDolor(String localizacionDolor)
    {
        this.localizacionDolor=localizacionDolor;
    }
    
    public String getLocalizacionDolor()
    {
        return this.localizacionDolor;
    }
    
    public void setPatronSuenio(String patronSuenio)
    {
        this.patronSuenio=patronSuenio;
    }
    
    public String getPatronSuenio()
    {
        return this.patronSuenio;
    }
    
    public void setNumeroIdentificacion(int numeroIdentificacion)
    {
        this.numeroIdentificacion=numeroIdentificacion;
    }
    
    public int getNumeroIdentificacion()
    {
        return this.numeroIdentificacion;
    }
    
    public void setMedicamentosRecientes(String medicamentosRecientes)
    {
        this.medicamentosRecientes=medicamentosRecientes;
    }
    
    public String getMedicamentosRecientes()
    {
        return this.medicamentosRecientes;
    }
    
}
