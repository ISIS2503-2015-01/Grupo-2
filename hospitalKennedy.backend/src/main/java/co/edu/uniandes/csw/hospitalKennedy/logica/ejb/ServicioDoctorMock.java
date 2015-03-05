/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.ejb;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.PacienteDTO;
import co.edu.uniandes.csw.hospitalKennedy.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioDoctorMock;
import javax.ejb.Stateless;

import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.hospitalKennedy.persistencia.mock.ServicioPersistenciaMock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.Response;


/**
 *
 * @author estudiante
 */

@Stateful
public class ServicioDoctorMock implements IServicioDoctorMock {
    
    //@EJB
    //public static IServicioPersistenciaMockLocal persistencia;
    
    @PersistenceContext(unitName = "HospitalKennedyPU")
    EntityManager entityManager;
    
    private ArrayList<Paciente> pacientes;

    public ServicioDoctorMock()
    {
        
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if(ServicioPacienteMock.persistencia == null)
        //{
        //    persistencia=new ServicioPersistenciaMock();
        //}
        //else
        //    persistencia = ServicioPacienteMock.persistencia;
    }
    
    @Override
    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public EntityManager getPersistencia() {
        return entityManager;
    }
    
    

    @Override
    public ArrayList<Paciente> getPacientes() {
        //return (ArrayList<Paciente>)entityManager.find(Paciente.class, pacientes);
        Query q = entityManager.createQuery("select u from Paciente u");
        List<Paciente> paciente = q.getResultList();
        ArrayList p = new ArrayList(paciente);
        return p;
    }
    
    
    
    /**
     * 
     */
    @Override
    public PacienteDTO agregarPaciente(PacienteDTO paciente) 
    {
        Paciente p = new Paciente();
        
        p.setAltura(paciente.getAltura());
        p.setCedulaCiudadania(paciente.getCedulaCiudadania());
        p.setEdad(paciente.getEdad());
        p.setNombre(paciente.getNombre());
        p.setReportes(paciente.getReportes());

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(paciente);
            entityManager.getTransaction().commit();
            entityManager.refresh(paciente);
            
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            p = null;
        } finally {
        	entityManager.clear();
        	entityManager.close();
        }
        
        return paciente;

    }

    /**
     * Remueve un mueble del carrito de compra
     * @param mueble Mueble a remover
     * @param removerCero Indica si al ser cero se elimina de la lista
     */
    @Override
    public Paciente removerPaciente(String idPaciente)
    {
        Query q1 = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> p = q1.getResultList();
        Query q2 = entityManager.createQuery("delete u from Paciente u where u.id = '"+idPaciente+"'");
        q2.executeUpdate();
        return p.get(0);

    }
    
    
    
}
