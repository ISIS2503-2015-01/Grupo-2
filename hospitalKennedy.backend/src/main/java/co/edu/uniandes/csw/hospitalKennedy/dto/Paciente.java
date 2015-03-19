/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import com.sun.istack.NotNull;
import java.util.ArrayList;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
 
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
 
 

/**
 *
 * @author jssalamanca1967
 */

@Entity
public class Paciente implements Serializable{
    
    private static final long serialVersionUID = 2L;
    
    //--------------------------------
    // Atributos
    //--------------------------------
     
    /**
     * La cédula del paciente
     */
    @Id
    private Long id;
    
    private int altura;
    private int edad;
    //private int cedulaCiudadania;
    private String nombre;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Reporte> reportes;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Catalizador catalizadores;
    
    @NotNull
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;
 
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;
    
    public Paciente(){
        
    }
    
    public Paciente(Long id, String nombre, int edad, int cedulaCiudadania, int altura, ArrayList<Reporte> reportesN){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
//        this.cedulaCiudadania = cedulaCiudadania;
        this.altura = altura;
        reportes = reportesN;
    }
    
    @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }
 
    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
    }
    
    public void setCatalizadores(Catalizador catalizadores)
    {
        this.catalizadores=catalizadores;
    }
    
    public Catalizador getCatalizadores()
    {
        return catalizadores;
    }
    
    public void agregarCatalizadorActividadFisica(String actividad)
    {
        catalizadores.agregarActividadFisica(actividad);
    }
    
    public void agregarCatalizadorAlimentacion(String alimentacion)
    {
        catalizadores.agregarAlimentacion(alimentacion);
    }
    
    public void agregarCatalizadorPatronSuenio(String patron)
    {
        catalizadores.agregarPatronSuenio(patron);
    }
    
    public void agregarCatalizadorMedicamentosRecientes(String medicamentos)
    {
        catalizadores.agregarMedicamentosRecientes(medicamentos);
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
            reportes=new ArrayList<Reporte>();
        }
        return reportes;
    }
    
    public Reporte getReporte(Long idReporte)
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
    
    public void removerReporte(Long idReporte)
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
    
//    public void setCedulaCiudadania(int cedulaCiudadania) {
//        this.cedulaCiudadania = cedulaCiudadania;
//    }
//
//    public int getCedulaCiudadania() {
//        return cedulaCiudadania;
//    }
//    
    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setId(Long id) {
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

    public Long getId() {
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
