/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.interfaces;

import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.dto.ReporteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public interface IServicioPacienteMock {
 
    
    public List<Reporte> getReportes(Long idPaciente);
    public ReporteDTO agregarReporte(Long idPaciente, ReporteDTO reporte);
    public Reporte removerReporte(Long idPaciente, Long idReporte);
    public Paciente darPaciente(Long idPaciente);
    public List<Paciente> darPacientes();
    public Reporte getReportePorPaciente(Long idPaciente,Long idReporte);
    public List<String> darCatalizadoresActividadFisica(Long idPaciente);
    public List<String> darCatalizadoresAlimentacion(Long idPaciente);
    public List<String> darCatalizadoresPatronSuenio(Long idPaciente);
    public List<String> darCatalizadoresMedicamentosRecientes(Long idPaciente);
    public List<String> darCatalizadores(Long idPaciente);
    public List<Reporte> getReportesEntreFechas(Long idPaciente, String codFecha1, String codFecha2);
    
    
}
