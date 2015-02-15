/* ========================================================================
 * Copyright 2014 ClinicaKennedy
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 ClinicaKennedy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201411201032

*/

package co.edu.uniandes.csw.ClinicaKennedy.doctor.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.ClinicaKennedy.doctor.logic.dto.DoctorPageDTO;
import co.edu.uniandes.csw.ClinicaKennedy.doctor.logic.dto.DoctorDTO;
import co.edu.uniandes.csw.ClinicaKennedy.doctor.logic.api.IDoctorLogicService;
import co.edu.uniandes.csw.ClinicaKennedy.doctor.persistence.DoctorPersistence;
import co.edu.uniandes.csw.ClinicaKennedy.doctor.persistence.api.IDoctorPersistence;
import co.edu.uniandes.csw.ClinicaKennedy.doctor.persistence.entity.DoctorEntity;
import co.edu.uniandes.csw.ClinicaKennedy.doctor.persistence.converter.DoctorConverter;
import static co.edu.uniandes.csw.ClinicaKennedy.util._TestUtil.*;

@RunWith(Arquillian.class)
public class DoctorLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(DoctorLogicService.class.getPackage())
				.addPackage(IDoctorLogicService.class.getPackage())
				.addPackage(DoctorPersistence.class.getPackage())
				.addPackage(DoctorEntity.class.getPackage())
				.addPackage(IDoctorPersistence.class.getPackage())
                .addPackage(DoctorDTO.class.getPackage())
                .addPackage(DoctorConverter.class.getPackage())
                .addPackage(DoctorEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IDoctorLogicService doctorLogicService;
	
	@Inject
	private IDoctorPersistence doctorPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<DoctorDTO> dtos=doctorPersistence.getDoctors();
		for(DoctorDTO dto:dtos){
			doctorPersistence.deleteDoctor(dto.getId());
		}
	}

	private List<DoctorDTO> data=new ArrayList<DoctorDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			DoctorDTO pdto=new DoctorDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setLogin(generateRandom(String.class));
			pdto.setPassword(generateRandom(String.class));
			pdto=doctorPersistence.createDoctor(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createDoctorTest(){
		DoctorDTO ldto=new DoctorDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setLogin(generateRandom(String.class));
		ldto.setPassword(generateRandom(String.class));
		
		
		DoctorDTO result=doctorLogicService.createDoctor(ldto);
		
		Assert.assertNotNull(result);
		
		DoctorDTO pdto=doctorPersistence.getDoctor(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getLogin(), pdto.getLogin());	
		Assert.assertEquals(ldto.getPassword(), pdto.getPassword());	
	}
	
	@Test
	public void getDoctorsTest(){
		List<DoctorDTO> list=doctorLogicService.getDoctors();
		Assert.assertEquals(list.size(), data.size());
        for(DoctorDTO ldto:list){
        	boolean found=false;
            for(DoctorDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getDoctorTest(){
		DoctorDTO pdto=data.get(0);
		DoctorDTO ldto=doctorLogicService.getDoctor(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getLogin(), ldto.getLogin());
		Assert.assertEquals(pdto.getPassword(), ldto.getPassword());
        
	}
	
	@Test
	public void deleteDoctorTest(){
		DoctorDTO pdto=data.get(0);
		doctorLogicService.deleteDoctor(pdto.getId());
        DoctorDTO deleted=doctorPersistence.getDoctor(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateDoctorTest(){
		DoctorDTO pdto=data.get(0);
		
		DoctorDTO ldto=new DoctorDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setLogin(generateRandom(String.class));
		ldto.setPassword(generateRandom(String.class));
		
		
		doctorLogicService.updateDoctor(ldto);
		
		
		DoctorDTO resp=doctorPersistence.getDoctor(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getLogin(), resp.getLogin());	
		Assert.assertEquals(ldto.getPassword(), resp.getPassword());	
	}
	
	@Test
	public void getDoctorPaginationTest(){
		
		DoctorPageDTO dto1=doctorLogicService.getDoctors(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		DoctorPageDTO dto2=doctorLogicService.getDoctors(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(DoctorDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(DoctorDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(DoctorDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(DoctorDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        DoctorPageDTO dto3=doctorLogicService.getDoctors(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(DoctorDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(DoctorDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}