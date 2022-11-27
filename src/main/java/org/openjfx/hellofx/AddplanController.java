/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.hellofx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;



/**
 * FXML Controller class
 *
 * @author abhishekvenkata
 */
public class AddplanController implements Initializable {
    
    @FXML
    private Label welcome_string;
    @FXML
    private ComboBox insurance_type;
    @FXML
    private ComboBox insurance_name;
    @FXML
    private ComboBox tenure;
    @FXML
    private ComboBox monthly_payment;
    @FXML
    private Button plan_button;
    @FXML 
    private Button update_button;
    @FXML
    private String instype;
    @FXML
    private String insname;
    @FXML
    private String tenureval;
    @FXML
    private int payment;
    
    private final ObservableList<String> ins_type = FXCollections.observableArrayList("Silver","Gold","Platinum");
    private final ObservableList<String> ins_name = FXCollections.observableArrayList("Health","Car");
    private final ObservableList<Integer> mon = FXCollections.observableArrayList(250,350,450);
    private final ObservableList<String> ten = FXCollections.observableArrayList("36 months", "48 months", "96 months");
    
    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        insurance_type.setItems(ins_type);
        insurance_name.setItems(ins_name);
        tenure.setItems(ten);
        monthly_payment.setItems(mon);
        
        UserSession user = UserSession.getInstance();
        String emailId = (String) user.getUserName();
        List<Insurance> plans = new ArrayList<Insurance>();
        try {
            plans = getPlans(emailId);
            Iterator i = plans.iterator();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AddplanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator i = plans.iterator();
        if(i.hasNext())
        {
           welcome_string.setText("Update your plan");
           Insurance plan = new Insurance();
           plan = plans.get(0);
           insurance_type.setValue(plan.getInsurance_type());
           insurance_name.setValue(plan.getInsurance_name());
           monthly_payment.setValue(plan.getMonthly_premium());
           tenure.setValue(plan.getTenure()+" months");
           update_button.setVisible(true);
           plan_button.setVisible(false);
        }
        else
        {
           welcome_string.setText("Add New plan");
           plan_button.setVisible(true);
           update_button.setVisible(false); 
        }
    }
    
    @FXML
    public void updateplan(ActionEvent event) throws SQLException, IOException {

        Window owner = update_button.getScene().getWindow();
        
        int tenval;
        String tenure_value;
        tenure_value = (String) tenure.getValue();
        tenure_value = tenure_value.substring(0,2);
        instype = (String) insurance_type.getValue();
        insname = (String) insurance_name.getValue();
        payment = (int) monthly_payment.getValue();
        tenval = Integer.parseInt(tenure_value);
        
         
            UserSession user = UserSession.getInstance();
            String emailId = (String) user.getUserName();
            List<Insurance> plans = new ArrayList<>();
            try {
                plans = getPlans(emailId);
            } catch (SQLException | IOException ex) {
                Logger.getLogger(AddplanController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(plans!=null)
            {
                Insurance plan = new Insurance();
                plan = plans.get(0);
                JdbcDao jdbcDao = new JdbcDao();
                jdbcDao.setRecord(plan.email_id, instype, insname,
                        payment, tenval);
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Plan updated Successful!",
                     "Updated plan "+insname+" with "+ instype +""+" "+payment);
            }
        switchToPostLogin();
    }
    
    
    @FXML
    public void addplan(ActionEvent event) throws SQLException, IOException {

        Window owner = plan_button.getScene().getWindow();
        
        if (insurance_name.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add Insurance name");
            return;
        }
        if(insurance_type.getValue() == null)
        {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add insurance type");
        }
        
        if(monthly_payment.getValue() == null)
        {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add payment value");
        }
        if(tenure.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add tenure");
            return;
        }
        
        int tenval;
        tenureval = (String) tenure.getValue();
        tenureval = tenureval.substring(0,2);
        instype = (String) insurance_type.getValue();
        insname = (String) insurance_name.getValue();
        payment = (int) monthly_payment.getValue();
        tenval = Integer.parseInt(tenureval);
        
        if (instype.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add Insurance name");
            return;
        }

        if (insname.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add insurance type");
            return;
        }
        if (payment==0) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add tenure");
            return;
        }
        if(tenureval.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add payment value");
            return;
        }
         
        System.out.println(instype);
        System.out.println(insname);
        System.out.println(payment);
        System.out.println(tenval);
        //sSystem.out.println(passwordField.getText());
        
        //get emailId of User
        UserSession user = UserSession.getInstance();
        String emailId = (String) user.getUserName();
        List<Insurance> plans = new ArrayList<>();
        JdbcDao jdbcDao = new JdbcDao();

        jdbcDao.addRecord(emailId, instype, insname , payment, tenval);
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Plan added Successful!",
                 "Added plan "+insname+" with "+ instype +""+" "+payment);
        switchToPostLogin();
    }
    
    @FXML
    private void switchToPostLogin() throws IOException {
        App.setRoot("PostLogin");
    }
    
    @FXML
    private void switchToTerms() throws IOException {
        App.setRoot("terms");
    }
    
    @FXML
    private void switchtoAddPlan() throws IOException {
        App.setRoot("addplan");
    }
    
    @FXML public List<Insurance> getPlans(String emailid) throws SQLException, IOException {
        
        List<Insurance> plans = new ArrayList<>();
        JdbcDao jdbcDao = new JdbcDao();
        plans = jdbcDao.getRecord(emailid);
        return plans;
    }
    
    
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
