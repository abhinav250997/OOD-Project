/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.hellofx;

/**
 *
 * @author abhishekvenkata
 */
import org.openjfx.hellofx.PostLoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterController{

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;
    
    @FXML
    private Label insurance_plan;
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Scene scene;

    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(fullNameField.getText());
        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());
        if (fullNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your name");
            return;
        }

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

        String fullName = fullNameField.getText();
        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        if(emailId.endsWith("@northeastern.edu"))
        {
            JdbcDao jdbcDao = new JdbcDao();
            jdbcDao.insertRecord(fullName, emailId, password);
        
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
            "Welcome " + fullNameField.getText());
       
            switchToLogin();
        }
        else
        {
            showAlert(Alert.AlertType.ERROR, owner, "Registration Unuccessful!",
            "Email must end with @northeastern.edu ");
            
        }
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("Home");
    }
    
    @FXML
    private void switchToLogin() throws IOException {
        
        
        App.setRoot("Login");
    }
    
    @FXML
    private void switchToRegister() throws IOException {
        
        
        App.setRoot("primary");
    }

    

}
