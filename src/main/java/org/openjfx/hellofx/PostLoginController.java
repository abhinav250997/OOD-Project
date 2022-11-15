/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.hellofx;

import org.openjfx.hellofx.Insurance;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 *
 * @author abhishekvenkata
 */
public class PostLoginController implements Initializable {
    
    @FXML
    private Label email_id;

    @FXML
    private Label tenure;
   
    @FXML
    private Label premium;
    
    @FXML
    private Label insurance_name;
    
    @FXML
    private Label insurance_type;
    
    @FXML
    private void switchToPlanlist() throws IOException {
        App.setRoot("plans");
    }

    @FXML public List<Insurance> getPlans() throws SQLException, IOException {
        
        List<Insurance> plans = new ArrayList<>();
        JdbcDao jdbcDao = new JdbcDao();
        plans = jdbcDao.getRecord("tiruchunapalli.a@northeastern.edu");
        return plans;
    }
            
    /**
     *
     * @param url
     * @param rb
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Insurance insure = new Insurance();
        List<Insurance> plans = new ArrayList<>();
        try {
            plans = getPlans();
        } catch (SQLException ex) {
            Logger.getLogger(PostLoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PostLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //insurance_plan.setText("Insurance values");
        
        //plans.forEach((n) -> email_id.setText(""+n.email_id));
        //plans.forEach((n) -> insurance_name.setText(""+n.insurance_name));
        plans.forEach((n) -> premium.setText(""+n.monthly_premium));
        plans.forEach((n) -> tenure.setText(""+n.tenure));

        //plans.forEach((n) -> System.out.println(n.insurance_type));
        
    }
}

