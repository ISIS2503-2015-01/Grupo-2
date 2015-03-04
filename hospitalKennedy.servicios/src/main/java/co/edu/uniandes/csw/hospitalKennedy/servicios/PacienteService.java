/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.servicios;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.dto.ReporteDTO;
import co.edu.uniandes.csw.hospitalKennedy.logica.ejb.ServicioPacienteMock;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPacienteMock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
import org.json.simple.JSONObject;

/**
 *
 * @author estudiante
 */
@Path("/Pacientes")
@Stateful
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteService {
    
    //@EJB    
    //private IServicioPacienteMock pacienteEjb;
    
    //public PacienteService()
    //{
    //    pacienteEjb = new ServicioPacienteMock();
    //}
    
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
    
    @POST
    @Path("{id}/agregarReportes/")
    public Response agregarReporte(ReporteDTO reporte){

        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + id + " - " + lista.get(0).getActividadFisica());
        //for(Reporte reporte: lista){
            
          //  pacienteEjb.agregarReporte(id, reporte);
        //}
        //return lista;
        
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
            rta.put("reporte_id", r.getId());
                    
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
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        
    }
    
    @GET
    @Path("Servicio/")
    public void algo()
    {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    }
    
    @DELETE
    @Path("{id}/borrar/")
    public Response eliminarReporte(String idReporte) throws Exception{
        //for(Reporte reporte: lista){
        //    pacienteEjb.removerReporte(id, reporte);
        //}
        Query q1 = entityManager.createQuery("select u from Reporte where u.id = '"+idReporte+"'");
        List<Reporte> reporte = q1.getResultList();
        Query q2 = entityManager.createQuery("delete u from Reporte u where u.id = '"+idReporte+"'");
        q2.executeUpdate();
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(reporte).build();

    }
    
    @GET
    @Path("/paciente")
    public List<Paciente> darPacientes(){
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return pacienteEjb.darPacientes();
    }
    
    @GET
    @Path("{id}/reportes/")
    public List<Reporte> darReportes(@PathParam("id") String id){
        return pacienteEjb.getReportes(id);
    }
    
    @GET
    @Path("{id}/reportes/{idReporte}") //URL de ejemplo http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1L/reportes/1L

    public List<Reporte> darReportePorPaciente(@PathParam("id") String id, @PathParam("idReporte")String idReporte)
    {
        System.out.println("YAAAAAAAAAAAAAA id paciente "+ id +" id reporte "+ idReporte );
        Reporte rep = pacienteEjb.getReportePorPaciente(id, idReporte); 
        ArrayList res = new ArrayList<Reporte>();
        res.add(rep);
        System.out.println(res);
        return res;
    }
    
   @GET
    @Path("/{id}/reportes/{fecha1}/{fecha2}") //Ejemplo de este metodo: http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1/reportes/0/6424221442709
    public List<Reporte> getReportesEntreFechas(@PathParam("id") String id, @PathParam("fecha1") String codFecha1, @PathParam("fecha2") String codFecha2){
        
        return pacienteEjb.getReportesEntreFechas(id, codFecha1, codFecha2);
        
    }
}
