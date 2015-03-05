/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.ejb;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
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
import javax.persistence.Query;
import org.json.simple.JSONObject;

/**
 *
 * @author estudiante
 */
@Stateful
public class ServicioPacienteMock implements IServicioPacienteMock {
    
    //@EJB
    //public static IServicioPersistenciaMockLocal persistencia;
     @PersistenceContext(unitName = "HospitalKennedyPU")
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
    public ArrayList<Reporte> getReportes(String idPaciente) 
    {
        Query q = entityManager.createQuery("select u from Reporte u where u.idPaciente = '"+idPaciente+"'");
        List<Reporte> reporte = q.getResultList();
        ArrayList<Reporte>reportes = new ArrayList(reporte);
        return reportes;
    }
    
             
    @Override
    public ReporteDTO agregarReporte(String idPaciente, ReporteDTO reporte){
    
       Reporte r = new Reporte();
        JSONObject rta = new JSONObject();
        r.setActividadFisica(reporte.getActividadFisica());
        r.setAlimentacion(reporte.getAlimentacion());
        r.setGravedad(reporte.getGravedad());
        r.setFechaCreacion(reporte.getFechaCreacion());
        r.setLocalizacionDolor(reporte.getLocalizacionDolor());
        r.setPatronSuenio(reporte.getPatronSuenio());
        r.setNumeroIdentificacion(reporte.getNumeroIdentificacion());
        r.setMedicamentosRecientes(reporte.getMedicamentosRecientes());
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(r);
            entityManager.getTransaction().commit();
            entityManager.refresh(r);
            rta.put("reporte_idReporte", r.getId());
                    
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
        return reporte;

    }
    
    @Override
    public Reporte removerReporte(String idPaciente, String idReporte)
    {
        Query q1 = entityManager.createQuery("select u from Paciente where u.id = '"+idPaciente+"' and u.idReporte ='"+idReporte+"'");
        List<Reporte> reporte = q1.getResultList();
        Query q2 = entityManager.createQuery("delete u from Reporte u where u.id = '"+idReporte+"'");
        q2.executeUpdate();
        return reporte.get(0);
    }

    
    @Override
    public List<Paciente> darPacientes(){
        
        Query q = entityManager.createQuery("select u from Paciente u");
        List<Paciente> paciente = q.getResultList();
        ArrayList p = new ArrayList(paciente);
        return p;

    }  
    
    @Override
    public Paciente darPaciente(String idPaciente){
        Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> paciente = q.getResultList();
        return paciente.get(0);
    }

    @Override
    public Reporte getReportePorPaciente(String idPaciente, String idReporte) {
    
        Query q = entityManager.createQuery("select u from Reporte u where u.idPaciente = '"+idPaciente+"'"+" and u.id = "+idReporte+"'");
        List<Reporte> reporte = q.getResultList();
        return reporte.get(0);
    }

    @Override
    public List<Reporte> getReportesEntreFechas(String idPaciente, String  codFecha1, String codFecha2) {
        
       Query q = entityManager.createQuery("select u from Reporte u where u.fecha1 = '"+codFecha1+"' and u.fecha2 = '"+codFecha2+"' and u.idPaciente = '"+idPaciente+"'");
       List<Reporte> reporte = q.getResultList();
       return reporte;
        
    }

    
}
