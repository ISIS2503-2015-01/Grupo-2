/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Calendar;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author estudiante
 */
@Entity
public class Catalizador implements Serializable{
    private static final long serialVersionUID = 1L;
    //--------------------------
    // Atributos
    //--------------------------
    
    @Id
    private Long id;
    
    private List<String> actividadesFisicas;
    private List<String> alimentacion;
    private List<String> patronSuenio;
    private List<String> medicamentosRecientes;
    private List<String> todoCatalizador;
    
    public Catalizador()
    {
        
    }
    
    public Catalizador(Long id)
    {
        this.id=id;
        actividadesFisicas = new ArrayList<String>();
        alimentacion = new ArrayList<String>();
        patronSuenio = new ArrayList<String>();
        medicamentosRecientes = new ArrayList<String>();
        todoCatalizador = new ArrayList<String>();
        
     
    }
    
    public void setId(Long id)
    {
        this.id=id;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setTodoCatalizador(List<String>catalizadores)
    {
        this.todoCatalizador=catalizadores;
    }
    
    public List<String> getTodoCatalizador()
    {
        return todoCatalizador;
    }
    
    public void setActividadesFisicas(List<String> actividadesFisicas)
    {
        this.actividadesFisicas=actividadesFisicas;
    }
    
    public List<String> getActividadesFisicas()
    {
        return actividadesFisicas;
    }
    
    public void setAlimentacion(List<String> alimentacion)
    {
        this.alimentacion=alimentacion;
    }
    
    public List<String> getAlimentacion()
    {
        return alimentacion;
    }
    
    public void setPatronSuenio(List<String> patronSuenio)
    {
        this.patronSuenio=patronSuenio;
    }
    
    public List<String> getPatronSuenio()
    {
        return patronSuenio;
    }
    
    public void setMedicamentosRecientes(List<String> medicamentosRecientes)
    {
        this.medicamentosRecientes=medicamentosRecientes;
    }
    
    public List<String> getMedicamentosRecientes()
    {
        return medicamentosRecientes;
    }
    
    public void agregarActividadFisica(String actividadFisica)
    {
        boolean repetido= false;
        for(int i =0;i<actividadesFisicas.size()&&!repetido;i++)
        {
            if(actividadesFisicas.get(i).equalsIgnoreCase(actividadFisica))
            {
                repetido=true;
            }
        }
        if(repetido=false)
        {
            actividadesFisicas.add(actividadFisica);
            todoCatalizador.add(actividadFisica);
        }
    }
    
    public void agregarAlimentacion(String alimentacion)
    {
        boolean repetido= false;
        for(int i =0;i<this.alimentacion.size()&&!repetido;i++)
        {
            if(this.alimentacion.get(i).equalsIgnoreCase(alimentacion))
            {
                repetido=true;
            }
        }
        if(repetido=false)
        {
            this.alimentacion.add(alimentacion);
            todoCatalizador.add(alimentacion);
        }
    }
    
    public void agregarPatronSuenio(String patron)
    {
        boolean repetido= false;
        for(int i =0;i<patronSuenio.size()&&!repetido;i++)
        {
            if(patronSuenio.get(i).equalsIgnoreCase(patron))
            {
                repetido=true;
            }
        }
        if(repetido=false)
        {
            patronSuenio.add(patron);
            todoCatalizador.add(patron);
        }
    }
    
    public void agregarMedicamentosRecientes(String medicamentos)
    {
        boolean repetido= false;
        for(int i =0;i<medicamentosRecientes.size()&&!repetido;i++)
        {
            if(medicamentosRecientes.get(i).equalsIgnoreCase(medicamentos))
            {
                repetido=true;
            }
        }
        if(repetido=false)
        {
            medicamentosRecientes.add(medicamentos);
            todoCatalizador.add(medicamentos);
        }
    }
            
}
