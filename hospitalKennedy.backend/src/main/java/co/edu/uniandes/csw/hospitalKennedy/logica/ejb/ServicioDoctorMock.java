/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.ejb;

import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioDoctorMock;
import javax.ejb.Stateless;

import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.hospitalKennedy.persistencia.mock.ServicioPersistenciaMock;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;


/**
 *
 * @author estudiante
 */

@Stateful
public class ServicioDoctorMock implements IServicioDoctorMock {
    
    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    private ArrayList<Paciente> pacientes;

    public ServicioDoctorMock()
    {
        persistencia=new ServicioPersistenciaMock();
    }

    @Override
    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public IServicioPersistenciaMockLocal getPersistencia() {
        return persistencia;
    }
    
    

    @Override
    public ArrayList<Paciente> getPacientes() {
        return (ArrayList<Paciente>)persistencia.findAll(Paciente.class);
    }
    
    
    
    /**
     * Agrega un nuevo mueble al carro de compras
     * @param mueble Mueble que se agrega al carrito
     */
    @Override
    public void agregarPaciente(Paciente paciente) 
    {
        try
        {
            persistencia.create(paciente);
        }
        catch(Exception ex)
        {
            Logger.getLogger(ServicioDoctorMock.class.getName()).log(Level.SEVERE, null, ex);                
                    
        }
        

    }

    /**
     * Remueve un mueble del carrito de compra
     * @param mueble Mueble a remover
     * @param removerCero Indica si al ser cero se elimina de la lista
     */
    @Override
    public void removerPaciente(Paciente paciente)
    {
        try
        {
            persistencia.delete(paciente);
        }
        catch(Exception ex)
        {
            Logger.getLogger(ServicioDoctorMock.class.getName()).log(Level.SEVERE, null, ex);                
                    
        }
        

    }
    
    
    
}
