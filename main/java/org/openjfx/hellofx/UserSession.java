/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.hellofx;
import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXML;

/**
 *
 * @author abhishekvenkata
 */

public final class UserSession {

    @FXML
    private static UserSession instance;

    @FXML
    private String userName;
    
    @FXML
    private Set<String> privileges;

    
    private UserSession(String userName, Set<String> privileges) {
        this.userName = userName;
        this.privileges = privileges;
    }
    
    @FXML
    public static UserSession getInstance()
    {
        return instance;
    }
    
    @FXML
    public static UserSession getInstance(String userName, Set<String> privileges) {
        if(instance == null) {
            instance = new UserSession(userName, privileges);
        }
        return instance;
    }
    
    @FXML
    public static void setInstance() {
        instance = null;
    }

    @FXML
    public String getUserName() {
        return userName;
    }

    @FXML
    public Set<String> getPrivileges() {
        return privileges;
    }

    @FXML
    public void cleanUserSession() {
        userName = "";// or null
        privileges = new HashSet<>();// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}