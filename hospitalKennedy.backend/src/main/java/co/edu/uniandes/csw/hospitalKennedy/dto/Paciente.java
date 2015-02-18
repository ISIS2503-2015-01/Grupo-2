/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import java.util.ArrayList;

/**
 *
 * @author jssalamanca1967
 */
public class Paciente {
    
    
    //--------------------------------
    // Atributos
    //--------------------------------
    
    /**
     * Altura en cm del paciente
     */
    private int altura;
    private int edad;
    private int cedulaCiudadania;
    private Long id;
    private String nombre;
    private ArrayList<Reporte> reportes;
    
    public Paciente(Long id, String nombre, int edad, int cedulaCiudadania, int altura, ArrayList<Reporte> reportesN){
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
