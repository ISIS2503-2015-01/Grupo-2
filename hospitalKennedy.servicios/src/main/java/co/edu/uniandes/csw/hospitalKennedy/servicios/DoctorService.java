/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.servicios;

import co.edu.uniandes.csw.hospitalKeneddy.PersistenceManager;
import co.edu.uniandes.csw.hospitalKennedy.dto.DoctorDTO;
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
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorService {
    
    //@EJB
    //private IServicioDoctorMock doctorEjb;
   
    private ServicioDoctorMock servicioDoctor;
   
    public DoctorService(){
        servicioDoctor = new ServicioDoctorMock();
    }
    
    @POST
    @Path("/agregarDoctor")
    public Response agregarDoctor(DoctorDTO doctor){
        
        DoctorDTO rta = servicioDoctor.agregarDoctor(doctor);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();

    }
    
    @POST
    @Path("/agregarPaciente")
    public Response agregarPaciente(PacienteDTO paciente){

        PacienteDTO p = servicioDoctor.agregarPaciente(paciente);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(p).build();
       
    }
    
    /**
     *
     * @param idPaciente
     */
    @DELETE
    @Path("borrar/{idPaciente}")
    public Response eliminarPaciente(@PathParam("idPaciente") Long idPaciente){
        //for(Paciente paciente: lista){
        //    doctorEjb.removerPaciente(paciente);
        //}
        Paciente p = servicioDoctor.removerPaciente(idPaciente);
        return Response.status(200).header("Access-Control-allow-Origin", "*").entity(p).build();
        
    }
    
    //@GET
    //@Path("/paciente/{idPaciente}")
    //public Response darPaciente(@PathParam("idPaciente") String idPaciente){
     //   return doctorEjb.getPacientes();
     
    //    return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(paciente).build();           
    //}
    
    /**
     *
     * @return
     */
        
    @GET
    @Path("/paciente/")
    public Response darPacientes(){
     //   return doctorEjb.getPacientes();
        
        ArrayList<Paciente> p = servicioDoctor.getPacientes();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(p).build();           
    }
    
}
