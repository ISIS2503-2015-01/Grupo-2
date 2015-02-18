/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.servicios;

import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
import co.edu.uniandes.csw.hospitalKennedy.logica.ejb.ServicioPacienteMock;
import co.edu.uniandes.csw.hospitalKennedy.logica.interfaces.IServicioPacienteMock;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("/Pacientes")
@Stateful
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteService {
    
    @EJB    
    private IServicioPacienteMock pacienteEjb;
    
    public PacienteService()
    {
        pacienteEjb = new ServicioPacienteMock();
    }
    
    @POST
    @Path("{id}/agregarReportes/")
    public List<Reporte> agregarReportes(@PathParam("id") Long id, List<Reporte> lista){

        for(Reporte reporte: lista){
            pacienteEjb.agregarReporte(id, reporte);
        }

        
        return lista;
    }
    
    @GET
    @Path("Servicio/")
    public void algo()
    {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    }
    
    @DELETE
    @Path("{id}/borrar/")
    public void eliminarReportes(@PathParam("id") Long id, List<Reporte> lista) throws Exception{
        for(Reporte reporte: lista){
            pacienteEjb.removerReporte(id, reporte);
        }

    }
    
    @GET
    public List<Paciente> darPacientes(){
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return pacienteEjb.darPacientes();
    }
    
    @GET
    @Path("{id}/reportes/")
    public List<Reporte> darReportes(@PathParam("id") Long id){
        return pacienteEjb.getReportes(id);
    }
    

    @Path("{id}/reportes/{idReporte}") //URL de ejemplo http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1/reportes/1

    public Reporte darReportePorPaciente(@PathParam("id") Long id, @PathParam("idReporte")Long idReporte)
    {
        System.out.println("YAAAAAAAAAAAAAA id paciente "+ id +" id reporte "+ idReporte );
        return pacienteEjb.getReportePorPaciente(id, idReporte);
    }
    
           
    
    @GET
    @Path("/reporte/{idReporte}") //Ejemplo de este metodo: http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/reporte/123456
    public Reporte getReporte(@PathParam("id") long id, @PathParam("idReporte") long idReporte){
        System.out.println("Entroooooooooo y cogio el id de "+ idReporte);
        List reportes = pacienteEjb.getReportes(id);
        Iterator<Reporte> it = reportes.iterator();
        while(it.hasNext()){
            Reporte reporteAct = it.next();
            if(reporteAct.getId()== idReporte)
            {
             } else {
               return reporteAct;
            }
        }
        return null;
    }
    
//    @GET
//    @Path("/{id}/reportes/{fecha1}/{fecha2}") //Ejemplo de este metodo: http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/1/reportes/0/6424221442709
//    public List<Reporte> getReportesEntreFechas(@PathParam("id") long id, @PathParam("fecha1") long codFecha1, @PathParam("fecha2") long codFecha2){
//        
//        return pacienteEjb.getReportesEntreFechas(id, codFecha1, codFecha2);
//        
//    }
}
