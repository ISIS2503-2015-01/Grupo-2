/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.logica.ejb;

import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.dto.Reporte;
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

/**
 *
 * @author estudiante
 */
@Stateful
public class ServicioPacienteMock implements IServicioPacienteMock {
    
    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    

    public ServicioPacienteMock()
    {
        persistencia = new ServicioPersistenciaMock();
        
    }

    @Override
    public ArrayList<Reporte> getReportes(Long idPaciente) 
    {
        ArrayList<Reporte> rta = new ArrayList<Reporte>();
        
        try
        {
            rta = (ArrayList<Reporte>) persistencia.findReportes(idPaciente);
        }
        catch(Exception ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);

        }
        return rta;
    }
    
             
    @Override
    public void agregarReporte(Long idPaciente, Reporte reporte){
    
        try
        {
            persistencia.createReporte(idPaciente, reporte);
        }
        catch(Exception ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        

    }
    
    @Override
    public void removerReporte(Long idPaciente, Reporte reporte)
    {

        try
        {
            persistencia.deleteReporte(idPaciente, reporte);
        }
        catch(Exception ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    
    @Override
    public List<Paciente> darPacientes(){
        
        System.out.println("Eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeehhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return persistencia.findAll(Paciente.class);

    }  

    @Override
    public Reporte getReportePorPaciente(Long idPaciente, Long idReporte) {
    
        ArrayList<Reporte> reportes = getReportes(idPaciente);
        
        for(int i =0;i<reportes.size();i++)
        {
            if( reportes.get(i).getId() == idReporte)
            {
                return reportes.get(i);
            }

        }
        return null;
    }

    @Override
    public List<Reporte> getReportesEntreFechas(long id, String  codFecha1, String codFecha2) {
        
        ArrayList<Reporte> reportes = getReportes(id);
        ArrayList<Reporte> res = new ArrayList<Reporte>();
        for(int i = 0; i< reportes.size(); i++){
             Reporte reporteAct = reportes.get(i);
             
             if(reporteAct.getFechaCreacion().compareTo(codFecha1)>0 && reporteAct.getFechaCreacion().compareTo(codFecha2)<0){
                 res.add(reporteAct);
             }
        }
        return res;
    }

    
}
