/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 *
 * @author Nasri
 */
public class UtlisateurControlleur   implements Initializable
{
    @FXML
    private Label nomutlisateur;

    @FXML
    private Label email;

    @FXML
    private Button btndeconnextion;
    
    @FXML
    private Label reclamation;
    
      @FXML
    private ImageView reclamation1;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {

    }
     public void myFunction( String Text )
        {
            nomutlisateur.setText(Text);
        }
     
      public void handleButton6Action(MouseEvent event) throws MessagingException
      {
             try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
      }

     public void handleButtonAction(MouseEvent event) throws MessagingException
       {

        if (event.getSource() ==nomutlisateur ) 
        {
            
            
               try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/editUser.fxml")); 

                    Parent root = (Parent) loader.load();
                    editUserController utcontroller = loader.getController(); 
                    utcontroller.myFunction(nomutlisateur.getText());
               
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                  
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
       
     
        }
        else 
        {
            if(event.getSource() ==reclamation)
        {
              try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/Recamation.fxml")); 

                    Parent root = (Parent) loader.load();
                    reclamationController utcontroller = loader.getController(); 
                    utcontroller.myFunction(nomutlisateur.getText());
               
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               
                } catch (IOException ex) {
                    Logger.getLogger(reclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            
        }
    
            
        }
     
}
