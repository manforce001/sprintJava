/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 *
 * @author Nasri
 */
public class adminController 
{
    
    @FXML
    private Pane GestionSpon;

    @FXML
    private Button gestionUti;

    @FXML
    private Button GestionVote;

    @FXML
    private Button GestionPublication;

    @FXML
    private Button GestionCommentaire;

    @FXML
    private Button GestionSpo;
    
    
     @FXML
    public void handleButton1Action(MouseEvent event) throws MessagingException {

        if (event.getSource() == gestionUti) {
            //login here
                try {
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml")));
                    stage.setScene(scene);
                    stage.show();
                  
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
     
        }
    }
    
    
}
