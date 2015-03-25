/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.ejb;
import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Catalizador;
import co.edu.uniandes.csw.hospitalKennedy.dto.Doctor;
import co.edu.uniandes.csw.hospitalKennedy.dto.DoctorDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.PacienteDTO;
import co.edu.uniandes.csw.hospitalKennedy.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioDoctorMock;
import javax.ejb.Stateless;

import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPersistenciaMockLocal;
//import co.edu.uniandes.csw.hospitalKennedy.persistencia.mock.ServicioPersistenciaMock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.core.Response;

/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioDoctorMock implements IServicioDoctorMock {

    //@EJB
    //public static IServicioPersistenciaMockLocal persistencia;
    @PersistenceUnit(unitName = "HospitalKennedyPU")
    EntityManager entityManager;

    private ArrayList<Paciente> pacientes;

    public ServicioDoctorMock() {

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
    public List<Paciente> getPacientes() {

        System.out.println("asdasdasdasdasdasdasdasdasdasdasdasdasd");
        //return (ArrayList<Paciente>)entityManager.find(Paciente.class, pacientes);
        Query q = entityManager.createQuery("select * from Paciente");
        List<Paciente> paciente = q.getResultList();
        return paciente;
    }

    /**
     *
     */
    @Override
    public PacienteDTO agregarPaciente(PacienteDTO paciente) {
        System.out.println("ENTRO AL METODO");
        
        Paciente p = new Paciente();

        p.setAltura(paciente.getAltura());
        p.setId(paciente.getCedulaCiudadania());
        p.setEdad(paciente.getEdad());
        p.setNombre(paciente.getNombre());

        System.out.println("La altura es: " + paciente.getAltura());
        System.out.println("La cédula es: " + paciente.getCedulaCiudadania());
        System.out.println("La edad es: " + paciente.getEdad());
        System.out.println("El nombre es: " + paciente.getNombre());
        System.out.println("La altura es: " + paciente.getAltura());

        p.setReportes(paciente.getReportes());

        System.out.println("Los reportes son los siguiente [  " + paciente.getReportes()+"  ]");

        try {
            entityManager.getTransaction().begin();
            System.out.println("´:::: empezó");
            entityManager.persist(p);
                        System.out.println("´:::: PERSISTE!!!");

            entityManager.getTransaction().commit();
                        System.out.println("´:::: COMMIT!!!");

            entityManager.refresh(p);
                        System.out.println("´:::: REFRESCÓ");


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

    public DoctorDTO agregarDoctor(DoctorDTO doctor) {

        Doctor d = new Doctor(doctor.getId(), doctor.getNombre(), doctor.getPassword(), doctor.getLogin());

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(d);
            entityManager.getTransaction().commit();
            entityManager.refresh(d);

        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            doctor = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }

        return doctor;
    }

    /**
     * Remueve un mueble del carrito de compra
     */
    @Override
    public Paciente removerPaciente(Long idPaciente) {

        Paciente p = entityManager.find(Paciente.class, idPaciente);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(p);
            entityManager.getTransaction().commit();
            //entityManager.refresh(p);

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

        return p;

    }

}
