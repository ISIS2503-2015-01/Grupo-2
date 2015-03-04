/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;


import java.io.Serializable;
import java.util.Calendar;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;
 
 


/**
 *
 * @author jssalamanca1967
 */
@NoSql(dataFormat=DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class Doctor implements Serializable{
     private static final long serialVersionUID = 1L;
    //--------------------------
    // Atributos
    //--------------------------
    @Id
    @GeneratedValue
    @Field(name="_idDoctor")
    private String id;

    private String nombre;
    private String password;
    private String login;
    
    public Doctor(String id, String pNombre, String pPsw, String pLogin){
        this.id = id;
        nombre = pNombre;
        password = pPsw;
        login = pLogin;
    }
    public Doctor ()
    {
        
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
    
    
}
