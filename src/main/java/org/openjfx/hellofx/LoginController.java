/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.hellofx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.openjfx.hellofx.LogController;
import org.openjfx.hellofx.UserSession;
/**
 * FXML Controller class
 *
 * @author abhishekvenkata
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;
    
    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();
        LogController.s1.clear();
        LogController.queue.clear();
        JdbcDao jdbcDao = new JdbcDao();
        boolean check1 = jdbcDao.searchAdminRecord(emailId, password);
        if(check1)
        {
            
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Admin Login Successful!","Welcome back");
            UserSession.setInstance();
            
            HashSet<String> privileges = new HashSet<>();          
            UserSession.getInstance(emailId, privileges);
            switchToAdminLogin();
            return;
        }
        
        
        boolean check = jdbcDao.searchRecord(emailId, password);
        if(check)
        {
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!","Welcome ");
            HashSet<String> privileges = new HashSet<>(); 
            LogController.s1.clear();
            LogController.queue.clear();
            UserSession.setInstance();
            UserSession.getInstance(emailId, privileges);
            switchToPostLogin();
            return;
        }
        showAlert(Alert.AlertType.ERROR, owner, "Login unsuccessful","Try again");
        
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
    @FXML
    private void switchToPostLogin() throws IOException{
        App.setRoot("PostLogin");
    }
    
    private void switchToAdminLogin() throws IOException{
        App.setRoot("Search");
    }
    
    @FXML
    private void switchToHome() throws IOException{
        App.setRoot("Home");
    }
}
