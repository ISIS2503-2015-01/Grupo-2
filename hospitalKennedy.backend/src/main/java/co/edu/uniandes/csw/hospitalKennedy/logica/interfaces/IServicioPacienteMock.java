/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.interfaces;

import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public interface IServicioPacienteMock {
 
    
    public ArrayList<Reporte> getReportes(Long idPaciente);
    public void agregarReporte(Long idPaciente, Reporte reporte);
    public void removerReporte(Long idPaciente, Reporte reporte);
    public List<Paciente> darPacientes();
    public Reporte getReportePorPaciente(Long idPaciente,Long idReporte);

    public List<Reporte> getReportesEntreFechas(long id, long codFecha1, long codFecha2);
    
    
}
