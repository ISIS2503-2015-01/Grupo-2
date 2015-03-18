/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import java.util.ArrayList;


 
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private List<Reporte> reportes;
    
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
        if(this.reportes==null)
        {
            reportes=new ArrayList();
        }
        this.reportes = reportes;
    }

    public List<Reporte> getReportes() {
        if(this.reportes==null)
        {
            reportes=new ArrayList();
        }
        return reportes;
    }
    
    public Reporte getReporte(String idReporte)
    {
        Reporte r = null;
        boolean ya = false;
        for(int i=0;i<reportes.size()&&!ya;i++)
        {
            if(reportes.get(i).getId().equals(idReporte))
            {
                r = reportes.get(i);
                ya=true;
            }
        }
        return r;
    }
    
    public List<Reporte> getReportesEntreFechas(String fecha1, String fecha2)
    {
        List<Reporte> estos= new ArrayList<Reporte>();
        for(int i =0;i<reportes.size();i++)
        {
            SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy");
            try
            {
                Date fech1 = formato.parse(fecha1);
                Date fech2 = formato.parse(fecha2);
                Date fecha = formato.parse(reportes.get(i).getFechaCreacion());
                if(fecha.after(fech1)&&fecha.before(fech2))
                {
                    estos.add(reportes.get(i));
                }
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
            
            
            
        }
        return estos;
    }
    public void removerReporte(String idReporte)
    {
        boolean ya =false;
        for(int i =0;i<reportes.size()&&!ya;i++)
        {
            if(reportes.get(i).getId().equals(idReporte))
            {
                reportes.remove(i);
                ya=true;
            }
        }
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
