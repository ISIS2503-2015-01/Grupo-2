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
    
     ServicioPacienteMock servicioPaciente;
    
    //@PostConstruct
    //public void init() {
       
    //}
     
     public PacienteService()
     {
         servicioPaciente = new ServicioPacienteMock();
     }
    
    @POST
    @Path("{id}/agregarReportes/")
    public Response agregarReporte(@PathParam("id") String idPaciente, ReporteDTO reporte){

        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + id + " - " + lista.get(0).getActividadFisica());
        //for(Reporte reporte: lista){
            
          //  pacienteEjb.agregarReporte(id, reporte);
        //}
        //return lista;
        
        ReporteDTO r = servicioPaciente.agregarReporte(idPaciente, reporte);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(r).build();
        
    }
    
    @GET
    @Path("Servicio/")
    public void algo()
    {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    }
    
    @DELETE
    @Path("{id}/borrar/{idReporte}")
    public Response eliminarReporte(@PathParam("id") String idPaciente, @PathParam("idReporte") String idReporte) throws Exception{
        //for(Reporte reporte: lista){
        //    pacienteEjb.removerReporte(id, reporte);
        //}
        Reporte r = servicioPaciente.removerReporte(idPaciente, idReporte);
        
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(r).build();

    }
    
    @GET
    @Path("/paciente/{idPaciente}")
    public Response darPaciente(@PathParam("idPaciente") String idPaciente)
    {
        //System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        //return pacienteEjb.darPacientes();
        Paciente p = servicioPaciente.darPaciente(idPaciente);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(p).build();  
    }
    
    @GET
    @Path("{id}/reportes/")
    public Response darReportes(@PathParam("id") String idPaciente){
        //return pacienteEjb.getReportes(id);
       ArrayList<Reporte>reportes= servicioPaciente.getReportes(idPaciente);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(reportes).build();
        
    }
    
    @GET
    @Path("{id}/reportes/{idReporte}") //URL de ejemplo http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1L/reportes/1L

    public Response darReportePorPaciente(@PathParam("id") String idPaciente, @PathParam("idReporte")String idReporte)
    {
        
        //System.out.println("YAAAAAAAAAAAAAA id paciente "+ id +" id reporte "+ idReporte );
        //Reporte rep = pacienteEjb.getReportePorPaciente(id, idReporte); 
        //ArrayList res = new ArrayList<Reporte>();
        //res.add(rep);
        //System.out.println(res);
        //return res;
      
        Reporte reporte = servicioPaciente.getReportePorPaciente(idPaciente, idReporte);
        
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(reporte).build();
    }
    
   @GET
    @Path("/{id}/reportes/{fecha1}/{fecha2}") //Ejemplo de este metodo: http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1/reportes/0/6424221442709
    public Response getReportesEntreFechas(@PathParam("id") String idPaciente, @PathParam("fecha1") String codFecha1, @PathParam("fecha2") String codFecha2){
        
        //return pacienteEjb.getReportesEntreFechas(id, codFecha1, codFecha2);
        List<Reporte> reporte = servicioPaciente.getReportesEntreFechas(idPaciente, codFecha1, codFecha2);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(reporte).build();
        
    }
}
