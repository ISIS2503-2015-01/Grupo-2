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
        return pacientes;
    }
    
    
    
    /**
     * Agrega un nuevo mueble al carro de compras
     * @param mueble Mueble que se agrega al carrito
     */
    @Override
    public void agregarPaciente(Paciente paciente) 
    {
        boolean found = false;
        Paciente item;
        for(int i= 0, max= pacientes.size(); i < max; i++)
        {
            item = (Paciente)pacientes.get(i);
            if (item.getCedulaCiudadania() == paciente.getCedulaCiudadania())
            {
                found = true;
                break;
            }
        }

        if (!found)
        {
            pacientes.add(paciente);           
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

        Paciente foundItem = null;
        Paciente item;
        for(int i= 0, max= pacientes.size(); i < max; i++)
        {
            item = (Paciente)pacientes.get(i);
            if (item.getCedulaCiudadania()== paciente.getCedulaCiudadania())
            {
                foundItem = item;
                break;
            }
        }

        if (foundItem != null) {
            
            pacientes.remove(foundItem);
        }
    }
    
    
    
}
