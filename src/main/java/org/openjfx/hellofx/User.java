/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.hellofx;

/**
 *
 * @author abhishekvenkata
 */
public class User {
    
    private int id;
    private String full_name;
    private String email_id;

    public User(int id, String full_name, String email_id) {
        this.id = id;
        this.full_name = full_name;
        this.email_id = email_id;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
    
    
}
