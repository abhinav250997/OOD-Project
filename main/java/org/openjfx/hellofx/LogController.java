/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.hellofx;

import org.openjfx.hellofx.Insurance;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window; 
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javafx.scene.control.TextArea;

/**
 *
 * @author abhishekvenkata
 */
public class LogController {
    
    @FXML 
    private TextArea log;

    @FXML
    private Button showlog;

    public static Stack<String> s1 = new Stack();
    public static Deque<String> queue = new ArrayDeque<>();
    
    @FXML
    public static void push_log(String val)
    {
        //s1.push("Task 1");
        //s1.push("Task 2");
        s1.push(val);
        System.out.println("Stack push went through");
    }
    
    @FXML
    public void printlog(ActionEvent event) throws SQLException, IOException {

        Window owner = showlog.getScene().getWindow();
        if(!(s1.empty()))
        {
            log.setText(" "+s1.peek());
            queue.addFirst(s1.peek());
            s1.pop();
        }
        while(!(s1.empty()))
        {
            log.setText(log.getText()+"\n"+s1.peek());
            queue.addFirst(s1.peek());
            s1.pop();
        } 
    }  

    @FXML
    private void switchToLogin() throws IOException{
        App.setRoot("Login");

    }
    
    @FXML
    private void switchToPostLogin() throws IOException{     
        while(!(queue.isEmpty()))
        {
            s1.push(queue.remove());   
        }
        App.setRoot("PostLogin");
    }
}
