/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

/**
 *
 * @author jssalamanca1967
 */
public class Doctor {
    
    //--------------------------
    // Atributos
    //--------------------------
    
    private Long id;
    private String nombre;
    private String password;
    private String login;
    
    public Doctor(Long id, String pNombre, String pPsw, String pLogin){
        this.id = id;
        nombre = pNombre;
        password = pPsw;
        login = pLogin;
    }

    public void setId(Long id) {
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

    public Long getId() {
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
