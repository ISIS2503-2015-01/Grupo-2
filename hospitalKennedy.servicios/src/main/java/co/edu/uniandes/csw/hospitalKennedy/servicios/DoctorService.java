/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.servicios;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.PacienteDTO;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.logica.ejb.ServicioDoctorMock;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioDoctorMock;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONObject;

/**
 *
 * @author estudiante
 */
@Path("/Doctor")
@Stateful
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorService {
    
    //@EJB
    //private IServicioDoctorMock doctorEjb;
   
  
    @PersistenceContext(unitName = "HospitalKennedyPU")
    EntityManager entityManager;
    
      @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //public DoctorService(){
    //    doctorEjb = new ServicioDoctorMock();
    //}
    @POST
    @Path("/agregar")
    public Response agregarPaciente(PacienteDTO paciente){
      //  System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
      //  for(Paciente paciente: lista){
      //      doctorEjb.agregarPaciente(paciente);
      //  }
        
      //  return lista;
        Paciente p = new Paciente();
        JSONObject rta = new JSONObject();
        p.setAltura(paciente.getAltura());
        p.setCedulaCiudadania(paciente.getCedulaCiudadania());
        p.setEdad(paciente.getEdad());
        p.setNombre(paciente.getNombre());
        p.setReportes(paciente.getReportes());

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            entityManager.refresh(p);
            rta.put("paciente_id", p.getId());
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
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
       
    }
    
    @DELETE
    @Path("borrar/")
    public Response eliminarPaciente(String idPaciente){
        //for(Paciente paciente: lista){
        //    doctorEjb.removerPaciente(paciente);
        //}
        Query q1 = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> paciente = q1.getResultList();
        Query q2 = entityManager.createQuery("delete u from Paciente u where u.id = '"+idPaciente+"'");
        q2.executeUpdate();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(paciente).build();           

    }
    
    @GET
    @Path("/paciente/{idPaciente}")
    public Response darPaciente(@PathParam("idPaciente") String idPaciente){
     //   return doctorEjb.getPacientes();
     Query q = entityManager.createQuery("select u from Paciente u where u.id = '"+idPaciente+"'");
        List<Paciente> paciente = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(paciente).build();           
    }
}
