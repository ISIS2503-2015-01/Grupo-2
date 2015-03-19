/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.ejb;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Catalizador;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.dto.ReporteDTO;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPacienteMock;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.hospitalKennedy.persistencia.mock.ServicioPersistenciaMock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.json.simple.JSONObject;

/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioPacienteMock implements IServicioPacienteMock {
    
    //@EJB
    //public static IServicioPersistenciaMockLocal persistencia;
    @PersistenceUnit(unitName = "HospitalKennedyPU")
    EntityManager entityManager; 

    public ServicioPacienteMock()
    {
        
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if(ServicioDoctorMock.persistencia == null)
        //{
        //    persistencia = new ServicioPersistenciaMock();
            
        //}
        //else
        //    persistencia = ServicioDoctorMock.persistencia;
    }

    @Override
    public List<Reporte> getReportes(Long idPaciente) 
    {
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> pacientes = q.getResultList();
        ArrayList<Reporte>reportes = new ArrayList(pacientes.get(0).getReportes());
        return reportes;
    }
    
     @Override        
    public ReporteDTO agregarReporte(Long idPaciente, ReporteDTO reporte)
    {
       Reporte r = new Reporte();
       
        r.setActividadFisica(reporte.getActividadFisica());
        r.setAlimentacion(reporte.getAlimentacion());
        r.setGravedad(reporte.getGravedad());
        r.setFechaCreacion(reporte.getFechaCreacion());
        r.setLocalizacionDolor(reporte.getLocalizacionDolor());
        r.setPatronSuenio(reporte.getPatronSuenio());
        r.setMedicamentosRecientes(reporte.getMedicamentosRecientes());  
        r.setId(reporte.getId());
        r.setCatalizadores(reporte.getCatalizador());
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> pacientes = q.getResultList();
        Paciente p = pacientes.get(0);
        p.agregarReporte(r);
        //Agrega los catalizadores del reporte
        
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            entityManager.refresh(p);   
        }catch(Throwable t){
                    t.printStackTrace();
                    if(entityManager.getTransaction().isActive())
                    {
                        entityManager.getTransaction().rollback();
                    }
                    r = null;
                }
        finally
        {
            entityManager.clear();
            entityManager.close();
        }
        return reporte;

    }
    
    @Override
    public Reporte removerReporte(Long idPaciente, Long idReporte)
    {
        Query q1 = entityManager.createQuery("select u from Paciente where u.id = '"+idPaciente+"'");
        List<Paciente> pacientes = q1.getResultList();
        Paciente p =pacientes.get(0);
        Reporte r = p.getReporte(idReporte);
        p.removerReporte(idReporte);
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(p);
            entityManager.getTransaction().commit();                    
        }
        catch(Throwable t)
                {
                    t.printStackTrace();
                    if(entityManager.getTransaction().isActive())
                    {
                        entityManager.getTransaction().rollback();
                    }
                    r = null;
                }
        finally
        {
            entityManager.clear();
            entityManager.close();
        }
        
//        Query q2 = entityManager.createQuery("delete u from Reporte u where u.id = '"+idReporte+"'");
//        q2.executeUpdate();
        return r;
    }

    
    @Override
    public List<Paciente> darPacientes(){
        
        Query q = entityManager.createQuery("select u from Paciente u");
        List<Paciente> paciente = q.getResultList();
        ArrayList p = new ArrayList(paciente);
        return p;

    }  
    
    @Override
    public Paciente darPaciente(Long idPaciente){
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> paciente = q.getResultList();
        return paciente.get(0);
    }

    @Override
    public Reporte getReportePorPaciente(Long idPaciente, Long idReporte) {
    
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'"+"'");
        List<Paciente> pacientes = q.getResultList();
        return pacientes.get(0).getReporte(idReporte);
    }

    @Override
    //Arreglar
    public List<Reporte> getReportesEntreFechas(Long idPaciente, String  codFecha1, String codFecha2) {
        
       Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
       List<Paciente> pacientes = q.getResultList();
       return pacientes.get(0).getReportesEntreFechas(codFecha1,codFecha2);
        
    }

    @Override
    public List<String> darCatalizadoresActividadFisica(Long idPaciente, Long idReporte) {
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> pacientes = q.getResultList();
        Reporte r = pacientes.get(0).getReporte(idReporte);
        return r.getCatalizadores().getActividadesFisicas();
    }

    @Override
    public List<String> darCatalizadoresAlimentacion(Long idPaciente, Long idReporte) {
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> pacientes = q.getResultList();
        Reporte r = pacientes.get(0).getReporte(idReporte);
        return r.getCatalizadores().getAlimentacion();
    }

    @Override
    public List<String> darCatalizadoresPatronSuenio(Long idPaciente,Long idReporte) {
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> pacientes = q.getResultList();
        Reporte r = pacientes.get(0).getReporte(idReporte);
        return r.getCatalizadores().getPatronSuenio();
    }

    @Override
    public List<String> darCatalizadoresMedicamentosRecientes(Long idPaciente, Long idReporte) {
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
       List<Paciente> pacientes = q.getResultList();
        Reporte r = pacientes.get(0).getReporte(idReporte);
        return r.getCatalizadores().getMedicamentosRecientes();
    }
    
    @Override
    public List<String>darCatalizadores(Long idPaciente, Long idReporte)
    {
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
       List<Paciente> pacientes = q.getResultList();
        Reporte r = pacientes.get(0).getReporte(idReporte);
        return r.getCatalizadores().getTodoCatalizador();
    }

    
}
