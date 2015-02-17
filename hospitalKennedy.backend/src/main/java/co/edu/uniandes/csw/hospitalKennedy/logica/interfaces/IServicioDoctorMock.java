/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.interfaces;

import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public interface IServicioDoctorMock {
    
    
    public void setPacientes(ArrayList<Paciente> pacientes);
    public IServicioPersistenciaMockLocal getPersistencia();
    public ArrayList<Paciente> getPacientes();
    public void agregarPaciente(Paciente paciente);
    public void removerPaciente(Paciente paciente);
    
}
